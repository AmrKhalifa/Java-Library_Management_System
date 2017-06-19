package backEnd.DirectOperations;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import backEnd.BorrowerData;
import backEnd.DatabaseAccessor;

public class BorrowInfo extends DatabaseAccessor {

	LinkedList<BorrowerData> borrowersData = new LinkedList<>();

	public BorrowInfo() throws Exception {
		loadData();

	}

	private void loadData() throws Exception

	{
		connect();

		String SQLGETBorrowersInfo = "SELECT  member_name, book_title, borrow_date " + "FROM library.books, "
				+ "library.members, " + "library.member_book_borrow " + "WHERE "
				+ "    library.books.book_id = library.member_book_borrow.book_id "
				+ "     AND library.members.member_id = library.member_book_borrow.member_id ";

		Statement getBorrowersInfo = connection.createStatement();
		ResultSet borrowersInfo = getBorrowersInfo.executeQuery(SQLGETBorrowersInfo);

		while (borrowersInfo.next()) {
			String name = borrowersInfo.getString(1);
			String title = borrowersInfo.getString(2);
			String date = borrowersInfo.getString(3);

			borrowersData.add(new BorrowerData(name, title, date));

		}

	}

	public LinkedList<BorrowerData> getBorrowersData() {
		return borrowersData;
	}

}
