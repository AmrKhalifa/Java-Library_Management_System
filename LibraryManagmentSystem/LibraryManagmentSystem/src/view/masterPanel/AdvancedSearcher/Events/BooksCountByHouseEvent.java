package view.masterPanel.AdvancedSearcher.Events;

import java.util.EventObject;

public class BooksCountByHouseEvent extends EventObject {

	String publishingHouseName;
	String fromBoundary;
	String toBoundary;
	
	public BooksCountByHouseEvent(Object source) {
		super(source);
	}
	
	public BooksCountByHouseEvent(Object source ,String publishingHouseName, String fromBoundary, String toBoundary)
	{
		super(source);
		
		this.publishingHouseName = publishingHouseName;
		this.fromBoundary = fromBoundary;
		this.toBoundary = toBoundary;
	}

	public String getPublishingHouseName() {
		return publishingHouseName;
	}

	public String getFromBoundary() {
		return fromBoundary;
	}

	public String getToBoundary() {
		return toBoundary;
	}
	
}
