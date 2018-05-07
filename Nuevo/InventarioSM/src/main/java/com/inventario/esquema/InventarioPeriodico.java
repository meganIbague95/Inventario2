package com.inventario.esquema;

public class InventarioPeriodico {
	private Integer idInventarioPeriodico;
	private Integer cantidad;
	private Producto producto;
	private Inventario inventario;

	public InventarioPeriodico(Integer idInventarioPeriodico, Integer cantidad, Producto producto,
			Inventario inventario) {
		this.idInventarioPeriodico = idInventarioPeriodico;
		this.cantidad = cantidad;
		this.producto = producto;
		this.inventario = inventario;
	}

	public InventarioPeriodico(Integer cantidad, Producto producto, Inventario inventario) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.inventario = inventario;
	}

	public Integer getIdInventarioPeriodico() {
		return idInventarioPeriodico;
	}

	public void setIdInventarioPeriodico(Integer idInventarioPeriodico) {
		this.idInventarioPeriodico = idInventarioPeriodico;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
}
