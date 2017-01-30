package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.EmployeeBean;
//import model.Model;
import model.Model;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	public void init() throws ServletException {

		 Model model;
		try {
			model = new Model(getServletConfig());
			Action.add(new BuyFundAction(model));
			 Action.add(new ChangePasswordAction(model));
			 Action.add(new CreateAccountAction(model));
			 Action.add(new CreateFundAction(model));
			 Action.add(new DepositCheckAction(model));
			 Action.add(new LoginAction(model));
			 Action.add(new LogoutAction(model));
			 Action.add(new RequestCheckAction(model));
			 Action.add(new ResearchFundAction(model));
			 Action.add(new ResetCustomerPasswordAction(model));
			 Action.add(new SellFundAction(model));
			 Action.add(new TransactionHistoryAction(model));
			 Action.add(new TransitionDayAction(model));
			 Action.add(new ViewAccountAction(model));
			 Action.add(new ViewCustomerAccountAction(model));
			 Action.add(new ViewCustTransactionHistAction(model));
			 Action.add(new EmployeeHomeAction(model));
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performAction(request);
		sendToNextPage(nextPage, request, response);
	}

	private String performAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);
		String userType = (String) session.getAttribute("userType");

		if (action.equals("login.do")) {
			return Action.perform(action, request);
		}

		if (userType != null && userType.equals("Employee")) {
			EmployeeBean employee = (EmployeeBean) session.getAttribute("user");
			if (employee == null) {
				return Action.perform("login.do", request);
			}
		} else if (userType != null && userType.equals("Customer")) {
			CustomerBean customer = (CustomerBean) session.getAttribute("user");
			if (customer == null) {
				return Action.perform("login.do", request);
			}
		} else {
			return "error.jsp";
		}

		return Action.perform(action, request);
	}

	private String getActionName(String servletPath) {
		int index = servletPath.lastIndexOf('/');
		return servletPath.substring(index + 1);
	}

	private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher dispathcer = request.getRequestDispatcher("Front-end/pages/" + nextPage);
			dispathcer.forward(request, response);
			return;
		}

		throw new ServletException(
				Controller.class.getName() + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}
}
