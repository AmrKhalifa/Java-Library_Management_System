package view.masterPanel.settings;

import java.net.Proxy.Type;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import backEnd.Model.User;

public class EditUserTableModel extends AbstractTableModel {

	LinkedList<User> users = new LinkedList<>();

	public void setData(LinkedList<User> users) {
		this.users = users;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return users.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		User user = users.get(row);

		switch (col) {

		case 0:
			return user.getUserType();
		case 1:
			return user.getUserpassword();
		case 2:
			return user.getUsername();
		case 3:
			return user.getUserID();

		}

		return null;
	}

	@Override
	public String getColumnName(int col) {

		String[] colName = { "النوع", "رمز المرور", "اسم المستخدم", "المستخدم" };

		return colName[col];
	}

	@Override
	public boolean isCellEditable(int row, int col) {

		switch (col) {
		case 0:
			if (row == 0)
				return false;
			return true;
		case 1:
			return true;
		case 2:
			return true;

		}

		return false;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {

		User user = users.get(row);

		switch (col) {
		case 0:
			user.setUserType(value.toString());
			break;
		case 1:
			user.setUserpassword(value.toString());
			break;
		case 2:
			user.setUsername(value.toString());
			break;

		}

		super.setValueAt(value, row, col);
	}

	@Override
	public Class<?> getColumnClass(int col) {
		switch (col) {
		case 0:
			return Types.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return int.class;
		}
		return super.getColumnClass(col);
	}

}
