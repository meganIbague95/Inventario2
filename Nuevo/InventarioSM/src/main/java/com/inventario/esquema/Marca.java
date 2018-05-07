package com.inventario.esquema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inventario.utilidades.Constantes;

public class Marca implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idMarca;
	private Campo nombre;
	private List<Campo> listaCampos;

	public Marca(int idMarca, String nombreMarca) {
		this.idMarca = idMarca;
		this.nombre = new Campo("nombre", "S", 30, Constantes.ALFANUMERICO, nombreMarca);
	}

	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
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
