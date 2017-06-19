package view.masterPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import Utils.Utils;
import backEnd.DirectOperations.AddBook;
import backEnd.DirectOperations.AddMember;
import backEnd.DirectOperations.Borrowing;
import backEnd.DirectOperations.Returning;
import maestro.Maestro;
import view.masterPanel.AdvancedSearcher.AdvancedSearcher;
import view.masterPanel.EventsAndListeners.AddBookEvent;
import view.masterPanel.EventsAndListeners.AddBookListener;
import view.masterPanel.EventsAndListeners.AddMemberEvent;
import view.masterPanel.EventsAndListeners.AddMemberListener;
import view.masterPanel.EventsAndListeners.BorrowBookEvent;
import view.masterPanel.EventsAndListeners.BorrowBookListener;
import view.masterPanel.EventsAndListeners.ReturnBookEvent;
import view.masterPanel.EventsAndListeners.ReturnBookListener;
import view.masterPanel.EventsAndListeners.SearchForBookEvent;
import view.masterPanel.EventsAndListeners.SearchForBookListener;
import view.masterPanel.TabbedPane.BookTablePanel;
import view.masterPanel.TabbedPane.ButtonListener;
import view.masterPanel.TabbedPane.MemberTablePanel;

public class MasterPanel extends JPanel implements ActionListener {

	private JToolBar toolbar;
	private JTabbedPane tablesContainer;
	private BookTablePanel bookTablePanel;
	private MemberTablePanel memberTablePanel;
	private JButton addBook;
	private JButton searchForBook;
	private JButton rentBook;
	private JButton returnBook;
	private JButton addMember;
	private JButton advancedSearch;
	private JTextArea txtArea;
	private JPanel signoutPanel;
	public JButton signoutButton;
	private int frameHight;
	private JButton settings;
	private BookAdder bookAdder;
	private BookSearcher bookSearcher;
	private BookBorrower bookRenter;
	private BookReturner bookReturner;
	private MemberAdder memberAdder;
	private AdvancedSearcher advancedSearcher;
	private Settings settingsEditor;
	private Maestro maestro;

	Font buttonsFont = new Font("ara Jozoor", Font.PLAIN, 20);
	Font buttonsFont_2 = new Font("Ara Aqeeq Bold", Font.PLAIN, 20);

	public MasterPanel() {
		makeButtons();
		MakeToolBar();
		makeTablesContainer();
		setLayout();

	}

	private void setLayout() {
		setVisible(true);
		setLayout(new BorderLayout());

		txtArea = new JTextArea();
		signoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		signoutPanel.add(signoutButton);
		add(tablesContainer, BorderLayout.CENTER);
		add(toolbar, BorderLayout.EAST);
		add(signoutPanel, BorderLayout.PAGE_START);

	}

	private void MakeToolBar() {
		toolbar = new JToolBar();
		toolbar.setVisible(true);
		toolbar.setOrientation(1);
		Border innerToolbarBorder = BorderFactory.createTitledBorder("");
		Border outerToolbarBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		toolbar.setBorder(new CompoundBorder(outerToolbarBorder, innerToolbarBorder));
		toolbar.setFloatable(false);
		toolbar.add(addBook);
		toolbar.add(searchForBook);
		toolbar.add(rentBook);
		toolbar.add(returnBook);
		toolbar.add(addMember);
		toolbar.add(advancedSearch);
		toolbar.addSeparator();
		toolbar.add(settings);

	}

	private void makeButtons() {

		addBook = new JButton("إضافة كتاب");
		addBook.setFont(buttonsFont);
		addBook.addActionListener(this);
		addBook.setIcon(Utils.creatImageIcon("/images/book-add-512.png"));

		searchForBook = new JButton("بحث عن كتاب");
		searchForBook.setFont(buttonsFont);
		searchForBook.addActionListener(this);
		searchForBook.setIcon(Utils.creatImageIcon("/images/Search_Book.png"));

		rentBook = new JButton("استلاف كتاب");
		rentBook.setFont(buttonsFont);
		rentBook.addActionListener(this);
		rentBook.setIcon(Utils.creatImageIcon("/images/borrow.png"));

		returnBook = new JButton("إرجاع كتاب");
		returnBook.setFont(buttonsFont);
		returnBook.addActionListener(this);
		returnBook.setIcon(Utils.creatImageIcon("/images/check.png"));

		addMember = new JButton("إضافة عضو");
		addMember.setFont(buttonsFont);
		addMember.addActionListener(this);
		addMember.setIcon(Utils.creatImageIcon("/images/member.jpg"));

		advancedSearch = new JButton("بحث متقدم");
		advancedSearch.setFont(buttonsFont);
		advancedSearch.addActionListener(this);
		advancedSearch.setIcon(Utils.creatImageIcon("/images/search.png"));

		settings = new JButton("إعـدادات");
		settings.setFont(buttonsFont);
		settings.setPreferredSize(advancedSearch.getPreferredSize());
		settings.addActionListener(this);
		settings.setIcon(Utils.creatImageIcon("/images/settings_2.png"));

		signoutButton = new JButton("تسجيل الخروج");
		signoutButton.setFont(buttonsFont);

	}

	public void makeTablesContainer() {
		tablesContainer = new JTabbedPane();
		Border outerBorder = BorderFactory.createEmptyBorder(10, 20, 10, 20);
		Border innerBorder = BorderFactory.createTitledBorder("");
		tablesContainer.setBorder(new CompoundBorder(outerBorder, innerBorder));
		tablesContainer.setFont(buttonsFont);
		bookTablePanel = new BookTablePanel();
		memberTablePanel = new MemberTablePanel();
		tablesContainer.add("الكتب", bookTablePanel);
		tablesContainer.setSize(new Dimension(600, 600));
		tablesContainer.add("الأعضاء", memberTablePanel);
		maestro = new Maestro();
		setMemberTablePanelListener();
		setBookTablePanelListener();

	}

	public void setFrameHight(int frameHight) {
		this.frameHight = frameHight;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if ((JButton) ev.getSource() == addBook) {
			bookAdder = new BookAdder(MasterPanel.this);
			addBook();
		}
		if ((JButton) ev.getSource() == searchForBook) {
			bookSearcher = new BookSearcher(MasterPanel.this);
			searchForBook();
		}

		if ((JButton) ev.getSource() == rentBook) {
			bookRenter = new BookBorrower(MasterPanel.this);
			rentBook();
		}
		if ((JButton) ev.getSource() == returnBook) {
			bookReturner = new BookReturner(MasterPanel.this);
			returnBook();
		}
		if ((JButton) ev.getSource() == addMember) {
			memberAdder = new MemberAdder(MasterPanel.this);
			addMember();
		}
		if ((JButton) ev.getSource() == advancedSearch)
			advancedSearcher = new AdvancedSearcher(MasterPanel.this);

		if ((JButton) ev.getSource() == settings)
			settingsEditor = new Settings(MasterPanel.this);
	}

	private void addBook() {
		bookAdder.setListener(new AddBookListener() {

			@Override
			public void fireAddingBook(AddBookEvent event) {

				try {
					new AddBook(event);
					maestro.connectBooks();
					maestro.saveBooks();
					maestro.loadBooks();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(bookAdder,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				bookTablePanel.setData(maestro.getBooks());
				bookTablePanel.refresh();
				bookAdder.dispose();
			}

		});
	}

	private void searchForBook() {
		bookSearcher.setListener(new SearchForBookListener() {

			@Override
			public void fireSreachingForBook(SearchForBookEvent event) {
				txtArea.append("Looking for : " + event.getBookName() + "\n");

			}

		});
	}

	private void rentBook() {
		////////////////// responding to renting a book////////////////////////
		bookRenter.setListener(new BorrowBookListener() {

			@Override
			public void fireRentingBook(BorrowBookEvent event) {
				try {
					new Borrowing(event);
					maestro.loadBooks();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(bookTablePanel,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(bookTablePanel,
							" عفوا ً هذا العضو غير مسجل بالمكتبة , الرجاء التسجيل لأول مرة أو التأكد من الرقم إذا كان  العضو مسجلا ً من قبل",
							"رقم عضو خاطئ", JOptionPane.WARNING_MESSAGE);
				}
				bookTablePanel.refresh();
			}

		});
	}

	private void returnBook() {
		bookReturner.setListener(new ReturnBookListener() {
			@Override
			public void fireReturningBook(ReturnBookEvent event) {
				try {
					new Returning(event);
					maestro.loadBooks();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(bookTablePanel,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(bookTablePanel,
							" عفوا ً هذا العضو غير مسجل بالمكتبة , الرجاء التسجيل لأول مرة أو التأكد من الرقم إذا كان  العضو مسجلا ً من قبل",
							"رقم عضو خاطئ", JOptionPane.WARNING_MESSAGE);
				}
				bookTablePanel.refresh();

			}

		});
	}

	private void addMember() {
		memberAdder.setListener(new AddMemberListener() {

			@Override
			public void fireAddingMember(AddMemberEvent event) {
				try {
					new AddMember(event);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(memberAdder,
							"عفوا ً لقد قمت بإدخال بيانات خاطئة أو مكررة في خانة لايجوز تكرارها", "خطأ في البيانات",
							JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(bookTablePanel,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
				}

				memberTablePanel.setData(maestro.getMembers());
				try {
					maestro.connectMembers();
					maestro.saveMembers();
					maestro.loadMembers();
				} catch (Exception e) {

				}

				memberTablePanel.refresh();
				memberAdder.dispose();
			}

		});

	}

	private void setBookTablePanelListener() {

		bookTablePanel.setListener(new ButtonListener() {

			@Override
			public void fireLoadButtonPressed() {

				// maestro.connectBooks();
				try {
					maestro.loadBooks();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(bookTablePanel,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
				bookTablePanel.setData(maestro.getBooks());
				bookTablePanel.refresh();

			}

			@Override
			public void fireSaveButtonPressed() {
				// maestro.connectMembers();
				try {
					maestro.saveBooks();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(bookTablePanel,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
				bookTablePanel.refresh();
			}

		});
	}

	private void setMemberTablePanelListener() {
		memberTablePanel.setListener(new ButtonListener() {

			@Override
			public void fireLoadButtonPressed() {
				try {
					maestro.connectMembers();
					maestro.loadMembers();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(bookTablePanel,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}

				memberTablePanel.setData(maestro.getMembers());
				memberTablePanel.refresh();

			}

			@Override
			public void fireSaveButtonPressed() {
				try {
					maestro.connectMembers();
					maestro.saveMembers();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(bookTablePanel,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}

				memberTablePanel.refresh();
			}

		});

	}

	public void hideFromRegular() {
		addBook.setVisible(false);
		addMember.setVisible(false);
		settings.setVisible(false);
		bookTablePanel.hideFromRegular();
		memberTablePanel.hideFromRegular();
	}
}
