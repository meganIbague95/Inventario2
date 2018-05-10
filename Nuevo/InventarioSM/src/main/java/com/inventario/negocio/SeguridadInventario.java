package com.inventario.negocio;

import com.inventario.esquema.Perfil;
import com.inventario.esquema.Persona;
import com.inventario.esquema.Usuario;
import com.inventario.logs.ManejoLogs;
import com.inventario.transaccionesbd.TransaccionesDAO;
import com.inventario.utilidades.Constantes;
import com.inventario.utilidades.Utilidades;

public class SeguridadInventario {
	private TransaccionesDAO dao;
	private ManejoLogs log = new ManejoLogs(SeguridadInventario.class);

	public SeguridadInventario() {
		try {
			dao = new TransaccionesDAO();
		} catch (Exception e) {
			log.escribirError("Error al instanciar dao", e);
		}
	}

	public Usuario validarUsuario(String usuario, String contrasenia) throws Exception {
		String contraseniaEncriptada = Utilidades.encriptarContrasenia(contrasenia);
		Usuario usuarioLogueo = null;
		System.out.println(usuario);
		usuarioLogueo = dao.consultarUsuarioSeguridad(usuario);
		if (usuarioLogueo == null) {
			throw new Exception("El usuario no existe en el sistema o se encuentra inactivo");
		}
		if (!contraseniaEncriptada.equals(usuarioLogueo.getContraseña())) {
			throw new Exception("Contraseña inválida");
		}
		if (usuarioLogueo.getEstado()!=null && usuarioLogueo.getEstado().equals(Constantes.ESTADO_CONECTADO)) {
			throw new Exception("El usuario se encuentra conectado en este momento");
		}

		return usuarioLogueo;
	}

	public void crearUsuario(Usuario usuario) throws Exception {
		String contraseniaEncriptada = Utilidades.encriptarContrasenia(usuario.getContraseña());
		usuario.setContraseña(contraseniaEncriptada);
		dao.insertarUsuario(usuario);
	}
	public void crearPerfil(Perfil perfil) throws Exception {
		dao.insertarPerfil(perfil);
	}
	public void crearPersona(Persona persona) throws Exception {
		dao.insertarPersona(persona);
	}
	public void editarUsuario(Usuario usuario) throws Exception {
		dao.editarUsuario(usuario);
	}

	public Boolean compararContrasenia(String contrasenia, String contraseniaVerificada) {
		return contrasenia.equals(contraseniaVerificada);
	}
}
