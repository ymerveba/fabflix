package handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Movies;
import domain.Star;
import dao.MoviesListDAO;

import service.UserException;
import mvcController.HttpRequestHandler;
import net.sf.json.JSONArray;

public class MoviePopup implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException, IOException,
			NumberFormatException, UserException {
		HttpSession session = request.getSession(true);
		PrintWriter out;
		try {
			response.setContentType("text/html");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");
			
			System.out.println("Here");
			
			out = response.getWriter();
			JSONArray arrayObj = new JSONArray();
			
			response.setContentType("text/html");
			
			List<Movies> movieList = (List<Movies>) session.getAttribute("movielist");
			Movies movie = new Movies(0, 0, null, null, null, null, null, null, null, null, null, null);
			
			for (Movies m: movieList) {
				if (m.getMovieId() == Integer.parseInt(request.getParameter("id"))) {
					movie = m;
				}
			}
			
			String result = "";
			String stars = "";
			
			result += movie.getTitle() + ";"; // s[0]
			for (Star s: MoviesListDAO.listStars(movie)) {
				stars += "<a href=\"https://www.google.com/#q=" + s.getName() + "\"</a>" + s.getName() + ", "; // s[1]
			}
			// get rid of trailing comma
			if (stars.length() > 0) {
				result += stars.substring(0, stars.length()-2);
			}
			result += ";";
			
			result += movie.getYear() + ";"; // s[2]
			
			result += movie.getDirector() + ";"; // s[3]
			
			result += movie.getTrailer_url() + ";"; // s[4]
			
			result += movie.getMovieId() + ";";  // s[5]
			
			result += movie.getStar_photo_url(); // s[6]
			
			out.println(result);
			
			out.close();
						
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	};

	public MoviePopup() {
		super();
	}
}
