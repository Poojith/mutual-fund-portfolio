package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.EmployeeBean;
import formbeans.LoginForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

public class LoginAction extends Action {
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public LoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			LoginForm form = new LoginForm(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "login.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "login.jsp";
			}

			String userType = form.getUserType();
			if (userType.equals("Employee")) {
				EmployeeBean employee = employeeDAO.read(form.getUserName());
				if (employee == null) {
					errors.add("Sorry, there's no such user. Please check your credentials.");
					return "login.jsp";
				}

				if (!employee.getPassword().equals(form.getPassword())) {
					errors.add("Incorrect password. Please try again.");
					return "login.jsp";
				}

				HttpSession session = request.getSession();
				session.setAttribute("user", employee);
				session.setAttribute("userType", "Employee");

				return "employee-home.do";
				
			} else if (userType.equals("Customer")) {
				CustomerBean customer = customerDAO.getCustomerByUserName(form.getUserName());
				if (customer == null) {
					errors.add("Sorry, there's no such user. Please check your credentials.");
					return "login.jsp";
				}

				if (!customer.getPassword().equals(form.getPassword())) {
					errors.add("Incorrect password. Please try again.");
					return "login.jsp";
				}

				HttpSession session = request.getSession();
				session.setAttribute("user", customer);
				session.setAttribute("userType", "Customer");
			}

			return "customer-home.do";
			
		} catch (RollbackException r) {
			errors.add(r.getMessage());
			return "error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
