package view.masterPanel.AdvancedSearcher;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class SpinnersGroupPanel extends JPanel{
	
	private SpinnerNumberModel daysModel;
	private SpinnerNumberModel monthsModel;
	private SpinnerNumberModel yearModel;
	private JSpinner daysSpinner;
	private JSpinner monthsSpinner;
	private JSpinner yearSpinner;
	
	Font buttonsFont_2 = new Font("Ara Aqeeq Bold", Font.PLAIN, 20);
	public SpinnersGroupPanel()
	{
		
		daysModel = new SpinnerNumberModel(1,1,30,1);
		monthsModel = new SpinnerNumberModel(1,1,12,1);
		yearModel = new SpinnerNumberModel(2016,2016,2100,1);
		
		yearSpinner = new JSpinner(yearModel);
		yearSpinner.setFont(buttonsFont_2);
		
		monthsSpinner = new JSpinner(monthsModel);
		monthsSpinner.setFont(buttonsFont_2);
		
		daysSpinner = new JSpinner(daysModel);
		daysSpinner .setFont(buttonsFont_2);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(yearSpinner);
		add(monthsSpinner);
		add(daysSpinner);
		
		
}
	
	public String getDate()
	{
		return yearSpinner.getValue()+"-"+monthsSpinner.getValue()+"-"+daysSpinner.getValue();
		
	}
	
	
}