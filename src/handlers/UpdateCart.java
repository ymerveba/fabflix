package handlers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcController.HttpRequestHandler;

import org.apache.log4j.Logger;

import domain.Movies;
import domain.ShoppingCart;


public class UpdateCart implements HttpRequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) {
		Logger logger=Logger.getRootLogger();
		
		//HttpSession session=request.getSession(true);	
		int id = 0;
		try {
			if (hasElement(request, "id")) id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			// pass
		}
	    try {
	    	HttpSession session=request.getSession(true);
    		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
    		if(null!=shoppingCart){
	    	Iterator<Entry<Movies, Integer>> it = shoppingCart.entrySet().iterator();
	    	Movies m = null;
	    	Map.Entry<Movies, Integer> pairs;
	    	while (it.hasNext()) {
	            pairs = (Map.Entry<Movies, Integer>) it.next();
	            if (pairs.getKey().getMovieId() == id) {
	            	m = pairs.getKey();
	            	break;
	            }
	        }
	    	
	    	if (hasElement(request, "update") && request.getParameter("update").equals("true")) {
    			it = shoppingCart.entrySet().iterator();
    			String s;
    			while (it.hasNext()) {
    	            pairs = (Map.Entry<Movies, Integer>) it.next();
    	            s = "z"+pairs.getKey().getMovieId();
    	            System.out.println(s);
    	            if (hasElement(request, s)) {
    	            	try {
    	    				id = Integer.parseInt(request.getParameter(s));
    	    				System.out.println(id);
    	    			} catch (Exception e) {
    	    				
    	    			}
    	    			if (id == 0) { shoppingCart.remove(pairs.getKey()); }
    	    			else { shoppingCart.put(pairs.getKey(), id); }
    	            }
    	        }
    		}
	    	if (m != null) {
	    		if (hasElement(request, "remove") && request.getParameter("remove").equals("true")) {
	    			shoppingCart.remove(m);
	    		}
	    	}
	    	System.out.println(shoppingCart.quantity);}
	    	request.getRequestDispatcher("JSP/ShoppingCart.jsp").forward(request,response);
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