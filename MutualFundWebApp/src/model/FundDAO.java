package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.FundBean;

public class FundDAO extends GenericDAO<FundBean>{
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(FundBean.class, tableName, cp);
    }
	
	public void create (FundBean bean)throws RollbackException {
    	try {
    		Transaction.begin();
    		FundBean funds[] = match(MatchArg.equals("name", bean.getName()));
    		if (funds != null && funds.length > 0) {
        		throw new RollbackException ("Fund name already exists");
        	}
    		FundBean funds2[] = match(MatchArg.equals("symbol", bean.getSymbol()));
    		if (funds2 != null && funds2.length > 0) {
    			throw new RollbackException ("Fund symbol already exists");
    		}
    		super.create(bean);
    		Transaction.commit();
    	} finally {
    		if (Transaction.isActive()) {
    			Transaction.rollback();
    		}
    	}
    }
	//read by symbol
	public FundBean read (String symbol) throws RollbackException{
    	FundBean funds[] = match(MatchArg.equals("symbol", symbol));
    	if (funds ==null || funds.length == 0) {
   
    	} else if (funds.length > 1) {
    		throw new RollbackException ("Multiple funds for this symbol");
    	}
    	return funds[0];
    }
	//read by fundId
	public FundBean read (int fundId) throws RollbackException{
    	FundBean funds[] = match(MatchArg.equals("fundId", fundId));
    	if (funds ==null || funds.length == 0) {
    		return null;
    	} else if (funds.length > 1) {
    		throw new RollbackException ("Multiple funds for this fundId");
    	}
    	return funds[0];
    }
	public FundBean[] readAll () throws RollbackException{
		FundBean[] funds = match();
        return funds;
    }
	
	
//	public void deposit (String name, int amt, TransactionBean bean) throws RollbackException {
//		try {
//			Transaction.begin();
//			FundBean funds[] = match(MatchArg.equals("name", name));
//			if (funds == null || funds.length == 0) {
//				throw new RollbackException("Fund " + name + " does not exists");
//			}
//			
//			
//		} finally {
//			if (Transaction.isActive()) {
//				Transaction.rollback();
//			}
//		}
//	}
}
