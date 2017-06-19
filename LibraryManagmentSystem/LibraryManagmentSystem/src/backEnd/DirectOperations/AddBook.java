package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import backEnd.DatabaseAccessor;
import view.masterPanel.EventsAndListeners.AddBookEvent;

public class AddBook extends DatabaseAccessor {

	String name;
	String writer;
	String house;
	int copiesCount;
	String date;

	public AddBook(AddBookEvent event) throws Exception {
		connect();
		extractData(event);
		addToDataBase();

	}

	public void extractData(AddBookEvent event) {
		name = event.getBookName();
		writer = event.getWriterName();
		house = event.getPublishingHouseName();
		copiesCount = event.getCopiesCount();
		date = event.getAdditionDate();
	}

	public void addToDataBase() throws SQLException {

		String SQLCheck = "SELECT COUNT(*) FROM library.books ";
		String SQLCount = "SELECT book_id FROM library.books ORDER BY book_id";
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

			final String SQLInsertStatement = "INSERT INTO library.books (book_id,book_title,book_writer,publishing_house"
					+ ",copies_count,addition_date_time) VALUES(?,?,?,?,?,?) ";
			final String SQLInitialTableInsert = " INSERT INTO library.books_initial_copies (book_id,initial_copies_count)"
					+ "VALUES(?,?)";

			PreparedStatement insertStatement = connection.prepareStatement(SQLInsertStatement);
			PreparedStatement initialTableInsertStatement = connection.prepareStatement(SQLInitialTableInsert);

			insertStatement.setInt(1, count + 1);
			insertStatement.setString(2, name);
			insertStatement.setString(3, writer);
			insertStatement.setString(4, house);
			insertStatement.setInt(5, copiesCount);
			insertStatement.setString(6, date);

			initialTableInsertStatement.setInt(1, count + 1);
			initialTableInsertStatement.setInt(2, copiesCount);

			insertStatement.executeUpdate();
			initialTableInsertStatement.executeUpdate();

			insertStatement.close();
			initialTableInsertStatement.close();
		} else {

			String SQLFirstInsert = "INSERT INTO library.books (book_id,book_title,book_writer,publishing_house"
					+ ",copies_count,addition_date_time) VALUES(?,?,?,?,?,?) ";
			String SQLInitialTableInsert = " INSERT INTO library.books_initial_copies (book_id,initial_copies_count)"
					+ "VALUES(?,?)";
			PreparedStatement insertStatement = connection.prepareStatement(SQLFirstInsert);
			PreparedStatement initialTableInsertStatement = connection.prepareStatement(SQLInitialTableInsert);
			insertStatement.setInt(1, 1001);
			insertStatement.setString(2, name);
			insertStatement.setString(3, writer);
			insertStatement.setString(4, house);
			insertStatement.setInt(5, copiesCount);
			insertStatement.setString(6, date);

			initialTableInsertStatement.setInt(1, 1001);
			initialTableInsertStatement.setInt(2, copiesCount);

			insertStatement.executeUpdate();
			initialTableInsertStatement.executeUpdate();

			insertStatement.close();
			initialTableInsertStatement.close();

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
