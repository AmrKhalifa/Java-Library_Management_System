package maestro;

import java.sql.SQLException;
import java.util.LinkedList;

import backEnd.DatabaseAccessor;
import backEnd.Model.Book;
import backEnd.Model.Member;
import backEnd.TableManipulator.BooksTableManipulator;
import backEnd.TableManipulator.MembersTableManipulator;
import view.masterPanel.Enums.SIMCategory;
import view.masterPanel.Enums.StudyYear;
import view.masterPanel.EventsAndListeners.AddBookEvent;
import view.masterPanel.EventsAndListeners.AddMemberEvent;

public class Maestro {

	private BooksTableManipulator booksTableManipulator = new BooksTableManipulator();
	private MembersTableManipulator membersTableManipulator = new MembersTableManipulator();

	public void addBook(AddBookEvent event) {
		String bookTitle = event.getBookName();
		String writerName = event.getWriterName();
		String publishingHouseName = event.getPublishingHouseName();
		int copiesCount = event.getCopiesCount();
		String additionDateTime = event.getAdditionDate();

		booksTableManipulator
				.addBook(new Book(bookTitle, writerName, publishingHouseName, copiesCount, additionDateTime));

	}

	public void addMember(AddMemberEvent event) {

		String memberName = event.getMemberName();
		StudyYear year = StudyYear.valueOf(event.getStudyYear());
		String studyField = event.getStudyField();
		SIMCategory sim = SIMCategory.valueOf(event.getSimCategory());
		String number = event.getWhatsAppNumber();
		String joiningDate = event.getJoingingDateTime();

		membersTableManipulator.addMember(new Member(memberName, year, studyField, sim, number, joiningDate));

	}

	public LinkedList<Book> getBooks() {
		return booksTableManipulator.getBooks();
	}

	public void connectBooks() throws Exception {

		DatabaseAccessor.connect();

	}

	public void saveBooks() throws SQLException {

		booksTableManipulator.save();

	}

	public void loadBooks() throws SQLException {

		booksTableManipulator.load();

	}

	public void bookDissconnect() throws Exception {

		DatabaseAccessor.disconnect();

	}

	public LinkedList<Member> getMembers() {
		return membersTableManipulator.getMembers();
	}

	public void connectMembers() throws Exception {

		DatabaseAccessor.connect();

	}

	public void saveMembers() throws SQLException {

		membersTableManipulator.save();

	}

	public void loadMembers() throws SQLException {

		membersTableManipulator.load();

	}

	public void membersDissconnect() throws Exception {

		DatabaseAccessor.disconnect();

	}
}
