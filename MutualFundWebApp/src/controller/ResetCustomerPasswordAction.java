package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import formbeans.ResetPasswordForm;
import model.CustomerDAO;
import model.Model;

public class ResetCustomerPasswordAction extends Action {
	private CustomerDAO customerDAO;

	public ResetCustomerPasswordAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "reset-customer-password.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			ResetPasswordForm form = new ResetPasswordForm(request);
			if (!form.isPresent()) {
				return "employee-reset-customer-password.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "employee-reset-customer-password.jsp";
			}

			CustomerBean customer = customerDAO.getCustomerByUserName(form.getUserName());
			if (customer == null) {
				errors.add("There is no customer with the username: " + form.getUserName() + ". Please try again.");
				return "employee-reset-customer-password.jsp";
			}

			customer.setPassword(form.getNewPassword());
			customerDAO.updateCustomer(customer);
			request.setAttribute("message", "You have successfully changed the password for " + customer.getFirstName()
					+ " " + customer.getLastName() + ".");
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
