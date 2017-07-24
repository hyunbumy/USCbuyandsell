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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// Hash the password using SHA256
		String hashedPass = Hashing.sha256().
				hashString(password, StandardCharsets.UTF_8).toString();
		
		// TODO: Check the DB for username and HASHED password
		boolean isRegistered = true;
		// If present, instantiate a User based on the info on DB
		// Then pass in the User object to the session then go to main page
		if (isRegistered) {
			// TEST
			User currUser = new User("Brad", "TYang", "didasdf@usc.edu","000-000-0000", username, hashedPass);
			HttpSession session = request.getSession();
			session.setAttribute("currUser", currUser);
			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher("/homepage.jsp");
			dispatch.forward(request, response);
		}
		
		// If not present, stay on Login and display error
		else {
			request.setAttribute("error", "User does not exist");
			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatch.forward(request, response);
		}
		
	}

}
