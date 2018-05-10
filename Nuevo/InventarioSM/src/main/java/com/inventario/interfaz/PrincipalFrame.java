package com.inventario.interfaz;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import com.inventario.controlador.ControladorInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class PrincipalFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menu;
	private JMenu opciones;
	private JMenuItem crearProducto;
	private JMenuItem crearParametrizacion;
	private JMenuItem consultarProductos;
	private JMenuItem crearInventario;
	private JMenuItem consultarInventario;
	private JButton btnCrearProducto;
	private JButton btnCrearParametrizacion;
	private JButton btnConsultarProductos;
	private JButton btnCrearInventario;
	private JButton btnCrearUsuario;
	private JButton btnEditarParametros;
	private JButton btnConsultarInventario;
	private JButton btnSalir;
	private ControladorInventario controlador;

	public PrincipalFrame() {
		setSize(720,270);
		setLayout(new GridLayout(4,2));
		setTitle("Principal");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(Boolean.FALSE);

		this.controlador = new ControladorInventario();
		int ancho = (int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize(). width);

		menu = new JMenuBar();
		menu.setBounds(0, 0, ancho, 20);
		opciones = new JMenu("Opciones");
		
		crearProducto = new JMenuItem("Crear Producto");
		crearProducto.addActionListener(controlador);
		crearProducto.setActionCommand(ConstantesInterfaz.INSERTAR_PRODUCTO);
		crearParametrizacion = new JMenuItem("Crear parametrizacion");
		crearParametrizacion.addActionListener(controlador);
		crearParametrizacion.setActionCommand(ConstantesInterfaz.CREAR_PARAMETRIZACION);
		consultarProductos = new JMenuItem("Consultar productos");
		consultarProductos.addActionListener(controlador);
		consultarProductos.setActionCommand(ConstantesInterfaz.CONSULTAR_PRODUCTO);
		
		
		
		crearInventario = new JMenuItem("Crear Inventario");
		crearInventario.addActionListener(controlador);
		crearInventario.setActionCommand(ConstantesInterfaz.CREAR_INVENTARIO);

		consultarInventario= new JMenuItem("Consultar Inventario");
		consultarInventario.addActionListener(controlador);
		consultarInventario.setActionCommand(ConstantesInterfaz.CONSULTAR_INVENTARIO);
		
		Image icrearProducto = new ImageIcon(".\\imagenes\\agregarProducto.jpg").getImage();
		ImageIcon imagecrearProducto = new ImageIcon(icrearProducto.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnCrearProducto= new JButton("Agregar Producto");
		btnCrearProducto.setBounds(30, 50,150,70);
		btnCrearProducto.setIcon(imagecrearProducto);
		btnCrearProducto.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrearProducto.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrearProducto.addActionListener(controlador);
		btnCrearProducto.setActionCommand(ConstantesInterfaz.INSERTAR_PRODUCTO);	
		Image icrearPara = new ImageIcon(".\\imagenes\\crearPara.jpg").getImage();
		ImageIcon imagecrearPara = new ImageIcon(icrearPara.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnCrearParametrizacion= new JButton("Crear Parametrización");
		btnCrearParametrizacion.setBounds(190, 50,180,70);
		btnCrearParametrizacion.setIcon(imagecrearPara);
		btnCrearParametrizacion.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrearParametrizacion.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrearParametrizacion.addActionListener(controlador);
		btnCrearParametrizacion.setActionCommand(ConstantesInterfaz.CREAR_PARAMETRIZACION);	
		Image iconsultarPro = new ImageIcon(".\\imagenes\\consultarPro.png").getImage();
		ImageIcon imageconsultarPro = new ImageIcon(iconsultarPro.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnConsultarProductos= new JButton("Consultar productos");
		btnConsultarProductos.setBounds(380, 50,150,70);
		btnConsultarProductos.setIcon(imageconsultarPro);
		btnConsultarProductos.setHorizontalTextPosition( SwingConstants.CENTER );
		btnConsultarProductos.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnConsultarProductos.addActionListener(controlador);
		btnConsultarProductos.setActionCommand(ConstantesInterfaz.CONSULTAR_PRODUCTO);
		
		Image icrearUsuario = new ImageIcon(".\\imagenes\\crearUsuarioPri.jpg").getImage();
		ImageIcon imagecrearUsuario = new ImageIcon(icrearUsuario.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnCrearUsuario= new JButton("Crear usuario");
		btnCrearUsuario.setBounds(380,140,150,70);
		btnCrearUsuario.setIcon(imagecrearUsuario);
		btnCrearUsuario.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrearUsuario.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrearUsuario.addActionListener(controlador);
		btnCrearUsuario.setActionCommand(ConstantesInterfaz.DIALOG_CREAR_USUARIO);
		
		Image ieditarParametros = new ImageIcon(".\\imagenes\\crearInvemtario.png").getImage();
		ImageIcon imageeditarParametros= new ImageIcon(ieditarParametros.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnEditarParametros= new JButton("Editar parámetros");
		btnEditarParametros.setBounds(540,50,150,70);
		btnEditarParametros.setIcon(imageeditarParametros);
		btnEditarParametros.setHorizontalTextPosition( SwingConstants.CENTER );
		btnEditarParametros.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnEditarParametros.addActionListener(controlador);
		btnEditarParametros.setActionCommand(ConstantesInterfaz.DIALOG_EDITAR_PARAMETROS);
		
		Image icrearIn = new ImageIcon(".\\imagenes\\crearInventarioPri.jpg").getImage();
		ImageIcon imagecrearIn = new ImageIcon(icrearIn.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnCrearInventario= new JButton("Crear inventario");
		btnCrearInventario.setBounds(30, 140,150,70);
		btnCrearInventario.setIcon(imagecrearIn);
		btnCrearInventario.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrearInventario.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrearInventario.addActionListener(controlador);
		btnCrearInventario.setActionCommand(ConstantesInterfaz.CREAR_INVENTARIO);
		Image iconsultarInv = new ImageIcon(".\\imagenes\\consultarInve.png").getImage();
		ImageIcon imageconsultarInv = new ImageIcon(iconsultarInv.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnConsultarInventario= new JButton("Consultar inventario");
		btnConsultarInventario.setBounds(190, 140,180,70);
		btnConsultarInventario.setIcon(imageconsultarInv);
		btnConsultarInventario.setHorizontalTextPosition( SwingConstants.CENTER );
		btnConsultarInventario.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnConsultarInventario.addActionListener(controlador);
		btnConsultarInventario.setActionCommand(ConstantesInterfaz.CONSULTAR_INVENTARIO);
		Image iSalir = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageSalir = new ImageIcon(iSalir.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnSalir= new JButton("Cerrar");
		btnSalir.setBounds(540, 140,150,70);
		btnSalir.setIcon(imageSalir);
		btnSalir.setHorizontalTextPosition( SwingConstants.CENTER );
		btnSalir.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnSalir.addActionListener(controlador);
		btnSalir.setActionCommand(ConstantesInterfaz.CERRAR_PRINCIPAL);
		
		menu.add(opciones);
		opciones.add(crearProducto);

		opciones.add(crearParametrizacion);
		opciones.add(consultarProductos);
		opciones.add(crearInventario);
		opciones.add(consultarInventario);
		this.add(menu);
		this.add(btnCrearProducto);
		this.add(btnCrearParametrizacion);
		this.add(btnEditarParametros);
		this.add(btnConsultarProductos);
		this.add(btnCrearUsuario);
		this.add(btnCrearInventario);
		this.add(btnConsultarInventario);
		this.add(btnSalir);
		paintComponents(getGraphics());

	}


}
