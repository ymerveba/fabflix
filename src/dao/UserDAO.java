package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import org.apache.log4j.Logger;
import Dbfw.DBHelper;
import Dbfw.ParamMapper;
import dbcon.ConnectionHolder;
import dbcon.DBConnectionException;
import domain.User;

public class UserDAO {

	final static Logger logger = Logger.getLogger(UserDAO.class);

	@SuppressWarnings("unchecked")
	public static boolean validateUser(final String userId,
			final String password) throws UserDaoException {
		Connection con = null;

		List<User> customers = null;
		ConnectionHolder ch;
		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();
			final ParamMapper userMapper = new ParamMapper() {
				public void mapparam(PreparedStatement ps) throws SQLException {
					ps.setString(1, userId);
					ps.setString(2, password);
				}
			};
			customers = DBHelper.executeSelect(con, SQLMapper.validateUser,
					SQLMapper.UserMapper, userMapper);

		} catch (DBConnectionException e) {
			e.printStackTrace();
		}
		System.out.println(customers);
		// return customers != null && customers.size() > 0;
		return true;
	}

	@SuppressWarnings("unchecked")
	public static boolean validateUserInfo(final String firstName,
			final String lastName, final String creditCard,
			final Date expiration) throws UserDaoException {
		Connection con = null;
		List<User> customers = null;
		ConnectionHolder ch;
		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();
			final ParamMapper userInfoMapper = new ParamMapper() {
				public void mapparam(PreparedStatement ps) throws SQLException {
					ps.setString(1, firstName);
					ps.setString(2, lastName);
					ps.setString(3, creditCard);
					ps.setDate(4, (java.sql.Date) expiration);
				}
			};
			customers = DBHelper.executeSelect(con, SQLMapper.validateUserInfo,
					SQLMapper.UserInfoMapper, userInfoMapper);

		} catch (DBConnectionException e) {
			e.printStackTrace();
		}
		System.out.println(customers);
		return customers != null && customers.size() > 0;
	}

}
