package view.masterPanel.settings;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Utils.Utils;
import backEnd.Model.User;
import backEnd.TableManipulator.UsersTableManipulator;

public class EditUserPanel extends JPanel {

	
	JTable usersTable;
	EditUserTableModel tableModel;
	JPanel savePanel;
	JButton saveButton;
	UsersTableManipulator tableManipulator;
	private JPopupMenu tableMenu;
	private JMenuItem removeItem;
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
	
	public EditUserPanel()
	{
		savePanel = new JPanel();
		tableModel = new EditUserTableModel();
		usersTable = new JTable(tableModel);
		usersTable.setDefaultEditor(Types.class, new typeCellEditor());
		usersTable.getTableHeader().setFont(labelsFont);		
		usersTable.setFont(textFieldFont);
		usersTable.setRowHeight(30);
		usersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		usersTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		usersTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		usersTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		usersTable.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		
		setLayout(new BorderLayout());
		add(new JScrollPane(usersTable),BorderLayout.CENTER);
		add(savePanel,BorderLayout.SOUTH);
		
		saveButton = new JButton("");
		saveButton.setIcon(Utils.creatImageIcon("/images/save.jpg"));
		savePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		savePanel.add(saveButton);
		
		tableManipulator = new UsersTableManipulator();
		try {
			tableManipulator.load();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setData(tableManipulator.getUsers());
		
		
		saveButton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					tableManipulator.save();
					tableManipulator.load();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
	}
	
	private void setData(LinkedList<User> users)
	{
		tableModel.setData(users);
	}
	
	
	
	private void resizeTable()
	{
		usersTable.setRowHeight(30);
		usersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		usersTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		usersTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		usersTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		usersTable.getColumnModel().getColumn(3).setPreferredWidth(70);
	}

}
