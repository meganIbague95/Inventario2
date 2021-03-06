package com.inventario.interfaz;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

public class DialogEditarProducto extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNombre;
	private JLabel lblCategoria;
	private JLabel lblTipo;
	private JLabel lblPrecio;
	private JLabel lblTamanio;
	private JLabel lblMarca;
	private JLabel lblGenero;
	private JLabel lblOrigen;
	private JLabel lblCantidad;
	private JLabel lblPrecioVenta;
	private JTextField txtNombre;
	private JComboBox<Categoria> boxCategoria;
	private JComboBox<TipoProducto> boxTipo;
	private JTextField txtPrecio;
	private JComboBox<Tamanio> boxTamanio;
	private JComboBox<Marca> boxMarca;
	private JComboBox<GeneroEnum> boxGenero;
	private JComboBox<Origen> boxOrigen;
	private JTextField txtCantidad;
	private JTextField txtPrecioVenta;
	private JButton btnCrear;
	private JButton btnCerrar;
	private ControladorInventario controlador;
	private Producto producto;

	private NegocioInventario negocio = new NegocioInventario();

	public DialogEditarProducto(ControladorInventario controladorInventario, Producto producto) {
		setSize(500, 450);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Editar Producto");
		setLocationRelativeTo(null);
		this.producto = producto;
		this.controlador = controladorInventario;
		this.negocio = this.controlador.getNi();
		inicializarComponentes();
	}

	public void inicializarComponentes() {
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 50, 100, 25);

		txtNombre = new JTextField(producto.getNombreProducto().getValorCampo());
		txtNombre.setBounds(140, 50, 150, 25);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(300, 50, 100, 25);

		txtCantidad = new JTextField(producto.getCantidad().getValorCampo());
		txtCantidad.setBounds(380, 50, 50, 25);

		lblPrecio = new JLabel("Precio interno");
		lblPrecio.setBounds(50, 100, 100, 25);

		txtPrecio = new JTextField(producto.getPrecioCompra().getValorCampo());
		txtPrecio.setBounds(140, 100, 100, 25);

		lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setBounds(250, 100, 100, 25);
		txtPrecioVenta = new JTextField(producto.getPrecioVenta().getValorCampo());
		txtPrecioVenta.setBounds(340, 100, 100, 25);

		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(50, 150, 100, 25);

		boxCategoria = new JComboBox<Categoria>();
		boxCategoria.setBounds(120, 150, 120, 25);
		boxCategoria.addItem(null);
		for (Object objeto : negocio.listarObjetos(TipoTablaEnum.CATEGORIA)) {
			Categoria categoria = (Categoria) objeto;
			boxCategoria.addItem(categoria);
		}

		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(250, 150, 100, 25);

		boxTipo = new JComboBox<TipoProducto>();
		boxTipo.setBounds(320, 150, 120, 25);
		boxTipo.addItem(null);
		for (Object objeto : negocio.listarObjetos(TipoTablaEnum.TIPO)) {
			TipoProducto tipo = (TipoProducto) objeto;
			boxTipo.addItem(tipo);
		}
		lblTamanio = new JLabel("Tama�o");
		lblTamanio.setBounds(50, 200, 100, 25);

		boxTamanio = new JComboBox<Tamanio>();
		boxTamanio.setBounds(120, 200, 120, 25);
		boxTamanio.addItem(null);
		for (Object objeto : negocio.listarObjetos(TipoTablaEnum.TAMANIO)) {
			Tamanio tamanio = (Tamanio) objeto;
			boxTamanio.addItem(tamanio);
		}
		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(250, 200, 100, 25);

		boxMarca = new JComboBox<Marca>();
		boxMarca.setBounds(320, 200, 120, 25);
		boxMarca.addItem(null);
		for (Object objeto : negocio.listarObjetos(TipoTablaEnum.MARCA)) {
			Marca marca = (Marca) objeto;
			boxMarca.addItem(marca);
		}

		lblGenero = new JLabel("G�nero");
		lblGenero.setBounds(50, 250, 100, 25);

		boxGenero = new JComboBox<GeneroEnum>();
		boxGenero.setBounds(120, 250, 120, 25);
		boxGenero.addItem(null);
		boxGenero.addItem(GeneroEnum.DAMA);
		boxGenero.addItem(GeneroEnum.HOMBRE);
		boxGenero.addItem(GeneroEnum.NI�A);
		boxGenero.addItem(GeneroEnum.NI�O);

		lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(250, 250, 100, 25);

		boxOrigen = new JComboBox<Origen>();
		boxOrigen.setBounds(320, 250, 120, 25);
		boxOrigen.addItem(null);
		for (Object objeto : negocio.listarObjetos(TipoTablaEnum.ORIGEN)) {
			Origen origen = (Origen) objeto;
			boxOrigen.addItem(origen);
		}
		
		Image crear = new ImageIcon(".\\imagenes\\botonIngresar.jpg").getImage();
		ImageIcon imageCrear = new ImageIcon(crear.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCrear = new JButton("Editar");
		btnCrear.setBounds(100, 300, 100, 60);
		btnCrear.setIcon(imageCrear);
		btnCrear.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrear.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrear.addActionListener(this.controlador);
		btnCrear.setActionCommand(ConstantesInterfaz.EDITAR_PRODUCTO);

		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(300, 300, 100, 60);
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR_EDITAR_PRODUCTO);
		this.add(lblNombre);
		this.add(txtNombre);
		this.add(lblCategoria);
		this.add(boxCategoria);
		this.add(lblPrecio);
		this.add(txtPrecio);
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
		this.add(lblCantidad);
		this.add(txtCantidad);
		this.add(lblPrecioVenta);
		this.add(txtPrecioVenta);
		this.add(btnCrear);
		this.add(btnCerrar);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JComboBox<Categoria> getBoxCategoria() {
		return boxCategoria;
	}

	public void setBoxCategoria(JComboBox<Categoria> boxCategoria) {
		this.boxCategoria = boxCategoria;
	}

	public JComboBox<TipoProducto> getBoxTipo() {
		return boxTipo;
	}

	public void setBoxTipo(JComboBox<TipoProducto> boxTipo) {
		this.boxTipo = boxTipo;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public void setTxtPrecio(JTextField txtPrecio) {
		this.txtPrecio = txtPrecio;
	}

	public JComboBox<Tamanio> getBoxTamanio() {
		return boxTamanio;
	}

	public void setBoxTamanio(JComboBox<Tamanio> boxTamanio) {
		this.boxTamanio = boxTamanio;
	}

	public JComboBox<Marca> getBoxMarca() {
		return boxMarca;
	}

	public void setBoxMarca(JComboBox<Marca> boxMarca) {
		this.boxMarca = boxMarca;
	}

	public JComboBox<GeneroEnum> getBoxGenero() {
		return boxGenero;
	}

	public void setBoxGenero(JComboBox<GeneroEnum> boxGenero) {
		this.boxGenero = boxGenero;
	}

	public JComboBox<Origen> getBoxOrigen() {
		return boxOrigen;
	}

	public void setBoxOrigen(JComboBox<Origen> boxOrigen) {
		this.boxOrigen = boxOrigen;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JTextField getTxtPrecioVenta() {
		return txtPrecioVenta;
	}

	public void setTxtPrecioVenta(JTextField txtPrecioVenta) {
		this.txtPrecioVenta = txtPrecioVenta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
