package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.EmployeeBean;
import databean.FundBean;
import databean.FundPriceHistoryBean;
import formbeans.CreateFundForm;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;

public class CreateFundAction extends Action {
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public CreateFundAction(Model model) {
		fundDAO = model.getFundDAO();
	}

	@Override
	public String getName() {
		return "create-fund.do";
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

			CreateFundForm form = new CreateFundForm(request);
			if (!form.isPresent()) {
				FundBean[] fundbeans = fundDAO.match();
				request.setAttribute("listfunds", fundbeans);
				return "employee-create-fund.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "employee-create-fund.jsp";
			}
			
			FundBean[] fundNames = fundDAO.match(MatchArg.equals("name", form.getFundName()));
			if (fundNames.length > 0) {
				errors.add("The fund name you entered already exists.");
				return "employee-create-fund.jsp";
			}

			FundBean[] beans = fundDAO.match(MatchArg.equals("symbol", form.getTicker()));
			if (beans.length > 0) {
				errors.add("The ticker you entered already exists.");
				return "employee-create-fund.jsp";
			}

			FundBean fund = new FundBean();
			fund.setName(form.getFundName());
			fund.setSymbol(form.getTicker());
			FundPriceHistoryBean fundhist = new FundPriceHistoryBean();
			fundDAO.create(fund);
			request.setAttribute("message", fund.getName() + " has been successfully created.");
			return "employee-success.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee-error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "employee-error.jsp";
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
}
