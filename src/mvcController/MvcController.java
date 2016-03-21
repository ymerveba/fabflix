package mvcController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import service.UserException;

/**
 * Servlet implementation class MvcController
 */
@WebServlet("/MvcController")
public class MvcController extends HttpServlet {

	static final long serialVersionUID = 1L;
	   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
   
    @SuppressWarnings("rawtypes")
	private Map handlers;

	public MvcController() {
		super();
	}  

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		String mvcProps = getServletContext().getRealPath(
				"/WEB-INF/mvc.properties");
		try {
			this.handlers = MvcUtil.buildHandlers(mvcProps);
		} catch (MvcException e) {
			throw new ServletException(
					"Unable to configure controller servlet", e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final Logger logger = Logger.getLogger(MvcController.class);

		String url = request.getServletPath();
		System.out.println(url+"URL");
		String key = url.substring(1, url.lastIndexOf('.'));
		System.out.println("check="+key);
		System.out.println(url.lastIndexOf('.'));
		
		HttpRequestHandler handler = (HttpRequestHandler) handlers.get(key);
		
		if (handler != null) {
			try {
				HttpSession session=request.getSession(true);
				System.out.println("USER: " + session.getAttribute("user"));
				if (session.getAttribute("user") == null && !url.equals("/Signin.cd")) {
					request.getRequestDispatcher("index.jsp").forward(request,response);
				}
				else {
					handler.handle(request, response);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else {
			logger.debug("No matching handler");
			throw new ServletException("No matching handler");
		}
	}

}