package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.FundBean;
import databean.HistoryBean;
import databean.TransactionBean;
import formbeans.ViewCustomerTransactionForm;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.TransactionDAO;

public class ViewCustTransactionHistAction extends Action {
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewCustTransactionHistAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	@Override
	public String getName() {
		return "employee-view-transaction-history.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			ViewCustomerTransactionForm form = new ViewCustomerTransactionForm(request);
			if (!form.isPresent()) {
				return "employee-view-transaction-history.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "employee-view-transaction-history.jsp";
			}

			CustomerBean customer = customerDAO.getCustomerByUserName(form.getUserName());
			if (customer == null) {
				errors.add("There is no customer with the username: " + form.getUserName() + ". Please try again.");
				return "employee-view-transaction-history.jsp";
			}

			TransactionBean[] transactionBeans = transactionDAO.findTransactionsByCustomerId(customer.getCustomerId());
			if (transactionBeans.length == 0) {
				errors.add("There are no transactions for this user");
				return "employee-view-transaction-history.jsp";
			}
			List<HistoryBean> histbeans = new ArrayList<HistoryBean>();
			for (TransactionBean tb : transactionBeans) {
				HistoryBean histbean = new HistoryBean();
				FundBean fund = fundDAO.read(tb.getFundId());
				if (fund != null) {
					histbean.setFundName(fund.getName());
					histbean.setSharePrice(tb.getSharePrice());
				} else {
					// do nothing. leave at null.
				}
				histbean.setAmount(tb.getAmount());
				histbean.setNumShares(tb.getShares());
				histbean.setOperation(tb.getTransactionType());
				histbean.setTransactionDate(tb.getExecuteDate());
				if (tb.getStatus().equals("completed")) {
					histbean.setStatus(tb.getStatus());
				} else {
					histbean.setStatus("pending");
				}
				histbeans.add(histbean);
			}

			request.setAttribute("histbeans", histbeans); // all the details or each history

			return "employee-view-transaction-history.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee-error.jsp";
		} 
		catch (Exception e) {
			errors.add(e.getMessage());
			return "employee-error.jsp";
		
		}
	}
}
