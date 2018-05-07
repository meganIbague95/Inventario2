package com.inventario.esquema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inventario.utilidades.Constantes;

public class Perfil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPerfil;
	private Campo nombre;
	private String descripcion;
	private List<Campo> listaCampos;
	public Perfil(String nombre, String descripcion) {
		this.nombre = new Campo("nombre", "S", 30, Constantes.ALFANUMERICO, nombre);
		this.descripcion = descripcion;
	}

	public Perfil(int idPerfil, String nombre, String descripcion) {
		this.idPerfil = idPerfil;
		this.nombre = new Campo("nombre", "S", 30, Constantes.ALFANUMERICO, nombre);
		this.descripcion = descripcion;
	}
	
	public Perfil(int idPerfil, String nombre) {
		this.idPerfil = idPerfil;
		this.nombre = new Campo("nombre", "S", 30, Constantes.ALFANUMERICO, nombre);
	}
	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	

	public String getNombre() {
		
		return nombre.getNombreCampo();
	}

	public void setNombre(String nombre) {
		this.nombre = new Campo("nombre", "S", 30, Constantes.ALFANUMERICO, nombre);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Campo> getListaCampos() {
		this.listaCampos = new ArrayList<Campo>();
		this.listaCampos.add(this.nombre);
		return listaCampos;
	}
	public String toString() {
		return  nombre.getValorCampo() ;
	}

}
