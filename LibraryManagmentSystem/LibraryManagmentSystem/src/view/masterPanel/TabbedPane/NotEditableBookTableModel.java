package view.masterPanel.TabbedPane;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import backEnd.Model.Book;

public class NotEditableBookTableModel extends AbstractTableModel {

	public NotEditableBookTableModel()
	{
		
	}
	
	LinkedList<Book> db= new LinkedList<>();
	
	public void setDate(LinkedList<Book> db)
	{
		this.db=db;
	}
	
	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		Book book= db.get(row);
		
		switch (col){
			case 5:
				return book.getId();
			case 4:
				return book.getBookTitle();
			case 3:
				return book.getWriterName();
			case 2:
				return book.getPublishingHouseName();
			case 1:
				return book.getCopiesCount();
			case 0:
				return book.getAdditionDateTime();		
		}
		return null;
				
	}
	
	String []columnName={"تاريخ الإضافة         ","المتوفر",
			"دارالنشر              ","اسم الكاتب              ","اسم الكتاب             ","كود الكتاب"};
	
	public String getColumnName(int i) {
		
		return columnName[i];
	}

	}
	

