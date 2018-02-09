package com.inventario.interfaz;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.inventario.controlador.ControladorInventario;
import com.inventario.enums.TipoInventarioEnum;
import com.inventario.esquema.Producto;
import com.inventario.utilidades.ConstantesInterfaz;
import com.toedter.calendar.JCalendar;

public class DialogCrearInventario extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTipo;
	private JComboBox<TipoInventarioEnum> boxTipoInventario;
	private JLabel lblFecha;
	private JCalendar calendarFecha;
	private JTable jtTable;
	private DefaultTableModel dtm;
	private ControladorInventario controlador;
	private JButton btnAgregarProductos;
	private JButton btnEliminarProductos;
	private JButton btnCrearInventario;

	public DialogCrearInventario(ControladorInventario controladorInventario) {
		setSize(900, 700);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Crear Inventario");
		setLocationRelativeTo(null);
		this.controlador = controladorInventario;
		createJtable();
		setColumnJtable();
		inicializarComponentes();
	}

	private void createJtable() {
		jtTable = new JTable();
		this.dtm = (DefaultTableModel) jtTable.getModel();
		JScrollPane scrollPane = new JScrollPane(jtTable);
		this.add(scrollPane);
		scrollPane.setBounds(50, 200, 780, 400);
	}

	public void agregarBoton() {
	}

	public void addValueJTable(Producto producto) {

		dtm.setRowCount(dtm.getRowCount() + 1);
		dtm.setValueAt(producto.getIdProducto(), dtm.getRowCount() - 1, 0);
		dtm.setValueAt(producto.getCantidad(), dtm.getRowCount() - 1, 1);
		dtm.setValueAt(producto.getNombreProducto(), dtm.getRowCount() - 1, 2);
		dtm.setValueAt(producto.getPrecioCompra(), dtm.getRowCount() - 1, 3);
		dtm.setValueAt(producto.getPrecioVenta(), dtm.getRowCount() - 1, 4);
		dtm.setValueAt(producto.getCategoria(), dtm.getRowCount() - 1, 5);
		dtm.setValueAt(producto.getGenero(), dtm.getRowCount() - 1, 6);
		dtm.setValueAt(producto.getMarca(), dtm.getRowCount() - 1, 7);
		dtm.setValueAt(producto.getTamanio(), dtm.getRowCount() - 1, 8);
		dtm.setValueAt(producto.getOrigen(), dtm.getRowCount() - 1, 9);
		dtm.setValueAt(producto.getTipo(), dtm.getRowCount() - 1, 10);

	}

	public void setColumnJtable() {
		DefaultTableModel dtm = (DefaultTableModel) jtTable.getModel();
		dtm.addColumn("Identificador");
		dtm.addColumn("Cantidad");
		dtm.addColumn("Nombre");
		dtm.addColumn("Precio interno");
		dtm.addColumn("Precio venta");
		dtm.addColumn("Categoria");
		dtm.addColumn("Genero");
		dtm.addColumn("Marca");
		dtm.addColumn("Tamaño");
		dtm.addColumn("Origen");
		dtm.addColumn("Tipo");

	}

	public void adicionarProducto(List<Producto> productos) {
		clearTable();
		for (int i = 0; i < productos.size(); i++) {
			addValueJTable(productos.get(i));
		}
		this.repaint();

	}

	private void clearTable() {
		int aux = dtm.getRowCount();
		for (int i = 0; i < aux; i++) {
			dtm.removeRow(0);
		}
	}

	public void inicializarComponentes() {
		lblTipo = new JLabel("Tipo de inventario:");
		lblTipo.setBounds(30, 30, 120, 20);

		boxTipoInventario = new JComboBox<TipoInventarioEnum>();
		boxTipoInventario.setBounds(150, 30, 100, 20);
		for (TipoInventarioEnum tipoInventario : TipoInventarioEnum.values()) {
			boxTipoInventario.addItem(tipoInventario);
		}

		lblFecha = new JLabel("Fecha de inventario:");
		lblFecha.setBounds(280, 30, 120, 20);

		calendarFecha = new JCalendar();
		calendarFecha.setBounds(400, 30, 200, 150);

		btnAgregarProductos = new JButton("Agregar productos");
		btnAgregarProductos.setBounds(50, 100, 150, 20);
		btnAgregarProductos.addActionListener(this.controlador);
		btnAgregarProductos.setActionCommand(ConstantesInterfaz.AGREGAR_PRODUCTOS);

		btnEliminarProductos = new JButton("Eliminar producto");
		btnEliminarProductos.setBounds(50, 150, 150, 20);
		btnEliminarProductos.addActionListener(this.controlador);
		btnEliminarProductos.setActionCommand(ConstantesInterfaz.ELIMINAR_PRODUCTO_AGREGADO);

		btnCrearInventario = new JButton("Crear inventario");
		btnCrearInventario.setBounds(50, 620, 150, 20);
		btnCrearInventario.addActionListener(this.controlador);
		btnCrearInventario.setActionCommand(ConstantesInterfaz.CERRAR_INVENTARIO);

		this.add(lblTipo);
		this.add(boxTipoInventario);
		this.add(lblFecha);
		this.add(calendarFecha);
		this.add(btnAgregarProductos);
		this.add(btnCrearInventario);
		this.add(btnEliminarProductos);
	}

	public JTable getJtTable() {
		return jtTable;
	}

	public void setJtTable(JTable jtTable) {
		this.jtTable = jtTable;
	}

	public JComboBox<TipoInventarioEnum> getBoxTipoInventario() {
		return boxTipoInventario;
	}

	public void setBoxTipoInventario(JComboBox<TipoInventarioEnum> boxTipoInventario) {
		this.boxTipoInventario = boxTipoInventario;
	}

	public JCalendar getCalendarFecha() {
		return calendarFecha;
	}

	public void setCalendarFecha(JCalendar calendarFecha) {
		this.calendarFecha = calendarFecha;
	}

}
