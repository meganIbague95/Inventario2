package com.inventario.esquema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inventario.utilidades.Constantes;

public class Categoria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCategoria;
	private Campo nombre;
	private List<Campo> listaCampos;

	public Categoria(Integer idCategoria, String nombre) {
		this.idCategoria = idCategoria;
		this.nombre = new Campo("nombre", "S", 30, Constantes.ALFANUMERICO, nombre);
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
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
