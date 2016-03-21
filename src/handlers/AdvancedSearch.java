package handlers;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

public class AdvancedSearch implements HttpRequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		Logger logger = Logger.getRootLogger();
		try {
			request.getRequestDispatcher("JSP/AdvancedSearch.jsp").forward(
					request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
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