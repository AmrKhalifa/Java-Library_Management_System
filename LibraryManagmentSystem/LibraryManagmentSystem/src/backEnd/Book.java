package backEnd;

public class Book {

	static long count = 1000;

	long id;
	private String bookTitle;
	private String writerName;
	private String publishingHouseName;
	private int copiesCount;
	String additionDateTime;
	boolean exsiting;

	
	public Book(String bookName, String writerName, String publishingHouseName, int copiesCount,
			String additionDateTime) {
		this.id = ++count;
		this.bookTitle = bookName;
		this.writerName = writerName;
		this.publishingHouseName = publishingHouseName;
		this.copiesCount = copiesCount;
		this.additionDateTime = additionDateTime;
	}

	public Book(int id,String bookName, String writerName, String publishingHouseName, int copiesCount,
			String additionDateTime)
	{
		this( bookName,  writerName,  publishingHouseName,  copiesCount,
				 additionDateTime);
		this.id=id;
		
	}
	
	public long getId() {
		return id;
	}

	public String getBookTitle() {
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
	
	public String getAdditionDateTime()
	{
		return additionDateTime;
	}

	public boolean isExisting()
	{
		return(copiesCount>0);
	}

	public boolean isExsiting() {
		return exsiting;
	}

	public void setExsiting(boolean exsiting) {
		this.exsiting = exsiting;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public void setPublishingHouseName(String publishingHouseName) {
		this.publishingHouseName = publishingHouseName;
	}

	public void setCopiesCount(int copiesCount) {
		this.copiesCount = copiesCount;
	}

	public void setAdditionDateTime(String additionDateTime) {
		this.additionDateTime = additionDateTime;
	}
	
	
}
