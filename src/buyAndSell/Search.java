package buyAndSell;

import java.io.IOException;
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
		String searchTerm = (String) request.getAttribute("term");
		String category = (String) request.getAttribute("category");
		
		String next = "/product.jsp";
		System.out.println(request.getContextPath());
		// Check the validity of the search
		// If not, send back to the homepage with 
		if (searchTerm == null || searchTerm.equals(""))
		{
			next = "/homepage.jsp";
			
		}
	}

}
