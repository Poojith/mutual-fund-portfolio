package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.FundBean;
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
			List<FundBean> funds = new ArrayList<FundBean>();
			List<Double> fundPrices = new ArrayList<Double>();
			for (TransactionBean tb : transactionBeans) {
				FundBean fund = fundDAO.read(tb.getFundId());
				funds.add(fund);
				fundPrices.add(fundPriceHistoryDAO.fundLatestPrice(fund));
			}

			request.setAttribute("transactions", transactionBeans); // transaction details of the customer
			request.setAttribute("funds", funds); // array of funds to get fund name
			request.setAttribute("fundPrices", fundPrices); // array of fund prices to get share price
			
			// need to add status of transaction

			return "employee-view-transaction-history.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
