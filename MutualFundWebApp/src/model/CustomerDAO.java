
package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.CustomerBean;

public class CustomerDAO extends GenericDAO<CustomerBean> {
    public CustomerDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(CustomerBean.class, tableName, cp);
    }
    public CustomerBean[] getCustomers() throws RollbackException {
        CustomerBean[] customers = match();
        Arrays.sort(customers); // We want them sorted by last and first names (as per User.compareTo());
        return customers;
    }
    
    public void create (CustomerBean bean)throws RollbackException {
    	
    	try {
    		Transaction.begin();
    		CustomerBean customers[] = match(MatchArg.equals("username", bean.getUsername()));
    		if (customers != null && customers.length > 0) {
        		throw new RollbackException ("Username already exists");
        	}
    		super.create(bean);
    		Transaction.commit();
    	} finally {
    		if (Transaction.isActive()) {
    			Transaction.rollback();
    		}
    	}
    }
    
    public CustomerBean read (String username) throws RollbackException{
    	CustomerBean customers[] = match(MatchArg.equals("username", username));
    	if (customers ==null || customers.length == 0) {
    		throw new RollbackException ("No customer for this username");
    	} else if (customers.length > 1) {
    		throw new RollbackException ("Multiple customers for this username");
    	}
    	return customers[0];
    }
    
    public CustomerBean setNewPassword(String username, String password) throws RollbackException {
        try {
            Transaction.begin();
            CustomerBean[] customers = (CustomerBean[])match(MatchArg.equals("username", username));

            if (customers == null || customers.length == 0) {
                throw new RollbackException("Customer " + username + " no longer exists");
            }

            customers[0].setPassword(password);

            update(customers[0]);
            Transaction.commit();
            
            return customers[0];
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
    
}
