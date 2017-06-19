package backEnd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import view.masterPanel.Enums.SIMCategory;
import view.masterPanel.Enums.StudyYear;

public class MembersTableManipulator extends DatabaseAccessor {

	private LinkedList<Member> members = new LinkedList<>();

	public void MembersDataBase() {
	}

	public void addMember(Member member) {
		members.add(member);
	}

	public void removeMember(Member member) {
		members.remove(member);
	}

	public LinkedList<Member> getMembers() {
		if (members.isEmpty())
			Member.count = 100;

		return members;
	}

	public void save() throws SQLException {

		final String SQLInsertStatement = "INSERT INTO library.members (member_id,member_name,study_year,study_field,sim_category"
				+ ",whats_app_number,joining_date_time) VALUES(?,?,?,?,?,?,?) ";

		final String SQLUpdateStatement = "UPDATE library.members set member_name =?, study_year=?,study_field =?,"
				+ "sim_category =?,whats_app_number =? ,joining_date_time =? WHERE member_id=? ";

		PreparedStatement insertStatment = connection.prepareStatement(SQLInsertStatement);
		PreparedStatement updateStatment = connection.prepareStatement(SQLUpdateStatement);

		for (Member member : members) {

			long id = member.getId();
			String name = member.getMemberName();
			String year = member.getStudyYear().name();
			String field = member.getStudyField();
			String sim = member.getSimCategory().name();
			String number = member.getWhatsAppNumber();
			String date = member.getJoiningDateTime();

			String bookCheckSQLStmt = "select count(*) as count from library.members where member_id = ?";
			PreparedStatement stmt = connection.prepareStatement(bookCheckSQLStmt, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			System.out.println("the count of the members with id = " + id + " is " + count);

			if (count == 0) {
				System.out.println(" inserting book with ID " + id);

				int col = 1;

				insertStatment.setLong(col++, id);
				insertStatment.setString(col++, name);
				insertStatment.setString(col++, year);
				insertStatment.setString(col++, field);
				insertStatment.setString(col++, sim);
				insertStatment.setString(col++, number);
				insertStatment.setString(col++, date);

				insertStatment.executeUpdate();

				System.out.println(" done insertion ...:) \n ");
			} else {
				System.out.println("updating member with id  " + id);

				int col = 1;

				updateStatment.setString(col++, name);
				updateStatment.setString(col++, year);
				updateStatment.setString(col++, field);
				updateStatment.setString(col++, sim);
				updateStatment.setString(col++, number);
				updateStatment.setString(col++, date);
				updateStatment.setLong(col++, id);

				updateStatment.executeUpdate();

				System.out.println("done update ... :) \n");

			}
			rs.close();

		}

	}

	public void load() throws SQLException {

		members.clear();

		String SQLloadStatement = "SELECT member_id,member_name,study_year,study_field,sim_category,whats_app_number"
				+ ",joining_date_time  FROM library.members ";

		Statement loadStatement = connection.createStatement();
		ResultSet result = loadStatement.executeQuery(SQLloadStatement);

		System.out.println(" creation of result set successful ... ");
		while (result.next()) {

			int id = result.getInt("member_id");
			String name = result.getString("member_name");
			StudyYear year = StudyYear.valueOf(result.getString("study_year"));
			String field = result.getString("study_field");
			SIMCategory sim = SIMCategory.valueOf(result.getString("sim_category"));
			String number = result.getString("whats_app_number");
			String date = result.getString("joining_date_time");

			Member newMember = new Member(id, name, year, field, sim, number, date);

			members.add(newMember);
			System.out.println("added member :" + newMember.getMemberName() + "\n");
		}

	}

	public void configure(int port, String username, String password) throws Exception {

	}

}
