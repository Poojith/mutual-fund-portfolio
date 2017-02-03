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
		
		

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();	
		String date = request.getParameter("date");
		
		try {
			
			String checkUser = (String) request.getSession(false).getAttribute("userType");
			if (!checkUser.equals("Employee")) {
				errors.add("Sorry, you do not have the required authorization to access this page.");
				return "customer-error.jsp";
			}
			
			EmployeeBean user = (EmployeeBean) request.getSession().getAttribute("user");

			if (user == null) {
				errors.add("Please login to access the requested page");
				return "login.jsp";
			}
			
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
			} else if (dateFormat.parse(date).compareTo(dateFormat.parse("01/01/2010")) <= 0) {
				errors.add("date has to be greater than 01/01/2010");
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
					fundPriceHistoryDAO.create(fundHistoryBean[0]);
				}
			}
			TransactionBean[] transactionBeans = transactionDAO.match(MatchArg.equals("status", null));
			for (int i=0; i<transactionBeans.length; i++) {
				switch(transactionBeans[i].getTransactionType()) {
					case 1: {
						buyFundAction(transactionBeans[i], fundpricemap);
						break;
					}
					case 2: {
						sellFundAction(transactionBeans[i], fundpricemap);
						break;
					} case 3: {			
						depositCheckAction(transactionBeans[i]);
						break;
					} case 4: {					
						requestCheckAction(transactionBeans[i]);
						break;
					}
				}
				transactionBeans[i].setExecuteDate(date);
				transactionBeans[i].setStatus("completed");			
				transactionDAO.update(transactionBeans[i]);
			}
			Transaction.commit();
			request.setAttribute("message", "You have successfully "
					+ "completed transition day");
			return "employee-success.jsp";
		} catch (RollbackException e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			return "employee-error.jsp";
		}	
		 catch (ParseException e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			return "employee-error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
		 
		 finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public void buyFundAction(TransactionBean transactionBean, Map<Integer, Double> fundpricemap) throws RollbackException {
		int customerId = transactionBean.getCustomerId();
		double amount = transactionBean.getAmount();
		int fundId = transactionBean.getFundId();
		double fundprice = fundpricemap.get(fundId);
		double shares = amount/fundprice;
		CustomerBean customerbean = customerDAO.read(customerId);
		if (customerbean.getCash()<amount) {
			
			//not enough balance
			return;
		}
		PositionBean positionbean = positionDAO.read(customerId, fundId);
		if (positionbean == null) {
			positionbean = new PositionBean();
			positionbean.setCustomerId(customerId);
			positionbean.setFundId(fundId);
			positionbean.setShares(shares);
			positionDAO.create(positionbean);
		} else {
			positionbean.setShares(positionbean.getShares()+shares);
			positionDAO.update(positionbean);
		}
		transactionBean.setShares(shares);
		transactionBean.setSharePrice(fundpricemap.get(fundId));
		customerbean.setCash(customerbean.getCash()-amount);
		customerDAO.update(customerbean);
		return;
	}
	public void sellFundAction(TransactionBean transactionBean, Map<Integer, Double> fundpricemap) throws RollbackException {
		int customerId = transactionBean.getCustomerId();
		double shares = transactionBean.getShares();
		int fundId = transactionBean.getFundId();
		double fundprice = fundpricemap.get(fundId);
		double amount = fundprice*shares;
		CustomerBean customerbean = customerDAO.read(customerId);
		PositionBean positionbean = positionDAO.read(customerId, fundId);
		if (positionbean == null) {
			return;
		} else {
			if (positionbean.getShares()<shares) {	
				return;
			}
		}
		transactionBean.setAmount(amount);
		transactionBean.setSharePrice(fundpricemap.get(fundId));
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
