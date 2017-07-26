package buyAndSell;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Post
 */
@WebServlet("/post_item")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void servie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("itemname");
		Float price = Float.parseFloat(request.getParameter("price"));
		String cate = request.getParameter("category");
		Category category;
		switch(cate) {
		case "Book":
			category = Category.BOOK;
			break;
		case "Electronic":
			category = Category.ELECTRONIC;
			break;
		case "Clothing":
			category = Category.CLOTHING;
			break;
		case "Service":
			category = Category.SERVICE;
			break;
		case "Movie":
			category = Category.MOVIE;
			break;
		}
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		String image = request.getParameter("image");
		String desc = request.getParameter("description");
		
		// Call sell item
		// Then redirect to homepage
	}

}
