package backEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import view.masterPanel.AdvancedSearcher.Events.RentingCountEvent;

public class BorrowCount extends DatabaseAccessor {

	private String fromBoundary;
	private String toBoundary;
	int count;

	public BorrowCount(RentingCountEvent event) throws Exception {
		extractDates(event);
		connect();
		try {
			countRentingBetweenDates(fromBoundary, toBoundary);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void extractDates(RentingCountEvent event) {
		fromBoundary = event.getFromBoundary();
		toBoundary = event.getToBoundary();
	}

	private void countRentingBetweenDates(String fromBoundary, String toBoundary) throws SQLException {

		String SQLCount = "SELECT COUNT(*) FROM library.member_book_renting WHERE DATE(renting_date) >= ? "
				+ "AND DATE(renting_date) <= ?";

		PreparedStatement countStatement = connection.prepareStatement(SQLCount);

		countStatement.setString(1, fromBoundary);
		countStatement.setString(2, toBoundary);

		ResultSet rs = countStatement.executeQuery();
		rs.next();
		count = rs.getInt(1);

	}

	public String getCount() {
		return " العدد هو  :   " + count;
	}
}
