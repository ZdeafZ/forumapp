package demo.forum.classes;

public class Post {
	private int id;
	private int threadID;
	private String username;
	private String text;
	
	public Post(int id, int threadID, String username, String text)
	{
		this.id = id;
		this.threadID = threadID;
		this.username = username;
		this.text = text;
	}
	public int getID()
	{
		return id;
	}
	public int getThreadID()
	{
		return threadID;
	}
	public String getUsername()
	{
		return username;
	}
	public String getText()
	{
		return text;
	}
}
