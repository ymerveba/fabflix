package handlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

import service.UserFacade;
import domain.Genre;

public class GoHome implements HttpRequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		Logger logger=Logger.getRootLogger();
		try {
			HttpSession session=request.getSession(true);
			if (session.getAttribute("genre") == null) {
				List<Genre> genre=UserFacade.listOfGenre();
				session.setAttribute("genre", genre);
			}
			if (session.getAttribute("browseTitles") == null) {
				List<String> browseTitles=UserFacade.listOfBrowseTitles();
				session.setAttribute("browseTitles", browseTitles);
			}
			request.getRequestDispatcher("JSP/MainMenu.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}		
}