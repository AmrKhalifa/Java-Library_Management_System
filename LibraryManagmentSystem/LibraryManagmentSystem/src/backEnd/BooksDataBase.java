package backEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class BooksDataBase extends DatabaseAccessor {

	private LinkedList<Book> books;

	public BooksDataBase() {
		books = new LinkedList<>();
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void removeBook(Book book) {
		books.remove(book);
	}

	public LinkedList<Book> getBooks() {
		if (books.isEmpty())
			Book.count = 1000;

		return books;
	}

	public void save() throws SQLException {

		final String SQLInsertStatement = "INSERT INTO library.books (book_id,book_title,book_writer,publishing_house"
				+ ",copies_count,addition_date_time) VALUES(?,?,?,?,?,?) ";

		final String SQLUpdateStatement = "UPDATE library.books set book_title =?, book_writer=?,publishing_house =?,"
				+ "copies_count =?,addition_date_time =? WHERE book_id=? ";

		PreparedStatement insertStatment = connection.prepareStatement(SQLInsertStatement);
		PreparedStatement updateStatment = connection.prepareStatement(SQLUpdateStatement);

		for (Book book : books) {
			long id = book.getId();
			String title = book.getBookTitle();
			String writer = book.getWriterName();
			String publishingHouse = book.getPublishingHouseName();
			int copies = book.getCopiesCount();
			String dateTime = book.getAdditionDateTime();

			String bookCheckSQLStmt = "select count(*) as count from library.book where book_id = ?";
			PreparedStatement stmt = connection.prepareStatement(bookCheckSQLStmt, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			System.out.println("the count of the books with id = " + id + " is " + count);

			if (count == 0) {
				System.out.println(" inserting book with ID " + id);

				int col = 1;
				insertStatment.setLong(col++, id);
				insertStatment.setString(col++, title);
				insertStatment.setString(col++, writer);
				insertStatment.setString(col++, publishingHouse);
				insertStatment.setInt(col++, copies);
				insertStatment.setString(col++, dateTime);

				insertStatment.executeUpdate();

				System.out.println(" done insertion ...:) ");
			} else {
				System.out.println("updating book with id  " + id);

				int col = 1;

				updateStatment.setString(col++, title);
				updateStatment.setString(col++, writer);
				updateStatment.setString(col++, publishingHouse);
				updateStatment.setInt(col++, copies);
				updateStatment.setString(col++, dateTime);
				updateStatment.setLong(col++, id);

				updateStatment.executeUpdate();

				System.out.println("done update ... :)");

			}
			rs.close();

		}

	}

	public void load() throws SQLException {

		books.clear();

		String SQLloadStatement = "SELECT book_id,book_title,book_writer,publishing_house,copies_count,addition_date_time"
				+ " FROM library.books ";
		Statement loadStatement = connection.createStatement();
		ResultSet result = loadStatement.executeQuery(SQLloadStatement);

		// System.out.println(" creation of result set successful ... ");
		while (result.next()) {

			int id = result.getInt("book_id");
			String title = result.getString("book_title");
			String writerName = result.getString("book_writer");
			String publishingHouseName = result.getString("publishing_house");
			int copiesCount = result.getInt("copies_count");
			String additionDateTime = result.getString("addition_date_time");

			Book newBook = new Book(id, title, writerName, publishingHouseName, copiesCount, additionDateTime);

			books.add(newBook);
			System.out.println(newBook.getBookTitle());
		}

	}

}
