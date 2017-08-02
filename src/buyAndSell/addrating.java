package buyAndSell;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addrating
 */
@WebServlet("/addrating")
public class addrating extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strRating = request.getParameter("rating");
		String strUser = request.getParameter("ratedUser");
		int rating = Integer.parseInt(strRating);
		int userID = Integer.parseInt(strUser);
		StoreDatabase.addRating(userID, rating);
		
		RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher("/inbox.jsp");
		dispatch.forward(request, response);
		
	}

}
