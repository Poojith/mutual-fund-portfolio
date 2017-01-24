package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import model.FundDAO;
import model.Model;

public class ResearchFundAction extends Action {
	private FundDAO fundDAO;
	public ResearchFundAction(Model model) {
		fundDAO = model.getFundDAO();
	}
	
	public String getName() {
		return "researchfund.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	      try {
	    	  request.setAttribute("fundlist", fundDAO.readAll());
	      	  return "customer-research-fund.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } 

}
}
