package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import daos.MyDAOException;
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
	    	  
	      } catch (Exception e) {
	            errors.add(e.getMessage());
	            return "buyfund.jsp";
	        } catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "buyfund.jsp";
	        
		
		return null;
	}

}
