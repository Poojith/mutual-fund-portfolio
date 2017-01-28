package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.TransactionBean;
import model.FundDAO;
import model.Model;
import model.TransactionDAO;
import viewbeans.TransactionhistoryBean;

public class TransactionHistoryAction extends Action {
	
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	public TransactionHistoryAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
	}
	
	public String getName() {
		return "transactionhistory.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	        HttpSession session = request.getSession();
	        String usertype = (String)(session.getAttribute("userType"));
	        if (usertype == "Customer") {
		      try {
		    	  CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
		    	  List<TransactionhistoryBean> transactionhistory = new ArrayList<TransactionhistoryBean>();
		    	  TransactionBean[] transactions = transactionDAO.findTransactionsByCustomerId(user.getCustomerId());
		    	  for(int i=0; i<transactions.length; i++) {
		    		  TransactionhistoryBean t = new TransactionhistoryBean();
		    		  if (transactions[i].getFundId() != 0) {
		    		  t.setFundName(fundDAO.read(transactions[i].getFundId()).getName());
		    		  }
		    		  if (transactions[i].getExecuteDate() != null) {
		    		  t.setExecuteDate(transactions[i].getExecuteDate());
		    		  }
		    		  if (transactions[i].getStatus() == null) {
		    			  t.setStatus("Pending");
		    		  }
		    		  if (transactions[i].getTransactionType() == 1 || transactions[i].getTransactionType() == 2) {
		    		  t.setShares(Double.toString(transactions[i].getShares()));
		    		  t.setSharePrice(Double.toString(transactions[i].getSharePrice()));
		    		  }
		    		  t.setAmount(transactions[i].getAmount());
		    		  switch(transactions[i].getTransactionType()) {
		    		  case 1:
		    			  t.setTransactionType("Buy Fund");
		    			  break;
		    		  case 2:
		    			  t.setTransactionType("Sell Fund");
		    			  break;
		    		  case 3:
		    			  t.setTransactionType("Deposit Check");
		    			  break;
		    		  case 4:
		    			  t.setTransactionType("Request Check");
		    			  break;
		    		  }
		    		  transactionhistory.add(t);
		    	  }
		    	  request.setAttribute("transactionhistory", transactionhistory.toArray(new TransactionhistoryBean[transactionhistory.size()]) );  	
		    	  return "customer-transaction-history.jsp";
		      } catch (RollbackException e) {
		        	errors.add(e.getMessage());
		        	return "customer-error.jsp";
		        }
	        }
	        return null;
	        /*
	        else if (usertype == "Employee") {
	        	EmployeeBean user = (EmployeeBean) (request.getSession(false).getAttribute("user"));
	        }
	        */
}
}