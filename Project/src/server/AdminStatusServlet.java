package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBHelper;

public class AdminStatusServlet extends BaseServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		if(!isLoggedIn(request, response, session)) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		
		String header = header();
		String footer = footerSearch();
		String admin = "<html>" + 
				"<body>" +
				"<center>What is the secret password?<br/>" +
				"<form action=\"admin\" method=\"post\">" +
				"<input type=\"text\" name=\"secretpassword\">" +
				"<input type=\"submit\" value=\"Submit\"></center><br/>" +
				"</form>" +
				"</body>" +
				"</html>";
		
		String finalResponse = header + admin + footer;
		PrintWriter writer = prepareResponse(response);
		
		writer.println(finalResponse);	
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute(USERNAME);
		
		String admin = request.getParameter("secretpassword");
		
		if (admin.equals(SECRETPASSWORD)) {
			DBHelper.addAdmin(username);
			response.sendRedirect(response.encodeRedirectURL("/search?" + STATUS + "=" + ADMIN));
		} else {
			response.sendRedirect(response.encodeRedirectURL("/search?" + STATUS + "=" + ADMINFAIL));
		}
		
		
		
		
		
	}

}
