package model;



import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean>{
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(PositionBean.class, tableName, cp);
    }
	
	 public void createPosition(PositionBean position) throws RollbackException{
	        if (position == null) {
	            return;
	        }
	        try {
	            Transaction.begin();
	            super.create(position);
	            Transaction.commit();
	        } finally {
	            if (Transaction.isActive()){Transaction.rollback();}
	        }
	    }
	    public void updatePosition(PositionBean position) throws RollbackException{
	        if (position == null) {
	            return;
	        }
	        try {
	            Transaction.begin();
	            super.update(position);
	            Transaction.commit();
	        } finally {
	        	if (Transaction.isActive()){Transaction.rollback();}
	        }
	    }
	    public PositionBean getPosition(int customerId, int fundId) throws RollbackException {
	        	PositionBean position = super.read(customerId, fundId);
		        return position;
	    }
	    
	    public PositionBean[] getPositionsByCustomerId(int customerId)  throws RollbackException{	        	
	    	PositionBean[] beans = match((MatchArg.equals("customerId", customerId)));
	        return beans;
	    }
	    
	    public PositionBean[] getPositionsByFundId(int fundId)  throws RollbackException{	        	
	    	PositionBean[] beans = match((MatchArg.equals("fundId", fundId)));
	        if (beans == null || beans.length==0) {
	        	throw new RollbackException("No poistions for this fundId");
	        }
	        return beans;
	    }
	    
	    
	    public void deletePosition(PositionBean position) throws RollbackException{
	        if (position == null) {
	            return;
	        }
	       
	        try {
	            Transaction.begin();
	            super.delete(position);
	            Transaction.commit();
	        }  finally {
	        	if (Transaction.isActive()){Transaction.rollback();}
	        }
	    }
	    public void deletePositionsByCustomerId(int customerId) {
	        
	    }
	    public void deletePositionsByFundId(int fundId) {
	        
	    }
	

}
