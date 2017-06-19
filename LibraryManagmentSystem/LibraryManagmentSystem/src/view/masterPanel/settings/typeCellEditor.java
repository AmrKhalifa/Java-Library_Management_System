package view.masterPanel.settings;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class typeCellEditor extends AbstractCellEditor implements TableCellEditor{

	JComboBox typeCombo;
	
	public typeCellEditor(){
		typeCombo = new JComboBox(Types.values());
	}
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return typeCombo.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
		typeCombo.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				typeCombo.setSelectedItem(typeCombo.getSelectedItem());
				fireEditingStopped();
				
			}
			
		});
		
		return typeCombo;
	}
	
	
}
