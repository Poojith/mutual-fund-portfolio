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
import formbeans.BuyFundForm;
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
	  //  	  FundBean[] sellfundlist = new FundBean();
	//    	  request.setAttribute("sellfundlist", sellfundlist);
	    	  SellfundForm form = formBeanFactory.create(request);
	    	  if (!form.isPresent()) {
					return "customer-sell-fund.jsp";
				}
	            errors.addAll(form.getValidationErrors());
	            if (errors.size() > 0) {
	                return "customer-sell-fund.jsp";
	            }
	            TransactionBean transaction = new TransactionBean();
	            transaction.setCustomerId(user.getCustomerId());
	            transaction.setFundId(fundDAO.read(form.getFund()).getFundId());
	            transaction.setTransactionType(2);
	            transaction.setAmount(form.getSharesDouble());
	            transactionDAO.create(transaction);
	    	  return "customer-sell-fund.jsp";
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "customer-sell-fund.jsp";
	}
		

}
}
