package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestcheckForm extends FormBean {
	private String amtWithdrawn;
	private String amtConfirm;
	private String requestCheck;
	public String getAmtWithdrawn() {
		return amtWithdrawn;
	}
	public void setAmtWithdrawn(String amtWithdrawn) {
		this.amtWithdrawn = amtWithdrawn.trim();
	}
	public double getAmountDouble() {
		return Double.parseDouble(amtWithdrawn);
	}
	public String getAmtConfirm() {
		return amtConfirm;
	}
	public void setAmtConfirm(String amtConfirm) {
		this.amtConfirm = amtConfirm.trim();
	}
	public String getRequestCheck() {
		return requestCheck;
	}
	public void setRequestCheck(String requestCheck) {
		this.requestCheck = requestCheck;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (amtWithdrawn == null || amtWithdrawn.length() == 0) {
			errors.add("Withdraw Amount is required");
		}
		
		if (amtConfirm == null || amtConfirm.length() == 0) {
			errors.add("Withdraw Amount Confirmation is required");
		}
		if(requestCheck == null) {
			errors.add("Request Check button is required");
		}
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!amtWithdrawn.equals(amtConfirm)) {
			errors.add("Withdran Amount do not match");
		}
		try {
			Double.parseDouble(amtWithdrawn);
		}
		catch(NumberFormatException e) {
			errors.add("Error in conversion of Withdrawn Amount. Please specify the amount in the right format.");
		}

		return errors;
	}
	
}