package view.masterPanel.AdvancedSearcher.Events;

import java.util.EventObject;

public class RentingCountEvent extends EventObject {

	String fromBoundary;
	String toBoundary;
	
	public RentingCountEvent(Object source) {
		super(source);
		
	}
	
	public RentingCountEvent(Object source, String fromBoundary, String toBoundary)
	{
		super(source);
		this.fromBoundary = fromBoundary;
		this.toBoundary = toBoundary;
	}

	public String getFromBoundary() {
		return fromBoundary;
	}

	public String getToBoundary() {
		return toBoundary;
	}

}
