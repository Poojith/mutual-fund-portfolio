package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.EmployeeBean;

public class EmployeeDAO extends GenericDAO{
	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(EmployeeBean.class, tableName, cp);
    }
}
