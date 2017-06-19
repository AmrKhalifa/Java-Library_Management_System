package view.masterPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import backEnd.DirectOperations.SearchForBook;
import backEnd.DirectOperations.SearchForBook.BookInfo;
import view.masterPanel.EventsAndListeners.SearchForBookEvent;
import view.masterPanel.EventsAndListeners.SearchForBookListener;

public class BookSearcher extends JDialog {
	private JPanel infoPanel;
	private JPanel confirmationPanel;
	private JLabel bookTitleLabel;
	private JTextField bookTitleField;
	JLabel writerLabel = new JLabel();
	JLabel writerLabelLabel = new JLabel("اسم الكاتب");
	JLabel houseLabel = new JLabel();
	JLabel houseLabelLabel = new JLabel("اسم دار النشر");
	JLabel avilableCopiesLabel = new JLabel();
	JLabel avilableCopiesLabeLabel = new JLabel("عدد النسخ المتوفر");
	JLabel borrowedCopiesLabel = new JLabel();
	JLabel borrowedCopiesLabelLabel = new JLabel("عدد النسخ المستعارة");
	private JButton submitButton;
	private JButton cancelButton;
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font buttonsFont_3 = new Font("Ara Aqeeq Bold", Font.PLAIN, 20);
	private SearchForBookEvent event;
	private SearchForBookListener listener;
	private SearchForBook searchForBook;
	// private BookInfo bookInfo;

	public BookSearcher(MasterPanel parent) {

		setVisible(true);
		setTitle("بحث عن كتاب");
		setLocationRelativeTo(parent);
		setLayout();
	}

	private void setLayout() {
		setLayout(new BorderLayout());
		setSize(500, 200);
		setMinimumSize(new Dimension(450, 200));
		setMaximumSize(new Dimension(500, 200));

		makeinfoPanel();
		add(infoPanel, BorderLayout.NORTH);
		makeConfirmationPanel();
		add(confirmationPanel, BorderLayout.SOUTH);
	}

	private void makeinfoPanel() {

		infoPanel = new JPanel();
		Border innerBorder = BorderFactory.createTitledBorder("");
		infoPanel.setBorder(innerBorder);

		bookTitleLabel = new JLabel("اسم الكتاب");
		bookTitleLabel.setFont(labelsFont);
		bookTitleField = new JTextField(20);

		infoPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;

		//////////////////////////////////// firstRow////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy = 0;

		gc.gridheight = 1;
		gc.ipady = 10;

		infoPanel.add(bookTitleField, gc);
		gc.gridx = 1;
		infoPanel.add(bookTitleLabel, gc);

		//////////////////////////////////// next
		//////////////////////////////////// row////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy++;

		gc.gridheight = 1;
		gc.ipady = 10;

		infoPanel.add(writerLabel, gc);
		gc.gridx = 1;
		infoPanel.add(writerLabelLabel, gc);
		//////////////////////////////////// next
		//////////////////////////////////// row////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy++;

		gc.gridheight = 1;
		gc.ipady = 10;

		infoPanel.add(houseLabel, gc);
		gc.gridx = 1;
		infoPanel.add(houseLabelLabel, gc);
		//////////////////////////////////// next
		//////////////////////////////////// row////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy++;

		gc.gridheight = 1;
		gc.ipady = 10;

		infoPanel.add(avilableCopiesLabel, gc);
		gc.gridx = 1;
		infoPanel.add(avilableCopiesLabeLabel, gc);

		//////////////////////////////////// next
		//////////////////////////////////// row////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy++;

		gc.gridheight = 1;
		gc.ipady = 10;

		infoPanel.add(borrowedCopiesLabel, gc);
		gc.gridx = 1;
		infoPanel.add(borrowedCopiesLabelLabel, gc);

		writerLabelLabel.setVisible(false);
		houseLabelLabel.setVisible(false);
		avilableCopiesLabeLabel.setVisible(false);
		borrowedCopiesLabelLabel.setVisible(false);

	}

	private void makeConfirmationPanel() {
		confirmationPanel = new JPanel();
		confirmationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		submitButton = new JButton("تأكيد");
		submitButton.setFont(buttonsFont_3);

		cancelButton = new JButton("إلغاء");
		cancelButton.setFont(buttonsFont_3);

		confirmationPanel.add(submitButton);
		confirmationPanel.add(cancelButton);

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				event = new SearchForBookEvent(this, bookTitleField.getText());
				try {
					searchForBook = new SearchForBook(event);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(submitButton,
							"عفواً تعذر الاتصال بقاعدة البيانات ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				// makeinfoPanel();
				BookInfo bookInfo = searchForBook.getBookInfo();
				transform(bookInfo);
				infoPanel.repaint();
				repaint();
			}

		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}

		});
	}

	public void setListener(SearchForBookListener listener) {
		this.listener = listener;
	}

	public void transform(BookInfo bookInfo) {

		setSize(500, 400);
		setMinimumSize(new Dimension(450, 400));
		setMaximumSize(new Dimension(500, 400));

		writerLabelLabel.setVisible(true);
		houseLabelLabel.setVisible(true);
		avilableCopiesLabeLabel.setVisible(true);
		borrowedCopiesLabelLabel.setVisible(true);

		writerLabel.setText(bookInfo.getBookWriter());
		houseLabel.setText(bookInfo.getPublishingHouse());
		avilableCopiesLabel.setText(bookInfo.getAvilableCopies());
		borrowedCopiesLabel.setText(bookInfo.getBorrowedCopies());

	}
}
