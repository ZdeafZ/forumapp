package demo.forum.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LogOutServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter printWriter = response.getWriter();
		if (session != null) 
		{
			session.invalidate();
			printWriter.println("<h1>You have been logged out</h1>");
			request.getRequestDispatcher("./forum_index").include(request,response);
			
		}
		else 
		{
			printWriter.println("Please log in here :<button onClick=\"location.href='./login.html'\">LOG IN</button>");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
