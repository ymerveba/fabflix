package handlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

import dao.MoviesListDAO;
import domain.Genre;
import domain.Movies;
import domain.ShoppingCart;
import service.UserFacade;

public class ValidateUser implements HttpRequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Logger logger=Logger.getRootLogger();
		
		try {
			HttpSession session=request.getSession(true);
			session.removeAttribute("user");
			session.removeAttribute("password");
			session.removeAttribute("shoppingCart");
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			
			System.out.println("User:    " + user);
			System.out.println("Password: " + password);
		
			Boolean validateUser;
			validateUser = UserFacade.validateUser(user, password);
			//validateUser = true;
			user = "abc@email.com";
			password = "abc";
			if (validateUser) {
				System.out.println("Valid user");
				logger.debug(user);
				logger.debug(password);
				List<Movies> MoviesALL=UserFacade.searchMoviesList();
				String[] arrayObj=MoviesListDAO.list_database(MoviesALL);
				session.setAttribute("arrayObj", arrayObj);
				session.setAttribute("moviesAll",MoviesALL);
				session.setAttribute("user", user);
				session.setAttribute("password", password);
				session.setAttribute("shoppingCart", new ShoppingCart());
				if (session.getAttribute("genre") == null) {
					List<Genre> genre=UserFacade.listOfGenre();
					session.setAttribute("genre", genre);
				}
				if (session.getAttribute("browseTitles") == null) {
					List<String> browseTitles=UserFacade.listOfBrowseTitles();
					session.setAttribute("browseTitles", browseTitles);
				}
				request.getRequestDispatcher("JSP/MainMenu.jsp").forward(request,response);
			}
			else {
				session.setAttribute("error_msg","**Please enter valid user and password**");
				request.getRequestDispatcher("index.jsp").forward(request,response);
			}
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}		
}