package com.inventario.interfaz;

import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.inventario.controlador.ControladorInventario;
import com.inventario.enums.TipoTablaEnum;
import com.inventario.utilidades.ConstantesInterfaz;

public class DialogCrearObjeto extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblObjeto;
	private JComboBox<String> boxTipoTabla;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JButton btnCrear;
	private JButton btnCerrar;
	private ControladorInventario controlador;

	public DialogCrearObjeto(ControladorInventario controladorInventario) {
		setSize(270, 230);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Crear parametrización");
		setLocationRelativeTo(null);
//		this.setAlwaysOnTop(true);
//		this.setFocusable(false);
		this.controlador = controladorInventario;
		inicializarComponentes();
	}

	public void inicializarComponentes() {
		lblObjeto = new JLabel("Tipo de objeto:");
		lblObjeto.setBounds(30, 30, 100, 25);

		boxTipoTabla = new JComboBox<>();
		boxTipoTabla.setBounds(130, 30, 100, 25);

		for (TipoTablaEnum tipoTabla : TipoTablaEnum.values()) {
			boxTipoTabla.addItem(tipoTabla.getNombreTabla());
		}

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 80, 100, 25);

		txtNombre = new JTextField();
		txtNombre.setBounds(80, 80, 150, 25);

		Image crear = new ImageIcon(".\\imagenes\\botonIngresar.jpg").getImage();
		ImageIcon imageCrear = new ImageIcon(crear.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCrear = new JButton("Crear");
		btnCrear.setBounds(30, 120, 80, 60);
		btnCrear.setIcon(imageCrear);
		btnCrear.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCrear.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCrear.addActionListener(this.controlador);
		btnCrear.setActionCommand(ConstantesInterfaz.CREAR_OBJETO);

		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(140, 120, 80, 60);
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR_PARAMETRIZACION);

		this.add(lblObjeto);
		this.add(boxTipoTabla);
		this.add(lblNombre);
		this.add(txtNombre);
		this.add(btnCrear);
		this.add(btnCerrar);

	}

	

	public JComboBox<String> getBoxTipoTabla() {
		return boxTipoTabla;
	}

	public void setBoxTipoTabla(JComboBox<String> boxTipoTabla) {
		this.boxTipoTabla = boxTipoTabla;
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

}
