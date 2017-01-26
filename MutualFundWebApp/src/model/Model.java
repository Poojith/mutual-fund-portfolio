// Name: Ajinkya Nimbalkar Date: 5th December 2016 Course Number :08672
package model;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.TransactionBean;
import databean.PositionBean;
import databean.FundPriceHistoryBean;
import databean.FundBean;
import databean.EmployeeBean;
import databean.CustomerBean;

public class Model {
	
	private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;
    private FundDAO fundDAO;
    private FundPriceHistoryDAO fundPriceHistoryDAO;
    private PositionDAO positionDAO;
    private TransactionDAO transactionDAO;

    public Model(ServletConfig config) throws ServletException, RollbackException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriver");
            String jdbcURL = config.getInitParameter("jdbcURL");

            ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

            customerDAO = new CustomerDAO(pool, "customer");
            employeeDAO = new EmployeeDAO(pool, "employee");
            fundDAO = new FundDAO(pool, "fund");
            fundPriceHistoryDAO = new FundPriceHistoryDAO(pool, "fundHistory");
            positionDAO = new PositionDAO(pool, "position");
            transactionDAO = new TransactionDAO(pool, "transaction");
            
            //pre-populatnig with employee
            if (employeeDAO.getCount()==0) {
	            EmployeeBean employeebean = new EmployeeBean();
	            employeebean.setAddrLine1("address1");
	            employeebean.setAddrLine2("address2");
	            employeebean.setCity("city");
	            employeebean.setFirstName("firstname");
	            employeebean.setLastName("lastname");
	            employeebean.setPassword("password");
	            employeebean.setState("state");
	            employeebean.setUsername("username");
	            employeebean.setZip("zip");
	            employeeDAO.create(employeebean);
            }
            
            if (customerDAO.getCount()==0) {
	            CustomerBean customerbean = new CustomerBean();
	            customerbean.setAddrLine1("address1");
	            customerbean.setAddrLine2("address2");
	            customerbean.setCity("city");
	            customerbean.setFirstName("firstname");
	            customerbean.setLastName("lastname");
	            customerbean.setPassword("password");
	            customerbean.setState("state");
	            customerbean.setUsername("username");
	            customerbean.setZip("zip");
	            customerDAO.create(customerbean);
            }
            
            
        } catch (DAOException e) {
            throw new ServletException(e);
        } catch (RollbackException f) {
        	throw new ServletException(f);
        }
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }
    
    public FundDAO getFundDAO() {
        return fundDAO;
    }
    public FundPriceHistoryDAO getFundPriceHistoryDAO() {
        return fundPriceHistoryDAO;
    }
    public PositionDAO getPositionDAO() {
        return positionDAO;
    }
    public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }
    
    
}
