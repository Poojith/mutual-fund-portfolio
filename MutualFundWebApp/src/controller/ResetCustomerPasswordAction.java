package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.EmployeeBean;
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

			String userType = (String) request.getSession().getAttribute("userType");
			if (!userType.equals("Employee")) {
				errors.add("You do not have the required authorization to access the requested page.");
				return "customer-error.jsp";
			}

			EmployeeBean user = (EmployeeBean) request.getSession(false).getAttribute("user");
			if (user == null) {
				errors.add("You cannot access the requested page without logging in. Please sign in.");
				return "login.jsp";
			}

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
			return "employee-success.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee-error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "employee-error.jsp";
		}
	}
}
