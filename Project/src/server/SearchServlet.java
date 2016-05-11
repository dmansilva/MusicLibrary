package server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.DBHelper;
import threadSafety.ThreadSafeMusicLibrary;

public class SearchServlet extends BaseServlet {


	private static final long serialVersionUID = 1L;

	/**
	 * GET /search returns a web page containing a search box where a student's name may be entered.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(!isLoggedIn(request, response, session)) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		String status = getParameterValue(request, STATUS);
		boolean loginSuccess = status != null && status.equals(VERIFIEDUSERNAME)?true:false;
		String responseHtml = responseFormatSearch();
		String footer = footerSearch();
		String finalResponse = responseHtml + footer;
		PrintWriter writer = prepareResponse(response);
		if(loginSuccess) {
			writer.println("<h3><font color=\"red\">Login Successful!</font></h3>");
		}
		writer.println(finalResponse);
	}

}
