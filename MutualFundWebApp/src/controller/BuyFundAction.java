package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.TransactionBean;
import formbeans.BuyFundForm;
import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TransactionDAO;

public class BuyFundAction extends Action {
	
	private FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory
            .getInstance(BuyFundForm.class);
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	public BuyFundAction(Model model) {
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() {
		return "buyfund.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	      try {
	    	  String type = (String) request.getSession(false).getAttribute("userType");
	       	  if (!type.equals("Customer")) {
	    		  errors.add("Please use Employee pages only");
	    		  return "employee-error.jsp";
	    	  }
	    	  CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
	    	  if (user == null) {
	    		  return "login.do";
	    	  }
	    	  CustomerBean cash = customerDAO.getCustomerByUserName(user.getUsername());
	    	  request.setAttribute("cash", cash);
	 
	    	  request.setAttribute("buyfundlist", fundDAO.readAll());
	    	  BuyFundForm form = formBeanFactory.create(request);
   	  
	    	  if (!form.isPresent()) {
					return "customer-buy-fund.jsp";
				}
		            errors.addAll(form.getValidationErrors());
	 	            if (errors.size() > 0) {
	
	                return "customer-buy-fund.jsp";
	            }
		    
	 	          TransactionBean[] buyfund = transactionDAO.match(MatchArg.and(MatchArg.equals("customerId", user.getCustomerId()),MatchArg.equals("executeDate", null),MatchArg.equals("transactionType", 1)));
	 	          TransactionBean[] check = transactionDAO.match(MatchArg.and(MatchArg.equals("customerId", user.getCustomerId()),MatchArg.equals("executeDate", null),MatchArg.equals("transactionType", 4)));
	 	          Double total = 0.0;
	 	         for(int i=0; i< buyfund.length; i++) {
	 	        	  total = total + buyfund[i].getAmount();
	 	          }
	 	         for(int i=0; i< check.length; i++) {
	 	        	  total = total + check[i].getAmount();
	 	          }
    	  if ((user.getCash() - total) >= form.getAmountDouble()) {
    		    TransactionBean transaction = new TransactionBean();
 	            transaction.setCustomerId(user.getCustomerId());
 	            transaction.setFundId(fundDAO.read(form.getFundName()).getFundId());
 	            transaction.setTransactionType(1);
 	            transaction.setAmount(form.getAmountDouble());
 	            transactionDAO.create(transaction);
 	            request.setAttribute("message", "Your request for buying the fund is under processing");
 	    	  return "customer-success.jsp"; 
	    	  } else {
		        	 Double balance = user.getCash() - total;
		        	 DecimalFormat df = new DecimalFormat("0.00");
		    		 errors.add("Not enough cash, your current cash balance is $" + df.format(balance) + " ,which may due to some pending transactions");
		        	return "customer-buy-fund.jsp";
	    	  }

	       
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "customer-error.jsp";
	        } catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "customer-error.jsp";
	} catch (Exception e) {
		errors.add(e.getMessage());
		return "customer-error.jsp";
	}
		

}
}
