package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.PositionBean;

public class PositionDAO extends GenericDAO{
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(PositionBean.class, tableName, cp);
    }
}
