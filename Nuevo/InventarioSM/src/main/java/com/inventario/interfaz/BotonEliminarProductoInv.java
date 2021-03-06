package com.inventario.interfaz;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.inventario.controlador.ControladorInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class BotonEliminarProductoInv extends DefaultCellEditor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton button;
	private Boolean   isPushed;
	private ControladorInventario controladorInventario;

	public BotonEliminarProductoInv(JCheckBox checkBox,ControladorInventario controladorInventario) {
		super(checkBox);
		this.controladorInventario=controladorInventario;
		button = new JButton("Eliminar");
		button.setOpaque(Boolean.TRUE);
		button.addActionListener(controladorInventario);
		button.setActionCommand(ConstantesInterfaz.ELIMINAR_PRODUCTO_INVENTARIO);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			Boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());

		} else{
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		isPushed = Boolean.TRUE;
		Integer idProducto= new Integer(table.getValueAt(row, 0).toString());
		controladorInventario.setIdProductoEliminarInventario(idProducto);
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed)  {
		}
		isPushed = Boolean.FALSE;
		return "" ;
	}

	public boolean stopCellEditing() {
		isPushed = Boolean.FALSE;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}

	

}
