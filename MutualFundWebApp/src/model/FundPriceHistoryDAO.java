package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.FundBean;
import databean.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean>{
	public FundPriceHistoryDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(FundPriceHistoryBean.class, tableName, cp);
    }
	
	public FundPriceHistoryBean[] research (FundBean bean) throws RollbackException {
		FundPriceHistoryBean[] funds = match(MatchArg.equals("fundId", bean.getFundId()));
    	if (funds == null || funds.length == 0) {
    		throw new RollbackException ("No fund history for this symbol");
    	} 
    	return funds;
	}
}

