package handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

import service.UserFacade;

public class RegisterUser implements HttpRequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Logger logger=Logger.getRootLogger();
		
		try {
			HttpSession session=request.getSession(true);
			session.removeAttribute("user");
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			
			System.out.println("User:    " + user);
			System.out.println("Password: " + password);
		
			Boolean validateUser = UserFacade.validateUser(user, password);
			if (validateUser) {
				System.out.println("Valid user");
				logger.debug(user);
				logger.debug(password);
				
				session.setAttribute("user", user);
				session.setAttribute("password", password);
				
				request.getRequestDispatcher("MainMenu.jsp").forward(request,response);
			}
			else {
				request.setAttribute("error_msg","**Please enter valid user and password**  ");
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