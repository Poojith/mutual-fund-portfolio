package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.FundBean;
import databean.PositionBean;
import databean.TransactionBean;
import formbeans.SellfundForm;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;
import viewbeans.PortfolioBean;

public class SellFundAction extends Action {
	
	private FormBeanFactory<SellfundForm> formBeanFactory = FormBeanFactory
            .getInstance(SellfundForm.class);
	private FundDAO fundDAO;
	private CustomerDAO customerDAO;
	private FundPriceHistoryDAO fundpricehistoryDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	public SellFundAction(Model model) {
		fundDAO = model.getFundDAO();
		customerDAO = model.getCustomerDAO();
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
	    	  String type = (String) request.getSession(false).getAttribute("userType");
	       	  if (!type.equals("Customer")) {
	    		  errors.add("Please use Employee pages only");
	    		  return "employee-error.jsp";
	    	  }
	    	  CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
	    	  CustomerBean cash = customerDAO.getCustomerByUserName(user.getUsername());
	    	  request.setAttribute("cash", cash);
	    	  PositionBean[] position = positionDAO.getPositionsByCustomerId(user.getCustomerId());
	    	  List<FundBean> sellfundlist = new ArrayList<FundBean>();
	    	  List<PortfolioBean> portfolio = new ArrayList<PortfolioBean>();
	    	  // get position total value for display the table, get sellfundlist for dropdown list
	    	  for (int i=0; i<position.length; i++) {
		    	  FundBean fundbean = new FundBean();
		    	  PortfolioBean p = new PortfolioBean();
	    		  fundbean.setFundId(position[i].getFundId());
	    		  fundbean.setName(fundDAO.read(fundbean.getFundId()).getName());
	    		  fundbean.setSymbol(fundDAO.read(fundbean.getFundId()).getSymbol());
	    		  Double price = fundpricehistoryDAO.fundLatestPrice(fundbean);
	    		  p.setFundName(fundDAO.read(fundbean.getFundId()).getName());
	    		  DecimalFormat df1 = new DecimalFormat("0.000");
	    		  p.setShares(df1.format(position[i].getShares()));
	    		  DecimalFormat df2 = new DecimalFormat("0.00");
	    		  p.setTotalValue(df2.format(price * position[i].getShares()));
	    		  p.setSymbol(fundDAO.read(fundbean.getFundId()).getSymbol());
	    		  portfolio.add(p);
	    		  sellfundlist.add(fundbean);  
	    	  }
	    	  request.setAttribute("portfolio", portfolio.toArray(new PortfolioBean[portfolio.size()]));
	    	  FundBean[] newfundlist = sellfundlist.toArray(new FundBean[sellfundlist.size()]);
	    	  request.setAttribute("sellfundlist", newfundlist);
	    	  
	    	  SellfundForm form = formBeanFactory.create(request);
	    	    	  if (!form.isPresent()) {
					return "customer-sell-fund.jsp";
				}
	            errors.addAll(form.getValidationErrors());
	            if (errors.size() > 0) {
	                return "customer-sell-fund.jsp";
	            }
	            int fundid = fundDAO.read(form.getFund()).getFundId();
	            double shares = positionDAO.getPosition(user.getCustomerId(), fundid).getShares();
	 	         TransactionBean[] sellfund = transactionDAO.match(MatchArg.and(MatchArg.equals("customerId", user.getCustomerId()),MatchArg.equals("executeDate", null),MatchArg.equals("transactionType", 2),MatchArg.equals("fundId", fundid)));
	 	         Double totalshares = 0.0;
	 	         for(int i=0; i< sellfund.length; i++) {
	 	        	totalshares = totalshares + sellfund[i].getShares();
	 	          }
	            
	            if (form.getSharesDouble() <= (shares - totalshares)) {      
	            // update transaction
	            TransactionBean transaction = new TransactionBean();
	            transaction.setCustomerId(user.getCustomerId());
	            transaction.setFundId(fundDAO.read(form.getFund()).getFundId());
	            transaction.setTransactionType(2);
	            transaction.setShares(form.getSharesDouble());
	            transactionDAO.create(transaction);
	   	            }
	            else {
	            	 Double balance = shares - totalshares;
		        	 DecimalFormat df = new DecimalFormat("0.000");
		        	errors.add("Not enough shares, your current available shares for " + fundDAO.read(form.getFund()).getName() + " is " + df.format(balance));
		        	return "customer-sell-fund.jsp";
		         }
	          request.setAttribute("message", "Your request for selling the fund is under processing");
	    	  return "customer-success.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "customer-error.jsp";
	        } catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "customer-error.jsp";
	} catch (Exception e) {
		errors.add(e.getMessage());
		return "customer-error.jsp";
	}
		

}
}
