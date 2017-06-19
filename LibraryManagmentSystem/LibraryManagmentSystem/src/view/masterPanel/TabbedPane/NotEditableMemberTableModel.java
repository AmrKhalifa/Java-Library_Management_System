package view.masterPanel.TabbedPane;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import backEnd.Model.Book;
import backEnd.Model.Member;

public class NotEditableMemberTableModel extends AbstractTableModel {

	 LinkedList<Member> members = new LinkedList<>();

	public void setData(LinkedList<Member> members) {
		this.members=members;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return members.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Member member = members.get(row);

		switch (col) {
		case 0:
			return member.getJoiningDateTime();
		case 1:
			return member.getWhatsAppNumber();
		case 2:
			return member.getSimCategory();
		case 3:
			return member.getStudyField();
		case 4:
			return member.getStudyYear();
		case 5:
			return member.getMemberName();
		case 6:
			return member.getId();
		}

		return null;
	}

	String[] colName = { "تاريخ الانضمام       ", "رقم الوتسآآب       ", "الشريحة", "التخصص                 ",
			"العام الدراسي", "اسم العضو            ", "كود العضو" };
	public String getColumnName(int col) {

		return colName[col];
	}

}
