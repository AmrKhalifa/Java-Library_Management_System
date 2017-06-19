package view.masterPanel.AdvancedSearcher;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import backEnd.Model.BorrowedBook;

public class ResultTableModel extends AbstractTableModel {

	LinkedList<BorrowedBook> books = new LinkedList<>() ;
	
	public void setData(LinkedList<BorrowedBook> books)
	{
		this.books=books;
	}
	@Override
	public int getColumnCount() {
		
		return 2;
	}

	@Override
	public int getRowCount() {
		
		return books.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		BorrowedBook book = books.get(row);
		
		switch(col)
		{
			case 1:
				return book.getBookTitle(); 
			case 0:
				return book.getBorrowedCount();
		}
		
		return null;
	}

	String []  colName = {"العدد المستعار","اسم الكتاب                             "};
	@Override
	public String getColumnName(int col) {
		
		return colName[col];
	}

	
}
