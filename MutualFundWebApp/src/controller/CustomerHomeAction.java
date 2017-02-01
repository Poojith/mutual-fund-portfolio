package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import databean.CustomerBean;
import model.Model;

public class CustomerHomeAction extends Action {

	public CustomerHomeAction(Model model) {
	}

	@Override
	public String getName() {
		return "customer-home.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
	    	  String type = (String) request.getSession(false).getAttribute("userType");
	       	  if (!type.equals("Customer")) {
	    		  errors.add("Please use Employee pages only");
	    		  return "employee-error.jsp";
	    	  }
			CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
			if (user == null) {
				errors.add("You cannot access the requested page without logging in. Please sign in.");
				return "login.jsp";
			}

			String userType = (String) request.getSession().getAttribute("userType");
			if (!userType.equals("Customer")) {
				errors.add("You do not have the required authorization to access the requested page.");
				return "login.jsp";
			}

			return "customer-home.jsp";

		} catch (Exception e) {
			errors.add(e.getMessage());
			return "customer-error.jsp";
		}
	}
}
