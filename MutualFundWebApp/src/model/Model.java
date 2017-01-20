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
    /*
	private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;
    private FundDAO fundDAO;
    private FundPriceHistoryDAO fundPriceHistoryDAO;
    private PositionDAO positionDAO;
    private TransactionDAO transactionDAO;

    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriver");
            String jdbcURL = config.getInitParameter("jdbcURL");

            ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

            customerDAO = new CustomerDAO(pool, "customer");
            
            favoriteDAO = new FavoriteDAO(pool, "animbalk_favorite");
            int numusers = (int)userDAO.getCount();
            if (numusers >= 3) {
            	//do nothing
            } else {
            	for (int i=numusers; i<3; i++) {
            		UserBean user = new UserBean();
            		user.setEmail(new String(i + "email"));
            		user.setFirstName(new String(i + "firstname"));
            		user.setLastName(new String(i + "lastname"));
            		user.setPassword(new String(i + "password"));
            		userDAO.create(user);
            		UserBean[] users = userDAO.match(MatchArg.equals("email", user.getEmail()));
            		for (int j=0; j<4; j++) {
	            		FavoriteBean favorite = new FavoriteBean();
	            		favorite.setUserId(i + users[0].getUserId());
	            		favorite.setUrl(new String("http://www." + j + "google.com"));
	            		favorite.setComment(new String("Favorite Number: " + j));
	            		favorite.setClicks(0);
	            		favoriteDAO.create(favorite);
            		}
            	}
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

    public UserDAO getUserDAO() {
        return userDAO;
    }
    */
}
