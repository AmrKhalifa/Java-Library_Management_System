package backEnd.Model;

import view.masterPanel.Enums.SIMCategory;
import view.masterPanel.Enums.StudyYear;

public class Member {

	public static long count = 100;
	long id;
	String memberName;
	StudyYear studyYear;
	String studyField;
	SIMCategory simCategory;
	String WhatsAppNumber;
	String joiningDateTime;

	public Member(String memberName, StudyYear studyYear, String studyField, SIMCategory simCategory,
			String number, String joiningDateTime) {
		
		this.id = ++count;
		this.memberName = memberName;
		this.studyYear = studyYear;
		this.studyField = studyField;
		this.simCategory = simCategory;
		this.WhatsAppNumber = number;
		this.joiningDateTime = joiningDateTime;
	}

	
	public Member(long id, String memberName, StudyYear studyYear, String studyField, SIMCategory simCategory,
			String whatsAppNumber, String joiningDateTime) {
		
		this(memberName, studyYear, studyField, simCategory, whatsAppNumber, joiningDateTime);
		this.id = id;
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


	public void setId(long id) {
		this.id = id;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public void setStudyYear(StudyYear studyYear) {
		this.studyYear = studyYear;
	}


	public void setStudyField(String studyField) {
		this.studyField = studyField;
	}


	public void setSimCategory(SIMCategory simCategory) {
		this.simCategory = simCategory;
	}


	public void setWhatsAppNumber(String whatsAppNumber) {
		WhatsAppNumber = whatsAppNumber;
	}


	public void setJoiningDateTime(String joiningDateTime) {
		this.joiningDateTime = joiningDateTime;
	}

	
}
