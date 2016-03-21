package handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserException;
import mvcController.HttpRequestHandler;
import net.sf.json.JSONArray;

public class SearchPredictor implements HttpRequestHandler {

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

			out = response.getWriter();

			String[] StringObj = (String[]) session.getAttribute("arrayObj");
			String query = request.getParameter("term");
			query.toLowerCase();
			JSONArray arrayObj = new JSONArray();
			for (int i = 0; i < StringObj.length; i++) {
				if (null != StringObj[i]) {
					String list = StringObj[i].toLowerCase();
					if ((query.length() == 1 && list.startsWith(query))
							|| (query.length() > 1 && list.indexOf(query) > -1)) {
						arrayObj.add(StringObj[i]);
					}
				}
			}
			System.out.println("Object" + arrayObj);
			out.println(arrayObj.toString());
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	};

	public SearchPredictor() {
		super();
	}
}
