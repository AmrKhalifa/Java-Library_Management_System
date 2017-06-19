package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backEnd.DatabaseAccessor;
import view.loginPanel.UserEnteredEvent;

public class RegularUserEntered extends DatabaseAccessor {

	private String passedUsername;
	private String passedPassword;
	// private String usernameInDB=null;
	private String passwordInDB = null;
	private String isRegular = null;

	public RegularUserEntered(UserEnteredEvent event) throws Exception {
		connect();
		extractData(event);
		checkDataBase();

	}

	public void extractData(UserEnteredEvent event) {

		this.passedUsername = event.getUsername().replaceAll("\\s+", "");
		this.passedPassword = event.getPassword();
	}

	public void checkDataBase() throws SQLException {

		String SQLCheckUser = "SELECT password,admin FROM library.users WHERE username = ?";
		PreparedStatement checkAdmin = connection.prepareStatement(SQLCheckUser);

		checkAdmin.setString(1, passedUsername);

		ResultSet userData = checkAdmin.executeQuery();

		if (userData.next()) {
			passwordInDB = userData.getString(1);
			isRegular = userData.getString(2);
		}

		userData.close();
		checkAdmin.close();

		try {
			if (connection != null)
				connection.close();
			System.out.println("......connection closed ....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isAdmin() {
		try {
			System.out.println(
					"\n \n **" + (passwordInDB.equals(passedPassword) && isRegular.equals("عادي")) + "\n \n **");
			return (passwordInDB.equals(passedPassword) && isRegular.equals("عادي"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}
