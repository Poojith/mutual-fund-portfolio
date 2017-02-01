package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class CreateFundForm {
	private String fundName;
	private String ticker;
	private String createFundButton;

	public CreateFundForm(HttpServletRequest request) {
		String fundInput = request.getParameter("fundName");
		String tickerInput = request.getParameter("ticker");

		if (fundInput != null) {
			fundName = sanitize(fundInput).trim();
			fundName = fundName.substring(0, 1).toUpperCase() + fundName.substring(1);
		}

		if (tickerInput != null) {
			ticker = sanitize(tickerInput).trim();
		}

		createFundButton = request.getParameter("action");
	}

	public String getFundName() {
		return fundName;
	}

	public String getTicker() {
		return ticker;
	}

	public String getCreateFundButton() {
		return createFundButton;
	}

	public boolean isPresent() {
		return createFundButton != null;
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

		if (sb == null)
			return input;
		matcher.appendTail(sb);
		return sb.toString();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.length() == 0)
			errors.add("Fund name is required");
		
		if (fundName.length() > 50)
			errors.add("Fund name must be within 50 characters.");

		if (ticker == null || ticker.length() == 0)
			errors.add("Ticker is required");
		
		if (ticker.length() > 50)
			errors.add("The ticker name must be within 50 characters.");

		if (createFundButton == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		if (!createFundButton.equals("Create fund")) {
			errors.add("Invalid button.");
		}

		return errors;
	}
}
