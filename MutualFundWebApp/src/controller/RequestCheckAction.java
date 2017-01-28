package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.TransactionBean;
import formbeans.RequestcheckForm;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

public class RequestCheckAction extends Action {
	
	private FormBeanFactory<RequestcheckForm> formBeanFactory = FormBeanFactory
            .getInstance(RequestcheckForm.class);
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	public RequestCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
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
	        	 Date date = new Date();
	        	 DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	        	 transaction.setExecuteDate(df.format(date));
	        	 transactionDAO.create(transaction);
	        	 
	        	 //update customer cash
	        	 CustomerBean updateuser = new CustomerBean();
	        	 updateuser.setCash(user.getCash()-form.getAmountDouble());
	        	 updateuser.setAddrLine1(user.getAddrLine1());
	        	 updateuser.setAddrLine2(user.getAddrLine2());
	        	 updateuser.setCity(user.getCity());
	        	 updateuser.setCustomerId(user.getCustomerId());
	        	 updateuser.setFirstName(user.getFirstName());
	        	 updateuser.setLastName(user.getLastName());
	        	 updateuser.setState(user.getState());
	        	 updateuser.setUsername(user.getUsername());
	        	 updateuser.setZip(user.getZip());
	        	 updateuser.setPassword(user.getPassword());
	        	 customerDAO.update(updateuser);
	         }
	         else {
	        	errors.add("Not enough cash");
	        	return "error.jsp";
	         }
	         request.setAttribute("message", "Request check was successful");
	    	  return "success.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "customer-request-check.jsp";
	}
		

}
}
