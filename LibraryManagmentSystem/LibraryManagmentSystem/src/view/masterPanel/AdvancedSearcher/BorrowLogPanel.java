package view.masterPanel.AdvancedSearcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class BorrowLogPanel extends javax.swing.JPanel {

	SpinnersPanel fromPanel;
	SpinnersPanel toPanel;
	JLabel resultLabel;
	JButton calculateButton;

	Font buttonsFont = new Font("ara Jozoor", Font.PLAIN, 20);

	public BorrowLogPanel() {
		fromPanel = new SpinnersPanel();
		toPanel = new SpinnersPanel();
		calculateButton = new JButton("أحسب");
		calculateButton.setFont(buttonsFont);
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("ara Jozoor", Font.PLAIN,25));
		setLayout();
		calculate();
	}

	private void setLayout() {
		setLayout(new GridBagLayout());

		Border outerMostBorder = BorderFactory.createEmptyBorder(50, 50, 50, 50);
		Border outerBorder = BorderFactory.createTitledBorder("");
		Border innerBorder = BorderFactory.createTitledBorder(outerBorder, "بحث عن عدد الكتب المستعارة �?ي �?ترة معينة", 3, 2,buttonsFont);
		setBorder(new CompoundBorder(outerMostBorder, innerBorder));

		putComponents();
		fromPanel.setBorder(BorderFactory.createTitledBorder(outerBorder,"من",3,2,buttonsFont));
		toPanel.setBorder(BorderFactory.createTitledBorder(outerBorder,"إلى",3,2,buttonsFont));
		
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

		add(resultLabel ,gc);
		///////////////////////////////// nextRow////////////////////////////////////////
		gc.gridy++;

		add(calculateButton, gc);

	}
	
	private void calculate()
	{
		calculateButton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent ev) {
				
				resultLabel.setText(fromPanel.getDate() + "  " + toPanel.getDate());
			}
			
		});
	}
	
}
