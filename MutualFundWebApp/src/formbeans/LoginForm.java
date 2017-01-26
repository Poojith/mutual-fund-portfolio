package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
	private String userName;
	private String password;
	private String customerLoginButton;
	private String employeeLoginButton;
	private String userType;

	public LoginForm(HttpServletRequest request) {
		String name = request.getParameter("userName");
		if (name != null) {
			userName = name.trim();
		}
		password = request.getParameter("password");
		String action = request.getParameter("action");
		
		if (action == null) {
			return;
		}
		if (action.equals("Customer login")) {
			customerLoginButton = action;
			userType = "Customer";
			
		} else if (action.equals("Employee login")) {
			employeeLoginButton = action;
			userType = "Employee";
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
	
	public boolean isPresent() {
		return employeeLoginButton != null || customerLoginButton != null;
	}
	
	public String getUserType() {
		return userType;
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
