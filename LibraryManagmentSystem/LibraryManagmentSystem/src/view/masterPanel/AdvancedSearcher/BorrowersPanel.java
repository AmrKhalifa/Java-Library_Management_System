package view.masterPanel.AdvancedSearcher;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import backEnd.BorrowerData;

public class BorrowersPanel extends JPanel {

	private JTable borrowersTable;
	private BorrowersTableModel tableModel = new BorrowersTableModel();
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font buttonsFont = new Font("Ara Aqeeq Bold", Font.PLAIN, 16);
	
	
	public BorrowersPanel() {
		setLayout();
	}

	private void setLayout() {
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		borrowersTable = new JTable(tableModel);
		borrowersTable.setFont(new Font("Arial", Font.PLAIN, 	12));
		borrowersTable.getTableHeader().setFont(labelsFont);
		borrowersTable.setFillsViewportHeight(true);
		borrowersTable.setRowHeight(30);
		borrowersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(borrowersTable.getModel());
		borrowersTable.setRowSorter(sorter);
		
		
		add(new JScrollPane(borrowersTable));
	}

	public void setData(LinkedList<BorrowerData> borrowersData) {
		
		tableModel.setData(borrowersData);
		tableModel.fireTableDataChanged();
	}
}
