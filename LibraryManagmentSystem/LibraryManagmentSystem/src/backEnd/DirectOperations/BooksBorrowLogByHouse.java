package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import backEnd.DatabaseAccessor;
import backEnd.Model.BorrowedBook;
import view.masterPanel.AdvancedSearcher.Events.BooksLogByHouseEvent;

public class BooksBorrowLogByHouse extends DatabaseAccessor {

	private String houseName;
	private String fromBoundary;
	private String toBoundary;
	int count = 0;
	LinkedList<BorrowedBook> books = new LinkedList<>();

	public BooksBorrowLogByHouse(BooksLogByHouseEvent event) throws Exception {
		extractData(event);
		connect();
		countBooksOfWriterBetweenDates(houseName, fromBoundary, toBoundary);
	}

	private void extractData(BooksLogByHouseEvent event) {
		this.houseName = event.getPublishingHouseName();
		this.fromBoundary = event.getFromBoundary();
		this.toBoundary = event.getToBoundary();
	}

	private void countBooksOfWriterBetweenDates(String houseName, String fromBoundar, String toBoundary)
			throws Exception {

		// be careful the next SQL statement selects distinctly
		String SQLGetId = "SELECT DISTINCT book_id FROM library.member_book_borrow WHERE DATE(borrow_date) >= ? "
				+ "AND DATE(borrow_date)<= ?";
		String SQLgetHouseAndTitle = "SELECT publishing_house,book_title ,copies_count "
				+ "FROM library.books WHERE book_id = ?";
		String SQLgetInitialCopies = "SELECT initial_copies_count FROM library.books_initial_copies "
				+ "WHERE book_id = ?";

		PreparedStatement getIdStatement = connection.prepareStatement(SQLGetId);
		PreparedStatement getHouseAndTitleStatement = connection.prepareStatement(SQLgetHouseAndTitle);
		PreparedStatement getInitialCopiestStatement = connection.prepareStatement(SQLgetInitialCopies);

		getIdStatement.setString(1, fromBoundary);
		getIdStatement.setString(2, toBoundary);
		ResultSet IDrs = getIdStatement.executeQuery();
		System.out.println("attempting to enter the while loop");
		while (IDrs.next()) {
			int id = IDrs.getInt(1);
			System.out.println("\n *" + id + "*\n");

			getHouseAndTitleStatement.setInt(1, id);

			ResultSet housesAndTitles = getHouseAndTitleStatement.executeQuery();
			housesAndTitles.next();
			String house = housesAndTitles.getString(1).toString();
			System.out.println("\n *" + house + "*\n");
			if (house.replaceAll("\\s+", "").equalsIgnoreCase(houseName.replaceAll("\\s+",
					""))) {/*
							 * System.out.println("equals"); ++count;
							 */
				getInitialCopiestStatement.setInt(1, id);
				ResultSet copiesCount = getInitialCopiestStatement.executeQuery();
				copiesCount.next();

				String title = housesAndTitles.getString(2);
				int availabeCopiesCount = housesAndTitles.getInt(3);

				int initialCopiesCount = copiesCount.getInt(1);

				int borrowedCopiesCount = initialCopiesCount - availabeCopiesCount;

				books.add(new BorrowedBook(title, borrowedCopiesCount));
				// titles.add(writerAndTitleRs.getString(2));

			}
			System.out.println("the count is : " + count);
			housesAndTitles.close();
		}

		getIdStatement.close();
		getHouseAndTitleStatement.close();
		IDrs.close();

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

	public LinkedList<BorrowedBook> getBooks() {
		return books;
	}
}
