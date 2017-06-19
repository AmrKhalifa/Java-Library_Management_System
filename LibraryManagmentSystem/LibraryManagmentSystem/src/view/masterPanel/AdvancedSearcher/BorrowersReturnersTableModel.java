package view.masterPanel.AdvancedSearcher;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import backEnd.BorrowerReturner;

public class BorrowersReturnersTableModel extends AbstractTableModel {

	LinkedList<BorrowerReturner> list = new LinkedList<>();

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub

		BorrowerReturner entity = list.get(row);

		switch (col) {
		case 0:
			return entity.getName();
		}

		return null;
	}

	public void setData(LinkedList<BorrowerReturner> list) {
		this.list = list;
	}

	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return "         «”„ «·⁄÷Ê      											                          ";
	}

}
