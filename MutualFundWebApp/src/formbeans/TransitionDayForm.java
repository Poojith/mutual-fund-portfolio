package formbeans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.FundBean;

public class TransitionDayForm {
	private String transitionDate;
	private String transitionDayButton;

	private boolean checkPrice = true;
	private boolean checkPriceRange = true;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
	private Map<Integer, Double> map = new HashMap<Integer, Double>();

	public TransitionDayForm(HttpServletRequest request) {
		String dateInput = request.getParameter("date");
		transitionDayButton = request.getParameter("action");
		
		
		if (dateInput != null) {
			transitionDate = dateInput.trim();
		}
//		transitionDayButton = request.getParameter("action");
//		*/
		HttpSession session = request.getSession();
		FundBean[] fundBeans = (FundBean[]) session.getAttribute("fundArray");
		if (fundBeans == null) {
			return;
		}
		for (FundBean fb : fundBeans) {
			String parameter = "fund" + fb.getFundId();
			String price = request.getParameter(parameter);
			System.out.println("The price here is: " + price);
			boolean isDouble = checkPriceValue(price);
			if (isDouble) {
				if (getPriceAsDouble(price) < 1 || getPriceAsDouble(price) > 10000000) {
					checkPriceRange = false;
				}
				map.put(fb.getFundId(), getPriceAsDouble(price));
			} else {
				checkPrice = false;
				break;
			}
		}
		
		/*
		boolean checkDateFormat = checkDateFormat(transitionDate);
		
		if (checkDateFormat) {
			transitionDate = dateInput;
		}
		*/
	}

	public String getTransitionDate() {
		return transitionDate;
	}

	public String getTransitionDayButton() {
		return transitionDayButton;
	}

	public Map<Integer, Double> getFundPriceMap() {
		System.out.println("size of getfundpricemap is:" + map.size());
		return map;
	}

	public double getPriceAsDouble(String price) {
		double priceAsDouble = Double.parseDouble(price);
		return priceAsDouble;
	}

	public boolean isPresent() {
		if (transitionDayButton == null || !transitionDayButton.equals("Update prices")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkDateFormat(String date) {
		if (date == null) {
			return false;
		}
		try {
			
			dateFormat.parse((date));
		} catch (ParseException parseEx) {
			System.out.println(parseEx.getMessage());
			return false;
		}
		return true;
	}

	public boolean checkPriceValue(String price) {
		if (price == null || price.length() == 0) {
			return false;
		}
		try {
			Double.parseDouble(price);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (transitionDate == null || transitionDate.length() == 0) {
			errors.add("Please select a date for the transition day");
		}

		if (!checkPrice) {
			errors.add("Please specify the price in the right format.");
		}
		
		if(!checkPriceRange)
			errors.add("Please specify an amount that is between $1 and $10,000,000.");

		if (!checkDateFormat(transitionDate)) {
			errors.add("Error in parsing the date");
		}

		if (transitionDayButton == null || !transitionDayButton.equals("Update prices")) {
			errors.add("Invalid submit button");
		}

		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}
}
