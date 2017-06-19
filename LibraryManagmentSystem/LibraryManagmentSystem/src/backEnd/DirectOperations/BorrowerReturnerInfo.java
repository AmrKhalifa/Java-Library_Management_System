package backEnd.DirectOperations;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import backEnd.BorrowerData;
import backEnd.BorrowerReturner;
import backEnd.DatabaseAccessor;

public class BorrowerReturnerInfo extends DatabaseAccessor {

	LinkedList<BorrowerReturner> list = new LinkedList<>();

	public BorrowerReturnerInfo(String tableName) throws Exception {

		loadData(tableName);

	}

	private void loadData(String tableName) throws Exception {
		connect();

		String SQLGETInfo = " SELECT member_name FROM " + tableName + " "
				+ " NATURAL JOIN library.members GROUP BY member_id " + " ORDER BY COUNT(member_id) DESC ";

		
		Statement getInfo = connection.createStatement();
		ResultSet Info = getInfo.executeQuery(SQLGETInfo);

		while (Info.next()) {
			String name = Info.getString(1);

			list.add(new BorrowerReturner(name));
		}
	}

	public LinkedList<BorrowerReturner> getInfo() {
		return list;
	}

}
