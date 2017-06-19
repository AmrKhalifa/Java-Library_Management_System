package view.masterPanel.EventsAndListeners;

import java.util.EventObject;

public class SearchForBookEvent extends EventObject {

	private String bookTitle;

	public SearchForBookEvent(Object source) {
		super(source);

	}

	public SearchForBookEvent(Object source, String bookName) {
		super(source);

		this.source = source;
		this.bookTitle = bookName;
	}

	public String getBookName() {
		return bookTitle;
	}

}
