package dbcon;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionHolder {
	private static ConnectionHolder instance = null;
	private DataSource ds = null;

	private ConnectionHolder() // Empty
	{
	}

	private void initAppserverDatasource() throws DBConnectionException {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/moviedb");
		} catch (NamingException e) {
			throw new DBConnectionException("Unable to get datasource", e);
		}
	}

	public static ConnectionHolder getInstance() throws DBConnectionException {
		synchronized (ConnectionHolder.class) {
			if (instance == null) {
				instance = new ConnectionHolder();
				// Replace this line for Web Application
				// instance.initAppDatasource();

				instance.initAppserverDatasource();

			}
		}
		return instance;
	}

	public Connection getConnection() throws DBConnectionException {
		try {
			System.out.println("In getconn");
			return ds.getConnection();

		} catch (SQLException e) {
			throw new DBConnectionException("Unable to obtain a connection", e);
		}
	}

	public void dispose() throws DBConnectionException {
		BasicDataSource bds = (BasicDataSource) ds;
		try {
			bds.close();
		} catch (SQLException e) {
			throw new DBConnectionException("Unable to close the connection", e);
		}
	}

}
