package handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;
import service.UserFacade;
import domain.Movies;
import domain.Star;


public class MovieDetail implements HttpRequestHandler {

	
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		List<Movies> movieList = null;
		
		
		
		String moviesListby = request.getParameter("act");
		
		if(null!=moviesListby&&!moviesListby.equals("") &&moviesListby.equalsIgnoreCase("show")){
			request.setAttribute("moviesListby","Search");
			String id=(request.getParameter("id"));
			
			Star star=UserFacade.StarListMovies(id);
	    	
			//String a=request.getParameter("act");
			
			  request.setAttribute("star", star);
			   
			   
			   
			  
				try {
					request.getRequestDispatcher("JSP/StarDetails.jsp").forward(request,response);
				return;
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		else{
		String Movie_id=request.getParameter("id");
		List<Movies> details_Movie=UserFacade.MovieDetail(Movie_id);
		
		Movies m=details_Movie.get(0);
		/*for(int i=0;i<details_Movie.size();i++)
		{
			stars[i]=details_Movie.get(i).getStar_First_name()+details_Movie.get(i).getStar_last_name();
		System.out.println(stars[i]);
		}
			System.out.println(m);*/
		String movieListBy=request.getParameter("moviesListby");
		
		if(null!=movieListBy&&!movieListBy.equals("")&&movieListBy.equalsIgnoreCase("genres")){
			m.setGenre_of_movie(request.getParameter("genreName"));
		}
		
		request.setAttribute("star", details_Movie);
		request.setAttribute("details", m);
		try {
			request.getRequestDispatcher("JSP/MovieDetails.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}

}