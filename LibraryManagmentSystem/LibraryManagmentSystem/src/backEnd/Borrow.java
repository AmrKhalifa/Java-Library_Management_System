package backEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import view.masterPanel.EventsAndListeners.RentBookEvent;

public class Borrow extends DatabaseAccessor {

	public Borrow(RentBookEvent event) throws Exception {
		rentBook(event);
	}

	private void rentBook(RentBookEvent event) throws Exception {
		long memberId = event.getMemberCode();
		long bookId = event.getBookCode();
		String rentDate = event.getRentingDatetime();
		connect();
		try {
			addToMemberBookTable(memberId, bookId, rentDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addToMemberBookTable(long memberId, long bookId, String rentDate) throws SQLException {

		String SQLGetCount = "SELECT copies_count FROM library.books WHERE book_id=?";
		String SQLGetPK = "SELECT COUNT(*) FROM library.member_book_renting";
		String SQLAdd = "INSERT INTO library.member_book_renting values(?,?,?,?)";
		String SQLUpdate = "UPDATE library.books SET copies_count =? WHERE book_id =?";

		PreparedStatement countStatement = connection.prepareStatement(SQLGetCount);
		Statement getPK = connection.createStatement();
		PreparedStatement insertStatement = connection.prepareStatement(SQLAdd);
		PreparedStatement updateStatement = connection.prepareStatement(SQLUpdate);

		countStatement.setLong(1, bookId);
		ResultSet countResult = countStatement.executeQuery();
		countResult.next();

		try {
			int count = countResult.getInt(1);
			if (count != 0) {
				ResultSet rs = getPK.executeQuery(SQLGetPK);
				rs.next();
				int PK = rs.getInt(1);

				insertStatement.setInt(1, ++PK);
				insertStatement.setLong(2, memberId);
				insertStatement.setLong(3, bookId);
				insertStatement.setString(4, rentDate);
				insertStatement.executeUpdate();

				updateStatement.setInt(1, count - 1);
				updateStatement.setLong(2, bookId);
				updateStatement.executeUpdate();

			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
