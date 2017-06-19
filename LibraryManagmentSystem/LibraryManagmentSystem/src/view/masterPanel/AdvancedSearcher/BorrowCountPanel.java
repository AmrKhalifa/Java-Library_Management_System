package view.masterPanel.AdvancedSearcher;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import backEnd.DirectOperations.BorrowingCount;
import view.masterPanel.AdvancedSearcher.Events.BorrowCountEvent;

public class BorrowCountPanel extends javax.swing.JPanel {

	SpinnersGroupPanel fromSpinnersGroup;
	SpinnersGroupPanel toSpinnersGroup;
	JLabel resultLabel;
	JButton calculateButton;
	BorrowCountEvent event;
	BorrowingCount rentingCount;// backEnd could be done better with the maestro
								// class

	Font buttonsFont = new Font("ara Jozoor", Font.PLAIN, 20);

	public BorrowCountPanel() {
		fromSpinnersGroup = new SpinnersGroupPanel();
		toSpinnersGroup = new SpinnersGroupPanel();
		calculateButton = new JButton("أحسب");
		calculateButton.setFont(buttonsFont);
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("ara Jozoor", Font.PLAIN, 25));
		setLayout();
		calculate();
	}

	private void setLayout() {
		setLayout(new GridBagLayout());

		Border outerMostBorder = BorderFactory.createEmptyBorder(50, 50, 50, 50);
		Border outerBorder = BorderFactory.createTitledBorder("");
		Border innerBorder = BorderFactory.createTitledBorder(outerBorder, "بحث عن عدد الكتب المستعارة في فترة معينة",
				3, 2, buttonsFont);
		setBorder(new CompoundBorder(outerMostBorder, innerBorder));

		putComponents();
		fromSpinnersGroup.setBorder(BorderFactory.createTitledBorder(outerBorder, "من", 3, 2, buttonsFont));
		toSpinnersGroup.setBorder(BorderFactory.createTitledBorder(outerBorder, "إلى", 3, 2, buttonsFont));

	}

	private void putComponents() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;
		//////////////////////////////////// firstRow///////////////////////////////////
		gc.gridx = 1;
		gc.gridy = 0;
		gc.ipady = 30;

		add(fromSpinnersGroup, gc);
		////////////////////////////////// nextRow//////////////////////////////////////
		gc.gridy++;
		gc.gridy++;

		add(toSpinnersGroup, gc);
		////////////////////////////////// nextRow//////////////////////////////////////
		gc.gridy++;

		add(resultLabel, gc);
		///////////////////////////////// nextRow////////////////////////////////////////
		gc.gridy++;

		add(calculateButton, gc);

	}

	private void calculate() {
		calculateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {

				event = new BorrowCountEvent(BorrowCountPanel.this, fromSpinnersGroup.getDate(), toSpinnersGroup.getDate());

				try {
					rentingCount = new BorrowingCount(event);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(calculateButton,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
				resultLabel.setText(rentingCount.getCount());

			}

		});
	}

}
