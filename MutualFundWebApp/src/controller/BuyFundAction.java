package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

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
	}
	
	public String getName() {
		return "buyfund.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	      try {
	    	  
	    	  return "buyfund.jsp";
	      }  catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "buyfund.jsp";
	}
		

}
}
