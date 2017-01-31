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
import formbeans.RequestcheckForm;
import model.Model;
import model.TransactionDAO;

public class RequestCheckAction extends Action {
	
	private FormBeanFactory<RequestcheckForm> formBeanFactory = FormBeanFactory
            .getInstance(RequestcheckForm.class);
	private TransactionDAO transactionDAO;
	public RequestCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "requestcheck.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	      try {
	    	  CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
	    	  RequestcheckForm form = formBeanFactory.create(request);
	    	  if (!form.isPresent()) {
					return "customer-request-check.jsp";
				}
	            errors.addAll(form.getValidationErrors());
	            if (errors.size() > 0) {
	                return "customer-error.jsp";
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
	 	         
	         if (form.getAmountDouble() <= (user.getCash() - total)) {
	        	 // update transaction
	        	 TransactionBean transaction = new TransactionBean();
	        	 transaction.setAmount(form.getAmountDouble());
	        	 transaction.setCustomerId(user.getCustomerId());
	        	 transaction.setTransactionType(4);
	        	 transactionDAO.create(transaction);
	         }
	         else {
	        	 Double balance = user.getCash() - total;
	        	 DecimalFormat df = new DecimalFormat("0.00");
	    		 errors.add("Not enough cash, your current cash balance is $" + df.format(balance) + " ,which may due to some pending transactions");
	        	return "customer-error.jsp";
	         }
	         request.setAttribute("message", "Your request for withdraw the fund is under processing");
	    	  return "customer-success.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "customer-error.jsp";
	        } catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "customer-request-check.jsp";
	}
		

}
}
