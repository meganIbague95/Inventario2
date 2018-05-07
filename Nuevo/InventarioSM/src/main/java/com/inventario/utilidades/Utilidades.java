package com.inventario.utilidades;

import org.apache.commons.codec.digest.DigestUtils;

import com.inventario.enums.TipoTablaEnum;
import com.inventario.esquema.Categoria;
import com.inventario.esquema.Marca;
import com.inventario.esquema.Origen;
import com.inventario.esquema.Tamanio;
import com.inventario.esquema.TipoProducto;

public class Utilidades {

	public static Integer obtenerCodigo(TipoTablaEnum tipoTabla, Object objeto) {
		Integer codigo = null;

		if (TipoTablaEnum.CATEGORIA == tipoTabla) {
			Categoria categoria = (Categoria) objeto;
			codigo = categoria.getIdCategoria();
		} else if (TipoTablaEnum.MARCA == tipoTabla) {
			Marca marca = (Marca) objeto;
			codigo = marca.getIdMarca();
		} else if (TipoTablaEnum.ORIGEN == tipoTabla) {
			Origen origen = (Origen) objeto;
			codigo = origen.getIdOrigen();
		} else if (TipoTablaEnum.TIPO == tipoTabla) {
			TipoProducto tipo = (TipoProducto) objeto;
			codigo = tipo.getIdTipo();
		} else if (TipoTablaEnum.TAMANIO == tipoTabla) {
			Tamanio tamanio = (Tamanio) objeto;
			codigo = tamanio.getIdTamanio();
		}
		return codigo;
	}

	public static String obtenerNombre(TipoTablaEnum tipoTabla, Object objeto) {
		String nombre = null;

		if (TipoTablaEnum.CATEGORIA == tipoTabla) {
			Categoria categoria = (Categoria) objeto;
			nombre = categoria.getNombre().getValorCampo();
		} else if (TipoTablaEnum.MARCA == tipoTabla) {
			Marca marca = (Marca) objeto;
			nombre = marca.getNombre().getValorCampo();
		} else if (TipoTablaEnum.ORIGEN == tipoTabla) {
			Origen origen = (Origen) objeto;
			nombre = origen.getNombre().getValorCampo();
		} else if (TipoTablaEnum.TIPO == tipoTabla) {
			TipoProducto tipo = (TipoProducto) objeto;
			nombre = tipo.getNombre().getValorCampo();
		} else if (TipoTablaEnum.TAMANIO == tipoTabla) {
			Tamanio tamanio = (Tamanio) objeto;
			nombre = tamanio.getNombre().getValorCampo();
		}
		return nombre;
	}

	public static String encriptarContrasenia(String contrasenia) {
		final String token = "IbAgUeCoMpAnYT0k3n3nc1p710n";
		StringBuilder textoEncriptar = new StringBuilder(contrasenia).append(token);
		return DigestUtils.md5Hex(textoEncriptar.toString());
	}

	public static Boolean validarCampoObligatorio(Object campo) {
		Boolean campoValido = Boolean.TRUE;
		if (campo == null || campo.toString().trim().equals("")) {
			campoValido = Boolean.FALSE;
		}
		return campoValido;
	}
	public static Boolean validarNumerico(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  
	}

	public static Boolean validarCampoVacio(String str) {
		return (str == null || str.trim().length() == 0);
	}
}
