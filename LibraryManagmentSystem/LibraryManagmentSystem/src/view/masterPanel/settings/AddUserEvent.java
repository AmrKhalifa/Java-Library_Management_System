package view.masterPanel.settings;

public class AddUserEvent {

	String username;
	String password;
	String userType;
	
	
	public AddUserEvent(String username, String password, String userType) {
		
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getUserType() {
		return userType;
	}
	
	
}
