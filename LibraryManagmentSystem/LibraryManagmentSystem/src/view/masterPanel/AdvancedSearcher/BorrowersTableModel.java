package view.masterPanel.AdvancedSearcher;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import backEnd.BorrowerData;

public class BorrowersTableModel extends AbstractTableModel {

	private LinkedList<BorrowerData> borrowersData = new LinkedList<>();
	
	@Override
	public int getColumnCount() {
		
		return 3;
	}

	@Override
	public int getRowCount() {
		
		return borrowersData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		BorrowerData borrower = borrowersData.get(row);
		
		switch(col)
		{
			case 2: return borrower.getBorrowerName();
			case 1: return borrower.getBookTitle();
			case 0: return borrower.getBorrowDate();
				
		}
		
		return null;
	}

	public void setData(LinkedList<BorrowerData> borrowersData)
	{
		this.borrowersData=borrowersData;
	}

	String[] colName= {" «—ÌŒ «·«” ⁄«—…","«”„ «·ﬂ «»","«·„” ⁄Ì—"};
	
	
	@Override
	public String getColumnName(int row) {
		// TODO Auto-generated method stub
		return colName[row];
	}
	
	
	
}
