package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
	private String userName;
	private String password;
	private String customerLoginButton;
	private String employeeLoginButton;

	public LoginForm(HttpServletRequest request) {
		String name = request.getParameter("userName");
		if (name != null) {
			userName = name.trim();
		}
		password = request.getParameter("password");
		String action = request.getParameter("action");
		if (action.equals("Customer login")) {
			customerLoginButton = action;
		} else if (action.equals("Employee login")) {
			employeeLoginButton = action;
		}
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getCustomerLoginButton() {
		return customerLoginButton;
	}

	public String getEmployeeLoginButton() {
		return employeeLoginButton;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0)
			errors.add("User name is required.");

		if (password == null || password.length() == 0)
			errors.add("Password is required.");

		if (customerLoginButton == null && employeeLoginButton == null) {
			errors.add("Invalid button(s).");
		}

		if (errors.size() > 0)
			return errors;

		if (customerLoginButton != null && !customerLoginButton.equals("Customer login")) {
			errors.add("Customer login button was modified.");
		}

		if (employeeLoginButton != null && !employeeLoginButton.equals("Employee login")) {
			errors.add("Employee login button was modified.");
		}

		return errors;
	}
}
