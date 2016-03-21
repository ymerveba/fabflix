package handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

import domain.ShoppingCart;

public class Success implements HttpRequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		Logger logger=Logger.getRootLogger();
		
		try {
			HttpSession session=request.getSession(true);
			request.getRequestDispatcher("JSP/Success.jsp").forward(request,response);
			/* TODO: Delete all items from cart */
			session.setAttribute("shoppingCart", new ShoppingCart());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}		
}