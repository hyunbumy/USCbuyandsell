package buyAndSell;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class createWishlist
 */
@WebServlet("/createWishlist")
public class createWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currItemId = Integer.parseInt(request.getParameter("itemID"));
		String next = "/individualitem.jsp?itemID="+currItemId;
		if (StoreDatabase.getCurrUser() == null)
			next = "/login.jsp";
		else
		{
			if (StoreDatabase.sendWishListMessage(currItemId))
				request.setAttribute("added", "Item added to your Wishlist!");
			else
				request.setAttribute("added", "Item already exists in your Wishlist");
		}
		RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(next);
		dispatch.forward(request, response);
	}

}
