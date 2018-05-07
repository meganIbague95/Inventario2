package com.inventario.esquema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inventario.utilidades.Constantes;

public class Tamanio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idTamanio;
	private Campo nombre;
	private List<Campo> listaCampos;

	public Tamanio(Integer idTamanio, String nombre) {
		this.idTamanio = idTamanio;
		this.nombre = new Campo("nombre", "S", 30, Constantes.ALFANUMERICO, nombre);
	}

	public Integer getIdTamanio() {
		return idTamanio;
	}

	public void setIdTamanio(Integer idTamanio) {
		this.idTamanio = idTamanio;
	}

	public Campo getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = new Campo("nombre", "S", 30, Constantes.ALFANUMERICO, nombre);
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
