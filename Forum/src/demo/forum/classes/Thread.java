package demo.forum.classes;

public class Thread {
	private int id;
	private String threadName;
	private String threadUser;
	private String threadText;
	
	public Thread(int id, String threadName, String threadUser)
	{
		this.id = id;
		this.threadName = threadName;
		this.threadUser = threadUser; 
	}
	public Thread(String threadName, String threadUser, String threadText)
	{
		this.threadName = threadName;
		this.threadUser = threadUser;
		this.threadText = threadText;
	}
	public Thread(int id, String threadName, String threadUser, String threadText)
	{
		this.id = id;
		this.threadName = threadName;
		this.threadUser = threadUser;
		this.threadText = threadText;
	}
	public int getID()
	{
		return id;
	}
	public String getThreadName()
	{
		return threadName;
	}
	public String getThreadUser()
	{
		return threadUser;
	}
	public String getThreadText()
	{
		return threadText;
	}

}
