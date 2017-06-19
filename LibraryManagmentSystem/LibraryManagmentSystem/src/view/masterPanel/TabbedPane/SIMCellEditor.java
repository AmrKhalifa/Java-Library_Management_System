package view.masterPanel.TabbedPane;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import view.masterPanel.Enums.SIMCategory;

public class SIMCellEditor extends AbstractCellEditor implements TableCellEditor {

	JComboBox sim;
	public SIMCellEditor(){
		sim= new JComboBox(SIMCategory.values());
	}
	
	@Override
	public Object getCellEditorValue() {
		return sim.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
		sim.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sim.setSelectedItem(sim.getSelectedItem());
				fireEditingStopped();
				
			}
			
		});
		
		return sim;
	}

}
