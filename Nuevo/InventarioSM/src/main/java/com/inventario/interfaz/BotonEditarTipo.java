package com.inventario.interfaz;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.inventario.controlador.ControladorInventario;
import com.inventario.negocio.NegocioInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class BotonEditarTipo extends DefaultCellEditor{
	protected JButton button;
	private String    label;
	private boolean   isPushed;
	private DialogEditarProducto dialogEditarProducto;
	private ControladorInventario controladorInventario;
	private NegocioInventario inventario= new NegocioInventario();
	JTable table;
	int row; int column;

	public BotonEditarTipo(JCheckBox checkBox,ControladorInventario controladorInventario) {
		super(checkBox);
		this.controladorInventario=controladorInventario;
		button = new JButton("Editar");
		button.setOpaque(true);
		button.addActionListener(controladorInventario);
		button.setActionCommand(ConstantesInterfaz.ABRIR_EDITAR_TIPO);
		//		    button.addActionListener(new ActionListener() {
		//		      public void actionPerformed(ActionEvent e) {
		//		        fireEditingStopped();
		//		      }
		//		    });
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
		//		    label = (value ==null) ? "Hola" : value.toString();
		//		    button.setText( label );
		isPushed = true;
		Integer idTipo= new Integer(table.getValueAt(row, 0).toString());
		controladorInventario.setIdTipoEditar(idTipo);
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed)  {
			//
			//


			//		      JOptionPane.showMessageDialog(button ,label + ": Ouch!");
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
