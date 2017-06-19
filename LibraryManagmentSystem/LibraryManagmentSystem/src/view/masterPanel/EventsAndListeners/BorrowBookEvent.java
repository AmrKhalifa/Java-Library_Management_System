package view.masterPanel.EventsAndListeners;

import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BorrowBookEvent extends EventObject {

	private long bookCode;
	private long memberCode;
	private String rentingDatetime;

	public BorrowBookEvent(Object source) {
		super(source);

	}

	public BorrowBookEvent(Object source, String bookCode, String memberCode, String rentingDatetime)
			throws NumberFormatException {
		super(source);

		this.source = source;
		this.bookCode = Long.parseLong(bookCode);
		this.memberCode = Long.parseLong(memberCode);
		this.rentingDatetime = rentingDatetime;
	}

	public long getBookCode() {
		return bookCode;
	}

	public long getMemberCode() {
		return memberCode;
	}

	public String getRentingDatetime() {
		return rentingDatetime;
	}

}
