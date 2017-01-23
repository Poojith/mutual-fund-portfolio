package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class BuyFundForm {
	private String fundName;
	private String buyAmount;
	private String buyFundButton;

	public BuyFundForm(HttpServletRequest request) {
		fundName = request.getParameter("fund");
		String amount = request.getParameter("buyAmount");
		if (amount != null) {
			buyAmount = amount.trim();
		}
		buyFundButton = request.getParameter("action");
	}

	public String getFundName() {
		return fundName;
	}

	public String getBuyAmount() {
		return buyAmount;
	}
	
	public String getBuyFundButton() {
		return buyFundButton;
	}

	/*
	 * getValidationErrors() must be called prior to this method to ensure no
	 * exception is thrown.
	 */
	public double getBuyAmountAsDouble() {
		return Double.parseDouble(buyAmount);
	}

	public boolean isPresent() {
		return buyFundButton != null;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.length() == 0) {
			errors.add("Fund name is required.");
		}

		if (buyAmount == null || buyAmount.length() == 0) {
			errors.add("Please specify the amount in order to buy the fund");
		}

		if (buyFundButton == null) {
			errors.add("Button is required");
		}

		if (errors.size() > 0) {
			return errors;
		}

		if (!buyFundButton.equals("Buy fund")) {
			errors.add("Invalid button.");
		}

		try {
			Double.parseDouble(buyAmount);
		} catch (NumberFormatException e) {
			errors.add("Error in conversion of amount. Please specify the amount in the right format.");
		}

		return errors;
	}
}
