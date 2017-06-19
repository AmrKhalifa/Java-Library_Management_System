package backEnd.Model;

public class User {
	
	public static long count =1;
	long userID;
	String username;
	String userpassword;
	String userType;
	
	public User(String username, String userpassword, String userType)
	{
		this.userID = count++;
		this.username = username;
		this.userpassword = userpassword;
		this.userType = userType;
	}
	
	
	public User(long userID, String username, String userpassword, String userType) {
		
		this.userID = userID;
		this.username = username;
		this.userpassword = userpassword;
		this.userType = userType;
	}

	
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public static void remove(User user) {
		
		
	}
	
	
}

