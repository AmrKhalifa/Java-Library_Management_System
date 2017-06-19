package view.masterPanel.EventsAndListeners;

import java.util.EventObject;

public class AddMemberEvent extends EventObject {

	String memberName;
	String studyYear;
	String studyField;
	String simCategory;
	String WhatsAppNumber;
	String joingingDateTime;
	
	public AddMemberEvent(Object source) {
		super(source);
	}

	public AddMemberEvent(Object source, String memberName, String studyYear, String studyField, String simCategory,
			String whatsAppNumber, String joiningDateTime) {

		super(source);

		this.memberName = memberName;
		this.studyYear = studyYear;
		this.studyField = studyField;
		this.simCategory = simCategory;
		this.WhatsAppNumber = whatsAppNumber;
		this.joingingDateTime = joiningDateTime;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getStudyYear() {
		return studyYear;
	}

	public String getStudyField() {
		return studyField;
	}

	public String getSimCategory() {
		return simCategory;
	}

	public String getWhatsAppNumber() {
		return WhatsAppNumber;
	}

	public String getJoingingDateTime() {
		return joingingDateTime;
	}
	
	

}
