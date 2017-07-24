package buyAndSell;

import java.io.IOException;
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
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String username = request.getParameter("username");
		String password = request.getParameter("userpassword");
		String passwordConfirm = request.getParameter("confirm_password");
		String email = request.getParameter("useremail");
		String phoneNum = request.getParameter("phonenum");
		
	}

}
