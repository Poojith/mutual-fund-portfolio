package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.FundBean;
import databean.PositionBean;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class ViewAccountAction extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundpricehistoryDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	public ViewAccountAction(Model model) {
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		fundpricehistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
	}
	
	public String getName() {
		return "viewaccount.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	      try {
	    	  CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
	    	  PositionBean[] position = positionDAO.getPositionsByCustomerId(user.getCustomerId());
              for (int i=0; i<position.length; i++) {
		    	  FundBean fundbean = new FundBean();
	    		  fundbean.setFundId(position[i].getFundId());
	    		  fundbean.setSymbol(fundDAO.read(fundbean.getFundId()).getSymbol());
	    		  Double price = fundpricehistoryDAO.fundLatestPrice(fundbean);
	    		  position[i].setTotalValue(price * position[i].getShares()); 
	    	  }
	    	  request.setAttribute("position", position);
	    	  request.setAttribute("lasttransactiondate", transactionDAO.findLastTransactionDate(user.getCustomerId()));
	    	  return "customer-view-portfolio.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "customer-error.jsp";
	        } 
		

}
}
