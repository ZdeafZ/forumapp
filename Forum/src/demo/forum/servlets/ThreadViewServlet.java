package demo.forum.servlets;

import demo.forum.classes.Thread;
import demo.forum.classes.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.forum.utils.DatabaseInterface;

@WebServlet("/thread")
public class ThreadViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThreadViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			PrintWriter printWriter = response.getWriter();
			DatabaseInterface db = new DatabaseInterface();
			if(db.isConnected()) {
				int id = Integer.parseInt(request.getParameter("id"));
				Thread thread = db.getSelectedThread(id);
				ArrayList<Post> postList = db.getAllRelatedPosts(id);
				request.setAttribute("thread",thread);
				request.setAttribute("post_list", postList);
				request.getRequestDispatcher("/thread_view.jsp").forward(request,response);
			}
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/forum_index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
