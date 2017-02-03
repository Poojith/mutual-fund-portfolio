package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import formbeans.PwdchangeForm;
import model.CustomerDAO;
import model.Model;

public class ChangePasswordAction extends Action {
	
	private FormBeanFactory<PwdchangeForm> formBeanFactory = FormBeanFactory
            .getInstance(PwdchangeForm.class);
	private CustomerDAO customerDAO;
	public ChangePasswordAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() {
		return "changepassword.do";
	}

	public String perform(HttpServletRequest request) {
		   List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors", errors);
	      try {
	    	  String type = (String) request.getSession(false).getAttribute("userType");
	       	  if (!type.equals("Customer")) {
	    		  errors.add("Please use Employee pages only");
	    		  return "employee-error.jsp";
	    	  }
	    	  PwdchangeForm form = formBeanFactory.create(request);
	    	  if (!form.isPresent()) {
					return "customer-change-password.jsp";
				}
	            errors.addAll(form.getValidationErrors());
	            if (errors.size() > 0) {
	                return "customer-change-password.jsp";
	            }
	            HttpSession session = request.getSession();
	            CustomerBean user = (CustomerBean) request.getSession(false).getAttribute("user");
	            if (form.getOldPassword().equals(customerDAO.getCustomerByUserName(user.getUsername()).getPassword())) {
	            	CustomerBean updateduser = customerDAO.setNewPassword(user.getUsername(), form.getNewPassword());
		            session.setAttribute("user", updateduser);
					request.setAttribute("message", "Password changed for " + user.getUsername());
		    	  return "customer-success.jsp";
	            } else {
	            	errors.add("Old password is incorrect");
		    		  return "customer-change-password.jsp";
	            }
	            
	      } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "customer-error.jsp";
	        } catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "customer-error.jsp";
	} catch (Exception e) {
		errors.add(e.getMessage());
		return "customer-error.jsp";
	}
		

}
}
