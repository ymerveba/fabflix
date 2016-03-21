package handlers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;
import domain.Genre;
import domain.Movies;
import service.UserFacade;

public class SearchMovie implements HttpRequestHandler {

	@SuppressWarnings("unchecked")
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		//Logger logger=Logger.getRootLogger();
		
		/* if no element, redirect to blank MovieList page */
		if (!hasElement(request, "moviesListby")) {
			try {
				request.getRequestDispatcher("JSP/MovieList.jsp").forward(request,response);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		HttpSession session = request.getSession(true);
		List search = new ArrayList();
		List<Movies> movieList = null;
		int currentPage = 1;
		int moviesPerPage = 20;
		String sortType = "titleASC"; // titleASC, titleDEC, yearASC, yearDEC
		String a=request.getParameter("act");
		String url=request.getParameter("pic_url");
		String moviesListby = request.getParameter("moviesListby");
		if (moviesListby.equalsIgnoreCase("genres")){
			if (!request.getParameter("id").equals(request.getParameter("lastID"))) {
				search.add(0,(request.getParameter("id")));
				movieList = UserFacade.MoviesList(search, moviesListby);
			    request.setAttribute("moviesListby","genres");
			    request.setAttribute("genreName",request.getParameter("Name"));
			    session.setAttribute("movielist", movieList); 
			    request.setAttribute("movielist", movieList);
				request.setAttribute("lastID", request.getParameter("id"));
		    }
		}
		else if(moviesListby.equalsIgnoreCase("Search")){
			request.setAttribute("moviesListby","Search");
			search.add(0,(request.getParameter("moviesList")));
			movieList = UserFacade.MoviesList(search, moviesListby);
	    	session.setAttribute("movielist", movieList); /* cache to avoid searching through database for movie id */ 
			request.setAttribute("movielist", movieList);
			//String a=request.getParameter("act");
			if(null!=a&&a.equalsIgnoreCase("show")){
				
				request.setAttribute("PicUrl",url);
				try {
					request.getRequestDispatcher("JSP/StarDetails.jsp").forward(request,response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		else if (moviesListby.equalsIgnoreCase("Advanced")){
			request.setAttribute("moviesListby","Advanced");
	    	search.add(0,(request.getParameter("title")));
	    	search.add(1,(request.getParameter("year")));
	    	search.add(2,(request.getParameter("director")));
	    	search.add(3,(request.getParameter("firstName")));
	    	search.add(4,(request.getParameter("lastName")));
	    	
	    	/* TODO: DOES NOT CACHE! movieList is recalculated each time */
	    	movieList = UserFacade.MoviesList(search, moviesListby);
	    	session.setAttribute("movielist", movieList); /* cache to avoid searching through database for movie id */ 
			request.setAttribute("movielist", movieList);
	    }
	    
		else if (moviesListby.equalsIgnoreCase("Title")) {
			request.setAttribute("moviesListby","Title");
			search.add(0,(request.getParameter("title")));
			if (!request.getParameter("title").equals(request.getParameter("lastTitle"))) {
				movieList = UserFacade.MoviesList(search, moviesListby);
		    	session.setAttribute("movielist", movieList);
				request.setAttribute("movielist", movieList);
				request.setAttribute("lastTitle", request.getParameter("title"));
			}
		}
		
		/*
		for (Movies m : movieList) {
			m.stars = UserFacade.StarList(m);
			m.genres = UserFacade.GenreList(m);
		}
		*/
		
		if (hasElement(request, "currentPage")) {
			try {
				currentPage = Integer.parseInt((String) request.getParameter("currentPage"));
			} catch (Exception e) { }
		}
		if (hasElement(request, "moviesPerPage")) {
			try {
				moviesPerPage = Integer.parseInt((String) request.getParameter("moviesPerPage"));
			} catch (Exception e) { }
		}
		if (hasElement(request, "sort")) {
			try {
				String srt = (String) request.getParameter("sort");
				switch (srt) {
				case "titleASC":System.out.println("ArrangeMoviesAscendingOrder");
				case "titleDEC":
				case "yearASC":
				case "yearDEC":
					sortType = srt;
					break;
				default:
					break;
				}
			} catch (Exception e) { }
		}
		
		movieList = (List<Movies>) session.getAttribute("movielist");
		
		if (moviesListby.equalsIgnoreCase("genres")){
			/* set url for use later */
			request.setAttribute(
					"sortByTitleASC", "Search.cd" +
							"?id=" + request.getParameter("id") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + "titleASC");
			request.setAttribute(
					"sortByTitleDEC", "Search.cd" +
							"?id=" + request.getParameter("id") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + "titleDEC");
			request.setAttribute(
					"sortByYearASC", "Search.cd" +
							"?id=" + request.getParameter("id") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + "yearASC");
			request.setAttribute(
					"sortByYearDEC", "Search.cd" +
							"?id=" + request.getParameter("id") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + "yearDEC");
			request.setAttribute(
					"show20URL", "Search.cd" +
							"?id=" + request.getParameter("id") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + 20 +
							"&sort=" + sortType);
			request.setAttribute(
					"show50URL", "Search.cd" +
							"?id=" + request.getParameter("id") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + 50 +
							"&sort=" + sortType);
			request.setAttribute(
					"show100URL", "Search.cd" +
							"?id=" + request.getParameter("id") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + 100 +
							"&sort=" + sortType);
			request.setAttribute(
					"prevURL", "Search.cd" +
							"?id=" + request.getParameter("id") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + (Math.max(currentPage - 1, 1)) +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + sortType);
			request.setAttribute(
					"nextURL", "Search.cd" +
							"?id=" + request.getParameter("id") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + (Math.min(currentPage + 1,
									(int) (movieList.size()/moviesPerPage) + 1)) +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + sortType);
		}
		
		else if (moviesListby.equalsIgnoreCase("Advanced")){
			
			
		}
		
		else if (moviesListby.equalsIgnoreCase("Title")){
			/* set url for use later */
			request.setAttribute(
					"sortByTitleASC", "Search.cd" +
							"?title=" + request.getParameter("title") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + "titleASC");
			request.setAttribute(
					"sortByTitleDEC", "Search.cd" +
							"?title=" + request.getParameter("title") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + "titleDEC");
			request.setAttribute(
					"sortByYearASC", "Search.cd" +
							"?title=" + request.getParameter("title") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + "yearASC");
			request.setAttribute(
					"sortByYearDEC", "Search.cd" +
							"?title=" + request.getParameter("title") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + "yearDEC");
			request.setAttribute(
					"show20URL", "Search.cd" +
							"?title=" + request.getParameter("title") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + 20 +
							"&sort=" + sortType);
			request.setAttribute(
					"show50URL", "Search.cd" +
							"?title=" + request.getParameter("title") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + 50 +
							"&sort=" + sortType);
			request.setAttribute(
					"show100URL", "Search.cd" +
							"?title=" + request.getParameter("title") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + 1 +
							"&moviesPerPage=" + 100 +
							"&sort=" + sortType);
			request.setAttribute(
					"prevURL", "Search.cd" +
							"?title=" + request.getParameter("title") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + (Math.max(currentPage - 1, 1)) +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + sortType);
			request.setAttribute(
					"nextURL", "Search.cd" +
							"?title=" + request.getParameter("title") +
							"&moviesListby=" + moviesListby +
							"&currentPage=" + (Math.min(currentPage + 1,
									(int) (movieList.size()/moviesPerPage) + 1)) +
							"&moviesPerPage=" + moviesPerPage +
							"&sort=" + sortType);
		}
	    
		switch (sortType) {
			case "titleASC":
				Collections.sort(movieList, new Comparator<Movies>() {
			        public int compare(Movies m1, Movies m2) {
			            return m1.getTitle().compareTo(m2.getTitle());
			        }
				});
				break;
			case "titleDEC":
				Collections.sort(movieList, new Comparator<Movies>() {
			        public int compare(Movies m1, Movies m2) {
			            return m2.getTitle().compareTo(m1.getTitle());
			        }
				});
				break;
			case "yearASC":
				Collections.sort(movieList, new Comparator<Movies>() {
			        public int compare(Movies m1, Movies m2) {
			            return m1.getYear().compareTo(m2.getYear());
			        }
				});
				break;
			case "yearDEC":
				Collections.sort(movieList, new Comparator<Movies>() {
			        public int compare(Movies m1, Movies m2) {
			            return m2.getYear().compareTo(m1.getYear());
			        }
				});
				break;
		}
		
		try {
			session.setAttribute("movielist", movieList);
			request.setAttribute("movielist", movieList);
			/* Currently displayed movies */
			List<Movies> showing = movieList.subList((currentPage-1)*moviesPerPage, Math.min(movieList.size(), currentPage*moviesPerPage));
			session.setAttribute("showing", showing); /* cache to avoid searching through database for movie id */ 
			request.setAttribute("showing", showing);
			request.getRequestDispatcher("JSP/MovieList.jsp").forward(request,response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean hasElement(HttpServletRequest request, String element) {
		Enumeration<String> parameterNames = request.getParameterNames();
    	while (parameterNames.hasMoreElements()) {
    		if (element.equals(parameterNames.nextElement())) {
    			return true;
    		}
    	}
    	return false;
	}
}