package demo.forum.classes;

public class User {
	private String username;
	private String password;
	private String profilePicturePath;
	private String description;
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	public User(String username, String password, String profilePicturePath, String description)
	{
		this.username = username;
		this.password = password;
		this.profilePicturePath = profilePicturePath;
		this.description = description;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public String getProfilePicturePath()
	{
		return profilePicturePath;
	}
	public String getDescription()
	{
		return description;
	}
	public void setProfilePicturePath(String profilePicturePath)
	{
		this.profilePicturePath = profilePicturePath;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
}
