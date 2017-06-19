package backEnd.DirectOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import backEnd.DatabaseAccessor;
import view.masterPanel.EventsAndListeners.AddMemberEvent;

public class AddMember extends DatabaseAccessor {

	String name;
	String year;
	String field;
	String sim;
	String number;
	String date;

	public AddMember(AddMemberEvent event) throws Exception, SQLException {
		connect();
		extractData(event);
		addToDataBase();
	}

	public void extractData(AddMemberEvent event) {
		name = event.getMemberName();
		year = event.getStudyYear();
		field = event.getStudyField();
		sim = event.getSimCategory();
		number = event.getWhatsAppNumber();
		date = event.getJoingingDateTime();

	}

	public void addToDataBase() throws SQLException {

		String SQLCheck = "SELECT COUNT(*) FROM library.members ";
		String SQLCount = "SELECT member_id FROM library.members ORDER BY member_id";
		Statement checkStatement = connection.createStatement();
		Statement countStatement = connection.createStatement();
		ResultSet checkResult = checkStatement.executeQuery(SQLCheck);
		checkResult.last();
		int checkEmpty = checkResult.getInt(1);

		if (checkEmpty != 0) {
			ResultSet countResult = countStatement.executeQuery(SQLCount);
			countResult.last();
			int count = countResult.getInt(1);

			countResult.close();
			final String SQLInsertStatement = "INSERT INTO library.members (member_id,member_name,study_year,study_field"
					+ ",sim_category,whats_app_number,joining_date_time) VALUES(?,?,?,?,?,?,?) ";

			PreparedStatement insertStatement = connection.prepareStatement(SQLInsertStatement);
			insertStatement.setInt(1, count + 1);
			insertStatement.setString(2, name);
			insertStatement.setString(3, year);
			insertStatement.setString(4, field);
			insertStatement.setString(5, sim);
			insertStatement.setString(6, number);
			insertStatement.setString(7, date);

			insertStatement.executeUpdate();
			insertStatement.close();
		} else {
			final String SQLFirstInsertStatement = "INSERT INTO library.members (member_id,member_name,study_year,"
					+ "study_field,sim_category,whats_app_number,joining_date_time) VALUES(?,?,?,?,?,?,?) ";

			PreparedStatement insertStatement = connection.prepareStatement(SQLFirstInsertStatement);
			insertStatement.setInt(1, 101);
			insertStatement.setString(2, name);
			insertStatement.setString(3, year);
			insertStatement.setString(4, field);
			insertStatement.setString(5, sim);
			insertStatement.setString(6, number);
			insertStatement.setString(7, date);

			insertStatement.executeUpdate();
			insertStatement.close();
		}

		checkResult.close();
		checkStatement.close();
		countStatement.close();

		try {
			if (connection != null)
				connection.close();
			System.out.println("connection closed ....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
