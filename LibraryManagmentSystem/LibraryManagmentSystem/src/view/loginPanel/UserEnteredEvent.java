package view.loginPanel;

public class UserEnteredEvent {

	private String username;
	private String password;
	
	public UserEnteredEvent(String username , String password)
	{
		this.username = username;
		this.password = password;
		
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
}
