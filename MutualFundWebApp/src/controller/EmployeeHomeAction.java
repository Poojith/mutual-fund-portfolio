package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import databean.EmployeeBean;
import model.Model;

public class EmployeeHomeAction extends Action {
	
	public EmployeeHomeAction(Model model) { }

	@Override
	public String getName() {
		return "employee-home.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			String userType = (String) request.getSession().getAttribute("userType");
			if (!userType.equals("Employee")) {
				errors.add("You do not have the required authorization to access the requested page.");
				return "customer-error.jsp";
			}
			
			EmployeeBean user = (EmployeeBean) request.getSession(false).getAttribute("user");
			if (user == null) {
				errors.add("You cannot access the requested page without logging in. Please sign in.");
				return "login.jsp";
			}

			return "employee-home.jsp";

		} catch (Exception e) {
			errors.add(e.getMessage());
			return "employee-error.jsp";
		}
	}
}
