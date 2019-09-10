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

@WebServlet("/profile_edit")
public class ProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProfileEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter printWriter = response.getWriter();
		response.setContentType("text/html");
		if(session != null)
		{
			try 
			{
				if(request.getParameter("profile_img_path").length() > 4000)
				{
					printWriter.println("Profile image path can not exceed 4000 symbols.<br/>");
					request.getRequestDispatcher("./profile_edit.html").include(request,response);
				}
				else if(request.getParameter("profile_descr").length() > 500)
				{
					printWriter.println("Profile description can not exceed 500 symbols.<br/>");
					request.getRequestDispatcher("./profile_edit.html").include(request,response);
				}
				
				DatabaseInterface db = new DatabaseInterface();
				if(db.isConnected())
				{
					User user = db.getUser((String)session.getAttribute("username"));
					user.setProfilePicturePath(request.getParameter("profile_img_path"));
					user.setDescription(request.getParameter("profile_descr"));
					db.updateUser(user);
					response.sendRedirect("./profile");
				}
			}
			catch(Exception sqle)
			{
				System.out.println("Something went wrong: " + sqle);
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
