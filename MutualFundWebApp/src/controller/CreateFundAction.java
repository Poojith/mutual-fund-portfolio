package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.FundBean;
import formbeans.CreateFundForm;
import model.FundDAO;
import model.Model;

public class CreateFundAction extends Action {
	private FundDAO fundDAO;

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
			CreateFundForm form = new CreateFundForm(request);
			if (!form.isPresent()) {
				return "employee-create-fund.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
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

			fundDAO.create(fund);
			request.setAttribute("message", fund.getName() + " has been successfully created.");
			return "success.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
