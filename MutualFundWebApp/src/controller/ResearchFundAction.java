package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.FundBean;
import formbeans.ResearchfundForm;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;

public class ResearchFundAction extends Action {
	private FormBeanFactory<ResearchfundForm> formBeanFactory = FormBeanFactory
            .getInstance(ResearchfundForm.class);
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundpricehistoryDAO;
	public ResearchFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundpricehistoryDAO = model.getFundPriceHistoryDAO();
	}
	
	public String getName() {
		return "researchfund.do";
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
	    	  request.setAttribute("fundlist", fundDAO.readAll());
	       	  ResearchfundForm form = formBeanFactory.create(request);
	       	  
	    	  if (!form.isPresent()) {
					return "customer-research-fund.jsp";
				}
	            errors.addAll(form.getValidationErrors());
	            if (errors.size() > 0) {
	                return "customer-research-fund.jsp";
	            }
	            FundBean fundbean = new FundBean();
	            fundbean.setFundId(form.getFundId());
	            request.setAttribute("fundpricehistory", fundpricehistoryDAO.research(fundbean));
	    	   	return "customer-research-fund.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "customer-error.jsp";
	        }  catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "customer-research-fund.jsp";
	}

}
}
 