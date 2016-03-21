package service;

import java.util.List;

import org.apache.log4j.Logger;

import dao.MoviesListDAO;
import dao.SearchDAO;
import dao.UserDAO;
import dao.UserDaoException;
import domain.Genre;
import domain.Movies;
import domain.Star;

public class UserFacade {
	
	public static boolean validateUser(String email, String password) {
		@SuppressWarnings("unused")
		Logger logger=Logger.getRootLogger();
		boolean validateUser = false;
		try {
			validateUser = UserDAO.validateUser(email, password);
		} catch (UserDaoException e) {
			e.printStackTrace();
		}
		return validateUser;
	}

	public static boolean validateUserInfo(String firstName, String lastName, String creditCard, java.sql.Date date) {
		@SuppressWarnings("unused")
		Logger logger=Logger.getRootLogger();
		boolean validateUser = false;
		try {
			validateUser = UserDAO.validateUserInfo(firstName, lastName, creditCard, date);
		} catch (UserDaoException e) {
			e.printStackTrace();
		}
		return validateUser;
	}
	
	public static List<Genre> listOfGenre() {
		return SearchDAO.listGenre();
	}
	
	public static List<String> listOfBrowseTitles() {
		return SearchDAO.listBrowseTitles();
	}

	public static List<Movies> MoviesList(List<?> search, String moviesListby) {
		// TODO Auto-generated method stub
		return MoviesListDAO.listMovies(search, moviesListby);
	}
	
	public static List<Star> StarList(Movies m) {
		return MoviesListDAO.listStars(m);
	}
	public static List<Movies> searchMoviesList (){
		return MoviesListDAO.ListMoviesALL();
	}
	
	public static List<Genre> GenreList(Movies m) {
		return MoviesListDAO.listGenres(m);
	}

	public static List<Movies> MovieDetail(String movie_id) {
		// TODO Auto-generated method stub
		return MoviesListDAO.MovieDetail(movie_id);
		
	}

	public static Star StarListMovies(String id) {
		return MoviesListDAO.getArtist(id);
		
	}
}