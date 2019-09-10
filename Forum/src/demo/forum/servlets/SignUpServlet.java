package demo.forum.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.forum.classes.User;
import demo.forum.utils.*;

import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter printWriter = response.getWriter();
		response.setContentType("text/html");
		if(request.getParameter("username") != "" || request.getParameter("password") != "") {
			if(request.getParameter("username").length() < 30 && request.getParameter("password").length() < 30) {
				
				DatabaseInterface db = new DatabaseInterface();
				if(db.isConnected()){
					
					User user = new User(request.getParameter("username"),request.getParameter("password"));
					
					if(db.checkIfUserExists(user))
					{
						printWriter.println("<h1>User under this name has already been registered<h1>");
					}
					else
					{
						try {
							db.addUser(user);
						}
						catch(Exception sqle)
						{
							System.out.println(sqle);
						}
						printWriter.println("<h1>You have been registered!<h1>");
						printWriter.println("Your username: " + user.getUsername() + "<br/>");
					}
					printWriter.println("<button onClick='location.href=\"./login.html\"'>LOG IN</button>");
				}
			
				else
				{
					//printWriter.println("Connection to the database was not successfull");
				}
			}
			else
			{
				printWriter.println("<h1>Username and password can't exceed 30 symbols :) </h1>");
				request.getRequestDispatcher("./signup.html").include(request,response);
			}
		}
		else
		{
			printWriter.println("<h1>Username and password can't be blank! :) </h1>");
			request.getRequestDispatcher("./signup.html").include(request,response);
		}
	}

}
