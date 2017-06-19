package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import backEnd.DatabaseAccessor;
import backEnd.Model.BorrowedBook;
import view.masterPanel.AdvancedSearcher.Events.BooksLogByWriterEvent;

public class BookBorrowLogByWriter extends DatabaseAccessor {

	private String writerName;
	private String fromBoundary;
	private String toBoundary;
	int count = 0;
	LinkedList<BorrowedBook> books = new LinkedList<>();

	public BookBorrowLogByWriter(BooksLogByWriterEvent event) throws Exception {
		extractData(event);
		connect();

		countBooksOfWriterBetweenDates(writerName, fromBoundary, toBoundary);

	}

	private void extractData(BooksLogByWriterEvent event) {
		this.writerName = event.getWriterName();
		this.fromBoundary = event.getFromBoundary();
		this.toBoundary = event.getToBoundary();
	}

	private void countBooksOfWriterBetweenDates(String writerName, String fromBoundar, String toBoundary)
			throws Exception {

		// be careful the next SQL statement selects distinctly
		String SQLGetId = "SELECT DISTINCT book_id FROM library.member_book_borrow WHERE DATE(borrow_date) >= ? "
				+ "AND DATE(borrow_date)<= ?";
		String SQLgetWriterAndTitle = "SELECT book_writer,book_title ,copies_count "
				+ "FROM library.books WHERE book_id = ?";
		String SQLgetInitialCopies = "SELECT initial_copies_count FROM library.books_initial_copies "
				+ "WHERE book_id = ?";

		PreparedStatement getIdStatement = connection.prepareStatement(SQLGetId);
		PreparedStatement getWriterAndTitleStatement = connection.prepareStatement(SQLgetWriterAndTitle);
		PreparedStatement getInitialCopiestStatement = connection.prepareStatement(SQLgetInitialCopies);

		getIdStatement.setString(1, fromBoundary);
		getIdStatement.setString(2, toBoundary);
		ResultSet IDrs = getIdStatement.executeQuery();
		System.out.println("attempting to enter the while loop");
		while (IDrs.next()) {
			int id = IDrs.getInt(1);
			System.out.println("\n *" + id + "*\n");

			getWriterAndTitleStatement.setInt(1, id);

			ResultSet writerAndTitles = getWriterAndTitleStatement.executeQuery();
			writerAndTitles.next();
			String writer = writerAndTitles.getString(1).toString();
			System.out.println("\n *" + writer + "*\n");
			if (writer.replaceAll("\\s+", "").equalsIgnoreCase(writerName.replaceAll("\\s+",
					""))) {/*
							 * System.out.println("equals"); ++count;
							 */
				getInitialCopiestStatement.setInt(1, id);
				ResultSet copiesCount = getInitialCopiestStatement.executeQuery();
				copiesCount.next();

				String title = writerAndTitles.getString(2);
				int availabeCopiesCount = writerAndTitles.getInt(3);

				int initialCopiesCount = copiesCount.getInt(1);

				int borrowedCopiesCount = initialCopiesCount - availabeCopiesCount;

				books.add(new BorrowedBook(title, borrowedCopiesCount));
				// titles.add(writerAndTitleRs.getString(2));

			}
			System.out.println("the count is : " + count);
			writerAndTitles.close();
		}

		getIdStatement.close();
		getWriterAndTitleStatement.close();
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
