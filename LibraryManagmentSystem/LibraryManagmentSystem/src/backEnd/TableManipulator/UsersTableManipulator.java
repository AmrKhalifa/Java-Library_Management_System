package backEnd.TableManipulator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import backEnd.DatabaseAccessor;
import backEnd.Model.User;

public class UsersTableManipulator extends DatabaseAccessor {

	private LinkedList<User> users;

	public UsersTableManipulator() {
		users = new LinkedList<>();
	}

	public void addUser(User user) {
		users.add(user);
	}

	public void removeUser(User user) {
		users.remove(user);
	}

	public LinkedList<User> getUsers() {
		if (users.isEmpty())
			User.count = 1;

		return users;
	}

	public void save() throws SQLException {

		try {
			connect();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final String SQLInsertStatement = "INSERT INTO library.users (user_id,username,password,admin)"
				+ " VALUES(?,?,?,?) ";

		final String SQLUpdateStatement = "UPDATE library.users SET  username =?,password =?,"
				+ "admin = ? WHERE user_id = ?";

		PreparedStatement insertStatment = connection.prepareStatement(SQLInsertStatement);
		PreparedStatement updateStatment = connection.prepareStatement(SQLUpdateStatement);

		for (User user : users) {
			long id = user.getUserID();
			String username = user.getUsername();
			String password = user.getUserpassword();
			String type = user.getUserType();

			String bookCheckSQLStmt = "SELECT COUNT(*) AS count FROM library.users WHERE user_id = ?";
			PreparedStatement stmt = connection.prepareStatement(bookCheckSQLStmt, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			System.out.println("the count of the uers with id = " + id + " is " + count);

			if (count == 0) {
				System.out.println(" inserting book with ID " + id);

				int col = 1;
				insertStatment.setLong(col++, id);
				insertStatment.setString(col++, username);
				insertStatment.setString(col++, password);
				insertStatment.setString(col++, type);

				try {
					insertStatment.executeUpdate();
				} catch (Exception e) {
					// do something about wrong values inserted in date time
					// field. S
					e.printStackTrace();
				}

				System.out.println(" done insertion ...:) ");
			} else {
				System.out.println("updating book with id  " + id);

				int col = 1;
				updateStatment.setString(col++, username);
				updateStatment.setString(col++, password);
				updateStatment.setString(col++, type);
				updateStatment.setLong(col++, id);

				updateStatment.executeUpdate();

				System.out.println("done update ... :)");

			}
			rs.close();

			stmt.close();

		}
		updateStatment.close();
		insertStatment.close();

		try {
			disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void load() throws SQLException {

		users.clear();
		try {
			connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String SQLloadStatement = "SELECT user_id,username,password,admin FROM library.users";
		Statement loadStatement = connection.createStatement();
		ResultSet result = loadStatement.executeQuery(SQLloadStatement);

		// System.out.println(" creation of result set successful ... ");
		while (result.next()) {

			long id = result.getLong("user_id");
			String username = result.getString("username");
			String password = result.getString("password");
			String userType = result.getString("admin");

			User newUser = new User(id, username, password, userType);

			users.add(newUser);
			System.out.println(newUser.getUsername());
		}
		result.close();
		loadStatement.close();
		try {
			disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
