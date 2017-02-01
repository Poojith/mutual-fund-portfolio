package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.EmployeeBean;
import formbeans.CreateAccountForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

public class CreateAccountAction extends Action {
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public CreateAccountAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "create-account.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			String checkUser = (String) request.getSession(false).getAttribute("userType");
			if (!checkUser.equals("Employee")) {
				errors.add("Sorry, you do not have the required authorization to access this page.");
				return "customer-error.jsp";
			}
			
			EmployeeBean user = (EmployeeBean) request.getSession().getAttribute("user");			
			if (user == null) {
				errors.add("Please login to access the requested page");
				return "login.jsp";
			}

			CreateAccountForm form = new CreateAccountForm(request);
			if (!form.isPresent()) {
				return "employee-create-account.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "employee-create-account.jsp";
			}

			String userType = form.getUserType();
			if (userType.equals("Employee")) {
				EmployeeBean[] bean = employeeDAO.match(MatchArg.equals("username", form.getUserName()));
				if (bean.length > 0) {
					errors.add("Sorry, the user name is already registered.");
					return "employee-create-account.jsp";
				}

				EmployeeBean employee = new EmployeeBean();
				employee.setFirstName(form.getFirstName());
				employee.setLastName(form.getLastName());
				employee.setPassword(form.getPassword());
				employee.setUsername(form.getUserName());
				employee.setAddrLine1(form.getAddressLine1());
				employee.setAddrLine2(form.getAddressLine2());
				employee.setCity(form.getCity());
				employee.setState(form.getState());
				employee.setZip(form.getZipCode());

				employeeDAO.create(employee);

				HttpSession session = request.getSession();
				session.setAttribute("user", employee);
				session.setAttribute("userType", "Employee");

			} else if (userType.equals("Customer")) {
				CustomerBean[] bean = customerDAO.match(MatchArg.equals("username", form.getUserName()));
				if (bean.length > 0) {
					errors.add("Sorry, the user name is already registered.");
					return "employee-create-account.jsp";
				}

				CustomerBean customer = new CustomerBean();
				customer.setUsername(form.getUserName());
				customer.setAddrLine1(form.getAddressLine1());
				customer.setAddrLine2(form.getAddressLine2());
				customer.setCash(0.0d);
				customer.setCity(form.getCity());
				customer.setFirstName(form.getFirstName());
				customer.setLastName(form.getLastName());
				customer.setPassword(form.getPassword());
				customer.setState(form.getState());
				customer.setZip(form.getZipCode());

				customerDAO.create(customer);

				HttpSession session = request.getSession();
				session.setAttribute("userType", "Employee");
			}

			request.setAttribute("message", "You have successfully created the account for " + form.getFirstName() + " "
					+ form.getLastName() + ".");
			return "employee-success.jsp";

		} catch (RollbackException r) {
			errors.add(r.getMessage());
			return "employee-error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "employee-error.jsp";
		}
	}
}
