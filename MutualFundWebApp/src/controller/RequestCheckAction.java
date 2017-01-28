package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	         if (form.getAmountDouble() <= user.getCash()) {
	        	 // update transaction
	        	 TransactionBean transaction = new TransactionBean();
	        	 transaction.setAmount(form.getAmountDouble());
	        	 transaction.setCustomerId(user.getCustomerId());
	        	 transaction.setTransactionType(4);
	        	 transactionDAO.create(transaction);
	         }
	         else {
	        	errors.add("Not enough cash");
	        	return "customer-error.jsp";
	         }
	         request.setAttribute("message", "Request check was successful");
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
