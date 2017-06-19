package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import backEnd.DatabaseAccessor;

public class DeleteRowInMemberTable extends DatabaseAccessor {

	long member_id;

	public DeleteRowInMemberTable(long member_id) throws Exception {

		connect();
		deleteRow(member_id);

	}

	private void deleteRow(long member_id) throws SQLException {
		String SQLDelete = "DELETE FROM library.members WHERE member_id = ?";
		PreparedStatement deleteStatement = connection.prepareStatement(SQLDelete);

		deleteStatement.setLong(1, member_id);
		deleteStatement.executeUpdate();

		deleteStatement.close();
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
