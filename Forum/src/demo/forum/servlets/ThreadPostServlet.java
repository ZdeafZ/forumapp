package demo.forum.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.forum.utils.DatabaseInterface;
import demo.forum.classes.Thread;


@WebServlet("/thread_post")
public class ThreadPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ThreadPostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		PrintWriter printWriter = response.getWriter();
		response.setContentType("text/html");
		if(session != null)
		{
			if(request.getParameter("thread_name") != "" && request.getParameter("thread_text") != "")
			{
				Thread thread = new Thread(request.getParameter("thread_name"),(String)session.getAttribute("username"),request.getParameter("thread_text"));
				DatabaseInterface db = new DatabaseInterface();
				if(db.isConnected())
				{	
					try 
					{
						db.addThread(thread);
						response.sendRedirect(request.getContextPath() + "/forum_index");
					}
					catch(SQLException sqle)
					{
						printWriter.println("<h1>Something went wrong, please try again !</h1>");
						request.getRequestDispatcher("/thread_post.html").include(request, response);
					}
				}
			}
			else
			{
				printWriter.println("<h1>Must include thread name and some text :)!</h1>");
				request.getRequestDispatcher("/thread_post.html").include(request, response);
			}
		}
		else
		{
			printWriter.println("Please log in here :<button onClick=\"location.href='./login.html'\">LOG IN</button>");
		}
		
	}

}
