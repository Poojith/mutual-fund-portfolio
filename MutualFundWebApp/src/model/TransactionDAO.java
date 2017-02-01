package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.FundPriceHistoryBean;
import databean.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean>{
	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(TransactionBean.class, tableName, cp);
    }
	
	public void createTransaction(TransactionBean transaction) throws RollbackException{
        if (transaction == null) {
            return;
        }
        try {
            Transaction.begin();
            super.create(transaction);
            Transaction.commit();
        }  finally {
            if (Transaction.isActive()) { Transaction.rollback(); };
        }
    }
    public void updateTransaction(TransactionBean transaction) throws RollbackException {
        if (transaction == null) {
            return;
        }
        try {
            Transaction.begin();
            super.update(transaction);
            Transaction.commit();
        }  finally {
        	if (Transaction.isActive()) { Transaction.rollback(); };
        }
    }
    public void deleteTransaction(TransactionBean transaction) throws RollbackException{
        if (transaction == null) {
            return;
        }
        try {
            Transaction.begin();
            super.delete(transaction);
            Transaction.commit();
        }  finally {
        	if (Transaction.isActive()) { Transaction.rollback(); };
        }
    }
    public TransactionBean[] findTransactionsByNullExecuteDate() throws RollbackException{
        TransactionBean[] beans = match(MatchArg.equals("executeDate", null));
        return beans;
    }
    
    public TransactionBean[] findTransactionsByCustomerId (int customerId) throws RollbackException{
        TransactionBean[] beans = match(MatchArg.equals("customerId", customerId));
        for (int i = 0; i < beans.length/2; i++)
  	  {
        	TransactionBean temp = beans[i];
        	beans[i] = beans[beans.length-1 - i];
        	beans[beans.length-1 - i] = temp;
  	  }
        return beans;
    }
    
    public TransactionBean[] findTransactionsByFundId(int fundId) throws RollbackException{
    	TransactionBean[] beans = match(MatchArg.equals("fundId", fundId));
        return beans;
    }
    
    public String findLastTransactionDate(int customerId) throws RollbackException {
    	TransactionBean[] beans = match(MatchArg.equals("customerId", customerId));
      	if (beans == null || beans.length == 0) {
    		return null;
    	}
    	Arrays.sort(beans);
    	return beans[0].getExecuteDate();
    }
    
    public String findGlobalLastTransactionDate() throws RollbackException {
    	TransactionBean[] beans = match();
    	if (beans==null || beans.length == 0) {
    		return null;
    	}
    	Arrays.sort(beans);
    	return beans[0].getExecuteDate();
    }
    
    public void deleteTransactionsByCustomerId(int customerId) {
        
    }
    public void deleteTransactionsByFundId(int fundId) {
        
    }
}
