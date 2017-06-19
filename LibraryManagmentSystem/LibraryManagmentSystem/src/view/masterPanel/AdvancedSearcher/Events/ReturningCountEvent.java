package view.masterPanel.AdvancedSearcher.Events;

import java.util.EventObject;

public class ReturningCountEvent extends EventObject {

	String fromBoundary;
	String toBoundary;
	
	public ReturningCountEvent(Object source) {
		super(source);
		
	}
	
	public ReturningCountEvent(Object source, String fromBoundary, String toBoundary)
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
