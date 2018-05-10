package com.inventario.interfaz;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.inventario.controlador.ControladorInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class BotonEdicion extends DefaultCellEditor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton button;
	private boolean   isPushed;
	private ControladorInventario controladorInventario;
	JTable table;
	int row; int column;

	public BotonEdicion(JCheckBox checkBox,ControladorInventario controladorInventario) {
		super(checkBox);
		this.controladorInventario=controladorInventario;
		button = new JButton("Editar");
		button.setOpaque(true);
		button.addActionListener(controladorInventario);
		button.setActionCommand(ConstantesInterfaz.ABRIR_VENTANA_EDITAR);
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
		controladorInventario.setIdProductoEditar(idProducto);
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed)  {
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
