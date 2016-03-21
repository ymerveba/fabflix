package handlers;

import java.io.IOException;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

import service.UserFacade;

public class ValidateUserInfo implements HttpRequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Logger logger=Logger.getRootLogger();
		
		try {
			HttpSession session=request.getSession(true);
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String creditCard = request.getParameter("creditCard");
			String expiration = request.getParameter("expiration");
			System.out.println(expiration);
			java.util.Date temp = null;
			try {
				temp = new SimpleDateFormat("yyyy-MM-dd")
				.parse(expiration);
				 System.out.println(temp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			    java.sql.Date sqlDate = new java.sql.Date(temp.getTime());
   
			/*try {
				java.util.Date date=expiration;
	            Date d= new Date(date.getTime());
			} catch (Exception e) {
				
			}
			*/
			Boolean validateUserInfo;
			validateUserInfo = UserFacade.validateUserInfo(firstName, lastName, creditCard, sqlDate);
			if (validateUserInfo) {
				System.out.println("Valid user");
				session.removeAttribute("shoppingCart");
				logger.debug(firstName);
				logger.debug(lastName);
				logger.debug(creditCard);
				logger.debug(expiration);
				request.getRequestDispatcher("JSP/Success.jsp").forward(request,response);
			}
			else {
				request.getRequestDispatcher("JSP/Failure.jsp").forward(request,response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}		
}