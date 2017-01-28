package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.TransactionBean;
import formbeans.BuyFundForm;
import model.FundDAO;
import model.Model;
import model.TransactionDAO;

public class BuyFundAction extends Action {
	
	private FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory
            .getInstance(BuyFundForm.class);
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	public BuyFundAction(Model model) {
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "buyfund.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	      try {
	    	  CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
	    	  request.setAttribute("buyfundlist", fundDAO.readAll());
	    	  BuyFundForm form = formBeanFactory.create(request);
   	  
	    	  if (!form.isPresent()) {
					return "customer-buy-fund.jsp";
				}

	            errors.addAll(form.getValidationErrors());
	            if (errors.size() > 0) {
	                return "error.jsp";
	            }
		    	  

	            TransactionBean transaction = new TransactionBean();
	            transaction.setCustomerId(user.getCustomerId());
	            transaction.setFundId(fundDAO.read(form.getFundName()).getFundId());
	            transaction.setTransactionType(1);
	            transaction.setAmount(form.getAmountDouble());
	            transactionDAO.create(transaction);
	            request.setAttribute("message", "Buy Fund was successful");
	    	  return "success.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "error.jsp";
	}
		

}
}
