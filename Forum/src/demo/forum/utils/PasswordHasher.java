package demo.forum.utils;

public class PasswordHasher {
	public static String encode(String pass)
	{
		StringBuffer encoded = new StringBuffer();
		for(int i = 0; i < pass.length(); i++)
		{
			char ch = ' ';
			if(Character.isUpperCase(pass.charAt(i)))
			{
				ch = (char)(((int)pass.charAt(i) + pass.length() - 65) % 26 + 65);
			}
			else
			{
				ch = (char)(((int)pass.charAt(i) + pass.length() - 97) % 26 + 97);
			}
			encoded.append(ch);
		}
		return encoded.toString();
	}
	public static String decode(String pass)
	{
		StringBuffer decoded = new StringBuffer();
		for(int i = 0; i < pass.length(); i++)
		{
			char ch = ' ';
			if(Character.isUpperCase(pass.charAt(i)))
			{
				ch = (char)(((int)pass.charAt(i) - pass.length() - 65) % 26 + 65);
			}
			else
			{
				ch = (char)(((int)pass.charAt(i) - pass.length() - 97) % 26 + 97);
			}
			decoded.append(ch);
			
		}
		return decoded.toString();
	}
}
