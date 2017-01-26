package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.TransactionBean;
import model.Model;
import model.TransactionDAO;

public class TransactionHistoryAction extends Action {
	
	private TransactionDAO transactionDAO;
	public TransactionHistoryAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "transactionhistory.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	      try {
	    	  CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
	    	  request.setAttribute("transactionhistory", transactionDAO.findTransactionsByCustomerId(user.getCustomerId()));  	
	    	  return "customer-transaction-history.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } 
}
}