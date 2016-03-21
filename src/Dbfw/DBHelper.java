package Dbfw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbcon.DBConnectionException;

public class DBHelper {

	// PARAMETERIZED SELECT
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List executeSelect(Connection con, final String sqlSt,
			ResultMapper outMap, ParamMapper inMap)
			throws DBConnectionException {
		List resultList = new ArrayList();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = con.prepareStatement(sqlSt);// query with wildcards ?
			// set parameters
			inMap.mapparam(preStmt);// query where wildcards gets assigned
			rs = preStmt.executeQuery();

			while (rs.next()) {
				Object obj = outMap.mapRow(rs);
				resultList.add(obj);
			}
		} catch (SQLException e) {
			throw new DBConnectionException(
					"Execution of select (prepared) statement failed", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (preStmt != null)
					preStmt.close();
			} catch (SQLException e) {
			}
		}
		return resultList;
	}

	// INSERT / DELETE / UPDATE DATA
	public static int executeUpdate(Connection con, final String sqlSt,
			ParamMapper inMap) throws DBConnectionException {
		PreparedStatement preStmt = null;
		int result = 0;

		try {
			preStmt = con.prepareStatement(sqlSt);
			if (inMap != null) // Supports parameterless call
				inMap.mapparam(preStmt);
			result = preStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DBConnectionException(
					"Execution of execute update statement failed", e);
		} finally {
			try {
				if (preStmt != null)
					preStmt.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List executeSelect(Connection con, final String sqlSt,
			ResultMapper outMap) throws DBConnectionException {
		List resultList = new ArrayList();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlSt);
			while (rs.next()) {
				Object obj = outMap.mapRow(rs);
				resultList.add(obj);
			}
		} catch (SQLException e) {
			throw new DBConnectionException(
					"Exceution of select (prepared) statement failed", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}
}
