package com.inventario.interfaz;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.inventario.controlador.ControladorInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class BotonEliminar extends DefaultCellEditor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton button;
	private boolean   isPushed;
	private ControladorInventario controladorInventario;
	JTable table;
	int row; int column;

	public BotonEliminar(JCheckBox checkBox,ControladorInventario controladorInventario) {
		super(checkBox);
		this.controladorInventario=controladorInventario;
		button = new JButton("Eliminar");
		button.setOpaque(true);
		button.addActionListener(controladorInventario);
		button.setActionCommand(ConstantesInterfaz.ELIMINAR_PRODUCTO);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		this.table=table;
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());

		} else{
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		isPushed = true;
		Integer idProducto= new Integer(table.getValueAt(row, 0).toString());
		controladorInventario.setIdProductoEliminar(idProducto);
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed)  {
			//
			//


			//	      JOptionPane.showMessageDialog(button ,label + ": Ouch!");
			// System.out.println(label + ": Ouch!");
		}
		isPushed = false;
		return "" ;
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}

}
