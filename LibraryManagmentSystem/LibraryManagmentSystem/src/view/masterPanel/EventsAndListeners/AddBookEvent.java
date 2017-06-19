package view.masterPanel.EventsAndListeners;

import java.awt.Event;
import java.util.EventObject;

public class AddBookEvent extends EventObject {

	
	private static final long serialVersionUID = -8427565199531239531L;
	private String bookTitle;
	private String writerName;
	private String publishingHouseName;
	private int copiesCount;
	private String additionDate;
	
	public AddBookEvent(Object source) {
		super(source);
	}

	public AddBookEvent(Object source, String bookName, String writerName, String publishingHouseName,
			int copiesCount,String additionDate) {
		super(source);

		this.source = source;
		this.bookTitle = bookName;
		this.writerName = writerName;
		this.publishingHouseName = publishingHouseName;
		this.copiesCount = copiesCount;
		this.additionDate=additionDate;
	}

	public String getBookName() {
		return bookTitle;
	}

	public String getWriterName() {
		return writerName;
	}

	public String getPublishingHouseName() {
		return publishingHouseName;
	}

	public int getCopiesCount() {
		return copiesCount;
	}

	public String getAdditionDate()
	{
		return additionDate;
	}

}
