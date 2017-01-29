package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.CustomerBean;
import databean.EmployeeBean;
import databean.FundBean;
import databean.FundPriceHistoryBean;
import databean.PositionBean;
import databean.TransactionBean;
import formbeans.DepositCheckForm;
import formbeans.TransitionDayForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class TransitionDayAction extends Action {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;

	public TransitionDayAction(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
	}

	@Override
	public String getName() {
		return "employee-transition-day.do";
	}

	@Override
	public String perform(HttpServletRequest request){
		System.out.println("reached here");
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		EmployeeBean employee = (EmployeeBean)session.getAttribute("user");
		if (employee == null) {
			errors.add("You are not authrised to perform transition day");
			return "employee-transition-day.jsp";
		}
		
		String date = request.getParameter("date");
		
		try {
			FundBean[] fundArray = fundDAO.match();
			TransitionDayForm form = new TransitionDayForm(request);
			if (!form.isPresent()) {
				System.out.println("transition form is not present");
				session.setAttribute("fundArray", fundArray);
				return "employee-transition-day.jsp";
			}
			if (date == null) {
				System.out.println("date is null");
				errors.add("Date needs to be specified");
				return "employee-transition-day.jsp";
			}
			String prevdate = transactionDAO.findGlobalLastTransactionDate();
			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "employee-transition-day.jsp";
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			dateFormat.setLenient(false);
			if (prevdate != null && dateFormat.parse(date).compareTo
				(dateFormat.parse(prevdate))<=0) {
				errors.add("date has to be greater than last transistion day date: " + prevdate);
				return "employee-transition-day.jsp";
			}
			
			Map<Integer, Double> fundpricemap = new HashMap<Integer, Double>();
			fundpricemap = form.getFundPriceMap();
			Transaction.begin();
			for (Map.Entry<Integer, Double> 
				entry : fundpricemap.entrySet()) {
				FundPriceHistoryBean[] fundHistoryBean = fundPriceHistoryDAO.match(MatchArg.equals("fundId", entry.getKey()));
				if (fundHistoryBean == null || fundHistoryBean.length==0) {
					FundPriceHistoryBean bean = new FundPriceHistoryBean();
					bean.setFundId(entry.getKey());
					bean.setPrice(entry.getValue());
					bean.setPriceDate(date);
					fundPriceHistoryDAO.create(bean);
				} else {
					
					fundHistoryBean[0].setPrice(entry.getValue());
					fundHistoryBean[0].setPriceDate(date);
					System.out.println(fundHistoryBean[0].getFundId());
					fundPriceHistoryDAO.update(fundHistoryBean[0]);
				}
			}
			TransactionBean[] transactionBeans = transactionDAO.match(MatchArg.equals("status", null));
			System.out.println("size of transactiobeans is: " + transactionBeans.length);
			for (int i=0; i<transactionBeans.length; i++) {
				switch(transactionBeans[i].getTransactionType()) {
					case 1: {
						buyFundAction(transactionBeans[i]);
					}
					case 2: {
						sellFundAction(transactionBeans[i]);
					} case 3: {
						depositCheckAction(transactionBeans[i]);
					} case 4: {
						requestCheckAction(transactionBeans[i]);
					}
				}
				transactionBeans[i].setExecuteDate(date);
				transactionBeans[i].setStatus("completed");
				System.out.println("updating transaction bean" + transactionBeans[i].getTransactionId());
				transactionDAO.update(transactionBeans[i]);
			}
			Transaction.commit();
			request.setAttribute("message", "You have successfully "
					+ "completed transition day");
			return "success.jsp";
		} catch (RollbackException e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			System.out.println("failing because of rollback exception");
			return "error.jsp";
			
		} /* catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println("failing because of exception");
			return "error.jsp";
		}*/ catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.add(e.getMessage());
			return "error.jsp";
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public void buyFundAction(TransactionBean transactionBean) throws RollbackException {
		int customerId = transactionBean.getCustomerId();
		double amount = transactionBean.getAmount();
		int fundId = transactionBean.getFundId();
		FundPriceHistoryBean fundHistoryBean = fundPriceHistoryDAO.read(fundId);
		double fundprice = fundHistoryBean.getPrice();
		double shares = amount/fundprice;
		CustomerBean customerbean = customerDAO.read(customerId);
		if (customerbean.getCash()<amount) {
			
			//not enough balance
			return;
		}
		PositionBean positionbean = positionDAO.read(customerId);
		transactionBean.setShares(shares);
		customerbean.setCash(customerbean.getCash()-amount);
		positionbean.setShares(positionbean.getShares()+shares);
		positionDAO.update(positionbean);
		customerDAO.update(customerbean);
		return;
	}
	public void sellFundAction(TransactionBean transactionBean) throws RollbackException {
		int customerId = transactionBean.getCustomerId();
		double shares = transactionBean.getShares();
		int fundId = transactionBean.getFundId();
		FundPriceHistoryBean fundHistoryBean = fundPriceHistoryDAO.read(fundId);
		double fundprice = fundHistoryBean.getPrice();
		double amount = fundprice*shares;
		CustomerBean customerbean = customerDAO.read(customerId);
		PositionBean positionbean = positionDAO.read(customerId);
		if (positionbean.getShares()<shares) {
			
			//not enough shares
			return;
		}
		transactionBean.setAmount(amount);
		customerbean.setCash(customerbean.getCash()+amount);
		positionbean.setShares(positionbean.getShares()-shares);
		positionDAO.update(positionbean);
		customerDAO.update(customerbean);
		return;
		
	}
	public void depositCheckAction(TransactionBean transactionBean) throws RollbackException {
		int customerId = transactionBean.getCustomerId();
		double amount = transactionBean.getAmount();
		CustomerBean customerbean = customerDAO.read(customerId);
		customerbean.setCash(customerbean.getCash()+amount);
		System.out.println("reached deposit check action: " + customerbean.getCash());
		customerDAO.update(customerbean);
		System.out.println("completed deposit check action");
		return;
	}
	public void requestCheckAction(TransactionBean transactionBean) throws RollbackException {
		int customerId = transactionBean.getCustomerId();
		double amount = transactionBean.getAmount();
		CustomerBean customerbean = customerDAO.read(customerId);
		if (customerbean.getCash()<amount) {
			
			//not enough cash
			return;
		}
		customerbean.setCash(customerbean.getCash()-amount);
		customerDAO.update(customerbean);
		return;
	}
}
