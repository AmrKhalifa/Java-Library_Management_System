package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import backEnd.DatabaseAccessor;
import view.masterPanel.EventsAndListeners.SearchForBookEvent;

public class SearchForBook extends DatabaseAccessor {

	private String bookTitle;
	private BookInfo bookInfo;

	public SearchForBook(SearchForBookEvent event) throws Exception {
		extractData(event);
		connect();
		getInfo(bookTitle);

	}

	private void extractData(SearchForBookEvent event) {

		this.bookTitle = event.getBookName();
	}

	private void getInfo(String bookTitle) throws SQLException {
		String SQLSearch = "SELECT book_id ,book_writer,publishing_house,copies_count FROM library.books"
				+ " WHERE  book_title LIKE '%" + bookTitle + "%'";
		String SQLInitialCount = "SELECT initial_copies_count FROM library.books_initial_copies"
				+ " WHERE book_id = ? ";

		Statement searchStatement = connection.createStatement();
		PreparedStatement initialCountStatement = connection.prepareStatement(SQLInitialCount);

		ResultSet searchResult = searchStatement.executeQuery(SQLSearch);
		if (searchResult.next()) {
			int book_id = searchResult.getInt(1);

			initialCountStatement.setInt(1, book_id);

			ResultSet initCount = initialCountStatement.executeQuery();
			if (initCount.next()) {

				int initialCount = initCount.getInt(1);
				int avilableCount = searchResult.getInt(4);

				String bookWriter = searchResult.getString(2);
				String house = searchResult.getString(3);
				int rentedCount = initialCount - avilableCount;

				bookInfo = new BookInfo(bookWriter, house, avilableCount, rentedCount);
			}
			searchResult.close();
			initialCountStatement.close();
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

	public class BookInfo {
		String bookWriter;
		String publishingHouse;
		int avilableCopies;
		int borrowedCopies;

		public BookInfo(String bookWriter, String publishingHouse, int avilableCopies, int rentedCopies) {
			this.bookWriter = bookWriter;
			this.publishingHouse = publishingHouse;
			this.avilableCopies = avilableCopies;
			this.borrowedCopies = rentedCopies;
		}

		public String getBookWriter() {
			return bookWriter;
		}

		public String getPublishingHouse() {
			return publishingHouse;
		}

		public String getAvilableCopies() {
			return "" + avilableCopies;
		}

		public String getBorrowedCopies() {
			return "" + borrowedCopies;
		}

	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}
}
