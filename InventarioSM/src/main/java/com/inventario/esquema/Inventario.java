package com.inventario.esquema;

import java.io.Serializable;
import java.util.Date;

import com.inventario.enums.TipoInventarioEnum;

public class Inventario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idInventario;
	private TipoInventarioEnum tipoInventario;
	private Date fechaInventario;

	
	public Inventario(Integer idInventario) {
		this.idInventario = idInventario;
	}

	public Inventario(Integer idInventario, TipoInventarioEnum tipoInventario, Date fechaInventario) {
		this.idInventario = idInventario;
		this.tipoInventario = tipoInventario;
		this.fechaInventario = fechaInventario;
	}

	public Inventario(TipoInventarioEnum tipoInventario, Date fechaInventario) {
		this.tipoInventario = tipoInventario;
		this.fechaInventario = fechaInventario;
	}

	public Integer getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}


	

	public TipoInventarioEnum getTipoInventario() {
		return tipoInventario;
	}

	public void setTipoInventario(TipoInventarioEnum tipoInventario) {
		this.tipoInventario = tipoInventario;
	}

	public Date getFechaInventario() {
		return fechaInventario;
	}

	public void setFechaInventario(Date fechaInventario) {
		this.fechaInventario = fechaInventario;
	}

	@Override
	public String toString() {
		return "Inventario [idInventario=" + idInventario + ", tipoInventario="
				+ tipoInventario + ", fechaInventario=" + fechaInventario + "]";
	}

}
