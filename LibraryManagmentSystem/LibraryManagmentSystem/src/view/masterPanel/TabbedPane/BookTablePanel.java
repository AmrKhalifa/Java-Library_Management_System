package view.masterPanel.TabbedPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Utils.Utils;
import backEnd.DirectOperations.DeleteRowInBookTable;
import backEnd.Model.Book;

public class BookTablePanel extends JPanel implements ActionListener {

	private JTable bookTable;
	private NotEditableBookTableModel notEditableBookTableModel;
	private EditableBookTableModel editableBookTableModel;
	private JPanel buttonsPanel;
	private JButton saveButton;
	private JButton loadButton;
	private JButton editButton;
	private ButtonListener listener;
	private JPopupMenu tableMenu;
	private JMenuItem removeItem;
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font buttonsFont = new Font("Ara Aqeeq Bold", Font.PLAIN, 16);
	long book_id = 0000;

	public BookTablePanel() {
		setLayout(new BorderLayout());
		Border outerBorder = BorderFactory.createEmptyBorder(10, 20, 20, 20);
		setBorder(outerBorder);

		notEditableBookTableModel = new NotEditableBookTableModel();
		editableBookTableModel = new EditableBookTableModel();
		bookTable = new JTable(notEditableBookTableModel);
		
		
		bookTable.setFont(new Font("Arial", Font.PLAIN, 15));
		bookTable.getTableHeader().setFont(labelsFont);
		bookTable.setFillsViewportHeight(true);
		
		resizeTable();
		
		tableMenu = new JPopupMenu();
		removeItem = new JMenuItem("حذف الكتاب");
		removeItem.setFont(buttonsFont);
		tableMenu.add(removeItem);
		
		saveButton = new JButton("");
		saveButton.setFont(buttonsFont);
		saveButton.setToolTipText("حفظ التغييرات");
		saveButton.setIcon(Utils.creatImageIcon("/images/save.jpg"));
		

		loadButton = new JButton("");
		loadButton.setFont(buttonsFont);
		loadButton.setToolTipText("تحميل الجدول");
		loadButton.setIcon(Utils.creatImageIcon("/images/load.png"));

		editButton = new JButton("");
		editButton.setFont(buttonsFont);
		editButton.setToolTipText("تعديل المعلومات");
		editButton.setIcon(Utils.creatImageIcon("/images/edit.png"));
		
		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		add(new JScrollPane(bookTable), BorderLayout.CENTER);

		add(buttonsPanel, BorderLayout.NORTH);

		buttonsPanel.add(saveButton);
		buttonsPanel.add(editButton);
		buttonsPanel.add(loadButton);

		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
		editButton.addActionListener(this);
		
		bookTable.setAutoCreateRowSorter(true);
		
		makeSorter();
	}

	private void resizeTable() {
		bookTable.setRowHeight(30);
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(160);
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(65);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(175);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(215);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(245);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		
	}
	
	private void makeSorter (){
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(bookTable.getModel());
		bookTable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		 
		int columnIndexForBook = 4;
		sortKeys.add(new RowSorter.SortKey(columnIndexForBook, SortOrder.ASCENDING));
		 
		int columnIndexForWriter= 3;
		sortKeys.add(new RowSorter.SortKey(columnIndexForWriter, SortOrder.ASCENDING));
		
		int columnIndexForHouse= 2;
		sortKeys.add(new RowSorter.SortKey(columnIndexForHouse, SortOrder.ASCENDING));
		
		sorter.setSortKeys(sortKeys);
		sorter.sort();
	}
	
	public void refresh() {
		notEditableBookTableModel.fireTableDataChanged();
		editableBookTableModel.fireTableDataChanged();
	}

	public void setData(LinkedList<Book> books) {
		notEditableBookTableModel.setDate(books);
		editableBookTableModel.setData(books);
	}

	public void setListener(ButtonListener listener) {
		this.listener = listener;
	}

	private void createRowDeleter()
	{
		bookTable.addMouseListener(new MouseAdapter () {
			@Override
			public void mousePressed(MouseEvent ms) {
				int row = bookTable.rowAtPoint(ms.getPoint());
				bookTable.getSelectionModel().setSelectionInterval(row, row);
				if(ms.getButton() == MouseEvent.BUTTON3)
					tableMenu.show(bookTable, ms.getX(), ms.getY());
				
				System.out.println(ms.getSource());
			}
			
		});
		
		removeItem.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent ev) {
				
				int row = bookTable.getSelectedRow();
				book_id = (long) editableBookTableModel.getValueAt(row,5);
				editableBookTableModel.fireTableRowsDeleted(row, row);
				notEditableBookTableModel.fireTableRowsDeleted(row, row);
				refresh();
				resizeTable();
			}
			
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {


		if (loadButton == ev.getSource()){
			listener.fireLoadButtonPressed();
			resizeTable();
		}
		
		if(editButton == ev.getSource() ){
			bookTable.setModel(editableBookTableModel);
			createRowDeleter();
			refresh();
			resizeTable();
		}
		
		if(saveButton == ev.getSource()){
				listener.fireSaveButtonPressed();
				bookTable.setModel(notEditableBookTableModel);
				deleteRow(book_id);
				destroyRowDeleter();
				listener.fireLoadButtonPressed();
				refresh();
				resizeTable();
				
		}
		
	}

	private void deleteRow(long book_id) {
		if(book_id != 0000)
			try {
				new DeleteRowInBookTable(book_id);
			} catch (Exception e) {
			// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(removeItem,
						"عفواً تعذر الاتصال بقاعدة البيانات  ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
						"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
	}

	private void destroyRowDeleter()
	{
		bookTable.addMouseListener(new MouseAdapter () {
			@Override
			public void mousePressed(MouseEvent ms) {
				
			}
			
		});
		
		removeItem.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent ev) {
				
				
			}
			
		});
	}
	
	public void hideFromRegular()
	{
		editButton.setVisible(false);
		saveButton.setVisible(false);
	}
	
}
	
	

