package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A Servlet superclass with methods common to all servlets for this application.
 * @author srollins
 *
 */
public class BaseServlet extends HttpServlet {
	
	//protected static final String DATA = "data";
	//protected static final String UUID = "uuid";
	//protected static final String ITEM = "item";
	protected static final String FULLNAME = "fullname";
	protected static final String USERNAME = "username";
	protected static final String PASSWORD = "password";
	protected static final String VERIFYPASS = "vpassword";
	protected static final String VERIFIEDUSERNAME = "Login Successful";
	protected static final String DELETE = "delete";
	protected static final String STATUS = "status";
	protected static final String ERROR = "error";
	protected static final String NOT_LOGGED_IN = "not_logged_in";
	protected static final String UNEXISTS = "username exists";
	protected static final String MIXMATCH = "mixmatch";

	protected PrintWriter prepareResponse(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		return response.getWriter();
	}
	
	protected String responseFormatSearch() {
		
		String responseFormat = "<html" + 
										"<head><title>Song Finder</title></head>" + 
										"<h1><center> Song Finder</center></h1>" +
										"<hr>" +
										"<body>" +
										"<center> Welcome to Song Finder! Search for an artist, song title, or tag and we will give you a list of similar songs you might like. Enjoy!</center><br/>" +
										"<form action=\"songs\" method=\"post\">" +
										"<center>Search Type: " +
										"<select name =\"type\">" +
										"<option value=\"artist\">artist</option>" +
										"<option value=\"title\">title</option>" +
										"<option value=\"tag\">tag</option>" + 
										"</select>" +
										"Query: " +
										"<input type=\"text\" name=\"query\">" +
										"<input type=\"submit\" value=\"Submit\"></center><br/>" +
										"</form>" +
										"</body>" +
										"</html>";
		
		return responseFormat;
	}
	
	protected String footer() {
		
		String footer =  "<html>" +
				"<center> <a href=\"/logout\" >Logout</a> </center>" +
				"<center> <a href=\"/search\" >Go back to Search</a> </center>" +
				"</html>";
		
		return footer;
	}
	
	protected String footerSearch() {
		
		String footer = "<html>" +
				"<center> <a href=\"/logout\" >Logout</a> </center>" +
				"<center> <a href=\"/favsList\" >Favs List</a> </center>" +
				"</html>";
		
		return footer;
	}
	
	
	
	protected String header() {
		
		String header =	"<html" + 
				"<head><title>Song Finder</title></head>" + 
				"<h1><center> Song Finder</center></h1>" +
				"<hr>" +
				"<body>" +
				"<center> Welcome to Song Finder! Search for an artist, song title, or tag and we will give you a list of similar songs you might like. Enjoy!</center><br/>" +
				"<hr>" +
				"</body>" +
				"</html>";
		
		return header;
	}
	
	protected String getParameterValue(HttpServletRequest request, String key) {
		return request.getParameter(key);
	}
	
	
	protected boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		if(session.getAttribute(USERNAME) == null) {
			return false;
		}
		else {
			return true;
		}
			
	}
	
	

}
