package com.inventario.interfaz;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.inventario.controlador.ControladorInventario;
import com.inventario.esquema.Inventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class DialogMostrarInventarios extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorInventario controladorInventario;
	private JLabel lblInventario;
	private JComboBox<String> boxInventario;
	
	private JButton btnMostrar;
	private JButton btnCerrar;
	private JTable jtTable;
	private DefaultTableModel dtm ;


	public DialogMostrarInventarios(ControladorInventario controladorInventario) {
		this.controladorInventario=controladorInventario;
		setSize(800, 700);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Consultar inventarios");
		setLocationRelativeTo(null);
			createJtable();
			setColumnJtable();
		inicializar();
	}
	public void inicializar(){
		lblInventario= new JLabel("Inventario");
		lblInventario.setBounds(30,30,100,25);

		boxInventario= new JComboBox<String>();
		boxInventario.setBounds(150, 30, 100, 25);
		boxInventario.addItem("Local");
		boxInventario.addItem("Bodega");

		Image mostrar = new ImageIcon(".\\imagenes\\mostrarInventario.jpg").getImage();
		ImageIcon imageMostrar = new ImageIcon(mostrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnMostrar= new JButton("Mostrar");
		btnMostrar.setIcon(imageMostrar);
		btnMostrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnMostrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnMostrar.setBounds(330, 15, 100, 60);
		btnMostrar.addActionListener(this.controladorInventario);
		btnMostrar.setActionCommand(ConstantesInterfaz.MOSTRAR_INVENTARIO);
		
		Image cerrar = new ImageIcon(".\\imagenes\\cerrarInv.png").getImage();
		ImageIcon imageCerrar= new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar= new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(500, 15, 100, 60);
		btnCerrar.addActionListener(this.controladorInventario);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR_MOSTRAR_INVENTARIO);
		

		this.add(lblInventario);
		this.add(boxInventario);
		this.add(btnMostrar);
		this.add(btnCerrar);
	}
	private void createJtable() {
		jtTable = new JTable();
		this.dtm = (DefaultTableModel) jtTable.getModel();
		JScrollPane scrollPane = new JScrollPane(jtTable);
		this.add(scrollPane);
		scrollPane.setBounds(50, 100, 650, 550);
	}
	
	public void addValueJTable(Inventario inventario) {
		
		dtm.setRowCount(dtm.getRowCount() + 1);
		dtm.setValueAt(inventario.getIdInventario(), dtm.getRowCount() - 1, 0);
		dtm.setValueAt(inventario.getTipoInventario(), dtm.getRowCount() - 1, 2);
		dtm.setValueAt(inventario.getFechaInventario(), dtm.getRowCount() - 1, 3);
		jtTable.getColumn("Editar").setCellEditor(new BotonEditarInventario(new JCheckBox(),controladorInventario));
		jtTable.getColumn("Eliminar").setCellEditor(new BotonEliminarInventario(new JCheckBox(),controladorInventario));
		
	}

	public void setColumnJtable() {
		DefaultTableModel dtm = (DefaultTableModel) jtTable.getModel();
		dtm.addColumn("Identificador");
		dtm.addColumn("Tipo Inventario");
		dtm.addColumn("Fecha Inventario");
		dtm.addColumn("Editar");
		dtm.addColumn("Eliminar");

	}
	
	public void adicionarProducto(List<Inventario> inventarios) {
		clearTable();
		for (int i = 0; i < inventarios.size(); i++) {
			addValueJTable(inventarios.get(i));
		}
		this.repaint();
		

	}
	
	private void clearTable() {
		int aux=dtm.getRowCount();
		   for(int i=0; i<aux;i++){
			   dtm.removeRow(0);
		   }
	}
	public JComboBox<String> getBoxInventario() {
		return boxInventario;
	}
	public void setBoxInventario(JComboBox<String> boxInventario) {
		this.boxInventario = boxInventario;
	}
	
}
