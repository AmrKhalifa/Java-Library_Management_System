package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backEnd.DatabaseAccessor;
import view.masterPanel.AdvancedSearcher.Events.BooksCountByWriterEvent;

public class BookCountByWriter extends DatabaseAccessor {

	private String writerName;
	private String fromBoundary;
	private String toBoundary;
	int count;

	public BookCountByWriter(BooksCountByWriterEvent event) throws Exception {
		extractData(event);
		connect();
		countBooksOfWriterBetweenDates(writerName, fromBoundary, toBoundary);

	}

	private void extractData(BooksCountByWriterEvent event) {
		this.writerName = event.getWriterName();
		this.fromBoundary = event.getFromBoundary();
		this.toBoundary = event.getToBoundary();
	}

	private void countBooksOfWriterBetweenDates(String writerName, String fromBoundar, String toBoundary)
			throws SQLException {
		String SQLCount = "SELECT COUNT(*) FROM library.books WHERE DATE(addition_date_time) >= ? "
				+ "AND DATE(addition_date_time) <= ? AND book_writer = ?";

		PreparedStatement countStatement = connection.prepareStatement(SQLCount);

		countStatement.setString(1, fromBoundary);
		countStatement.setString(2, toBoundary);
		countStatement.setString(3, writerName);

		ResultSet rs = countStatement.executeQuery();
		rs.next();
		count = rs.getInt(1);
		rs.close();
		countStatement.close();
		try {
			if (connection != null)
				connection.close();
			System.out.println("connection closed ....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCount() {
		return " العدد هو  :   " + count;
	}
}
