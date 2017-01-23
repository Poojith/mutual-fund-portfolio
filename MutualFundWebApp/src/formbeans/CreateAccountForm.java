package formbeans;

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
		if(action.equals("Create customer account")) {
			customerAccountButton = action;
		}
		else if (action.equals("Create employee account")) {
			employeeAccountButton = action;
		}

		String userNameTest = null;
		if (userNameInput != null) {
			userNameTest = sanitize(userNameInput);
			userName = userNameTest.trim();
		}
		
		String firstNameTest = null;
		if(firstNameInput != null) {
			firstNameTest = sanitize(firstNameInput);
			firstName = firstNameTest.trim();
		}
		
		String lastNameTest = null;
		if(lastNameInput != null) {
			lastNameTest = sanitize(lastNameInput);
			lastName = lastNameTest.trim();
		}
		
		String addressLine1Test = null;
		if(addressLine1Input != null) {
			addressLine1Test = sanitize(addressLine1Input);
			addressLine1 = addressLine1Test.trim();
		} 
		
		String addressLine2Test = null;
		if(addressLine2Input != null) {
			addressLine2Test = sanitize(addressLine2Input);
			addressLine2 = addressLine2Test.trim();
		}
		
		String cityTest = null;
		if(cityInput != null) {
			cityTest = sanitize(cityInput);
			city = cityTest.trim();
		}
		
		String zipCodeTest = null;
		if(zipCodeInput != null) {
			zipCodeTest = sanitize(zipCodeInput);
			zipCode = zipCodeTest.trim();
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
		
		if(sb == null)
			return input;
		matcher.appendTail(sb);
		return sb.toString();
	}
}
