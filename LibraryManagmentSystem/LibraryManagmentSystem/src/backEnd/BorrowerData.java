package backEnd;

public class BorrowerData {

	private String borrowerName;
	private String bookTitle;
	private String borrowDate;

	public BorrowerData( String borrowerName, String bookTitle,String borrowDate) {
		this.bookTitle = bookTitle;
		this.borrowerName = borrowerName;
		this.borrowDate = borrowDate;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

}
