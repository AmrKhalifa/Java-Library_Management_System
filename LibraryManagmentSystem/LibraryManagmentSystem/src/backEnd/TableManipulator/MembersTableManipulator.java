package backEnd.TableManipulator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import backEnd.DatabaseAccessor;
import backEnd.Model.Member;
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

		try {
			connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

			stmt.close();

		}
		try {
			updateStatment.close();
			insertStatment.close();
			disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void load() throws SQLException {

		try {
			connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		members.clear();

		String SQLloadStatement = "SELECT member_id,member_name,study_year,study_field,sim_category,whats_app_number"
				+ ",joining_date_time  FROM library.members ";

		Statement loadStatement = connection.createStatement();
		ResultSet result = loadStatement.executeQuery(SQLloadStatement);

		System.out.println(" creation of result set successful ... ");
		while (result.next()) {

			int id = result.getInt("member_id");
			String name = result.getString("member_name");
			StudyYear year = doYears(result.getString("joining_date_time"), result.getString("study_year"));
			String field = result.getString("study_field");
			SIMCategory sim = SIMCategory.valueOf(result.getString("sim_category"));
			String number = result.getString("whats_app_number");
			String date = result.getString("joining_date_time");

			Member newMember = new Member(id, name, year, field, sim, number, date);

			members.add(newMember);
			System.out.println("added member :" + newMember.getMemberName() + "\n");
		}
		result.close();
		loadStatement.close();
		try {
			disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private StudyYear doYears(String date, String year) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String loadDate = sdf.format(cal.getTime());

		String addition = date.substring(0, 4);
		String loadYear = loadDate.substring(0, 4);
		int subtraction = (Integer.parseInt(loadYear) - Integer.parseInt(addition));

		System.out.println(addition);
		System.out.println(loadYear);
		System.out.println(subtraction);

		if (subtraction == 0)
			return StudyYear.valueOf(year);

		else {
			switch (year) {
			case "الأولى":
				return StudyYear.الثانية;
			case "الثانية":
				return StudyYear.الثالثة;
			case "الثالثة":
				return StudyYear.الرابعة;
			case "الرابعة":
				return StudyYear.الخامسة;
			case "الخامسة":
				return StudyYear.خريج;
			case "خريج":
				return StudyYear.خريج;

			}
		}

		return StudyYear.valueOf(year);

	}

}
