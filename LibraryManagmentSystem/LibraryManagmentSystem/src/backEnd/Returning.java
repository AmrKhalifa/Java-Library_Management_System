package backEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import view.masterPanel.EventsAndListeners.ReturnBookEvent;

public class Returning extends DatabaseAccessor {
	

	public Returning(ReturnBookEvent event) throws Exception
	{
		returnBook(event);
	}
	
	private void returnBook(ReturnBookEvent event) throws Exception
	{
		long memberId = event.getMemberCode();
		long bookId = event.getBookCode();
		String rentDate = event.getReturningDateTime();
		connect();
		try {
			addToMemberBookTable(memberId,bookId,rentDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private void addToMemberBookTable(long memberId, long bookId,String returnDate) throws SQLException
	{
		
		String SQLGetCount = "SELECT copies_count FROM library.books WHERE book_id=?";
		String SQLGetPK="SELECT COUNT(*) FROM library.member_book_returning";
		String SQLAdd = "INSERT INTO .member_book_returning values(?,?,?,?)";
		String SQLUpdate="UPDATE library.books SET copies_count =? WHERE book_id =?";
		
		
		PreparedStatement countStatement = connection.prepareStatement(SQLGetCount);
		Statement getPK=connection.createStatement();
		PreparedStatement insertStatement = connection.prepareStatement(SQLAdd);
		PreparedStatement updateStatement = connection.prepareStatement(SQLUpdate);
		
		
		countStatement.setLong(1, bookId);
		ResultSet countResult = countStatement.executeQuery();
		countResult.next();
		
		try {
			int count = countResult.getInt(1);
			ResultSet rs=getPK.executeQuery(SQLGetPK);
				rs.next();
				int PK=rs.getInt(1);
				
				insertStatement.setInt(1, ++PK);
				insertStatement.setLong(2, memberId);
				insertStatement.setLong(3, bookId);
				insertStatement.setString(4, returnDate);
				insertStatement.executeUpdate();
				
				updateStatement.setInt(1, count+1);
				updateStatement.setLong(2, bookId);
				updateStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
}
