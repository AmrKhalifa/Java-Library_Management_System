package view.masterPanel.AdvancedSearcher.Events;

import java.util.EventObject;

public class BooksLogByWriterEvent extends EventObject {
	
	String writerName;
	String fromBoundary;
	String toBoundary;
	
	public BooksLogByWriterEvent(Object source) {
		super(source);
	}
	
	public BooksLogByWriterEvent(Object source ,String writerName, String fromBoundary, String toBoundary)
	{
		super(source);
		
		this.writerName = writerName;
		this.fromBoundary = fromBoundary;
		this.toBoundary = toBoundary;
	}

	public String getWriterName() {
		return writerName;
	}

	public String getFromBoundary() {
		return fromBoundary;
	}

	public String getToBoundary() {
		return toBoundary;
	}
	
	
}
