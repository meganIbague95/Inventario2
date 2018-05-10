package com.inventario.esquema;

import java.math.BigDecimal;

public class InventarioPeriodico {
	private Integer idInventarioPeriodico;
	private Integer cantidad;
	private Producto producto;
	private Inventario inventario;
	private BigDecimal valorTotal;

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
	public InventarioPeriodico(Integer cantidad, Producto producto, Inventario inventario,BigDecimal valorTotal) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.inventario = inventario;
		this.valorTotal=valorTotal;
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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
