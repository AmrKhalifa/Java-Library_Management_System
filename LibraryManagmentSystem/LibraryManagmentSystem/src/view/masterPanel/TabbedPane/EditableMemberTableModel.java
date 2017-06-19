package view.masterPanel.TabbedPane;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import backEnd.Model.Member;
import view.masterPanel.Enums.SIMCategory;
import view.masterPanel.Enums.StudyYear;

public class EditableMemberTableModel extends AbstractTableModel {

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

	@Override
	public Class<?> getColumnClass(int col) {
		
	
		switch(col){
		
		case 0:
			return String.class;
					
		case 1:
			return String.class;
			
		case 2:
			return SIMCategory.class;
		
		case 3:
			return String.class;
			
		case 4:
			return StudyYear.class;
			
		case 5:
			return String.class;
			
		case 6:
			return int.class;
			
			
		default: 
			return super.getColumnClass(col);	
		}
			
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		
		switch (col)
		{
		case 0:
			return true;
		case 1:
			return true;
		case 2:
			return true;
		case 3:
			return true;
		case 4:
			return true;
		case 5:
			return true;
		case 6:
			return false;
		}
		
		return super.isCellEditable(row, col);
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		
		Member member= members.get(row);
		
		switch(col)
		{
			case 0: 
				member.setJoiningDateTime((String)value);
				break;
			case 1:
				member.setWhatsAppNumber((String)value);
				break;
			case 2:
				member.setSimCategory(SIMCategory.valueOf(value.toString()));
				break;
			case 3:
				member.setStudyField((String)value);
				break;
			case 4:
				member.setStudyYear(StudyYear.valueOf(value.toString()));
				break;
			case 5:
				member.setMemberName((String)value);
				break;
		}
		
	}

	
}
