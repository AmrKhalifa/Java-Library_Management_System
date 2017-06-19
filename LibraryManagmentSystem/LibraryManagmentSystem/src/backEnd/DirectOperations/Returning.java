package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JOptionPane;

import backEnd.DatabaseAccessor;
import view.masterPanel.EventsAndListeners.ReturnBookEvent;

public class Returning extends DatabaseAccessor {

	public Returning(ReturnBookEvent event) throws SQLException, Exception {
		returnBook(event);
	}

	private void returnBook(ReturnBookEvent event) throws SQLException, Exception {
		long memberId = event.getMemberCode();
		long bookId = event.getBookCode();
		String rentDate = event.getReturningDateTime();
		connect();
		addToMemberBookTable(memberId, bookId, rentDate);

	}

	private void addToMemberBookTable(long memberId, long bookId, String returnDate)
			throws SQLException ,Exception
	{
		String SQLCheckMember = "SELECT COUNT(*) FROM library.members WHERE member_id = ? ";
		String SQLGetCount = "SELECT copies_count FROM library.books WHERE book_id=?";
		String SQLCheckBorrow = "SELECT COUNT(*) FROM library.member_book_borrow WHERE  book_id = ? AND member_id = ? ";
		String SQLGetPK = "SELECT COUNT(*) FROM library.member_book_returning";
		String SQLAdd = "INSERT INTO .member_book_returning values(?,?,?,?)";
		//String SQLAdd
		String SQLUpdate = "UPDATE library.books SET copies_count =? WHERE book_id =?";
		String SQLDeleteBorrow= "DELETE FROM library.member_book_borrow WHERE book_id = ? "
				+ " AND member_id = ? ";

		PreparedStatement checkMember = connection.prepareStatement(SQLCheckMember);
		checkMember.setLong(1, memberId);
		ResultSet checkMemberResult = checkMember.executeQuery();
		checkMemberResult.next();
		if (checkMemberResult.getInt(1) == 1) { 
				
			try(PreparedStatement checkBorrowStmt = connection.prepareStatement(SQLCheckBorrow))
			{	
				checkBorrowStmt.setLong(1, bookId);
				checkBorrowStmt.setLong(2, memberId);
				ResultSet borrowResult = checkBorrowStmt.executeQuery();
				borrowResult.next();
				if(borrowResult.getInt(1)> 0)
				{
					try (PreparedStatement countStatement = connection.prepareStatement(SQLGetCount);
							Statement getPK = connection.createStatement();
							PreparedStatement insertStatement = connection.prepareStatement(SQLAdd);
							PreparedStatement updateStatement = connection.prepareStatement(SQLUpdate);

					) {

						countStatement.setLong(1, bookId);
						ResultSet countResult = countStatement.executeQuery();
						countResult.next();
						int count = countResult.getInt(1);
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
						insertStatement.setLong(2, memberId);
						insertStatement.setLong(3, bookId);
						insertStatement.setString(4, returnDate);
						insertStatement.executeUpdate();

						updateStatement.setInt(1, count + 1);
						updateStatement.setLong(2, bookId);
						updateStatement.executeUpdate();
						
						PreparedStatement delete = connection.prepareStatement(SQLDeleteBorrow);
						delete.setLong(1, bookId);
						delete.setLong(2, memberId);
						delete.executeUpdate();
						
						
						delete.close();
						rs.close();
						getPK.close();
						checkMemberResult.close();
						checkMember.close();
						checkBorrowStmt.close();
						try {
							if (connection != null)
								connection.close();
							System.out.println("connection closed ....");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}


				}
				else {
					JOptionPane.showInternalMessageDialog(null, "Œÿ√ ›Ì «·≈—Ã«⁄", "·«Ì„ﬂ‰ ≈—Ã«⁄ ﬂ «» ·„ Ì „ «” ⁄«— Â", JOptionPane.WARNING_MESSAGE);
				}
				}
				}
		else

		{
			JOptionPane.showInternalMessageDialog(null, "Œÿ√ ›Ì «·≈—Ã«⁄", "⁄›Ê«  €Ì— „”Ã· ﬂ⁄÷Ê", JOptionPane.WARNING_MESSAGE);
			throw new Exception();
		}
				
		
			
			
}}