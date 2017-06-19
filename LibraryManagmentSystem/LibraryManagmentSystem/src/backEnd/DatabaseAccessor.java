package backEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccessor {
	public static Connection connection;

	public static void connect() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver found attempting connection ....");
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		final String SQLAddress = "jdbc:mysql://localhost:3306/library";
		try {
			connection = DriverManager.getConnection(SQLAddress, "root", "root");
			System.out.println("connected to dataBase server ... :)");
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "\n" + e.getSQLState());
		}
	}

	public static void disconnect() throws Exception {
		if (connection != null)
			connection.close();
	}

}
