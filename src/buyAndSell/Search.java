package buyAndSell;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchTerm = request.getParameter("term");
		String category = request.getParameter("category");
		
		String next = "/searchResults.jsp";

		// Check the validity of the search
		// If not, send back to the home page with 
		if (searchTerm == null || searchTerm.equals("")) {
			// Get the url of the previous page
			//next = request.getHeader("referer").split("USCbuyandsell")[1];
			next = "/index.jsp";
			request.setAttribute("error", "Please enter a search term");
		}
		else {
			request.getSession().setAttribute("searchTerm", searchTerm);
		}
		

		// Forward to the appropriate page
		RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(next);
		dispatch.forward(request, response);
	}

}
