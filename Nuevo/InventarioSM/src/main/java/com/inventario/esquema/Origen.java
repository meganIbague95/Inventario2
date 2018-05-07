package com.inventario.esquema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inventario.utilidades.Constantes;

public class Origen implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idOrigen;
	private Campo nombre;
	private List<Campo> listaCampos;

	public Origen(Integer idOrigen, String nombre) {
		this.idOrigen = idOrigen;
		this.nombre = new Campo("nombre", "S", 30, Constantes.ALFANUMERICO, nombre);
	}

	public Integer getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(Integer idOrigen) {
		this.idOrigen = idOrigen;
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
