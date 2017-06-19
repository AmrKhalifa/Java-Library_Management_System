package view.masterPanel.TabbedPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import Utils.Utils;
import backEnd.DirectOperations.DeleteRowInMemberTable;
import backEnd.Model.Member;
import view.masterPanel.Enums.SIMCategory;
import view.masterPanel.Enums.StudyYear;

public class MemberTablePanel extends JPanel implements ActionListener {

	JTable memberTable;
	NotEditableMemberTableModel notEditableMemberTableModel;
	EditableMemberTableModel editableMemberTableModel;
	JPanel buttonsPanel;
	JButton saveButton;
	JButton loadButton;
	JButton editButton;
	ButtonListener listener;
	private JPopupMenu tableMenu;
	private JMenuItem removeItem;
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font buttonsFont = new Font("Ara Aqeeq Bold", Font.PLAIN, 16);
	
	long member_id= 00;
	public MemberTablePanel() {
		setLayout(new BorderLayout());
		Border outerBorder = BorderFactory.createEmptyBorder(10, 20, 20, 20);
		setBorder(outerBorder);

		notEditableMemberTableModel = new NotEditableMemberTableModel();
		editableMemberTableModel= new EditableMemberTableModel();
		memberTable = new JTable(notEditableMemberTableModel);
		memberTable.setFont(new Font("Arial", Font.PLAIN, 15));
		memberTable.setFillsViewportHeight(true);
		memberTable.getTableHeader().setFont(labelsFont);
		memberTable.setDefaultEditor(SIMCategory.class, new SIMCellEditor());
		memberTable.setDefaultEditor(StudyYear.class, new StudyYearCellEditor());
		
		resizeTable();
		
		tableMenu = new JPopupMenu();
		removeItem = new JMenuItem("حذف العضو");
		removeItem.setFont(buttonsFont);
		tableMenu.add(removeItem);
		
		add(new JScrollPane(memberTable), BorderLayout.CENTER);

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

		add(buttonsPanel, BorderLayout.NORTH);

		buttonsPanel.add(saveButton);
		buttonsPanel.add(editButton);
		buttonsPanel.add(loadButton);

		saveButton.addActionListener(this);
		editButton.addActionListener(this);
		loadButton.addActionListener(this);
		
		memberTable.setAutoCreateRowSorter(true);
		
		
	}

	private void resizeTable() {
		memberTable.setRowHeight(30);
		memberTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		memberTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		memberTable.getColumnModel().getColumn(2).setPreferredWidth(58);
		memberTable.getColumnModel().getColumn(3).setPreferredWidth(190);
		memberTable.getColumnModel().getColumn(4).setPreferredWidth(98);
		memberTable.getColumnModel().getColumn(5).setPreferredWidth(170);
		memberTable.getColumnModel().getColumn(6).setPreferredWidth(78);
		memberTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	public void setData(LinkedList<Member> members) {
		notEditableMemberTableModel.setData(members);
		editableMemberTableModel.setData(members);
	}

	

	public void setListener(ButtonListener listener) {
		this.listener = listener;
	}

	public void refresh() {
		notEditableMemberTableModel.fireTableDataChanged();
		editableMemberTableModel.fireTableDataChanged();
	}
	
	private void createRowDeleter()
	{
		memberTable.addMouseListener(new MouseAdapter () {
			@Override
			public void mousePressed(MouseEvent ms) {
				int row = memberTable.rowAtPoint(ms.getPoint());
				memberTable.getSelectionModel().setSelectionInterval(row, row);
				if(ms.getButton() == MouseEvent.BUTTON3)
					tableMenu.show(memberTable, ms.getX(), ms.getY());
			}
			
		});
		
		removeItem.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				int row = memberTable.getSelectedRow();
				member_id = (long) editableMemberTableModel.getValueAt(row,6);
				editableMemberTableModel.fireTableRowsDeleted(row, row);
				notEditableMemberTableModel.fireTableRowsDeleted(row, row);
				deleteRow(member_id);
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
			memberTable.setModel(editableMemberTableModel);
			createRowDeleter();
			refresh();
			resizeTable();
		}
		
		if(saveButton == ev.getSource()){
				listener.fireSaveButtonPressed();
				memberTable.setModel(notEditableMemberTableModel);
				deleteRow(member_id);
				destroyRowDeleter();
				listener.fireLoadButtonPressed();
				refresh();
				resizeTable();
				
		}
		
	}

	private void deleteRow(long member_id) {
		if(member_id != 00)
			try {
				new DeleteRowInMemberTable(member_id);
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
		memberTable.addMouseListener(new MouseAdapter () {
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

