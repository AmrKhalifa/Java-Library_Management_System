package view.masterPanel.TabbedPane;

import java.util.Date;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import backEnd.Model.Book;

public class EditableBookTableModel extends AbstractTableModel {
	
	LinkedList<Book> db= new LinkedList<>();

	public void setData(LinkedList<Book> db)
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

		Book book = db.get(row);

		switch (col) {
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

	String[] columnName = { "تاريخ الإضافة         ", "المتوفر", "دارالنشر              ",
			"اسم الكاتب              ", "اسم الكتاب             ", "كود الكتاب" };

	public String getColumnName(int i) {

		return columnName[i];
	}

	
	@Override
	public Class<?> getColumnClass(int col) {
		switch(col){
				
				case 0:
					return String.class;
							
				case 1:
					return String.class;
					
				case 2:
					return String.class;
				
				case 3:
					return String.class;
					
				case 4:
					return String.class;
					
				case 5:
					return int.class;
	
				default: 
					return super.getColumnClass(col);
				}

	}
	
	
	public boolean isCellEditable(int row, int col) {
		
		switch(col)
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
			return false;
			
		}
		
		return true;
	}

	
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		Book book = db.get(row);
		
		switch (col)
		{
			case 0:
				book.setAdditionDateTime(value.toString());
				break;
			case 1:
			try {
				book.setCopiesCount(Integer.parseInt((String) value));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case 2:
				book.setPublishingHouseName((String) value);
				break;
			case 3:
				book.setWriterName((String) value);
				break;
			case 4:
				book.setBookTitle((String) value);
				break;
					
		}
				
		
		
	}

	
}
