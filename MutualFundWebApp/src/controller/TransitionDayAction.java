package controller;

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
	public String perform(HttpServletRequest request) {
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
				session.setAttribute("fundArray", fundArray);
				return "employee-transition-day.jsp";
			}
			if (date == null) {
				errors.add("Date needs to be specified");
				return "employee-transition-day.jsp";
			}
			String prevdate = transactionDAO.findGlobalLastTransactionDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			dateFormat.setLenient(false);
			if (dateFormat.parse(date).compareTo
				(dateFormat.parse(prevdate))<=0) {
				errors.add("date has to be greater than last transistion day date: " + prevdate);
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "employee-transition-day.jsp";
			}
			Map<Integer, Double> fundpricemap = new HashMap<Integer, Double>();
			fundpricemap = form.getFundPriceMap();
			Transaction.begin();
			for (Map.Entry<Integer, Double> 
				entry : fundpricemap.entrySet()) {
				FundPriceHistoryBean fundHistoryBean = fundPriceHistoryDAO.read(entry.getKey());
				fundHistoryBean.setPrice(entry.getValue());
				fundHistoryBean.setExecuteDate(date);
				fundPriceHistoryDAO.update(fundHistoryBean);
			}
			Transaction.commit();
			Transaction.begin();
			TransactionBean[] transactionBeans = transactionDAO.match(MatchArg.notEquals("executeDate", ""));
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
				transactionDAO.update(transactionBeans[i]);
			}
			
			request.setAttribute("message", "You have successfully "
					+ "completed transition day");
			return "success.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
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
		customerDAO.update(customerbean);
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
