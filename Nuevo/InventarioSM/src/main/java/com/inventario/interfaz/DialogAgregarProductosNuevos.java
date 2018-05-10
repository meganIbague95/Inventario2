package com.inventario.interfaz;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.inventario.controlador.ControladorInventario;
import com.inventario.enums.GeneroEnum;
import com.inventario.esquema.Categoria;
import com.inventario.esquema.Marca;
import com.inventario.esquema.Origen;
import com.inventario.esquema.Producto;
import com.inventario.esquema.Tamanio;
import com.inventario.esquema.TipoProducto;
import com.inventario.utilidades.ConstantesInterfaz;

public class DialogAgregarProductosNuevos extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtTable;
	private DefaultTableModel dtm ;
	
	private JButton btnAgregar;
	private ControladorInventario controladorInventario;

	public  DialogAgregarProductosNuevos(ControladorInventario controladorInventario) {
		this.controladorInventario=controladorInventario;
		setSize(800, 800);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Agregar productos");
		setLocationRelativeTo(null);
		createJtable();
		setColumnJtable();
		inicializar();
		
	}
	private void createJtable() {
		jtTable = new JTable();
		this.dtm = (DefaultTableModel) jtTable.getModel();
		JScrollPane scrollPane = new JScrollPane(jtTable);
		this.add(scrollPane);
		scrollPane.setBounds(50, 30, 650, 600);
	}
	
	public void addValueJTable(Producto producto) {
		
		dtm.setRowCount(dtm.getRowCount() + 1);
		dtm.setValueAt(producto.getIdProducto(), dtm.getRowCount() - 1, 0);
		dtm.setValueAt(producto.getCantidad().getValorCampo(), dtm.getRowCount() - 1, 1);
		dtm.setValueAt(producto.getNombreProducto().getValorCampo(), dtm.getRowCount() - 1, 2);
		dtm.setValueAt(producto.getPrecioCompra().getValorCampo(), dtm.getRowCount() - 1, 3);
		dtm.setValueAt(producto.getPrecioVenta().getValorCampo(), dtm.getRowCount() - 1, 4);
		dtm.setValueAt(producto.getCategoria(), dtm.getRowCount() - 1, 5);
		dtm.setValueAt(producto.getGenero(), dtm.getRowCount() - 1, 6);
		dtm.setValueAt(producto.getMarca(), dtm.getRowCount() - 1, 7);
		dtm.setValueAt(producto.getTamanio(), dtm.getRowCount() - 1, 8);
		dtm.setValueAt(producto.getOrigen(), dtm.getRowCount() - 1, 9);	
		dtm.setValueAt(producto.getTipo(), dtm.getRowCount() - 1, 10);	
		jtTable.getColumn("Eliminar").setCellEditor(new BotonEliminarProductoInv(new JCheckBox(),controladorInventario));
		
	}

	public void setColumnJtable() {
		DefaultTableModel dtm = (DefaultTableModel) jtTable.getModel();
		dtm.addColumn("Identificador");
		dtm.addColumn("Cantidad");
		dtm.addColumn("Nombre");
		dtm.addColumn("Precio interno");
		dtm.addColumn("Precio venta");
		dtm.addColumn("Categoria");
		dtm.addColumn("Género");
		dtm.addColumn("Marca");
		dtm.addColumn("Tamaño");
		dtm.addColumn("Origen");
		dtm.addColumn("Tipo");
		dtm.addColumn("Eliminar");

	}
	
	public void adicionarProducto(List<Producto> productos) {
		clearTable();
		for (int i = 0; i < productos.size(); i++) {
			addValueJTable(productos.get(i));
		}
		this.repaint();
		

	}
	
	private void clearTable() {
		int aux=dtm.getRowCount();
		   for(int i=0; i<aux;i++){
			   dtm.removeRow(0);
		   }
	}
	private void inicializar(){

		
		
		btnAgregar= new JButton("Agregar");
		btnAgregar.setBounds(350, 650, 100, 25);
		btnAgregar.addActionListener(this.controladorInventario);
		btnAgregar.setActionCommand(ConstantesInterfaz.AGREGAR_PRODUCTOS_A_INVENTARIO);
		
		this.add(btnAgregar);
	}
	public Producto extraerDatos(){
		int filaSeleccionada= jtTable.getSelectedRow();
		Producto producto=null;
		if (filaSeleccionada == -1){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
        } else {        	
        	Integer identificador= (Integer)jtTable.getValueAt(filaSeleccionada, 0);
        	String cantidad=jtTable.getValueAt(filaSeleccionada, 1).toString();
        	String nombre=jtTable.getValueAt(filaSeleccionada, 2).toString();
        	String precioInterno=jtTable.getValueAt(filaSeleccionada, 3).toString();
        	String precioVenta=jtTable.getValueAt(filaSeleccionada, 4).toString();
        	Categoria categoria=(Categoria)jtTable.getValueAt(filaSeleccionada, 5);
        	GeneroEnum genero = (GeneroEnum)jtTable.getValueAt(filaSeleccionada, 6);
        	Marca marca=(Marca)jtTable.getValueAt(filaSeleccionada, 7);
        	Tamanio tamanio=(Tamanio)jtTable.getValueAt(filaSeleccionada, 8);
        	Origen origen= (Origen)jtTable.getValueAt(filaSeleccionada, 9);
        	TipoProducto tipo= (TipoProducto)jtTable.getValueAt(filaSeleccionada, 10);
        	producto = new Producto(nombre, categoria, genero, tipo, marca, precioInterno, tamanio, identificador, origen, cantidad, precioVenta);
        }
		return producto;
	}
	
	public JTable getJtTable() {
		return jtTable;
	}
	public void setJtTable(JTable jtTable) {
		this.jtTable = jtTable;
	}
	

}
