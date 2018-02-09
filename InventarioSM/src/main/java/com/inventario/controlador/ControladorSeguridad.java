package com.inventario.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.inventario.esquema.Usuario;
import com.inventario.interfaz.DialogLogin;
import com.inventario.interfaz.PrincipalFrame;
import com.inventario.negocio.SeguridadInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class ControladorSeguridad implements ActionListener {
	private SeguridadInventario si;
	private DialogLogin login;
	public static Usuario usuario;
	public ControladorSeguridad(DialogLogin login) {
		this.login=login;
		si= new SeguridadInventario();
	}
	public void actionPerformed(ActionEvent e) {
		try {
		if( e.getActionCommand().equals(ConstantesInterfaz.INGRESAR)){
			usuario=si.validarUsuario(login.getTxtUsuario().getText(),login.getTxtContrasenia().getText());
				if(usuario!=null){
					si.editarUsuario(usuario);
					PrincipalFrame principalFrame = new PrincipalFrame(usuario);
					principalFrame.setVisible(Boolean.TRUE);
				}			
		}
		else if( e.getActionCommand().equals(ConstantesInterfaz.CERRAR)){
			login.dispose();
			System.exit(0);
		}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),"Error autenticación",JOptionPane.ERROR_MESSAGE);
		}
	}

}
