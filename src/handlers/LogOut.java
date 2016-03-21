package handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

public class LogOut implements HttpRequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		Logger logger=Logger.getRootLogger();
		
		try {
			HttpSession session=request.getSession(true);
			session.removeAttribute("user");
			session.removeAttribute("password");
			session.removeAttribute("shoppingCart");
			request.getRequestDispatcher("index.jsp").forward(request,response);
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}		
}