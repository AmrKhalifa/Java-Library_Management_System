package view.masterPanel.AdvancedSearcher;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import backEnd.DirectOperations.BooksBorrowLogByHouse;
import view.masterPanel.AdvancedSearcher.Events.BooksLogByHouseEvent;

public class BooksLogByHousePanel extends JPanel {

	private JLabel publishingHouseNameLabel;
	private JTextField puplishingHouseNameField;
	private SpinnersGroupPanel fromSpinnersGroup;
	private SpinnersGroupPanel toSpinnersGroup;
	private JButton calculateButton;
	private JLabel resultLabel;
	private ResultDialog resultDialog;
	private BooksLogByHouseEvent event;
	private BooksBorrowLogByHouse bookLogByHouse;

	private final Font buttonsFont = new Font("ara Jozoor", Font.PLAIN, 20);
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font textFont = new Font("Arial", Font.PLAIN, 15);

	public BooksLogByHousePanel() {

		publishingHouseNameLabel = new JLabel("اسم دار النشر");
		publishingHouseNameLabel.setFont(labelsFont);

		puplishingHouseNameField = new JTextField(20);
		puplishingHouseNameField.setFont(textFont);

		fromSpinnersGroup = new SpinnersGroupPanel();
		toSpinnersGroup = new SpinnersGroupPanel();

		calculateButton = new JButton("أحسب");
		calculateButton.setFont(buttonsFont);

		resultLabel = new JLabel();
		resultLabel.setFont(new Font("ara Jozoor", Font.PLAIN, 25));

		setLayout();
		putComponents();
		calculate();
	}

	private void setLayout() {
		setLayout(new GridBagLayout());

		Border outerMostBorder = BorderFactory.createEmptyBorder(5, 30, 5, 30);
		Border outerBorder = BorderFactory.createTitledBorder("");
		Border innerBorder = BorderFactory.createTitledBorder(outerBorder,
				"بحث عن عدد الكتب المستعارة في فترة معينة ", 3, 2, buttonsFont);
		setBorder(new CompoundBorder(outerMostBorder, innerBorder));

		fromSpinnersGroup.setBorder(BorderFactory.createTitledBorder(outerBorder, "من", 3, 2, buttonsFont));
		toSpinnersGroup.setBorder(BorderFactory.createTitledBorder(outerBorder, "إلى", 3, 2, buttonsFont));
	}

	private void putComponents() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;
		//////////////////////////////////// firstRow//////////////////////////////////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.ipady = 10;

		add(puplishingHouseNameField, gc);

		gc.ipady = 30;
		gc.gridx = 1;
		add(publishingHouseNameLabel, gc);
		//////////////////////////////////// nextRow//////////////////////////////////
		gc.gridy++;
		gc.gridx = 0;
		add(fromSpinnersGroup, gc);
		////////////////////////////////// nextRow////////////////////////////////////
		gc.gridy++;
		gc.gridy++;

		add(toSpinnersGroup, gc);
		////////////////////////////////// nextRow////////////////////////////////////
		gc.gridy++;

		add(resultLabel, gc);
		///////////////////////////////// nextRow//////////////////////////////////////
		gc.gridy++;

		add(calculateButton, gc);
	}

	private void calculate() {

		calculateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {

				event = new BooksLogByHouseEvent(this, puplishingHouseNameField.getText(),
						fromSpinnersGroup.getDate(), toSpinnersGroup.getDate());

				
				try {
					bookLogByHouse = new BooksBorrowLogByHouse(event);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(calculateButton,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
				
				resultDialog = new ResultDialog(BooksLogByHousePanel.this);
				resultDialog.setData(bookLogByHouse.getBooks());
				//resultLabel.setText(bookCountByHouse.getCount());
			}

		});

	}
}
