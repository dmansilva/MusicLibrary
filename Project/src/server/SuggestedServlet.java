package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.DBHelper;

public class SuggestedServlet extends BaseServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		PrintWriter writer = prepareResponse(response);
		String username = (String) session.getAttribute(USERNAME);
		
		if(!isLoggedIn(request, response, session)) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		
		String header = header();
		String footer = footerSuggested();
		JSONArray suggested = new JSONArray();
		suggested = DBHelper.getSuggestedList();
		String responseTable = "";
		if (suggested.size() > 0) {
			responseTable = "<center>Here are some popular searches you might be interested in!</center>" +
					"<br>" +
					"<table border=\"2px\" width=\"100%\">" +				
					"<tr><td><strong>Type</strong></td><td><strong>Query</strong></td></tr>";
			
			for (int i = 0; i< suggested.size(); i++) {
				
				JSONObject eachSearch = (JSONObject) suggested.get(i);
				String responseTemp = "<tr><td>" + eachSearch.get("type") + "</td><td>" + 
										eachSearch.get("query") + "</td></tr>";
				responseTable += responseTemp;
				
			}
			
			String endTable = "</table>";
			responseTable += endTable;
		}
		else {
			responseTable = "<center>History is currently clear, so no popular searches!</center>";
		}
		
		String responseHtml = header + responseTable + footer;
		
		writer.println(responseHtml);
		
		
	}

}
