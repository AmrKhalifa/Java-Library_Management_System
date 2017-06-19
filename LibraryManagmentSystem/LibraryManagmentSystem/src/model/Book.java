package model;

public class Book {

	static long count = 1000;

	long id;
	private String bookName;
	private String writerName;
	private String publishingHouseName;
	private int copiesCount;
	String additionDateTime;

	public Book(String bookName, String writerName, String publishingHouseName, int copiesCount,
			String additionDateTime) {
		this.id = ++count;
		this.bookName = bookName;
		this.writerName = writerName;
		this.publishingHouseName = publishingHouseName;
		this.copiesCount = copiesCount;
		this.additionDateTime = additionDateTime;
	}

	public long getId() {
		return id;
	}

	public String getBookName() {
		return bookName;
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

}
