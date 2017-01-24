package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class CreateAccountForm {
	private String userName;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String zipCode;
	private String password;
	private String confirmPassword;
	private String customerAccountButton;
	private String employeeAccountButton;
	private String userType;

	public CreateAccountForm(HttpServletRequest request) {
		String userNameInput = request.getParameter("userName");
		String firstNameInput = request.getParameter("firstName");
		String lastNameInput = request.getParameter("lastName");
		String addressLine1Input = request.getParameter("address1");
		String addressLine2Input = request.getParameter("address2");
		String cityInput = request.getParameter("city");
		state = request.getParameter("state");
		String zipCodeInput = request.getParameter("zipcode");
		password = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");

		String action = request.getParameter("action");
		if (action.equals("Create customer account")) {
			customerAccountButton = action;
			userType = "Customer";
		} else if (action.equals("Create employee account")) {
			employeeAccountButton = action;
			userType = "Employee";
		}

		if (userNameInput != null) {
			userName = sanitize(userNameInput).trim();
		}

		if (firstNameInput != null) {
			firstName = sanitize(firstNameInput).trim();
		}

		if (lastNameInput != null) {
			lastName = sanitize(lastNameInput).trim();
		}

		if (addressLine1Input != null) {
			addressLine1 = sanitize(addressLine1Input).trim();
		}

		if (addressLine2Input != null) {
			addressLine2 = sanitize(addressLine2Input).trim();
		}

		if (cityInput != null) {
			city = sanitize(cityInput).trim();
		}

		if (zipCodeInput != null) {
			zipCode = sanitize(zipCodeInput).trim();
		}
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getCustomerAccountButton() {
		return customerAccountButton;
	}

	public String getEmployeeAccountButton() {
		return employeeAccountButton;
	}
	
	public boolean isPresent() {
		return employeeAccountButton != null || customerAccountButton != null; 
	}
	
	public String getUserType() {
		return userType;
	}

	public String sanitize(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}

		Pattern pattern = Pattern.compile("[<>\"&]");
		Matcher matcher = pattern.matcher(input);
		StringBuffer sb = null;
		while (matcher.find()) {
			if (sb == null) {
				sb = new StringBuffer();
			}
			switch (input.charAt(matcher.start())) {
			case '<':
				matcher.appendReplacement(sb, "&lt;");
				break;
			case '>':
				matcher.appendReplacement(sb, "&gt;");
				break;
			case '&':
				matcher.appendReplacement(sb, "&amp;");
				break;
			case '"':
				matcher.appendReplacement(sb, "&quot;");
				break;
			default:
				matcher.appendReplacement(sb, "&#" + ((int) input.charAt(matcher.start())) + ';');
			}
		}

		if (sb == null)
			return input;
		matcher.appendTail(sb);
		return sb.toString();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0)
			errors.add("User name is required.");

		if (firstName == null || firstName.length() == 0)
			errors.add("First name is required.");

		if (lastName == null || lastName.length() == 0)
			errors.add("Last name is required.");

		if (addressLine1 == null || addressLine1.length() == 0)
			errors.add("Address is required.");

		if (city == null || city.length() == 0)
			errors.add("City is required.");

		if (state == null || state.length() == 0)
			errors.add("State is required.");

		if (zipCode == null || zipCode.length() == 0)
			errors.add("ZIP code is required.");

		if (password == null || password.length() == 0)
			errors.add("Password is required.");

		if (confirmPassword == null || confirmPassword.length() == 0)
			errors.add("Please confirm your password.");

		if (customerAccountButton == null && employeeAccountButton == null) {
			errors.add("Invalid button(s).");
		}

		if (errors.size() > 0)
			return errors;

		if (!confirmPassword.equals(password))
			errors.add("Passwords do not match. Please try again.");

		if (customerAccountButton != null && !customerAccountButton.equals("Create customer account")) {
			errors.add("Create customer account was modified.");
		}

		if (employeeAccountButton != null && !employeeAccountButton.equals("Create employee account")) {
			errors.add("Create employee account was modified.");
		}

		return errors;
	}

}
