package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ViewCustomerTransactionForm {
	private String userName;
	private String viewTransactionButton;

	public ViewCustomerTransactionForm(HttpServletRequest request) {
		String name = request.getParameter("userName");
		if (name != null) {
			userName = name.trim();
		}
		viewTransactionButton = request.getParameter("action");
	}

	public String getUserName() {
		return userName;
	}

	public String getViewTransactionButton() {
		return viewTransactionButton;
	}

	public boolean isPresent() {
		return viewTransactionButton != null;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0)
			errors.add("User name is required.");

		if (viewTransactionButton == null || !viewTransactionButton.equals("View transaction"))
			errors.add("Invalid button.");

		return errors;
	}
}
