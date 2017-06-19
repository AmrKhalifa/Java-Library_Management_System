package backEnd;

public class MemberBook {

	static long count =1;
	private  long operationId;
	private long memberId;
	private long bookId;
	private String operationDateTime;
	
public MemberBook(long memberId, long bookId, String operationDateTime) {
		
		this.operationId=count++;
		this.memberId = memberId;
		this.bookId = bookId;
		this.operationDateTime = operationDateTime;	
		
	}

	public MemberBook(long operationId, long memberId, long bookId, String operationDateTime) {
		
		this(memberId,bookId,operationDateTime);
		this.operationId=operationId;
	}

	public long getOperationId() {
		return operationId;
	}

	public void setOperationId(long operationId) {
		this.operationId = operationId;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getOperationDateTime() {
		return operationDateTime;
	}

	public void setOperationDateTime(String operationDateTime) {
		this.operationDateTime = operationDateTime;
	}

	
	
	
}
