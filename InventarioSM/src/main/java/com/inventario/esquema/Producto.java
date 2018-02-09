package com.inventario.esquema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inventario.enums.GeneroEnum;
import com.inventario.utilidades.Constantes;

public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	private Campo nombreProducto;
	private Categoria categoria;
	private GeneroEnum genero;
	private TipoProducto tipo;
	private Marca marca;
	private Tamanio tamanio;
	private Origen origen;
	private Campo cantidad;
	private Campo precioCompra;
	private Campo precioVenta;
	private List<Campo> listaCampos;

	public Producto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Producto(String nombreProducto, Categoria categoria, GeneroEnum genero, TipoProducto tipo, Marca marca,
			String precioCompra, Tamanio tamanio, Integer idProducto, Origen origen, String cantidad,
			String precioVenta) {
		this.nombreProducto = new Campo("nombreProducto", "S", 30, Constantes.ALFANUMERICO, nombreProducto);
		this.precioCompra = new Campo("precioCompra", "S", 10, Constantes.NUMERICO, precioCompra);
		this.cantidad = new Campo("cantidad", "S", 4, Constantes.NUMERICO, cantidad);
		this.precioVenta = new Campo("precioVenta", "S", 10, Constantes.NUMERICO, precioVenta);
		this.genero = genero;
		this.categoria = categoria;
		this.tipo = tipo;
		this.marca = marca;
		this.tamanio = tamanio;
		this.idProducto = idProducto;
		this.origen = origen;
	}

	public Producto(String nombreProducto, Categoria categoria, GeneroEnum genero, TipoProducto tipo, Marca marca,
			String precioCompra, Tamanio tamanio, Origen origen, String cantidad, String precioVenta) {
		this.nombreProducto = new Campo("nombreProducto", "S", 30, Constantes.ALFANUMERICO, nombreProducto);
		this.precioCompra = new Campo("precioCompra", "S", 10, Constantes.NUMERICO, precioCompra);
		this.cantidad = new Campo("cantidad", "S", 4, Constantes.NUMERICO, cantidad);
		this.precioVenta = new Campo("precioVenta", "S", 10, Constantes.NUMERICO, precioVenta);
		this.categoria = categoria;
		this.tipo = tipo;
		this.marca = marca;
		this.tamanio = tamanio;
		this.origen = origen;
		this.genero = genero;
	}

	public Producto(Categoria categoria, GeneroEnum genero, TipoProducto tipo, Marca marca, Tamanio tamanio,
			Origen origen) {
		super();
		this.categoria = categoria;
		this.genero = genero;
		this.tipo = tipo;
		this.marca = marca;
		this.tamanio = tamanio;
		this.origen = origen;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public TipoProducto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Tamanio getTamanio() {
		return tamanio;
	}

	public void setTamanio(Tamanio tamanio) {
		this.tamanio = tamanio;
	}

	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	public Campo getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = new Campo("nombreProducto", "S", 30, Constantes.ALFANUMERICO, nombreProducto);
	}

	public GeneroEnum getGenero() {
		return genero;
	}

	public void setGenero(GeneroEnum genero) {
		this.genero = genero;
	}

	public Campo getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = new Campo("cantidad", "S", 4, Constantes.NUMERICO, cantidad);
	}

	public Campo getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(String precioCompra) {
		this.precioCompra = new Campo("precioCompra", "S", 10, Constantes.NUMERICO, precioCompra);
	}

	public Campo getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = new Campo("precioVenta", "S", 10, Constantes.NUMERICO, precioVenta);
	}

	public List<Campo> getListaCampos() {
		this.listaCampos = new ArrayList<Campo>();
		this.listaCampos.add(this.cantidad);
		this.listaCampos.add(this.nombreProducto);
		this.listaCampos.add(this.precioCompra);
		this.listaCampos.add(this.precioVenta);
		return listaCampos;
	}

	@Override
	public String toString() {
		return "idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", categoria=" + categoria
				+ ", genero=" + genero + ", tipo=" + tipo + ", marca=" + marca + ", tamanio=" + tamanio + ", origen="
				+ origen + ", cantidad=" + cantidad + ", precioCompra=" + precioCompra;
	}

}
