package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.FundBean;
import databean.PositionBean;
import formbeans.ViewCustomerAccountForm;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class ViewCustomerAccountAction extends Action {
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;

	public ViewCustomerAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		return "view-customer-account.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			ViewCustomerAccountForm form = new ViewCustomerAccountForm(request);
			if (!form.isPresent()) {
				CustomerBean[] customers = customerDAO.match();
				request.setAttribute("customer", customers);
				return "employee-view-customer-account.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "employee-view-customer-account.jsp";
			}

			CustomerBean customer = customerDAO.getCustomerByUserName(form.getUserName());
			if (customer == null) {
				errors.add("There is no customer with the username: " + form.getUserName() + ". Please try again.");
				return "employee-view-customer-account.jsp";
			}

			PositionBean[] positions = positionDAO.getPositionsByCustomerId(customer.getCustomerId());
			List<FundBean> funds = new ArrayList<FundBean>();

			for (PositionBean pb : positions) {
				funds.add(fundDAO.read(pb.getFundId()));
				pb.setTotalValue(
						(double) pb.getShares() * fundPriceHistoryDAO.fundLatestPrice(fundDAO.read(pb.getFundId())));
			}

			String date = transactionDAO.findLastTransactionDate(customer.getCustomerId());

			request.setAttribute("customer", customer);
			request.setAttribute("positions", positions);
			request.setAttribute("funds", funds);
			request.setAttribute("lastTransactionDate", date);

			return "employee-view-result-customer-account.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
