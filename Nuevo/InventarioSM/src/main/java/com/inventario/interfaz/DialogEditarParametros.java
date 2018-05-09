package com.inventario.interfaz;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.inventario.controlador.ControladorInventario;
import com.inventario.esquema.Categoria;
import com.inventario.esquema.Marca;
import com.inventario.esquema.Origen;
import com.inventario.esquema.Tamanio;
import com.inventario.esquema.TipoProducto;
import com.inventario.utilidades.ConstantesInterfaz;

public class DialogEditarParametros extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JButton btnCrear;
	private JButton btnCerrar;
	private ControladorInventario controlador;
	private Categoria categoria;
	private Marca marca;
	private TipoProducto tipo;
	private Origen origen;
	private Tamanio tamanio;

	public DialogEditarParametros(ControladorInventario controladorInventario, Categoria categoria) {
		setSize(270, 230);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Editar Categoria");
		setLocationRelativeTo(null);
		this.controlador = controladorInventario;
		this.categoria=categoria;

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 80, 100, 25);
		
		txtNombre = new JTextField(categoria.getNombre().getValorCampo());
		txtNombre.setBounds(130, 80, 100, 25);
		

		Image crear = new ImageIcon(".\\imagenes\\botonIngresar.jpg").getImage();
		ImageIcon imageCrear = new ImageIcon(crear.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCrear = new JButton("Editar");
		btnCrear.setBounds(30, 120, 80, 60);
		btnCrear.setIcon(imageCrear);
		btnCrear.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrear.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrear.addActionListener(this.controlador);
		btnCrear.setActionCommand(ConstantesInterfaz.EDITAR_CATEGORIA);

		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(140, 120, 80, 60);
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR);

		this.add(lblNombre);
		this.add(txtNombre);
		this.add(btnCrear);
		this.add(btnCerrar);
	}

	public DialogEditarParametros(ControladorInventario controladorInventario, Marca marca) {
		setSize(270, 200);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Editar marca");
		setLocationRelativeTo(null);
		this.controlador = controladorInventario;
		this.marca=marca;

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 30, 100, 25);
		
		txtNombre = new JTextField(marca.getNombre().getValorCampo());
		txtNombre.setBounds(130, 30, 100, 25);
		

		Image crear = new ImageIcon(".\\imagenes\\botonIngresar.jpg").getImage();
		ImageIcon imageCrear = new ImageIcon(crear.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCrear = new JButton("Editar");
		btnCrear.setBounds(30, 90, 80, 60);
		btnCrear.setIcon(imageCrear);
		btnCrear.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrear.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrear.addActionListener(this.controlador);
		btnCrear.setActionCommand(ConstantesInterfaz.EDITAR_MARCA);

		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(150, 90, 80, 60);
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR);

		this.add(lblNombre);
		this.add(txtNombre);
		this.add(btnCrear);
		this.add(btnCerrar);
	}
	
	public DialogEditarParametros(ControladorInventario controladorInventario, TipoProducto tipo) {
		setSize(270, 200);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Editar Tipo de producto");
		setLocationRelativeTo(null);
		this.controlador = controladorInventario;
		this.tipo=tipo;

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 30, 100, 25);
		
		txtNombre = new JTextField(tipo.getNombre().getValorCampo());
		txtNombre.setBounds(130, 30, 100, 25);
		

		Image crear = new ImageIcon(".\\imagenes\\botonIngresar.jpg").getImage();
		ImageIcon imageCrear = new ImageIcon(crear.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCrear = new JButton("Editar");
		btnCrear.setBounds(30, 90, 80, 60);
		btnCrear.setIcon(imageCrear);
		btnCrear.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrear.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrear.addActionListener(this.controlador);
		btnCrear.setActionCommand(ConstantesInterfaz.EDITAR_TIPO);

		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(150, 90, 80, 60);
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR);

		this.add(lblNombre);
		this.add(txtNombre);
		this.add(btnCrear);
		this.add(btnCerrar);
	}
	public DialogEditarParametros(ControladorInventario controladorInventario, Origen origen) {
		setSize(270, 200);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Editar Origen");
		setLocationRelativeTo(null);
		this.controlador = controladorInventario;
		this.origen=origen;


		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 30, 100, 25);
		
		txtNombre = new JTextField(origen.getNombre().getValorCampo());
		txtNombre.setBounds(130, 30, 100, 25);
		

		Image crear = new ImageIcon(".\\imagenes\\botonIngresar.jpg").getImage();
		ImageIcon imageCrear = new ImageIcon(crear.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCrear = new JButton("Editar");
		btnCrear.setBounds(30, 90, 80, 60);
		btnCrear.setIcon(imageCrear);
		btnCrear.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrear.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrear.addActionListener(this.controlador);
		btnCrear.setActionCommand(ConstantesInterfaz.EDITAR_ORIGEN);

		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(150, 90, 80, 60);
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR);

		this.add(lblNombre);
		this.add(txtNombre);
		this.add(btnCrear);
		this.add(btnCerrar);
	}
	public DialogEditarParametros(ControladorInventario controladorInventario, Tamanio tamanio) {
		setSize(270, 200);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Editar Tamaño");
		setLocationRelativeTo(null);
		this.controlador = controladorInventario;
		this.tamanio=tamanio;

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 30, 100, 25);
		
		txtNombre = new JTextField(tamanio.getNombre().getValorCampo());
		txtNombre.setBounds(130, 30, 100, 25);
		

		Image crear = new ImageIcon(".\\imagenes\\botonIngresar.jpg").getImage();
		ImageIcon imageCrear = new ImageIcon(crear.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCrear = new JButton("Editar");
		btnCrear.setBounds(30, 90, 80, 60);
		btnCrear.setIcon(imageCrear);
		btnCrear.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrear.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrear.addActionListener(this.controlador);
		btnCrear.setActionCommand(ConstantesInterfaz.EDITAR_TAMANIO);

		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(150, 90, 80, 60);
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR);

		this.add(lblNombre);
		this.add(txtNombre);
		this.add(btnCrear);
		this.add(btnCerrar);
	}
	
	


	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(JButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public void setBtnCerrar(JButton btnCerrar) {
		this.btnCerrar = btnCerrar;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public TipoProducto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}

	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	public Tamanio getTamanio() {
		return tamanio;
	}

	public void setTamanio(Tamanio tamanio) {
		this.tamanio = tamanio;
	}

}
