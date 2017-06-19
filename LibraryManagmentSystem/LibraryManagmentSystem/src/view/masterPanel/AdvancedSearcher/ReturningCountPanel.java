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
import backEnd.DirectOperations.ReturningCount;
import view.masterPanel.AdvancedSearcher.Events.ReturningCountEvent;

public class ReturningCountPanel extends javax.swing.JPanel {

	SpinnersGroupPanel fromPanel;
	SpinnersGroupPanel toPanel;
	JLabel resultLabel;
	JButton calculateButton;
	ReturningCountEvent event;
	ReturningCount returningCount;// backEnd could be done better with the maestro
								// class

	Font buttonsFont = new Font("ara Jozoor", Font.PLAIN, 20);

	public ReturningCountPanel() {
		fromPanel = new SpinnersGroupPanel();
		toPanel = new SpinnersGroupPanel();
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
		fromPanel.setBorder(BorderFactory.createTitledBorder(outerBorder, "من", 3, 2, buttonsFont));
		toPanel.setBorder(BorderFactory.createTitledBorder(outerBorder, "إلى", 3, 2, buttonsFont));

	}

	private void putComponents() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;
		//////////////////////////////////// firstRow///////////////////////////////////
		gc.gridx = 1;
		gc.gridy = 0;
		gc.ipady = 30;

		add(fromPanel, gc);
		////////////////////////////////// nextRow//////////////////////////////////////
		gc.gridy++;
		gc.gridy++;

		add(toPanel, gc);
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

				event = new ReturningCountEvent(ReturningCountPanel.this, fromPanel.getDate(), toPanel.getDate());

				try {
					returningCount = new ReturningCount(event);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(calculateButton,
							"عفواً تعذر الاتصال بقاعدة البيانات   ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
				resultLabel.setText(returningCount.getCount());

			}

		});
	}

}
