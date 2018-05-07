package com.inventario.interfaz;

import java.awt.Image;

import javax.sound.midi.ControllerEventListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.inventario.controlador.ControladorInventario;
import com.inventario.enums.TipoTablaEnum;
import com.inventario.esquema.Perfil;
import com.inventario.esquema.Tamanio;
import com.inventario.negocio.NegocioInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class DialogCrearUsuario extends JDialog{
	private JLabel lblUsuario;
	private JLabel lblContrasenia;
	private JLabel lblPerfil;
	private JLabel lblTipoIdentificacion;
	private JLabel lblIdentificacion;
	private JLabel lblPrimerNombre;
	private JLabel lblSegundoNombre;
	private JLabel lblPrimerApellido;
	private JLabel lblSegundoApellido;
	private JLabel lblCargo;
	private JLabel lblCorreo;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	private JTextField txtPrimerNombre;
	private JTextField txtSegundoNombre;
	private JTextField txtPrimerApellido;
	private JTextField txtSegundoApellido;
	private JTextField txtCargo;
	private JTextField txtCorreo;
	private JComboBox<Perfil> boxPerfil;
	private JComboBox<String> boxTipoIdentificacion;
	private JTextField txtIdentificacion;
	private JButton btnCrear;
	private JButton btnCancelar;
	private ControladorInventario controladorInventario;
	private NegocioInventario negocioInventario;	
	public DialogCrearUsuario(ControladorInventario controladorInventario) {
		this.controladorInventario = controladorInventario;
		this.negocioInventario = this.controladorInventario.getNi();
		setSize(650, 350);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Crear usuario");
		setLocationRelativeTo(null);
		inicializar();
	}

	public void inicializar(){
		lblUsuario= new JLabel("Usuario");
		lblUsuario.setBounds(30,30,100,20);
		
		txtUsuario= new JTextField();
		txtUsuario.setBounds(100, 30, 100, 20);
		
		lblContrasenia= new JLabel("Contraseña");
		lblContrasenia.setBounds(220,30,100,20);
		
		txtContrasenia= new JPasswordField();
		txtContrasenia.setBounds(300, 30, 100, 20);
		
		lblPerfil= new JLabel("Perfil");
		lblPerfil.setBounds(420,30,100,20);
		
		boxPerfil = new JComboBox<Perfil>();
		boxPerfil.setBounds(480, 30, 125, 25);
		for (Object objeto : negocioInventario.consultarPerfil()) {
			Perfil perfil=(Perfil)objeto;
			boxPerfil.addItem(perfil);
		}
		lblTipoIdentificacion= new JLabel("Tipo de identificación");
		lblTipoIdentificacion.setBounds(30,150,125,20);
		
		boxTipoIdentificacion= new JComboBox<String>();
		boxTipoIdentificacion.addItem("CC");
		boxTipoIdentificacion.addItem("TI");
		boxTipoIdentificacion.addItem("CE");
		boxTipoIdentificacion.addItem("NIT");
		boxTipoIdentificacion.setBounds(170, 150, 100, 20);

		lblIdentificacion= new JLabel("Identificación");
		lblIdentificacion.setBounds(300,150,125,20);
		
		txtIdentificacion= new JTextField();
		txtIdentificacion.setBounds(420, 150, 125, 20);
		
		lblPrimerNombre= new JLabel("Primer nombre");
		lblPrimerNombre.setBounds(30,70,125,20);
		
		txtPrimerNombre= new JTextField();
		txtPrimerNombre.setBounds(140, 70, 125, 20);
		
		lblSegundoNombre= new JLabel("Segundo nombre");
		lblSegundoNombre.setBounds(300,70,125,20);
		
		txtSegundoNombre= new JTextField();
		txtSegundoNombre.setBounds(420, 70, 125, 20);
		
		lblPrimerApellido= new JLabel("Primer Apellido");
		lblPrimerApellido.setBounds(30,110,125,20);
		
		txtPrimerApellido= new JTextField();
		txtPrimerApellido.setBounds(140, 110, 125, 20);
		
		lblSegundoApellido= new JLabel("Segundo Apellido");
		lblSegundoApellido.setBounds(300,110,125,20);
		
		txtSegundoApellido= new JTextField();
		txtSegundoApellido.setBounds(420, 110, 125, 20);
		
		lblCargo= new JLabel("Cargo");
		lblCargo.setBounds(30,190,125,20);
		
		txtCargo= new JTextField();
		txtCargo.setBounds(70, 200, 125, 20);
		
		lblCorreo= new JLabel("Correo");
		lblCorreo.setBounds(300,190,125,20);
		
		txtCorreo= new JTextField();
		txtCorreo.setBounds(420, 190, 125, 20);
		
		Image crear = new ImageIcon(".\\imagenes\\botonIngresar.jpg").getImage();
		ImageIcon imageCrear = new ImageIcon(crear.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCrear = new JButton("Crear");
		btnCrear.setBounds(180, 230, 80, 60);
		btnCrear.setIcon(imageCrear);
		btnCrear.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrear.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrear.addActionListener(this.controladorInventario);
		btnCrear.setActionCommand(ConstantesInterfaz.CREAR_USUARIO);

		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCancelar = new JButton("Cerrar");
		btnCancelar.setIcon(imageCerrar);
		btnCancelar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCancelar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCancelar.setBounds(360, 230, 80, 60);
		btnCancelar.addActionListener(this.controladorInventario);
		btnCancelar.setActionCommand(ConstantesInterfaz.CANCELAR_USUARIO);
		
		this.add(lblUsuario);
		this.add(txtUsuario);
		this.add(lblContrasenia);
		this.add(txtContrasenia);
		this.add(lblPerfil);
		this.add(boxPerfil);
		this.add(lblTipoIdentificacion);
		this.add(boxTipoIdentificacion);
		this.add(lblIdentificacion);
		this.add(txtIdentificacion);
		this.add(lblPrimerNombre);
		this.add(txtPrimerNombre);
		this.add(lblSegundoNombre);
		this.add(txtSegundoNombre);
		this.add(lblPrimerApellido);
		this.add(txtPrimerApellido);
		this.add(lblSegundoApellido);
		this.add(txtSegundoApellido);
		this.add(lblCargo);
		this.add(txtCargo);
		this.add(lblCorreo);
		this.add(txtCorreo);
		this.add(btnCrear);
		this.add(btnCancelar);
	}
	
public JPasswordField getTxtContrasenia() {
		return txtContrasenia;
	}

	public void setTxtContrasenia(JPasswordField txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}

	public JComboBox<Perfil> getBoxPerfil() {
		return boxPerfil;
	}

	public void setBoxPerfil(JComboBox<Perfil> boxPerfil) {
		this.boxPerfil = boxPerfil;
	}


	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JComboBox<String> getBoxTipoIdentificacion() {
		return boxTipoIdentificacion;
	}

	public void setBoxTipoIdentificacion(JComboBox<String> boxTipoIdentificacion) {
		this.boxTipoIdentificacion = boxTipoIdentificacion;
	}

	public JTextField getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(JTextField txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}
	

public JTextField getTxtPrimerNombre() {
		return txtPrimerNombre;
	}

	public void setTxtPrimerNombre(JTextField txtPrimerNombre) {
		this.txtPrimerNombre = txtPrimerNombre;
	}

	public JTextField getTxtSegundoNombre() {
		return txtSegundoNombre;
	}

	public void setTxtSegundoNombre(JTextField txtSegundoNombre) {
		this.txtSegundoNombre = txtSegundoNombre;
	}

	public JTextField getTxtPrimerApellido() {
		return txtPrimerApellido;
	}

	public void setTxtPrimerApellido(JTextField txtPrimerApellido) {
		this.txtPrimerApellido = txtPrimerApellido;
	}

	public JTextField getTxtSegundoApellido() {
		return txtSegundoApellido;
	}

	public void setTxtSegundoApellido(JTextField txtSegundoApellido) {
		this.txtSegundoApellido = txtSegundoApellido;
	}

	public JTextField getTxtCargo() {
		return txtCargo;
	}

	public void setTxtCargo(JTextField txtCargo) {
		this.txtCargo = txtCargo;
	}

	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	public void setTxtCorreo(JTextField txtCorreo) {
		this.txtCorreo = txtCorreo;
	}

public static void main(String[] args) {
	ControladorInventario inventario= new ControladorInventario();
	DialogCrearUsuario crearUsuario= new DialogCrearUsuario(inventario);
	crearUsuario.setVisible(true);
}
}
