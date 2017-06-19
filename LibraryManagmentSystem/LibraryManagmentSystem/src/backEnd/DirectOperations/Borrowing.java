package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import backEnd.DatabaseAccessor;
import view.masterPanel.EventsAndListeners.BorrowBookEvent;

public class Borrowing extends DatabaseAccessor {

	public Borrowing(BorrowBookEvent event) throws SQLException, Exception {
		rentBook(event);
	}

	private void rentBook(BorrowBookEvent event) throws SQLException, Exception {
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

	private void addToMemberBookTable(long memberId, long bookId, String rentDate) throws SQLException, Exception {
		String SQLCheckMember = "SELECT COUNT(*) FROM library.members WHERE member_id = ? ";
		String SQLGetCount = "SELECT copies_count FROM library.books WHERE book_id=?";
		String SQLGetPK = "SELECT COUNT(*) FROM library.member_book_borrow";
		String SQLAdd = "INSERT INTO library.member_book_borrow values(?,?,?,?)";
		String SQLAddUnDeleted = "INSERT INTO library.member_book_borrow_undeleteable values(?,?,?,?)";
		String SQLUpdate = "UPDATE library.books SET copies_count =? WHERE book_id =?";

		PreparedStatement checkMember = connection.prepareStatement(SQLCheckMember);
		checkMember.setLong(1, memberId);
		ResultSet checkMemberResult = checkMember.executeQuery();
		checkMemberResult.next();

		if (checkMemberResult.getInt(1) == 1) {
			try (PreparedStatement countStatement = connection.prepareStatement(SQLGetCount);
					Statement getPK = connection.createStatement();
					PreparedStatement insertStatement = connection.prepareStatement(SQLAdd);
					PreparedStatement insertUndeletedStatement = connection.prepareStatement(SQLAddUnDeleted);
					PreparedStatement updateStatement = connection.prepareStatement(SQLUpdate);) {
				countStatement.setLong(1, bookId);
				ResultSet countResult = countStatement.executeQuery();
				countResult.next();
				int count = countResult.getInt(1);
				if (count != 0) {
					ResultSet rs = getPK.executeQuery(SQLGetPK);
					rs.next();
					//int PK = rs.getInt(1);
					
					Random rand = new Random();
					int n = rand.nextInt(5) + 1;
					int o = rand.nextInt(20) + 5;
					int p = rand.nextInt(25) +20;
					int z= rand.nextInt(50)+30;
					int PK = n*o*p*z ;
					int r = rand.nextInt(1000)+200;
					
					insertStatement.setInt(1, PK+r);
					insertUndeletedStatement.setInt(1,PK+r);
					insertStatement.setLong(2, memberId);
					insertUndeletedStatement.setLong(2, memberId);
					insertStatement.setLong(3, bookId);
					insertUndeletedStatement.setLong(3, bookId);
					insertStatement.setString(4, rentDate);
					insertUndeletedStatement.setString(4, rentDate);
					
					insertStatement.executeUpdate();
					insertUndeletedStatement.executeUpdate();

					updateStatement.setInt(1, count - 1);
					updateStatement.setLong(2, bookId);
					updateStatement.executeUpdate();
					
					countResult.close();
					rs.close();
					getPK.close();
					checkMemberResult.close();
					checkMember.close();

					try {
						if (connection != null)
							connection.close();
						System.out.println("connection closed ....");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
				
			}

		}

		else {
			throw new Exception();
		}
	}
}