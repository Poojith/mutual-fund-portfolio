package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean {
	private String fundName;
	private String buyAmount;
	private String buyFund;

	public String getFundName() {
		return fundName;
	}
	public String getBuyAmount() {
		return buyAmount;
	}
	
	public void setFundName(String fundName) {
		this.fundName = fundName.trim();
	}

	public void setBuyAmount(String buyAmount) {
		this.buyAmount = buyAmount.trim();
	}

	public double getAmountDouble() {
		return Double.parseDouble(buyAmount);
	}
	
	public String getBuyFund() {
		return buyFund;
	}
	public void setBuyFund(String buyFund) {
		this.buyFund = buyFund;
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if(fundName == null || fundName.length() == 0) {
			errors.add("Fund name is required.");
		}
		if(buyAmount == null || buyAmount.length() == 0) {
			errors.add("Please specify the amount in order to buy the fund");
		}
		if(buyFund == null) {
			errors.add("Buy Fund button is required");
		}

		if(errors.size() > 0) {
			return errors;
		}
		
		try {
			Double.parseDouble(buyAmount);
			if (getAmountDouble() < 1 || getAmountDouble() >= 10000000) {
				errors.add("Please specify a amount range between $1 and $10,000,000");
			}
		}
		catch(NumberFormatException e) {
			errors.add("Error in conversion of amount. Please specify the amount in the right format.");
		}
		
		return errors;
	}
}
