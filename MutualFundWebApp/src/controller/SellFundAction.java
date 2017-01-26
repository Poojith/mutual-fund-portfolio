package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.FundBean;
import databean.PositionBean;
import databean.TransactionBean;
import formbeans.SellfundForm;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class SellFundAction extends Action {
	
	private FormBeanFactory<SellfundForm> formBeanFactory = FormBeanFactory
            .getInstance(SellfundForm.class);
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundpricehistoryDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	public SellFundAction(Model model) {
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		fundpricehistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
	}
	
	public String getName() {
		return "sellfund.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	      try {
	    	  
	    	  CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
	    	  PositionBean[] position = positionDAO.getPositionsByCustomerId(user.getCustomerId());
	    	  List<FundBean> sellfundlist = new ArrayList<FundBean>();
	    	  // get position total value for display the table, get sellfundlist for dropdown list
	    	  for (int i=0; i<position.length; i++) {
		    	  FundBean fundbean = new FundBean();
	    		  fundbean.setFundId(position[i].getFundId());
	    		  fundbean.setName(fundDAO.read(fundbean.getFundId()).getName());
	    		  fundbean.setSymbol(fundDAO.read(fundbean.getFundId()).getSymbol());
	    		  Double price = fundpricehistoryDAO.fundLatestPrice(fundbean);
	    		  position[i].setTotalValue(price * position[i].getShares());
	    		  sellfundlist.add(fundbean);  
	    	  }
	    	  request.setAttribute("position", position);
	    	  FundBean[] newfundlist = sellfundlist.toArray(new FundBean[sellfundlist.size()]);
	    	  request.setAttribute("sellfundlist", newfundlist);
	    	  
	    	  SellfundForm form = formBeanFactory.create(request);
	    	    	  if (!form.isPresent()) {
					return "customer-sell-fund.jsp";
				}
	            errors.addAll(form.getValidationErrors());
	            if (errors.size() > 0) {
	                return "error.jsp";
	            }
	            int fundid = fundDAO.read(form.getFund()).getFundId();
	            double shares = positionDAO.getPosition(user.getCustomerId(), fundid).getShares();

	            if (form.getSharesDouble() <= shares) {      
	            // update transaction
	            TransactionBean transaction = new TransactionBean();
	            transaction.setCustomerId(user.getCustomerId());
	            transaction.setFundId(fundDAO.read(form.getFund()).getFundId());
	            transaction.setTransactionType(2);
	            transaction.setShares(form.getSharesDouble());
	            transactionDAO.create(transaction);
	   	            }
	            else {
		        	errors.add("Not enough shares");
		        	return "error.jsp";
		         }
	          request.setAttribute("message", "Sell Fund was successful");
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
