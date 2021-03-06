package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellfundForm extends FormBean {
	private String fund;
	private String numShares;
	private String sellFund;
	
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund.trim();
	}
	public String getNumShares() {
		return numShares;
	}
	public double getSharesDouble() {
		return Double.parseDouble(numShares);
	}
	public void setNumShares(String numShares) {
		this.numShares = numShares.trim();
	}
	public String getSellFund() {
		return sellFund;
	}
	public void setSellFund(String sellFund) {
		this.sellFund = sellFund;
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if(fund == null || fund.length() == 0) {
			errors.add("Fund name is required.");
		}
		
		if(numShares == null || numShares.length() == 0) {
			errors.add("Please specify the number of shares in order to sell the fund");
		}
		if(sellFund == null) {
			errors.add("Sell Fund button is required");
		}
		if(errors.size() > 0) {
			return errors;
		}
		
		try {
			Double.parseDouble(numShares);
			if (getSharesDouble() < 1 || getSharesDouble() > 10000000) {
				errors.add("Please specify an amount that is between $1 and $10,000,000.");
			}
		}
		catch(NumberFormatException e) {
			errors.add("Error in conversion of Number of shares. Please specify the amount in the right format.");
		}
		
		return errors;
	}
		
}
