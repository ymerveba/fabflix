package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import Dbfw.DBHelper;
import Dbfw.ParamMapper;
import dbcon.ConnectionHolder;
import dbcon.DBConnectionException;
import domain.Genre;
import domain.Movies;
import domain.Star;

public class MoviesListDAO {
	public static Connection connection;

	public static void init() {
		ConnectionHolder ch;
		try {
			ch = ConnectionHolder.getInstance();
			connection = ch.getConnection();
		} catch (DBConnectionException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Movies> listMovies(final List<?> search, String listBy) {
		List<Movies> movies = null;
		if (connection == null) {
			init();
		}

		try {
			if (listBy.equalsIgnoreCase("genres")) {
				final ParamMapper moviesByGenre = new ParamMapper() {
					public void mapparam(PreparedStatement ps)
							throws SQLException {
						ps.setString(1, (String) search.get(0));
					}
				};

				movies = DBHelper.executeSelect(connection,
						SQLMapper.listMoviesByGenre, SQLMapper.MovieByGenre,
						moviesByGenre);
			} else if (listBy.equals("Search")) {
				final ParamMapper AdvancedSearchMovies = new ParamMapper() {
					public void mapparam(PreparedStatement ps)
							throws SQLException {
						ps.setString(1, (String) search.get(0) + "%");
						ps.setString(2, (String) search.get(0));
						ps.setString(3, (String) search.get(0) + "%");
						ps.setString(4, (String) search.get(0) + "%");
						ps.setString(5, (String) search.get(0) + "%");
						ps.setString(6, (String) search.get(0));
					}
				};

				movies = DBHelper.executeSelect(connection,
						SQLMapper.AdvancedSearch,
						SQLMapper.MovieByAdvancedSearch, AdvancedSearchMovies);
			}

			else if (listBy.equalsIgnoreCase("Advanced")) {
				final ParamMapper AdvancedSearchMovies = new ParamMapper() {
					public void mapparam(PreparedStatement ps)
							throws SQLException {
						final String item1 = (String) search.get(0);
						final String item2 = (String) search.get(2);
						final String item3 = (String) search.get(3);
						final String item4 = (String) search.get(4);
						if (null != item1 && (!item1.isEmpty())
								&& !item1.equals(""))
							ps.setString(1, (String) search.get(0) + "%");
						else
							ps.setString(1, (String) search.get(0));
						ps.setString(2, (String) search.get(1));
						if (null != item1 && (!item2.isEmpty())
								&& !item2.equals(""))
							ps.setString(3, (String) search.get(2) + "%");
						else
							ps.setString(3, (String) search.get(2));
						if (null != item1 && (!item3.isEmpty())
								&& (!item3.equals("")))
							ps.setString(4, (String) search.get(3) + "%");
						else
							ps.setString(4, (String) search.get(3));
						if (null != item1 && (!item4.isEmpty())
								&& (!item4.equals("")))
							ps.setString(5, (String) search.get(4) + "%");
						else
							ps.setString(5, (String) search.get(4));
						ps.setString(6, "");
					}
				};

				movies = DBHelper.executeSelect(connection,
						SQLMapper.AdvancedSearch,
						SQLMapper.MovieByAdvancedSearch, AdvancedSearchMovies);
			}

			else if (listBy.equalsIgnoreCase("Title")) {
				final ParamMapper movieByTitle = new ParamMapper() {
					public void mapparam(PreparedStatement ps)
							throws SQLException {
						ps.setString(1, (String) search.get(0) + "%");
					}
				};

				movies = DBHelper.executeSelect(connection,
						SQLMapper.BrowseTitle, SQLMapper.MovieByAdvancedSearch,
						movieByTitle);
			}
		} catch (DBConnectionException e) {
			e.printStackTrace();
		}

		return movies;
	}

	public static List<Star> listStars(final Movies m) {
		List<Star> stars = null;
		if (connection == null) {
			init();
		}
		try {
			final ParamMapper star = new ParamMapper() {
				public void mapparam(PreparedStatement ps) throws SQLException {
					ps.setString(1, Integer.toString(m.getMovieId()));
				}
			};
			stars = DBHelper.executeSelect(connection, SQLMapper.getStars,
					SQLMapper.StarsOfMovie, star);
		} catch (DBConnectionException e) {
			e.printStackTrace();
		}
		return stars;
	}

	public static List<Genre> listGenres(final Movies m) {
		List<Genre> genre = null;
		if (connection == null) {
			init();
		}

		try {

			final ParamMapper star = new ParamMapper() {
				public void mapparam(PreparedStatement ps) throws SQLException {
					ps.setLong(1, m.getMovieId());
				}
			};
			genre = DBHelper.executeSelect(connection, SQLMapper.getGenre,
					SQLMapper.GenresOfMovie, star);
		} catch (DBConnectionException e) {
			e.printStackTrace();
		}
		return genre;
	}

	public static List<Movies> ListMoviesALL() {
		JSONArray arrayObj = new JSONArray();
		List<Movies> movies = null;
		if (connection == null) {
			init();
		}

		try {
			movies = DBHelper.executeSelect(connection,
					SQLMapper.listAllMovies, SQLMapper.MovieList);
		} catch (DBConnectionException e) {

		}
		return movies;
	}

	@SuppressWarnings("unchecked")
	public static List<Movies> MovieDetail(final String movie_id) {
		// TODO Auto-generated method stub
		List<Movies> details = null;
		if (connection == null) {
			init();
		}
		try {
			final ParamMapper MovieDetails = new ParamMapper() {
				public void mapparam(PreparedStatement ps) throws SQLException {
					ps.setString(1, movie_id);
				}
			};
			details = DBHelper.executeSelect(connection, SQLMapper.Moviedetail,
					SQLMapper.DetailsOfMovie, MovieDetails);
		} catch (DBConnectionException e) {
			e.printStackTrace();
		}
		return details;

	}

	public static String[] list_database(List<Movies> movies) {
		String[] z = new String[movies.size() * 8];
		for (int j = 0, i = 0; i < z.length; i = i + 4) {
			if (j < movies.size()) {
				if (null != movies.get(j).getTitle())
					z[i] = movies.get(j).getTitle();
				if (null != movies.get(j).getDirector())
					z[i + 1] = movies.get(j).getDirector();
				if (null != movies.get(j).getStar_First_name())
					z[i + 2] = movies.get(j).getStar_First_name();
				if (null != movies.get(j).getStar_last_name())
					z[i + 3] = movies.get(j).getStar_last_name();

				j++;
			} else {
				break;
			}
		}
		// int end = z.length;
		Set<String> alreadyPresent = new HashSet<String>();
		String[] whitelist = new String[0];

		for (String nextElem : z) {
			if (!alreadyPresent.contains(nextElem)) {
				whitelist = Arrays.copyOf(whitelist, whitelist.length + 1);
				whitelist[whitelist.length - 1] = nextElem;
				alreadyPresent.add(nextElem);
			}
		}
		return whitelist;
	}

	@SuppressWarnings("unchecked")
	public static Star getArtist(final String id) {
		final String getStar="Select * from moviedb.stars where id="+id;
		List<Star> a=new ArrayList<Star>();
		List<Movies> m=new ArrayList<Movies>();
		final ParamMapper Movies = new ParamMapper() {
			public void mapparam(PreparedStatement ps)
					throws SQLException {
				ps.setString(1, id);
				
			}
		};

		try {
			m = DBHelper.executeSelect(connection,
					SQLMapper.getStarsMovies,
					SQLMapper.MovieBy, Movies);
			a = DBHelper.executeSelect(connection,
					getStar,
					SQLMapper.StarsOfMovie);
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	a.get(0).setMovies(m);
		
		return a.get(0);
		
		
		
		
	}

}
