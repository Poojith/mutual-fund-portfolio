package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import formbeans.DepositCheckForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;
import model.TransactionDAO;

public class TransitionDayAction extends Action {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private TransactionDAO transactionDAO;

	public TransitionDayAction(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		return "employee-transition-day.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
