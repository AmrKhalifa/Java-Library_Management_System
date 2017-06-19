package view.masterPanel.AdvancedSearcher;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import backEnd.Model.BorrowedBook;

public class ResultDialog extends JDialog {

	JPanel resultPanel;
	JTable resultTable;
	ResultTableModel tableModel;
	JButton exitButton;
	private final Font buttonsFont = new Font("ara Jozoor", Font.PLAIN, 20);
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	
	public ResultDialog(JPanel parent)
	{
		setVisible(true);
		setLocationRelativeTo(parent);
		setTitle("النتيجة");
		setSize(500,400);
		MakeComponents();
	}
	
	public void MakeComponents()
	{
		resultPanel = new JPanel();
		resultPanel.setLayout(new BorderLayout());
		
		makeTable();
		
		exitButton = new JButton ("خروج");
		exitButton.setFont(buttonsFont);
		
		resultPanel.add(new JScrollPane(resultTable),BorderLayout.CENTER);
		resultPanel.add(exitButton,BorderLayout.SOUTH);
		
		add(resultPanel);
		
		exitButton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();	
			}
			
		});
	}
	public void makeTable()
	{
		tableModel = new ResultTableModel();
		resultTable = new JTable(tableModel);
		resultTable.setAutoCreateRowSorter(true);
		resultTable.getTableHeader().setFont(labelsFont);
		resultTable.setAutoResizeMode(resultTable.AUTO_RESIZE_ALL_COLUMNS);
		resultTable.getColumnModel().getColumn(1).setPreferredWidth(320);
		resultTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		resultTable.setFont(new Font("Arial",Font.PLAIN,14));
		resultTable.setRowHeight(30);
		
		
	}
	
	public void setData(LinkedList<BorrowedBook> books)
	{
		tableModel.setData(books);
	}
}
