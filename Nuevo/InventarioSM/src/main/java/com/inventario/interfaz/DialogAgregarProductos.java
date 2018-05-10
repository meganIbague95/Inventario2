package com.inventario.interfaz;

import java.awt.Image;
import java.math.BigDecimal;
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
import com.inventario.enums.GeneroEnum;
import com.inventario.enums.TipoTablaEnum;
import com.inventario.esquema.Categoria;
import com.inventario.esquema.Marca;
import com.inventario.esquema.Origen;
import com.inventario.esquema.Producto;
import com.inventario.esquema.Tamanio;
import com.inventario.esquema.TipoProducto;
import com.inventario.negocio.NegocioInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class DialogAgregarProductos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtTable;
	private DefaultTableModel dtm;
	private JLabel lblCategoria;
	private JLabel lblMarca;
	private JLabel lblOrigen;
	private JLabel lblTamanio;
	private JLabel lblTipo;
	private JLabel lblGenero;

	private JComboBox<Categoria> boxCategoria;
	private JComboBox<Marca> boxMarca;
	private JComboBox<Origen> boxOrigen;
	private JComboBox<Tamanio> boxTamanio;
	private JComboBox<TipoProducto> boxTipo;
	private JComboBox<GeneroEnum> boxGenero;
	private JButton btnFiltrar;
	private JButton btnCerrar;
	private JButton btnAgregar;
	private NegocioInventario negocioInventario;
	private ControladorInventario controladorInventario;

	public DialogAgregarProductos(ControladorInventario controladorInventario) {
		this.controladorInventario = controladorInventario;
		this.negocioInventario = this.controladorInventario.getNi();
		setSize(900, 700);
		setResizable(Boolean.FALSE);
		setLayout(null);
//		setAlwaysOnTop( true );
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
		scrollPane.setBounds(30, 200, 830, 450);
	}

	public void agregarBoton() {
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

	private void inicializar() {


		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(50, 30, 100, 25);

		boxCategoria = new JComboBox<Categoria>();
		boxCategoria.setBounds(120, 30, 100, 25);
		boxCategoria.addItem(null);
		for (Object objeto : negocioInventario.listarObjetos(TipoTablaEnum.CATEGORIA)) {
			Categoria categoria = (Categoria) objeto;
			boxCategoria.addItem(categoria);
		}

		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(330, 30, 100, 25);

		boxTipo = new JComboBox<TipoProducto>();
		boxTipo.setBounds(390, 30, 100, 25);
		boxTipo.addItem(null);
		for (Object objeto : negocioInventario.listarObjetos(TipoTablaEnum.TIPO)) {
			TipoProducto tipo = (TipoProducto) objeto;
			boxTipo.addItem(tipo);
		}
		lblTamanio = new JLabel("Tamaño");
		lblTamanio.setBounds(600, 30, 100, 25);

		boxTamanio = new JComboBox<Tamanio>();
		boxTamanio.setBounds(690, 30, 100, 25);
		boxTamanio.addItem(null);
		for (Object objeto : negocioInventario.listarObjetos(TipoTablaEnum.TAMANIO)) {
			Tamanio tamanio = (Tamanio) objeto;
			boxTamanio.addItem(tamanio);
		}
		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(50, 80, 100, 25);

		boxMarca = new JComboBox<Marca>();
		boxMarca.setBounds(120, 80, 100, 25);
		boxMarca.addItem(null);
		for (Object objeto : negocioInventario.listarObjetos(TipoTablaEnum.MARCA)) {
			Marca marca = (Marca) objeto;
			boxMarca.addItem(marca);
		}

		lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(330, 80, 100, 25);

		boxOrigen = new JComboBox<Origen>();
		boxOrigen.setBounds(390, 80, 100, 25);
		boxOrigen.addItem(null);
		for (Object objeto : negocioInventario.listarObjetos(TipoTablaEnum.ORIGEN)) {
			Origen origen = (Origen) objeto;
			boxOrigen.addItem(origen);
		}
		lblGenero = new JLabel("Género");
		lblGenero.setBounds(600, 80, 100, 25);

		boxGenero = new JComboBox<GeneroEnum>();
		boxGenero.setBounds(690, 80, 100, 25);
		boxGenero.addItem(null);
		boxGenero.addItem(GeneroEnum.HOMBRE);
		boxGenero.addItem(GeneroEnum.DAMA);
		boxGenero.addItem(GeneroEnum.NIÑO);
		boxGenero.addItem(GeneroEnum.NIÑA);

		Image filtrar = new ImageIcon(".\\imagenes\\filtrar.png").getImage();
		ImageIcon imageFiltrar= new ImageIcon(filtrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setIcon(imageFiltrar);
		btnFiltrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnFiltrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnFiltrar.setBounds(150, 120, 100, 60);
		btnFiltrar.addActionListener(this.controladorInventario);
		btnFiltrar.setActionCommand(ConstantesInterfaz.FILTRAR_AGREGAR);
		
		Image agregar = new ImageIcon(".\\imagenes\\agregarProducto.jpg").getImage();
		ImageIcon imageAgregar = new ImageIcon(agregar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(imageAgregar);
		btnAgregar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnAgregar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnAgregar.setBounds(400, 120, 100, 60);
		btnAgregar.addActionListener(this.controladorInventario);
		btnAgregar.setActionCommand(ConstantesInterfaz.AGREGAR_PRO);

		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(650, 120, 100, 60);
		btnCerrar.addActionListener(this.controladorInventario);
		btnCerrar.setActionCommand(ConstantesInterfaz.SALIR_AGREGAR_PRO);

		this.add(lblCategoria);
		this.add(boxCategoria);
		this.add(lblTipo);
		this.add(boxTipo);
		this.add(lblTamanio);
		this.add(boxTamanio);
		this.add(lblMarca);
		this.add(boxMarca);
		this.add(lblGenero);
		this.add(boxGenero);
		this.add(lblOrigen);
		this.add(boxOrigen);
		this.add(lblGenero);
		this.add(boxGenero);
		this.add(btnFiltrar);
		this.add(btnAgregar);
		this.add(btnCerrar);
	}

	public Producto extraerDatos() {
		int filaSeleccionada = jtTable.getSelectedRow();
		Producto producto = null;
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
		} else {
			Integer identificador = (Integer) jtTable.getValueAt(filaSeleccionada, 0);
			String cantidad =  jtTable.getValueAt(filaSeleccionada, 1).toString();
			String nombre =  jtTable.getValueAt(filaSeleccionada, 2).toString();
			String precioInterno =  jtTable.getValueAt(filaSeleccionada, 3).toString();
			String precioVenta =  jtTable.getValueAt(filaSeleccionada, 4).toString();
			Categoria categoria = (Categoria) jtTable.getValueAt(filaSeleccionada, 5);
			GeneroEnum genero = (GeneroEnum)jtTable.getValueAt(filaSeleccionada, 6);
			Marca marca = (Marca) jtTable.getValueAt(filaSeleccionada, 7);
			Tamanio tamanio = (Tamanio) jtTable.getValueAt(filaSeleccionada, 8);
			Origen origen = (Origen) jtTable.getValueAt(filaSeleccionada, 9);
			TipoProducto tipo = (TipoProducto) jtTable.getValueAt(filaSeleccionada, 10);
			producto = new Producto(nombre, categoria, genero, tipo, marca, precioInterno, tamanio, identificador,
					origen, cantidad, precioVenta);
		}
		return producto;
	}

	public JComboBox<Categoria> getBoxCategoria() {
		return boxCategoria;
	}

	public void setBoxCategoria(JComboBox<Categoria> boxCategoria) {
		this.boxCategoria = boxCategoria;
	}

	public JComboBox<Marca> getBoxMarca() {
		return boxMarca;
	}

	public void setBoxMarca(JComboBox<Marca> boxMarca) {
		this.boxMarca = boxMarca;
	}

	public JComboBox<Origen> getBoxOrigen() {
		return boxOrigen;
	}

	public void setBoxOrigen(JComboBox<Origen> boxOrigen) {
		this.boxOrigen = boxOrigen;
	}

	public JComboBox<Tamanio> getBoxTamanio() {
		return boxTamanio;
	}

	public void setBoxTamanio(JComboBox<Tamanio> boxTamanio) {
		this.boxTamanio = boxTamanio;
	}

	public JComboBox<TipoProducto> getBoxTipo() {
		return boxTipo;
	}

	public void setBoxTipo(JComboBox<TipoProducto> boxTipo) {
		this.boxTipo = boxTipo;
	}

	public JComboBox<GeneroEnum> getBoxGenero() {
		return boxGenero;
	}

	public void setBoxGenero(JComboBox<GeneroEnum> boxGenero) {
		this.boxGenero = boxGenero;
	}

	public JTable getJtTable() {
		return jtTable;
	}

	public void setJtTable(JTable jtTable) {
		this.jtTable = jtTable;
	}

}
