package com.inventario.esquema;

import java.io.Serializable;

public class Campo implements Serializable {

    /** Atributo que define el seria de la clase **/
    private static final long serialVersionUID = 1L;
    // Características de cada campo de entrada
    private String nombreCampo;
    private String obligatorio;
    private int longitud;
    private String tipoDato;
    private String valorCampo;
    // Mensaje de error
    private String mensajeError;

    public Campo(String nombreCampo, String obligatorio, int longitud, String tipoDato, String valorCampo) {
	this.nombreCampo = nombreCampo;
	this.nombreCampo = nombreCampo;
	this.obligatorio = obligatorio;
	this.longitud = longitud;
	this.tipoDato = tipoDato;
	this.valorCampo = valorCampo;
    }

    public Campo(String valorCampo) {
	this.valorCampo = valorCampo;
    }

    public String getNombreCampo() {
	return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
	this.nombreCampo = nombreCampo;
    }

    public String getObligatorio() {
	return obligatorio;
    }

    public void setObligatorio(String obligatorio) {
	this.obligatorio = obligatorio;
    }

    public int getLongitud() {
	return longitud;
    }

    public void setLongitud(int longitud) {
	this.longitud = longitud;
    }

    public String getTipoDato() {
	return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
	this.tipoDato = tipoDato;
    }

    public String getValorCampo() {
	return valorCampo;
    }

    public void setValorCampo(String valorCampo) {
	this.valorCampo = valorCampo;
    }

    public String getMensajeError() {
	return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
	this.mensajeError = mensajeError;
    }

    public String toString() {
	this.valorCampo = this.valorCampo == null ? "" : this.valorCampo;
	return this.nombreCampo + "(" + this.valorCampo + ");";
    }

}