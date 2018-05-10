package com.inventario.interfaz;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
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
	private JButton btnCerrar;
	private JButton btnExportarPdf;

	public DialogCrearInventario(ControladorInventario controladorInventario) {
		setSize(900, 700);
		setResizable(Boolean.FALSE);
//		setAlwaysOnTop( true );
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
		scrollPane.setBounds(50, 180, 780, 400);
	}

	public void agregarBoton() {
	}

	public void addValueJTable(Producto producto) {
		if(producto!=null){
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
		}
		else{
			JOptionPane.showMessageDialog(null, "Seleccione una fila válida");
		}

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

		
		Image agregar = new ImageIcon(".\\imagenes\\agregarAinventario.png").getImage();
		ImageIcon imageAgregar= new ImageIcon(agregar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnAgregarProductos = new JButton("Agregar Productos");
		btnAgregarProductos.setIcon(imageAgregar);
		btnAgregarProductos.setHorizontalTextPosition( SwingConstants.CENTER );
		btnAgregarProductos.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnAgregarProductos.setBounds(50, 80, 150, 60);
		btnAgregarProductos.addActionListener(this.controlador);
		btnAgregarProductos.setActionCommand(ConstantesInterfaz.AGREGAR_PRODUCTOS);

		Image eliminar = new ImageIcon(".\\imagenes\\eliminarDeInventario.png").getImage();
		ImageIcon imageEliminar= new ImageIcon(eliminar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnEliminarProductos = new JButton("Eliminar producto");
		btnEliminarProductos.setIcon(imageEliminar);
		btnEliminarProductos.setHorizontalTextPosition( SwingConstants.CENTER );
		btnEliminarProductos.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnEliminarProductos.setBounds(230, 80, 150, 60);
		btnEliminarProductos.addActionListener(this.controlador);
		btnEliminarProductos.setActionCommand(ConstantesInterfaz.ELIMINAR_PRODUCTO_AGREGADO);

		Image crear = new ImageIcon(".\\imagenes\\crearinventario.png").getImage();
		ImageIcon imageCrear= new ImageIcon(crear.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCrearInventario = new JButton("Crear inventario");
		btnCrearInventario.setIcon(imageCrear);
		btnCrearInventario.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrearInventario.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrearInventario.setBounds(50, 595, 150, 60);
		btnCrearInventario.addActionListener(this.controlador);
		btnCrearInventario.setActionCommand(ConstantesInterfaz.CERRAR_INVENTARIO);

		Image pdf = new ImageIcon(".\\imagenes\\pdf.png").getImage();
		ImageIcon imagePdf= new ImageIcon(pdf.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnExportarPdf= new JButton("Exportar a Pdf");
		btnExportarPdf.setIcon(imagePdf);
		btnExportarPdf.setHorizontalTextPosition( SwingConstants.CENTER );
		btnExportarPdf.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnExportarPdf.setBounds(360, 595, 150, 60);
		btnExportarPdf.addActionListener(this.controlador);
		btnExportarPdf.setActionCommand(ConstantesInterfaz.EXPORTAR_PDF);
		
		Image cerrar = new ImageIcon(".\\imagenes\\cerrarInv.png").getImage();
		ImageIcon imageCerrar= new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar= new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(675, 595, 150, 60);
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR_CREAR_INVENTARIO);
		
		this.add(lblTipo);
		this.add(boxTipoInventario);
		this.add(lblFecha);
		this.add(calendarFecha);
		this.add(btnAgregarProductos);
		this.add(btnCrearInventario);
		this.add(btnEliminarProductos);
		this.add(btnExportarPdf);
		this.add(btnCerrar);
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
