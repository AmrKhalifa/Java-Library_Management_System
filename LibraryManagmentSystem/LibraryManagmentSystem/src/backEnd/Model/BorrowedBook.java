package backEnd.Model;

public class BorrowedBook {

	String bookTitle;
	int borrowedCount;
	
	public BorrowedBook(String bookTitle,int borrowedCount)
	{
		this.bookTitle = bookTitle;
		this.borrowedCount = borrowedCount;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}
	public int getBorrowedCount() {
		return borrowedCount;
	}
	
}
