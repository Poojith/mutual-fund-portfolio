package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ResetPasswordForm {
	private String userName;
	private String newPassword;
	private String confirmPassword;
	private String resetPasswordButton;

	public ResetPasswordForm(HttpServletRequest request) {
		String name = request.getParameter("userName");
		if (name != null) {
			userName = name.trim();
		}
		newPassword = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");
		resetPasswordButton = request.getParameter("action");
	}

	public String getUserName() {
		return userName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getResetPasswordButton() {
		return resetPasswordButton;
	}

	public boolean isPresent() {
		return resetPasswordButton != null;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0)
			errors.add("User name is required.");

		if (newPassword == null || newPassword.length() == 0)
			errors.add("Password is required.");

		if (confirmPassword == null || confirmPassword.length() == 0)
			errors.add("Please confirm your password.");

		if (resetPasswordButton == null || !resetPasswordButton.equals("Reset password"))
			errors.add("Invalid button.");

		if (errors.size() > 0)
			return errors;

		if (!confirmPassword.equals(newPassword))
			errors.add("Passwords do not match. Please try again.");

		return errors;
	}
}
