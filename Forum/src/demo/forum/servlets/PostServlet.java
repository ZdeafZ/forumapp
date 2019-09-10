package demo.forum.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.forum.classes.Post;
import demo.forum.utils.DatabaseInterface;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		DatabaseInterface db = new DatabaseInterface();
		PrintWriter printWriter = response.getWriter();
		if(session != null)
		{
			int id = Integer.parseInt(request.getParameter("postCount")) + 1;
			int threadID = Integer.parseInt(request.getParameter("threadID"));
			Post post = new Post(id,threadID,(String)session.getAttribute("username"),request.getParameter("post_text"));
			try {
			db.addPost(post);
			}
			catch(Exception sqle)
			{
				System.out.println("Something went wrong: "+ sqle);
			}
			response.sendRedirect("./thread?id=" + String.valueOf(threadID));
		}
		else
		{
			printWriter.println("Please log in here :<button onClick=\"location.href='./login.html'\">LOG IN</button>");
		}
	}

}
