package view.masterPanel.AdvancedSearcher;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import backEnd.DirectOperations.BorrowInfo;
import backEnd.DirectOperations.BorrowerReturnerInfo;
import view.masterPanel.MasterPanel;

public class AdvancedSearcher extends JDialog implements ActionListener {

	private JPanel containerPanel;
	private JPanel exitPanel;
	private CardLayout C2;
	private JPanel buttonsHolderPanel;
	private BorrowersPanel borrowersPanel;
	private BorrowCountPanel rentingCountPanel;
	private ReturningCountPanel returningCountPanel;
	private BooksLogByWriterPanel booksCountbyWriterPanel;
	private BooksLogByHousePanel booksCountByHousePanel;
	private BorrowersReturnersPanel mostBorrowersPanel;
	private BorrowersReturnersPanel mostReturnersPanel;
	private JButton borrowersButton;
	private JButton borrowingButton;
	private JButton returningButton;
	private JButton writerLogButton;
	private JButton houseLogButton;
	private JButton mostBorrowersButton;
	private JButton mostRetuernersButton;
	private JButton exitButton;

	Font buttonsFont = new Font("ara Jozoor", Font.PLAIN, 20);

	public AdvancedSearcher(MasterPanel parent) {
		setVisible(true);
		setTitle("بحث متقدم");
		setLocation(300, 0);
		setLayout();
	}

	private void setLayout() {
		setLayout(new BorderLayout());
		setSize(700, 750);
		setMinimumSize(new Dimension(700, 750));
		setMaximumSize(new Dimension(700, 750));
		makeExitPanel();
		makeContainerPanel();
		add(containerPanel, BorderLayout.CENTER);
		add(exitPanel, BorderLayout.SOUTH);
	}

	private void makeExitPanel() {
		exitPanel = new JPanel();
		exitButton = new JButton("خـــــروج");
		exitButton.setFont(buttonsFont);
		exitButton.addActionListener(this);

		exitPanel.add(exitButton);
	}

	private void makeContainerPanel() {
		containerPanel = new JPanel();

		buttonsHolderPanel = new JPanel();
		borrowersPanel = new BorrowersPanel();
		rentingCountPanel = new BorrowCountPanel();
		returningCountPanel = new ReturningCountPanel();
		booksCountbyWriterPanel = new BooksLogByWriterPanel();
		booksCountByHousePanel = new BooksLogByHousePanel();
		mostBorrowersPanel = new BorrowersReturnersPanel();
		mostReturnersPanel = new BorrowersReturnersPanel();

		C2 = new CardLayout();
		C2.addLayoutComponent(buttonsHolderPanel, "0");
		C2.addLayoutComponent(rentingCountPanel, "1");
		C2.addLayoutComponent(returningCountPanel, "2");
		C2.addLayoutComponent(booksCountbyWriterPanel, "3");
		C2.addLayoutComponent(booksCountByHousePanel, "4");
		C2.addLayoutComponent(borrowersPanel, "5");
		C2.addLayoutComponent(mostBorrowersPanel, "6");
		C2.addLayoutComponent(mostReturnersPanel, "7");
		

		containerPanel.setLayout(C2);

		containerPanel.add(buttonsHolderPanel, "0");
		containerPanel.add(rentingCountPanel, "1");
		containerPanel.add(returningCountPanel, "2");
		containerPanel.add(booksCountbyWriterPanel, "3");
		containerPanel.add(booksCountByHousePanel, "4");
		containerPanel.add(borrowersPanel, "5");
		containerPanel.add(mostBorrowersPanel, "6");
		containerPanel.add(mostReturnersPanel, "7");

		C2.show(containerPanel, "0");

		makeButtonsPanel();

	}

	private void makeButtonsPanel() {

		Border innerBorder = BorderFactory.createTitledBorder("");
		Border outerBorder = BorderFactory.createEmptyBorder(100, 45, 100, 45);
		buttonsHolderPanel.setBorder(new CompoundBorder(outerBorder, innerBorder));

		borrowersButton = new JButton("إيجاد المستعيرين والكتب المستعارة");
		borrowersButton.setFont(buttonsFont);
		borrowersButton.addActionListener(this);

		borrowingButton = new JButton("إيجاد عدد الكتب التي تم استعارتها في فترة معينة");
		borrowingButton.setFont(buttonsFont);
		borrowingButton.addActionListener(this);

		returningButton = new JButton("إيجاد عدد الكتب التي تم ارجاعها في فترة معينة");
		returningButton.setFont(buttonsFont);
		returningButton.setPreferredSize(borrowingButton.getPreferredSize());
		returningButton.addActionListener(this);

		writerLogButton = new JButton("إيجاد عدد الكتب الخاصة بكاتب معين التي تم استعارتها في فترة معينة");
		writerLogButton.setFont(buttonsFont);
		writerLogButton.addActionListener(this);

		houseLogButton = new JButton("إيجاد عدد الكتب الخاصة بدار نشر معينة التي تم استعارتها في فترة معينة");
		houseLogButton.setFont(buttonsFont);
		houseLogButton.addActionListener(this);
		
		mostBorrowersButton = new JButton("الأكثر استعارة");
		mostBorrowersButton.setFont(buttonsFont);
		mostBorrowersButton.addActionListener(this);
		
		mostRetuernersButton = new JButton("الأكثر التزاما");
		mostRetuernersButton.setFont(buttonsFont);
		mostRetuernersButton.addActionListener(this);
		
		
		writerLogButton.setPreferredSize(houseLogButton.getPreferredSize());

		buttonsHolderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonsHolderPanel.add(borrowersButton);
		buttonsHolderPanel.add(borrowingButton);
		buttonsHolderPanel.add(returningButton);
		buttonsHolderPanel.add(writerLogButton);
		buttonsHolderPanel.add(houseLogButton);
		buttonsHolderPanel.add(mostBorrowersButton);
		buttonsHolderPanel.add(mostRetuernersButton);

	}

	@Override
	public void actionPerformed(ActionEvent ev) {

		if ((JButton) ev.getSource() == borrowingButton)
			C2.show(containerPanel, "1");

		if ((JButton) ev.getSource() == returningButton)
			C2.show(containerPanel, "2");

		if ((JButton) ev.getSource() == writerLogButton)
			C2.show(containerPanel, "3");

		if ((JButton) ev.getSource() == houseLogButton)
			C2.show(containerPanel, "4");

		if ((JButton) ev.getSource() == borrowersButton) {
			C2.show(containerPanel, "5");
			try {
				BorrowInfo info = new BorrowInfo();
				borrowersPanel.setData(info.getBorrowersData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if ((JButton) ev.getSource() == mostBorrowersButton) {
			C2.show(containerPanel, "6");
			try {
				BorrowerReturnerInfo info = new BorrowerReturnerInfo(" library.member_book_borrow_undeleteable ");
				mostBorrowersPanel.setData(info.getInfo());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if ((JButton) ev.getSource() == mostRetuernersButton) {
			C2.show(containerPanel, "7");
			try {
				BorrowerReturnerInfo info = new BorrowerReturnerInfo(" library.member_book_returning ");
				mostReturnersPanel.setData(info.getInfo());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		if ((JButton) ev.getSource() == exitButton)
			dispose();

	}

}
