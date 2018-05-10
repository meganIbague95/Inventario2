package com.inventario.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import com.inventario.esquema.Perfil;
import com.inventario.esquema.Persona;
import com.inventario.esquema.Usuario;
import com.inventario.interfaz.DialogLogin;
import com.inventario.interfaz.PrincipalFrame;
import com.inventario.negocio.SeguridadInventario;
import com.inventario.utilidades.Constantes;
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
					usuario.setConectado(Constantes.ESTADO_CONECTADO);
					usuario.setUltimoIngreso(new Date());
					usuario.setNumeroIngreso(usuario.getNumeroIngreso()+1);
					usuario.setIntentosFallidos(0);
					si.editarUsuario(usuario);
					login.dispose();
					PrincipalFrame principalFrame = new PrincipalFrame();
					principalFrame.setVisible(Boolean.TRUE);
					principalFrame.setExtendedState(6);
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
public static void main(String[] args) {
	SeguridadInventario si = new SeguridadInventario();
	Persona persona = new Persona("CC",105751499,"Sharon", "Dayana", "Ibague", "Pinzón", "Desarrolladora","sharon.ibague@gmail.com");
	Perfil perfil = new Perfil(1,"Administracio", "Administra la aplicación");
	Usuario usuario = new Usuario("sibague1", "Asdf1234", "A", "1",perfil, persona,new Date(),1,2);
	ControladorSeguridad.usuario=usuario;
	try {
		si.crearPerfil(perfil);
		si.crearPersona(persona);
		si.crearUsuario(usuario);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
