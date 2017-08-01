package buyAndSell;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MarkSold
 */
@WebServlet("/markassold")
public class MarkSold extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int messageID = Integer.parseInt(request.getParameter("messageID"));
		if (StoreDatabase.sendRatingMessage(messageID)) {
			request.setAttribute("marked", "Item has been sold!");
			
		}
			
		else {
			request.setAttribute("marked", "Something went wrong!");
		}
		RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher("/messagedisplay.jsp");
		dispatch.forward(request, response);
	}

}
