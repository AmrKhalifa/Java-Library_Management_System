package view.masterPanel.TabbedPane;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import view.masterPanel.Enums.SIMCategory;
import view.masterPanel.Enums.StudyYear;

public class StudyYearCellEditor extends AbstractCellEditor implements TableCellEditor{

	JComboBox studyYear;
	public StudyYearCellEditor(){
		studyYear= new JComboBox(StudyYear.values());
	}
	
	@Override
	public Object getCellEditorValue() {
		return studyYear.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
		studyYear.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				studyYear.setSelectedItem(studyYear.getSelectedItem());
				fireEditingStopped();
				
			}
			
		});
		
		return studyYear;
	}

}
