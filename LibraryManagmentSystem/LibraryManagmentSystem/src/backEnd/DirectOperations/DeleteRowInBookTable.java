package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import backEnd.DatabaseAccessor;

public class DeleteRowInBookTable extends DatabaseAccessor {

	int book_id;

	public DeleteRowInBookTable(long book_id) throws Exception {

		connect();
		deleteRow(book_id);

	}

	private void deleteRow(long book_id) throws SQLException {
		String SQLDelete = "DELETE FROM library.books WHERE book_id = ?";
		String SQLDeleteIninitalTable = " DELETE FROM library.books_initial_copies WHERE book_id = ?";
		PreparedStatement deleteStatement = connection.prepareStatement(SQLDelete);
		PreparedStatement deleteInitialStatement = connection.prepareStatement(SQLDeleteIninitalTable);

		deleteStatement.setLong(1, book_id);
		deleteInitialStatement.setLong(1, book_id);

		deleteStatement.executeUpdate();
		deleteInitialStatement.executeUpdate();

		deleteStatement.close();
		deleteInitialStatement.close();
		try {
			if (connection != null)
				connection.close();
			System.out.println("......connection closed ....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
