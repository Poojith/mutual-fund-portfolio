package controller;

import java.text.DecimalFormat;
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
import viewbeans.PortfolioBean;

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
	    	  String type = (String) request.getSession(false).getAttribute("userType");
	       	  if (!type.equals("Customer")) {
	    		  errors.add("Please use Employee pages only");
	    		  return "employee-error.jsp";
	    	  }
	    	  CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
	    	  PositionBean[] position = positionDAO.getPositionsByCustomerId(user.getCustomerId());
	    	  List<PortfolioBean> portfolio = new ArrayList<PortfolioBean>();
              for (int i=0; i<position.length; i++) {
		    	  FundBean fundbean = new FundBean();
		    	  PortfolioBean p = new PortfolioBean();
	    		  fundbean.setFundId(position[i].getFundId());
	    		  fundbean.setSymbol(fundDAO.read(fundbean.getFundId()).getSymbol());
	    		  Double price = fundpricehistoryDAO.fundLatestPrice(fundbean);
	    		  p.setFundName(fundDAO.read(fundbean.getFundId()).getName());
	    		  DecimalFormat df1 = new DecimalFormat("0.000");
	    		  p.setShares(df1.format(position[i].getShares()));
	    		  DecimalFormat df2 = new DecimalFormat("0.00");
	    		  p.setTotalValue(df2.format(price * position[i].getShares()));
	    		  portfolio.add(p);
	    	  }
	    	  request.setAttribute("portfolio", portfolio.toArray(new PortfolioBean[portfolio.size()]));
	    	  request.setAttribute("lasttransactiondate", transactionDAO.findLastTransactionDate(user.getCustomerId()));
	    	  return "customer-view-portfolio.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "customer-error.jsp";
	        }  catch (Exception e) {
	    		errors.add(e.getMessage());
	    		return "customer-error.jsp";
	    	}
		

}
}
