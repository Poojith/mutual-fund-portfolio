package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.TransactionBean;
import formbeans.DepositCheckForm;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

public class DepositCheckAction extends Action {
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public DepositCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		return "employee-deposit-check.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			DepositCheckForm form = new DepositCheckForm(request);
			if (!form.isPresent()) {
				return "employee-deposit-check.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "employee-deposit-check.jsp";
			}

			CustomerBean customer = customerDAO.getCustomerByUserName(form.getUserName());
			if (customer == null) {
				errors.add("There is no customer with the username: " + form.getUserName() + ". Please try again.");
				return "employee-deposit-check.jsp";
			}

			double amount = form.getDepositAmountAsDouble();
			TransactionBean transaction = new TransactionBean();
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setAmount(amount);
			transaction.setTransactionType(3);
			transactionDAO.create(transaction);

			request.setAttribute("message", "Your request of depositing the check for " + customer.getFirstName() + " "
					+ customer.getLastName() + " is under processing.");
			return "success.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
