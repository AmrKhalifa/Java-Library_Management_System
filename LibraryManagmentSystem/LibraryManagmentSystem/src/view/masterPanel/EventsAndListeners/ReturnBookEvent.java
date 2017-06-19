package view.masterPanel.EventsAndListeners;

import java.util.EventObject;

public class ReturnBookEvent extends EventObject {

	private long bookCode;
	private long memberCode;
	private String returningDateTime;

	public ReturnBookEvent(Object source) {
		super(source);

	}

	public ReturnBookEvent(Object source, String bookCode, String memberCode, String returningDateTime)
			throws NumberFormatException {
		super(source);

		this.source = source;
		this.bookCode = Long.parseLong(bookCode);
		this.memberCode = Long.parseLong(memberCode);
		this.returningDateTime = returningDateTime;
	}

	public long getBookCode() {
		return bookCode;
	}

	public long getMemberCode() {
		return memberCode;
	}

	public String getReturningDateTime() {
		return returningDateTime;
	}

}
