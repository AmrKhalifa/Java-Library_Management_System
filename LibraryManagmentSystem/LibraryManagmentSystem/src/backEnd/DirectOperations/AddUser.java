package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import backEnd.DatabaseAccessor;
import view.masterPanel.settings.AddUserEvent;

public class AddUser extends DatabaseAccessor {

	String username;
	String password;
	String userType;

	public AddUser(AddUserEvent event) throws Exception {

		extractData(event);
		addToDataBase(username, password, userType);

	}

	public void extractData(AddUserEvent event) {
		this.username = event.getUsername();
		this.password = event.getPassword();
		this.userType = event.getUserType();
	}

	public void addToDataBase(String username, String password, String admin) throws SQLException {

		String SQLCheck = "SELECT COUNT(*) FROM library.users ";
		String SQLCount = "SELECT user_id FROM library.users ORDER BY user_id";
		Statement checkStatement = connection.createStatement();
		Statement countStatement = connection.createStatement();
		ResultSet checkResult = checkStatement.executeQuery(SQLCheck);
		checkResult.last();
		int checkEmpty = checkResult.getInt(1);

		if (checkEmpty != 0) {
			ResultSet countResult = countStatement.executeQuery(SQLCount);
			countResult.last();
			int count = countResult.getInt(1);
			countResult.close();

			final String SQLInsertStatement = "INSERT INTO library.users (user_id,username,password,admin)"
					+ " VALUES(?,?,?,?) ";

			PreparedStatement insertStatement = connection.prepareStatement(SQLInsertStatement);

			insertStatement.setInt(1, count + 1);
			insertStatement.setString(2, username);
			insertStatement.setString(3, password);
			insertStatement.setString(4, admin);

			insertStatement.executeUpdate();

			insertStatement.close();

		} else {

			final String SQLInsertStatement = "INSERT INTO library.users (user_id,username,password,admin)"
					+ " VALUES(?,?,?,?) ";

			PreparedStatement insertStatement = connection.prepareStatement(SQLInsertStatement);

			insertStatement.setInt(1, 1);
			insertStatement.setString(2, username);
			insertStatement.setString(3, password);
			insertStatement.setString(4, admin);

			insertStatement.executeUpdate();

			insertStatement.close();

		}
		checkStatement.close();
		countStatement.close();
		checkResult.close();

		try {
			if (connection != null)
				connection.close();
			System.out.println("connection closed ....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
