package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import Dbfw.ResultMapper;
import domain.Genre;
import domain.Movies;
import domain.Star;

public class SQLMapper {
	public static final String validateUser = "select * from customers where email=? and password=?";
	public static final String validateUserInfo = "select * from creditcards where first_name=? and last_name=? and id=? and expiration=?";
	public static final String listGenresQuery = "SELECT * FROM moviedb.genres";
	public static final String listMoviesByGenre = "select * from genres_in_movies natural join movies where movie_id=id and genre_id=?";
	public static final String AdvancedSearch = "select * from movies as a,stars_in_movies as b,stars as c where (a.id=b.movie_id and b.star_id=c.id)AND( title like ? OR year= ? or director like ? or first_name LIKE ? or last_name like ? or c.id= ?) group by title;";
	public static final String BrowseTitle = "select * from movies as a,stars_in_movies as b,stars as c where (a.id=b.movie_id and b.star_id=c.id)AND( title like ?)group by title;";
	public static final String getStars = "SELECT * FROM moviedb.stars NATURAL JOIN(SELECT DISTINCT star_id AS id FROM moviedb.stars_in_movies WHERE movie_id= ? ) As a;";
	public static final String getGenre = "SELECT * FROM moviedb.genres NATURAL JOIN(SELECT DISTINCT genre_id As id FROM moviedb.genres_in_movies WHERE movie_id= ? ) As a;";
	public static final String listAllMovies = "select * from movies as a,stars_in_movies as b,stars as c where (a.id=b.movie_id and b.star_id=c.id)";
	public static final String Moviedetail = "SELECT * FROM movies as a,stars_in_movies as b,stars as c,genres_in_movies as d,genres as e where a.id=? and a.id=b.movie_id and b.star_id=c.id and  d.movie_id=a.id and e.id=d.genre_id group by b.star_id";
	public static final String getStarsMovies = "SELECT * FROM moviedb.movies NATURAL JOIN(SELECT DISTINCT movie_id AS id FROM moviedb.stars_in_movies WHERE star_id= ? ) As a;";
	
	
	public static final ResultMapper DetailsOfMovie = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {
			Movies m = null;
			int m_id = rs.getInt(1);
			int s_id = rs.getInt(7);
			String m_name = rs.getString(2);
			String year = rs.getString(3);
			String director = rs.getString(4);
			String b_url = rs.getString(5);
			String t_url = rs.getString(6);
			String star_fname = rs.getString(10);
			String star_lname = rs.getString(11);
			String star_dob = rs.getString(12);
			String Star_photo_url = rs.getString(13);
			String genre_of_movie = rs.getString(17);
			m = new Movies(s_id, m_id, m_name, year, director, b_url, t_url,
					star_fname, star_lname, star_dob, Star_photo_url,
					genre_of_movie);
			return m;
		}
	};
	public static final ResultMapper UserMapper = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {
			int cid = rs.getInt(1);
			return cid;
		}
	};

	public static final ResultMapper UserInfoMapper = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {
			String cid = rs.getString(1);
			return cid;
		}
	};

	public static final ResultMapper GenreList = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {

			Genre g = null;
			int g_id = rs.getInt(1);
			String g_name = rs.getString(2);
			g = new Genre(g_id, g_name);
			return g;
		}
	};
	public static ResultMapper MovieByGenre = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {
			Movies m = null;
			int m_id = rs.getInt(2);
			String m_name = rs.getString(4);
			String year = rs.getString(5);
			String director = rs.getString(6);
			String b_url = rs.getString(7);
			String t_url = rs.getString(8);

			m = new Movies(m_id, m_name, year, director, b_url, t_url);

			return m;
		}
	};
	public static ResultMapper MovieByAdvancedSearch = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {
			Movies m = null;
			int m_id = rs.getInt(1);
			String m_name = rs.getString(2);
			String year = rs.getString(3);
			String director = rs.getString(4);
			String b_url = rs.getString(5);
			String t_url = rs.getString(6);
			String star_fname = rs.getString(10);
			String star_lname = rs.getString(11);
			System.out.println("Inside resultmapper");
			m = new Movies(m_id, m_name, year, director, b_url, t_url,
					star_fname, star_lname);

			return m;
		}
	};
	public static ResultMapper MovieBy = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {
			Movies m = null;
			int m_id = rs.getInt(1);
			String m_name = rs.getString(2);
			String year = rs.getString(3);
			String director = rs.getString(4);
			String b_url = rs.getString(5);
			String t_url = rs.getString(6);
			
			System.out.println("Inside resultmapper");
			m = new Movies(m_id, m_name, year, director, b_url, t_url,
					"", "");

			return m;
		}
	};
	public static final ResultMapper StarsOfMovie = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {
			int s_id = rs.getInt(1);
			String f_name = rs.getString(2);
			String l_name = rs.getString(3);
			String dob = rs.getString(4);
			String photoUrl = rs.getString(5);
			Star s = new Star(s_id, f_name, l_name, dob, photoUrl);
			return s;
		}
	};

	public static final ResultMapper GenresOfMovie = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {
			int g_id = rs.getInt(1);
			String g_name = rs.getString(2);
			Genre g = new Genre(g_id, g_name);
			return g;
		}
	};
	public static ResultMapper MovieList = new ResultMapper() {
		public Object mapRow(ResultSet rs) throws SQLException {
			Movies m = null;
			int m_id = rs.getInt(1);
			String m_name = rs.getString(2);
			String year = rs.getString(3);
			String director = rs.getString(4);
			String b_url = rs.getString(5);
			String t_url = rs.getString(6);
			String star_fname = rs.getString(10);
			String star_lname = rs.getString(11);

			m = new Movies(m_id, m_name, year, director, b_url, t_url,
					star_fname, star_lname);

			return m;
		}
	};
}