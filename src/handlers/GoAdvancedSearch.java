package handlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

public class GoAdvancedSearch implements HttpRequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		Logger logger = Logger.getRootLogger();
		try {
			request.getRequestDispatcher("JSP/AdvancedSearch.jsp").forward(
					request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}