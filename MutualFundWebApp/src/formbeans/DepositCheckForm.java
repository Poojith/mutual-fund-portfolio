package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DepositCheckForm {
	private String userName;
	private String depositAmount;
	private String depositButton;

	public DepositCheckForm(HttpServletRequest request) {
		String name = request.getParameter("userName");
		if (name != null) {
			userName = name.trim();
		}
		String amount = request.getParameter("buyAmount");
		if (amount != null) {
			depositAmount = amount.trim();
		}
		depositButton = request.getParameter("action");
	}

	public String getUserName() {
		return userName;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public String getDepositButton() {
		return depositButton;
	}

	public boolean isPresent() {
		return depositButton != null;
	}

	public double getDepositAmountAsDouble() {
		return Double.parseDouble(depositAmount);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0)
			errors.add("User name is required.");

		if (depositAmount == null || depositAmount.length() == 0)
			errors.add("Deposit amount is required.");

		if (depositButton == null || !depositButton.equals("Deposit"))
			errors.add("Invalid button.");

		if (errors.size() > 0)
			return errors;

		try {
			Double.parseDouble(depositAmount);
			if (getDepositAmountAsDouble() <= 0 || getDepositAmountAsDouble() >= 10000000) {
				errors.add("Please specify a amount range between $0 and $10,000,000");
			}
		} catch (NumberFormatException e) {
			errors.add("Error in conversion of amount. Please specify the amount in the right format.");
		}

		return errors;
	}
}
