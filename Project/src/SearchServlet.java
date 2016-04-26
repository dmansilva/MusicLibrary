import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends BaseServlet {
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * GET /search returns a web page containing a search box where a student's name may be entered.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// create responseHtml
		
		
		
		//PrintWriter writer = prepareResponse(response);
		//writer.println(responseHtml);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//create responseHtml
		
		//PrintWriter writer = prepareResponse(response);
		//writer.println(responseHtml);
	}

}
