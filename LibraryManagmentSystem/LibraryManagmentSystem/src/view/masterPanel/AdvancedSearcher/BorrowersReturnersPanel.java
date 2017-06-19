package view.masterPanel.AdvancedSearcher;

import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import backEnd.BorrowerReturner;

public class BorrowersReturnersPanel extends JPanel {

	private JTable table;
	private BorrowersReturnersTableModel tableModel = new BorrowersReturnersTableModel();
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font buttonsFont = new Font("Ara Aqeeq Bold", Font.PLAIN, 16);
	
	public BorrowersReturnersPanel() {
		setLayout();
	}

	private void setLayout() {
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		table = new JTable(tableModel);
		table = new JTable(tableModel);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.getTableHeader().setFont(labelsFont);
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		
		add(new JScrollPane(table));
	}

	public void setData(LinkedList<BorrowerReturner> list) {
		
		tableModel.setData(list);
		tableModel.fireTableDataChanged();
		tableModel.fireTableDataChanged();
	}
	
}
