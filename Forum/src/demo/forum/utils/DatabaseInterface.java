package demo.forum.utils;
import demo.forum.utils.PasswordHasher;
import demo.forum.classes.Post;
import demo.forum.classes.Thread;
import java.sql.*;
import java.util.ArrayList;

import demo.forum.classes.User;

public class DatabaseInterface {
	
	public String url = "jdbc:firebirdsql://localhost:3050/c:/db/forum_database.fdb";
	public String user = "SYSDBA";
	public String password = "masterkey";
	
	private Connection con;
	private PreparedStatement insertUser;
	private PreparedStatement updateUser;
	private PreparedStatement insertThread;
	private PreparedStatement removeThread;
	private PreparedStatement insertPost;
	private PreparedStatement removePost;
	private PreparedStatement updatePost;

	//Prepared statement part
	public DatabaseInterface()
	{
		try {
			Class.forName ("org.firebirdsql.jdbc.FBDriver");
			con = DriverManager.getConnection(url,"SYSDBA","masterkey");
		}
		catch(Exception e)
		{
			System.out.println("Connection failed: " + e);
		}
		initializeStatements();
	}
	
	public void initializeStatements()
	{
		try {
			this.insertUser = con.prepareStatement("INSERT INTO USERS (username, password)\nVALUES (?,?);");
			this.insertThread = con.prepareStatement("INSERT INTO THREADS (thread_name, thread_user, thread_text) VALUES (?,?,?)");
			this.insertPost = con.prepareStatement("INSERT INTO POSTS (post_id, thread_id, user_name, text) VALUES (?,?,?,?)");
			this.updateUser = con.prepareStatement("UPDATE USERS SET profile_pic_path = ?, profile_descr = ? where username = ?");
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
	}
	
	public boolean isConnected()
	{
		if(con == null)
			return false;
		return true;
	}
	
	public void addUser(User user) throws SQLException
	{
		insertUser.setString(1, user.getUsername());
		insertUser.setString(2, PasswordHasher.encode(user.getPassword()));
		insertUser.executeUpdate();
	}
	public User getUser(String username)
	{
		try{
			
			PreparedStatement stmt = con.prepareStatement("SELECT * from USERS where username = ?");
			stmt.setString(1, username);
			ResultSet res = stmt.executeQuery();
			if(res.isBeforeFirst())
			{
				while(res.next())
				{
					String password = res.getString("password");
					String profilePicturePath = res.getString("profile_pic_path");
					String description = res.getString("profile_descr");
					return new User(username,password,profilePicturePath,description);
				}
			}
			//TODO ADD PROTECTION.
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		return null;
	}
	public void updateUser(User user) throws SQLException
	{
		try{

			updateUser.setString(1, user.getProfilePicturePath());
			updateUser.setString(2, user.getDescription());
			updateUser.setString(3, user.getUsername());
			updateUser.executeUpdate();
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
	}
	public void addThread(Thread thread) throws SQLException
	{
		insertThread.setString(1, thread.getThreadName());
		insertThread.setString(2, thread.getThreadUser());
		insertThread.setString(3, thread.getThreadText());
		insertThread.executeUpdate();
	}
	public void addPost(Post post) throws SQLException
	{
		insertPost.setInt(1,post.getID());
		insertPost.setInt(2, post.getThreadID());
		insertPost.setString(3, post.getUsername());
		insertPost.setString(4, post.getText());
		insertPost.executeUpdate();
	}
	public ArrayList<Thread> getYourThreads(String username)
	{
		ArrayList<Thread> list = new ArrayList<Thread>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * from THREADS where thread_user = ?");
			stmt.setString(1, username);
			ResultSet res = stmt.executeQuery();
			if(res.isBeforeFirst())
			{
				while(res.next())
				{
					int id = res.getInt("id");
					String threadName = res.getString("thread_name");
					String threadStarter = res.getString("thread_user");
					String threadText = res.getString("thread_text");
					list.add(new Thread(id,threadName,threadStarter,threadText));
				}
			}
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		return list;
	}
	public ArrayList<Thread> getAllThreads()
	{
		ArrayList<Thread> list = new ArrayList<Thread>();
		try {
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * from THREADS");
			if(res.isBeforeFirst())
			{
				while(res.next())
				{
					int id = Integer.parseInt(res.getString("id"));
					String threadName = res.getString("thread_name");
					String threadStarter = res.getString("thread_user");
					list.add(new Thread(id,threadName,threadStarter));
				}
			}
			stmt.close();
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		return list;
	}
	public Thread getSelectedThread(int id)
	{
		try{
			
			PreparedStatement stmt = con.prepareStatement("SELECT * from THREADS where id = ?");
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			if(res.isBeforeFirst())
			{
				while(res.next())
				{
					String threadName = res.getString("thread_name");
					String threadStarter = res.getString("thread_user");
					String threadText = res.getString("thread_text");
					return new Thread(threadName,threadStarter,threadText);
				}
			}
			//TODO ADD PROTECTION.
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		return null;
	}
	public ArrayList<Post> getAllRelatedPosts(int id)
	{
		ArrayList<Post> list = new ArrayList<Post>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * from POSTS where thread_id = ?");
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			if(res.isBeforeFirst())
			{
				while(res.next())
				{
					int postID = res.getInt("post_id");
					int threadID = res.getInt("thread_id");
					String username = res.getString("user_name");
					String text = res.getString("text");
					list.add(new Post(postID,threadID,username,text));
				}
			}
			stmt.close();
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		return list;
	}
	public boolean checkIfUserExists(User user)
	{
		try {
			
			PreparedStatement stmt = con.prepareStatement("SELECT username from USERS where username = ?");
			stmt.setString(1, user.getUsername());
			ResultSet res = stmt.executeQuery();
			System.out.println(res.isBeforeFirst());
			if(res.isBeforeFirst())
			{
				stmt.close();
				return true;
			}
			stmt.close();
			return false;
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
			return false;
		}
	}
	public boolean validateUser(User user)
	{
		try {
			
			PreparedStatement stmt = con.prepareStatement("SELECT username from USERS where username = ? and password = ?");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			ResultSet res = stmt.executeQuery();
			if(res.isBeforeFirst())
			{
				stmt.close();
				return true;
			}
			stmt.close();
			return false;
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
			return false;
		}
	}

}
