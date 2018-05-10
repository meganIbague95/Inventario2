package com.inventario.interfaz;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Container;
import java.awt.Image;
import java.net.URL;

import com.inventario.controlador.ControladorSeguridad;
import com.inventario.utilidades.ConstantesInterfaz;
import com.itextpdf.text.Font;
import com.sun.javafx.tk.Toolkit;

public class DialogLogin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblUsuario;
	private JLabel lblConstrasenia;
	private JLabel lblImagen;
	private JLabel lblUsuarioFoto;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	private JButton btnIngresar;
	private JButton btnCerrar;
	private ControladorSeguridad controlador;
	private  ImageIcon icono;
	private  ImageIcon ingresar;
	private Container contentPane;


	public DialogLogin() {
		this.controlador = new ControladorSeguridad(this);
//		setSize(this.getMaximumSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(Boolean.FALSE);
		contentPane= getContentPane();
		contentPane.setLayout(null);
		setTitle("Iniciar Sesion");
		setLocationRelativeTo(null);
		inicializarComponentes();

	}

	public void inicializarComponentes() {
		float escalar = 0.5F;  
		int ancho = (int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize(). width);
		icono = new ImageIcon(getClass().getResource("/imagenes/encabezado.jpg"));
		Image imagen = icono.getImage();
		ImageIcon iconoEscalado = new ImageIcon (imagen.getScaledInstance(ancho,300,Image.SCALE_SMOOTH));
		lblImagen = new JLabel();
		lblImagen.setIcon(iconoEscalado);
		lblImagen.setBounds(0, 0, ancho, 300);

		Image usuario = new ImageIcon("imagenes/IconoUsuarios.png").getImage();
		ImageIcon iconoEscalado2 = new ImageIcon (usuario.getScaledInstance(180,180,Image.SCALE_SMOOTH));
		lblUsuarioFoto = new JLabel();
		lblUsuarioFoto.setIcon(iconoEscalado2);
		lblUsuarioFoto.setBounds(300, 400, 200, 200);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(600, 400, 100, 30);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(700, 400, 150, 25);

		lblConstrasenia = new JLabel("Contraseña");
		lblConstrasenia.setBounds(600, 450, 100, 25);

		txtContrasenia = new JPasswordField();
		txtContrasenia.setBounds(700, 450, 150, 25);

		Image ingresar = new ImageIcon(".\\imagenes\\botonIngresar.jpg").getImage();
		ImageIcon imageIngresar = new ImageIcon(ingresar.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnIngresar = new JButton();
		btnIngresar.setText("Ingresar");
		btnIngresar.setIcon(imageIngresar);
		btnIngresar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnIngresar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnIngresar.setBounds(600, 500, 100, 80);
		btnIngresar.addActionListener(this.controlador);
		btnIngresar.setActionCommand(ConstantesInterfaz.INGRESAR);
		
		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(40,40,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(750, 500, 100, 80);
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR);
		contentPane.add(lblImagen);
		contentPane.add(lblUsuario);
		contentPane.add(lblUsuarioFoto);
		contentPane.add(txtUsuario);
		contentPane.add(lblConstrasenia);
		contentPane.add(txtContrasenia);
		contentPane.add(btnIngresar);
		contentPane.add(btnCerrar);
		paintComponents(getGraphics());

	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JTextField getTxtContrasenia() {
		return txtContrasenia;
	}

	public void setTxtContrasenia(JPasswordField txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}

	public JButton getBtnIngresar() {
		return btnIngresar;
	}

	public void setBtnIngresar(JButton btnIngresar) {
		this.btnIngresar = btnIngresar;
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public void setBtnCerrar(JButton btnCerrar) {
		this.btnCerrar = btnCerrar;
	}

	public static void main(String[] args) {
		DialogLogin login = new DialogLogin();

		login.setVisible(Boolean.TRUE);
		login.setExtendedState(6);
	}
}
