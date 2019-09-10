package demo.forum.servlets;
import demo.forum.classes.*;
import demo.forum.classes.Thread;

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


@WebServlet("/forum_index")
public class ForumIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ForumIndexServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			DatabaseInterface db = new DatabaseInterface();
			if(db.isConnected()) 
			{
				ArrayList<Thread> yourThreadList = db.getYourThreads((String)session.getAttribute("username"));
				request.setAttribute("my_thread_list", yourThreadList);
				ArrayList<Thread> threadList = db.getAllThreads();
				request.setAttribute("thread_list", threadList);
				request.getRequestDispatcher("./forum_index_view.jsp").forward(request,response);
			}
		}
		else
		{
			printWriter.println("Please log in here :<button onClick=\"location.href='./login.html'\">LOG IN</button>");		
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
