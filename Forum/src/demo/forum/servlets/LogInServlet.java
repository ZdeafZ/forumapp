package demo.forum.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.forum.classes.User;
import demo.forum.utils.*;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseInterface db = new DatabaseInterface();
		if(db.isConnected())
		{
			response.setContentType("text/html");
			PrintWriter printWriter = response.getWriter();
			User user = new User(request.getParameter("username"),PasswordHasher.encode(request.getParameter("password")));
			
			if(db.validateUser(user))
			{
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getUsername());
				response.sendRedirect(request.getContextPath() + "/forum_index");
			}
			else
			{
				printWriter.println("<h1>Your username or password might be wrong!</h1>");
				printWriter.println("<button onClick=\"location.href='./login.html'\">RETRY</button>");
			}
		}
		
	}

}
