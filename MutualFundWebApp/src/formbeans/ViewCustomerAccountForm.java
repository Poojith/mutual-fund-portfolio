package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ViewCustomerAccountForm {
	private String userName;
	private String viewAccountButton;

	public ViewCustomerAccountForm(HttpServletRequest request) {
		String name = request.getParameter("userName");
		if (name != null) {
			userName = name.trim();
		}
		viewAccountButton = request.getParameter("action");
	}

	public String getUserName() {
		return userName;
	}

	public String getViewAccountButton() {
		return viewAccountButton;
	}

	public boolean isPresent() {
		return viewAccountButton != null;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0)
			errors.add("User name is required.");

		if (viewAccountButton == null || !viewAccountButton.equals("View account"))
			errors.add("Invalid button.");

		return errors;
	}
}
