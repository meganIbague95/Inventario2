package com.inventario.interfaz;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.inventario.controlador.ControladorInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class BotonEditarInventario extends DefaultCellEditor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton button;
	private Boolean   isPushed;
	private ControladorInventario controladorInventario;

	public BotonEditarInventario(JCheckBox checkBox,ControladorInventario controladorInventario) {
		super(checkBox);
		this.controladorInventario=controladorInventario;
		button = new JButton("Editar");
		button.setOpaque(Boolean.TRUE);
		button.addActionListener(controladorInventario);
		button.setActionCommand(ConstantesInterfaz.ABRIR_EDITAR_INVENTARIO);
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
		Integer idInventario= new Integer(table.getValueAt(row, 0).toString());
		controladorInventario.setIdInventarioEditar(idInventario);
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
