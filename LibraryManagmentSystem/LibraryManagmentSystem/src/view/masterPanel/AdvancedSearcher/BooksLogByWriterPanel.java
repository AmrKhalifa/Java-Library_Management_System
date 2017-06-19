package view.masterPanel.AdvancedSearcher;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import backEnd.DirectOperations.BookBorrowLogByWriter;
import view.masterPanel.AdvancedSearcher.Events.BooksLogByWriterEvent;

public class BooksLogByWriterPanel extends JPanel {

	private JLabel writerNameLabel;
	private JTextField writerNameField;
	private SpinnersGroupPanel fromSpinnersGroup;
	private SpinnersGroupPanel toSpinnersGroup;
	private JButton calculateButton;
	private BooksLogByWriterEvent event;
	private BookBorrowLogByWriter bookLogByWriter;
	private ResultDialog resultDialog;
	private final Font buttonsFont = new Font("ara Jozoor", Font.PLAIN, 20);
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font textFont = new Font("Arial", Font.PLAIN, 15);

	public BooksLogByWriterPanel() {

		writerNameLabel = new JLabel("اسم الكاتب");
		writerNameLabel.setFont(labelsFont);

		writerNameField = new JTextField(20);
		writerNameField.setFont(textFont);

		fromSpinnersGroup = new SpinnersGroupPanel();
		toSpinnersGroup = new SpinnersGroupPanel();

		calculateButton = new JButton("أحسب");
		calculateButton.setFont(buttonsFont);


		setLayout();
		putComponents();
		calculate();
	}

	private void setLayout() {
		setLayout(new GridBagLayout());

		Border outerMostBorder = BorderFactory.createEmptyBorder(5, 30, 5, 30);
		Border outerBorder = BorderFactory.createTitledBorder("");
		Border innerBorder = BorderFactory.createTitledBorder(outerBorder,
				"بحث عن عدد الكتب الخاصة بكاتب معين", 3, 2, buttonsFont);
		setBorder(new CompoundBorder(outerMostBorder, innerBorder));

		fromSpinnersGroup.setBorder(BorderFactory.createTitledBorder(outerBorder, "من", 3, 2, buttonsFont));
		toSpinnersGroup.setBorder(BorderFactory.createTitledBorder(outerBorder, "إلى", 3, 2, buttonsFont));
	}

	private void putComponents() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;
		//////////////////////////////////// firstRow///////////////////////////////////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.ipady = 10;

		add(writerNameField, gc);

		gc.ipady = 30;
		gc.gridx = 1;
		add(writerNameLabel, gc);
		//////////////////////////////////// firstRow///////////////////////////////////
		gc.gridy++;
		gc.gridx = 0;
		add(fromSpinnersGroup, gc);
		////////////////////////////////// nextRow//////////////////////////////////////
		gc.gridy++;
		gc.gridy++;

		add(toSpinnersGroup, gc);
		////////////////////////////////// nextRow//////////////////////////////////////
		gc.gridy++;

		add(calculateButton, gc);
	}

	private void calculate() {

		calculateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {

				event = new BooksLogByWriterEvent(this, writerNameField.getText(), fromSpinnersGroup.getDate(),
						toSpinnersGroup.getDate());

				try {
					bookLogByWriter = new BookBorrowLogByWriter(event);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(calculateButton,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
				
				resultDialog = new ResultDialog(BooksLogByWriterPanel.this);
				resultDialog.setData(bookLogByWriter.getBooks());
				//resultLabel.setText(bookLogByWriter.getCount());
			}

		});

	}
}
