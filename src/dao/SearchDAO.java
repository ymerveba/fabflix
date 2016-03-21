package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Dbfw.DBHelper;
import dbcon.ConnectionHolder;
import dbcon.DBConnectionException;
import domain.Genre;

public class SearchDAO {
	@SuppressWarnings("unchecked")
	public static List<Genre> listGenre() {
		Connection con = null;
		List<Genre> genre = null;
		ConnectionHolder ch;
		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();
			genre = DBHelper.executeSelect(con, SQLMapper.listGenresQuery,
					SQLMapper.GenreList);

		} catch (DBConnectionException e) {
			e.printStackTrace();
		}

		return genre;
	}

	public static List<String> listBrowseTitles() {
		List<String> l = new ArrayList<String>(Arrays.asList("0", "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E",
				"F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
		return l;
	}

}
