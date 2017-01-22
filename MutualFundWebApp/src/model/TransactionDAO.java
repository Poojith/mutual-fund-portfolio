package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.TransactionBean;

public class TransactionDAO extends GenericDAO{
	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(TransactionBean.class, tableName, cp);
    }
}
