package handlers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

import domain.Movies;
import domain.ShoppingCart;

public class AddToCart implements HttpRequestHandler {

	@SuppressWarnings("unchecked")
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		Logger logger = Logger.getRootLogger();

		int id = 0;
		try {
			if (hasElement(request, "id"))
				id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			// pass
		}

		try {
			HttpSession session = request.getSession(true);
			ShoppingCart shoppingCart = (ShoppingCart) session
					.getAttribute("shoppingCart");
			List<Movies> movieList = (List<Movies>) session
					.getAttribute("movielist");
			Iterator<Movies> it = movieList.iterator();
			Movies m = null;
			Movies temp;
			while (it.hasNext()) {
				temp = it.next();
				if (temp.getMovieId() == id) {
					m = temp;
					break;
				}
			}

			if (m != null) {
				if (shoppingCart.containsKey(m)) {
					shoppingCart.put(m, shoppingCart.get(m) + 1);
				} else {
					shoppingCart.put(m, 1);
				}
			}
			request.getRequestDispatcher("JSP/ShoppingCart.jsp").forward(
					request, response);
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