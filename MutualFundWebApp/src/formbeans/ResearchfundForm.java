package formbeans;

import java.util.ArrayList;
import java.util.List;
import org.mybeans.form.FormBean;

public class ResearchfundForm extends FormBean {
	private int fundId;
	private String researchfund;
    public int getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = Integer.parseInt(fundId);
	}
	public String getResearchfund() {
		return researchfund;
	}
	public void setResearchfund(String researchfund) {
		this.researchfund = researchfund;
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if(fundId == 0) {
			errors.add("Fund name is required.");
		}
		
		if(researchfund == null) {
			errors.add("Research Fund button is required");
		}
		if(errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
}
