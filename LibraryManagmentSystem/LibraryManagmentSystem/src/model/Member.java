package model;

import view.masterPanel.Enums.SIMCategory;
import view.masterPanel.Enums.StudyYear;

public class Member {

	static long count=100;
	long id;
	String memberName;
	StudyYear studyYear;
	String studyField;
	SIMCategory simCategory;
	String WhatsAppNumber;
	String joiningDateTime;
	
	public Member(String memberName, StudyYear studyYear, String studyField, SIMCategory simCategory,
			String whatsAppNumber, String joiningDateTime) {
		this.id=++count;
		this.memberName = memberName;
		this.studyYear = studyYear;
		this.studyField = studyField;
		this.simCategory = simCategory;
		this.WhatsAppNumber = whatsAppNumber;
		this.joiningDateTime = joiningDateTime;
	}

	public long getId() {
		return id;
	}

	public String getMemberName() {
		return memberName;
	}

	public StudyYear getStudyYear() {
		return studyYear;
	}

	public String getStudyField() {
		return studyField;
	}

	public SIMCategory getSimCategory() {
		return simCategory;
	}

	public String getWhatsAppNumber() {
		return WhatsAppNumber;
	}

	public String getJoiningDateTime() {
		return joiningDateTime;
	}
	
	
	
}
