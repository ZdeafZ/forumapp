package demo.forum.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.forum.classes.User;
import demo.forum.utils.DatabaseInterface;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			String username = (String)session.getAttribute("username");
			DatabaseInterface db = new DatabaseInterface();
			if(db.isConnected()) 
			{
				try 
				{
					User user = db.getUser(username);
					request.setAttribute("user", user);
					request.getRequestDispatcher("./profile_view.jsp").forward(request,response);
				}
				catch(Exception sqle)
				{
					System.out.println(sqle);
				}
			}
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
