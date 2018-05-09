package com.inventario.utilidades;

import java.text.SimpleDateFormat;

public class Constantes {
	public final static String IP_BD="ip";
	public final static String INSTANCIA="instancia";
	public final static String USUARIO="usuario";
	public final static String CONSTRASENIA="contrasenia";
	public final static String PUERTO="puerto";
	public final static String URL="jdbc:mysql://";
	public final static String ARCHIVO_PROPIEDADES_DB="propiedades/PropiedadesBD.properties";
	public final static String ARCHIVO_PROPIEDADES_CONFIGURACION="propiedades/Configuracion.properties";
	
	public final static String NOMBRE_INVENTARIO="Inventario";
	public final static String NOMBRE_INVENTARIO_1=".pdf";
	public final static SimpleDateFormat FORMATO_FECHA_PDF= new SimpleDateFormat("dd MMMMM yyyy");
	public final static SimpleDateFormat FORMATO_FECHA_PDF_1= new SimpleDateFormat("ddMMyyyy");
	public final static String SLASH="/";
	/* TIpos de datos */
    public static final String CARACTER = "caracter";
    public static final String NUMERICO = "numerico";
    public static final String DECIMAL = "decimal";
    public static final String ALFANUMERICO = "alfanumerico";
    public static final String FECHA = "fecha";
    public static final String ESTADO_CONECTADO = "1";
    
}
