package buyAndSell;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Store s = (Store) request.getSession().getAttribute("store");
		String next = "/register.jsp";
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String username = request.getParameter("username");
		String password = request.getParameter("userpassword");
		String passwordConfirm = request.getParameter("confirm_password");
		String email = request.getParameter("useremail");
		String phoneNum = request.getParameter("phonenum");
		
		//passwords don't match
		if (!(password.equals(passwordConfirm))) {
			request.setAttribute("error", "Passwords do not match");
			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(next);
			dispatch.forward(request, response);
		}
		
		//attempt to create the user 
		if (Store.createUser(fname, lname, email, phoneNum, username, password)) {
			//forwarding to the home page- may want to find out where they came from and send them back there
			next = "/homepage.jsp";
		}
		//failed
		else {
			request.setAttribute("error", "The username "+username+" already exists in our system");
		}
		
		RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(next);
		dispatch.forward(request, response);
		
	}

}
