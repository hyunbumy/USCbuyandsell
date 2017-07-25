package buyAndSell;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.hash.Hashing;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String next = "/homepage.jsp";
		Store s = (Store) request.getSession().getAttribute("store");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		
		//can log in
		if (Store.login(username, password)) {
			//get the rest of the user's info via the database
			//User u = new User("firstName", "lastName", "email", "phoneNum", username, password);
		}
		
		//can't log in- either don't exist in the system or incorrect password
		else {
			request.setAttribute("error", "User does not exist");
		}
		
		RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(next);
		dispatch.forward(request, response);
		
	}

}
