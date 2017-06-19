package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backEnd.DatabaseAccessor;
import view.masterPanel.AdvancedSearcher.Events.ReturningCountEvent;

public class ReturningCount extends DatabaseAccessor {

	private String fromBoundary;
	private String toBoundary;
	int count;

	public ReturningCount(ReturningCountEvent event) throws Exception {
		extractDates(event);
		connect();
		countRentingBetweenDates(fromBoundary, toBoundary);

	}

	private void extractDates(ReturningCountEvent event) {
		fromBoundary = event.getFromBoundary();
		toBoundary = event.getToBoundary();
	}

	private void countRentingBetweenDates(String fromBoundary, String toBoundary) throws SQLException {

		String SQLCount = "SELECT COUNT(*) FROM library.member_book_returning WHERE DATE(returning_date_time) >= ? "
				+ "AND DATE(returning_date_time) <= ?";

		PreparedStatement countStatement = connection.prepareStatement(SQLCount);

		countStatement.setString(1, fromBoundary);
		countStatement.setString(2, toBoundary);

		ResultSet rs = countStatement.executeQuery();
		rs.next();
		count = rs.getInt(1);

		rs.close();
		countStatement.close();

		try {
			if (connection != null)
				connection.close();
			System.out.println("......connection closed ....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCount() {
		return " العدد هو  :   " + count;
	}
}
