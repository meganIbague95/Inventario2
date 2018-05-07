package com.inventario.esquema;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String contrasenia;
	private Date ultimoIngreso;
	private String estado;
	private Integer numeroIngreso;
	private Integer intentosFallidos;
	private Perfil perfil;
	private Persona persona;
	private String conectado;
	private Integer tipoIdentificacion;
	private Integer identificacion;

	public Usuario(String nombre, String contrasenia, Perfil perfil) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.perfil = perfil;
	}

	public Usuario(String nombre, String contrasenia, String estado, String conectado, Perfil perfil, Persona persona,Date ultimoIngreso, Integer numeroIngreso, Integer intentosFallidos) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.conectado = conectado;
		this.estado = estado;
		this.perfil = perfil;
		this.persona = persona;
		this.ultimoIngreso = ultimoIngreso;
		this.numeroIngreso = numeroIngreso;
		this.intentosFallidos = intentosFallidos;
	}

	public Usuario(Date ultimoIngreso, Integer numeroIngreso, Integer intentosFallidos) {
		super();
		this.ultimoIngreso = ultimoIngreso;
		this.numeroIngreso = numeroIngreso;
		this.intentosFallidos = intentosFallidos;
	}


	public Usuario(String nombre, String estado, int intentosFallidos, Perfil perfil, Persona persona) {
		this.nombre = nombre;
		this.estado = estado;
		this.intentosFallidos = intentosFallidos;
		this.perfil = perfil;
		this.persona = persona;
	}
	public Usuario(String nombre, String contrasenia, Perfil perfil, Persona persona) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.perfil = perfil;
		this.persona=persona;
	}

	public Usuario(String contrasenia,String estado) {
		this.estado = estado;
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contrasenia;
	}

	public void setContraseña(String contraseña) {
		this.contrasenia = contraseña;
	}

	public Date getUltimoIngreso() {
		return ultimoIngreso;
	}

	public void setUltimoIngreso(Date ultimoIngreso) {
		this.ultimoIngreso = ultimoIngreso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNumeroIngreso() {
		return numeroIngreso;
	}

	public void setNumeroIngreso(Integer numeroIngreso) {
		this.numeroIngreso = numeroIngreso;
	}

	public Integer getIntentosFallidos() {
		return intentosFallidos;
	}

	public void setIntentosFallidos(Integer intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getConectado() {
		return conectado;
	}

	public void setConectado(String conectado) {
		this.conectado = conectado;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", contrasenia=" + contrasenia + ", ultimoIngreso=" + ultimoIngreso
				+ ", estado=" + estado + ", numeroIngreso=" + numeroIngreso + ", intentosFallidos=" + intentosFallidos
				+ ", perfil=" + perfil + ", persona=" + persona + "]";
	}

}
