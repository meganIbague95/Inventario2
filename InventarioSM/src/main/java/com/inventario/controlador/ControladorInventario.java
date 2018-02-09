package com.inventario.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.inventario.enums.GeneroEnum;
import com.inventario.enums.TipoInventarioEnum;
import com.inventario.enums.TipoTablaEnum;
import com.inventario.esquema.Categoria;
import com.inventario.esquema.Inventario;
import com.inventario.esquema.InventarioPeriodico;
import com.inventario.esquema.Marca;
import com.inventario.esquema.Origen;
import com.inventario.esquema.Producto;
import com.inventario.esquema.Tamanio;
import com.inventario.esquema.TipoProducto;
import com.inventario.interfaz.DialogAgregarProductos;
import com.inventario.interfaz.DialogAgregarProductosNuevos;
import com.inventario.interfaz.DialogCrearInventario;
import com.inventario.interfaz.DialogCrearObjeto;
import com.inventario.interfaz.DialogEditarProducto;
import com.inventario.interfaz.DialogInsertarProducto;
import com.inventario.interfaz.DialogMostrarInventarios;
import com.inventario.interfaz.DialogMostrarProductos;
import com.inventario.negocio.NegocioInventario;
import com.inventario.utilidades.ConstantesInterfaz;
import com.inventario.utilidades.Utilidades;

public class ControladorInventario implements ActionListener {
	private NegocioInventario ni;
	private DialogInsertarProducto insertarProducto;
	private DialogCrearObjeto objetoDialog;
	private DialogMostrarProductos dialogMostrarProductos;
	private Integer idProductoEditar;
	private Integer idInventarioEditar;
	private Integer idProductoEliminar;
	private Integer idInventarioEliminar;
	private Integer IdProductoEliminarInventario;
	private ArrayList<Producto> codigosProductos;
	private DialogEditarProducto dialogEditarProducto;
	private DialogCrearInventario dialogCrearInventario;
	private DialogAgregarProductos dialogAgregarProductos;
	private DialogMostrarInventarios dialogMostrarInventarios;
	private DialogAgregarProductosNuevos agregarProductosNuevos;
	private DialogAgregarProductosNuevos da;
	private Boolean isAgregarProductos;
	private String inventario;

	public ControladorInventario() {
		ni = new NegocioInventario();
		codigosProductos = new ArrayList<Producto>();
		isAgregarProductos = Boolean.FALSE;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals(ConstantesInterfaz.CREAR)) {
				if (insertarProducto.getTxtCantidad().getText().length() == 0
						|| insertarProducto.getTxtNombre().getText().length() == 0
						|| insertarProducto.getTxtPrecio().getText().length() == 0
						|| insertarProducto.getTxtPrecioVenta().getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "No puede enviar campos vac�os");
				} else {
					if (Utilidades.validarNumerico(insertarProducto.getTxtPrecio().getText())
							&& Utilidades.validarNumerico(insertarProducto.getTxtCantidad().getText())
							&& Utilidades.validarNumerico(insertarProducto.getTxtPrecioVenta().getText())
							&& !Utilidades.validarNumerico(insertarProducto.getTxtNombre().getText())) {

						Categoria categoria = ((Categoria) insertarProducto.getBoxCategoria().getSelectedItem());
						Marca marca = ((Marca) insertarProducto.getBoxMarca().getSelectedItem());
						TipoProducto tipo = ((TipoProducto) insertarProducto.getBoxTipo().getSelectedItem());
						Tamanio tamanio = ((Tamanio) insertarProducto.getBoxTamanio().getSelectedItem());
						Origen origen = ((Origen) insertarProducto.getBoxOrigen().getSelectedItem());
						GeneroEnum genero = (GeneroEnum) insertarProducto.getBoxGenero().getSelectedItem();
						String precioCompra = insertarProducto.getTxtPrecio().getText();
						String cantidad =insertarProducto.getTxtCantidad().getText();
						String precioVenta = insertarProducto.getTxtPrecioVenta().getText();
						Producto producto = new Producto(insertarProducto.getTxtNombre().getText(), categoria,
								genero, tipo, marca, precioCompra, tamanio, origen, cantidad, precioVenta);
						ni.crearProducto(producto);
						insertarProducto.getTxtCantidad().setText("");
						insertarProducto.getTxtNombre().setText("");
						insertarProducto.getTxtPrecio().setText("");
						insertarProducto.getTxtPrecioVenta().setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Ingrese campos v�lidos");
					}

				}

			} else if (e.getActionCommand().equals(ConstantesInterfaz.CERRAR)) {
				System.out.println("holaaa");
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CREAR_OBJETO)) {
				if (objetoDialog.getTxtNombre().getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "No puede enviar campos vac�os");
				} else {
					TipoTablaEnum tipoTabla = (TipoTablaEnum) objetoDialog.getBoxTipoTabla().getSelectedItem();
					String nombre = objetoDialog.getTxtNombre().getText();
					ni.crearObjeto(tipoTabla, nombre);
					objetoDialog.getTxtNombre().setText("");
				}
			} else if (e.getActionCommand().equals(ConstantesInterfaz.INSERTAR_PRODUCTO)) {
				insertarProducto = new DialogInsertarProducto(this);
				insertarProducto.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CREAR_PARAMETRIZACION)) {
				objetoDialog = new DialogCrearObjeto(this);
				objetoDialog.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CONSULTAR_PRODUCTO)) {
				dialogMostrarProductos = new DialogMostrarProductos(this);
				dialogMostrarProductos.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.FILTRAR_PRODUCTO)) {
				Categoria categoria = ((Categoria) dialogMostrarProductos.getBoxCategoria().getSelectedItem());
				Marca marca = ((Marca) dialogMostrarProductos.getBoxMarca().getSelectedItem());
				TipoProducto tipo = ((TipoProducto) dialogMostrarProductos.getBoxTipo().getSelectedItem());
				Tamanio tamanio = ((Tamanio) dialogMostrarProductos.getBoxTamanio().getSelectedItem());
				Origen origen = ((Origen) dialogMostrarProductos.getBoxOrigen().getSelectedItem());
				GeneroEnum genero = (GeneroEnum)  dialogMostrarProductos.getBoxGenero().getSelectedItem();
				Producto producto = new Producto(categoria, genero, tipo, marca, tamanio, origen);
				dialogMostrarProductos.adicionarProducto(ni.consultarProducto(producto));
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CREAR_INVENTARIO)) {
				dialogCrearInventario = new DialogCrearInventario(this);
				dialogCrearInventario.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.EDITAR_PRODUCTO)) {
				Categoria categoria = ((Categoria) dialogEditarProducto.getBoxCategoria().getSelectedItem());
				Marca marca = ((Marca) dialogEditarProducto.getBoxMarca().getSelectedItem());
				TipoProducto tipo = ((TipoProducto) dialogEditarProducto.getBoxTipo().getSelectedItem());
				Tamanio tamanio = ((Tamanio) dialogEditarProducto.getBoxTamanio().getSelectedItem());
				Origen origen = ((Origen) dialogEditarProducto.getBoxOrigen().getSelectedItem());
				GeneroEnum genero = (GeneroEnum) dialogEditarProducto.getBoxGenero().getSelectedItem();

				Producto producto = dialogEditarProducto.getProducto();
				if (categoria != null) {
					producto.setCategoria(categoria);
				}
				if (marca != null) {
					producto.setMarca(marca);
				}
				if (origen != null) {
					producto.setOrigen(origen);
				}
				if (tipo != null) {
					producto.setTipo(tipo);
				}
				if (tamanio != null) {
					producto.setTamanio(tamanio);
				}
				if (genero != null) {
					producto.setGenero(genero);
				}
				if (dialogEditarProducto.getTxtPrecio().getText() != null
						&& !dialogEditarProducto.getTxtPrecio().getText().trim().equals("")) {
					String precioCompra = dialogEditarProducto.getTxtPrecio().getText();
					producto.setPrecioCompra(precioCompra);
				}
				if (dialogEditarProducto.getTxtPrecioVenta().getText() != null
						&& !dialogEditarProducto.getTxtPrecioVenta().getText().trim().equals("")) {
					String precioVenta = dialogEditarProducto.getTxtPrecioVenta().getText();
					producto.setPrecioVenta(precioVenta);
				}
				if (dialogEditarProducto.getTxtCantidad().getText() != null
						&& !dialogEditarProducto.getTxtCantidad().getText().trim().equals("")) {
					String cantidad = dialogEditarProducto.getTxtCantidad().getText();
					producto.setCantidad(cantidad);
				}
				ni.editarProducto(producto);
				dialogEditarProducto.dispose();
				dialogMostrarProductos.repaint();
			} else if (e.getActionCommand().equals(ConstantesInterfaz.ABRIR_VENTANA_EDITAR)) {
				Producto producto = ni.consultarProducto(idProductoEditar);

				dialogEditarProducto = new DialogEditarProducto(this, producto);
				dialogEditarProducto.setVisible(Boolean.TRUE);

			} else if (e.getActionCommand().equals(ConstantesInterfaz.ELIMINAR_PRODUCTO)) {
				int result = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar este producto?",
						ConstantesInterfaz.CONFIRMAR_ELIMINAR_PRODUCTO, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					ni.eliminarProducto(idProductoEliminar);
				}

			} else if (e.getActionCommand().equals(ConstantesInterfaz.AGREGAR_PRODUCTOS)) {

				dialogAgregarProductos = new DialogAgregarProductos(this);
				dialogAgregarProductos.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.FILTRAR_AGREGAR)) {
				Categoria categoria = ((Categoria) dialogAgregarProductos.getBoxCategoria().getSelectedItem());
				Marca marca = ((Marca) dialogAgregarProductos.getBoxMarca().getSelectedItem());
				TipoProducto tipo = ((TipoProducto) dialogAgregarProductos.getBoxTipo().getSelectedItem());
				Tamanio tamanio = ((Tamanio) dialogAgregarProductos.getBoxTamanio().getSelectedItem());
				Origen origen = ((Origen) dialogAgregarProductos.getBoxOrigen().getSelectedItem());
				GeneroEnum genero = (GeneroEnum) dialogAgregarProductos.getBoxGenero().getSelectedItem();
				Producto producto = new Producto(categoria, genero, tipo, marca, tamanio, origen);
				dialogAgregarProductos.adicionarProducto(ni.consultarProducto(producto));
			} else if (e.getActionCommand().equals(ConstantesInterfaz.AGREGAR_PRO)) {
				Boolean conf = Boolean.FALSE;
				if (!isAgregarProductos) {
					if (codigosProductos.size() > 0) {
						for (int i = 0; i < codigosProductos.size(); i++) {
							if (codigosProductos.get(i).getIdProducto()
									.equals(dialogAgregarProductos.extraerDatos().getIdProducto())) {
								conf = Boolean.TRUE;
							}
						}
						if (conf) {
							JOptionPane.showMessageDialog(null, "Ese producto ya se agreg�");
							conf = Boolean.FALSE;
						} else {
							codigosProductos.add(dialogAgregarProductos.extraerDatos());
						}
					} else {
						codigosProductos.add(dialogAgregarProductos.extraerDatos());
						System.out.println("esto esta vacio");
					}
					dialogCrearInventario.adicionarProducto(codigosProductos);
				} else {
					int filaSeleccionada = dialogAgregarProductos.getJtTable().getSelectedRow();
					if (filaSeleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
					} else {
						Integer cantidad = new Integer(dialogAgregarProductos.extraerDatos().getCantidad().getValorCampo());
						Integer idProducto = dialogAgregarProductos.extraerDatos().getIdProducto();
						Integer idInventario = idInventarioEditar;
						InventarioPeriodico inventarioPeriodico = new InventarioPeriodico(cantidad,
								new Producto(idProducto), new Inventario(idInventario));
						ni.crearInventarioPeriodico(inventarioPeriodico);
						da.adicionarProducto(ni.consultarProductoInventario(idInventarioEditar));
						System.out.println("este booleano es true");
						isAgregarProductos = Boolean.FALSE;
					}
				}
			} else if (e.getActionCommand().equals(ConstantesInterfaz.ELIMINAR_PRODUCTO_AGREGADO)) {
				int filaSeleccionada = dialogCrearInventario.getJtTable().getSelectedRow();
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
				} else {
					codigosProductos.remove(filaSeleccionada);
					dialogCrearInventario.adicionarProducto(codigosProductos);
				}

			} else if (e.getActionCommand().equals(ConstantesInterfaz.CERRAR_INVENTARIO)) {
				TipoInventarioEnum tipoInventario = (TipoInventarioEnum) dialogCrearInventario.getBoxTipoInventario()
						.getSelectedItem();
				Date fechaInventario = dialogCrearInventario.getCalendarFecha().getDate();
				java.sql.Date fechaSql = new java.sql.Date(fechaInventario.getTime());
				Inventario inventario = new Inventario(tipoInventario, fechaSql);
				ni.crearInventario(inventario);
				for (int i = 0; i < codigosProductos.size(); i++) {
					Integer cantidad = (Integer) (dialogCrearInventario.getJtTable().getValueAt(i, 1));
					System.out.println("cantidad " + cantidad);
					InventarioPeriodico inventarioPeriodico = new InventarioPeriodico(cantidad, codigosProductos.get(i),
							ni.consultarInventario());
					ni.crearInventarioPeriodico(inventarioPeriodico);
				}
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CONSULTAR_INVENTARIO)) {
				dialogMostrarInventarios = new DialogMostrarInventarios(this);
				dialogMostrarInventarios.setVisible(Boolean.TRUE);

			} else if (e.getActionCommand().equals(ConstantesInterfaz.MOSTRAR_INVENTARIO)) {
				inventario = dialogMostrarInventarios.getBoxInventario().getSelectedItem().toString();
				dialogMostrarInventarios.adicionarProducto(ni.consultarInventarios(inventario));

			} else if (e.getActionCommand().equals(ConstantesInterfaz.AGREGAR_NUEVOS_PRODUCTOS)) {
				agregarProductosNuevos = new DialogAgregarProductosNuevos(this);
				agregarProductosNuevos.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.AGREGAR_PRODUCTOS_A_INVENTARIO)) {
				isAgregarProductos = Boolean.TRUE;
				dialogAgregarProductos = new DialogAgregarProductos(this);
				dialogAgregarProductos.setVisible(Boolean.TRUE);
			}

			else if (e.getActionCommand().equals(ConstantesInterfaz.AGREGAR_PRODUCTO_NUEVO)) {
				Boolean conf = Boolean.FALSE;
				if (codigosProductos.size() > 0) {
					for (int i = 0; i < codigosProductos.size(); i++) {
						if (codigosProductos.get(i).getIdProducto()
								.equals(dialogAgregarProductos.extraerDatos().getIdProducto())) {
							conf = Boolean.TRUE;
						}
					}
					if (conf) {
						JOptionPane.showMessageDialog(null, "Ese producto ya se agreg�");
						conf = Boolean.FALSE;
					} else {
						codigosProductos.add(dialogAgregarProductos.extraerDatos());
					}
				} else {
					codigosProductos.add(dialogAgregarProductos.extraerDatos());
					System.out.println("esto esta vacio");
				}
				dialogCrearInventario.adicionarProducto(codigosProductos);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.ABRIR_EDITAR_INVENTARIO)) {
				da = new DialogAgregarProductosNuevos(this);
				da.setVisible(Boolean.TRUE);
				da.adicionarProducto(ni.consultarProductoInventario(idInventarioEditar));

			} else if (e.getActionCommand().equals(ConstantesInterfaz.ELIMINAR_PRODUCTO_INVENTARIO)) {
				int result = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar este producto?",
						ConstantesInterfaz.CONFIRMAR_ELIMINAR_PRODUCTO, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					ni.eliminarInventarioPeriodico(IdProductoEliminarInventario);
					da.adicionarProducto(ni.consultarProductoInventario(idInventarioEditar));
				}
			} else if (e.getActionCommand().equals(ConstantesInterfaz.ELIMINAR_INVENTARIO)) {
				int result = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar este Inventario?",
						ConstantesInterfaz.CONFIRMAR_ELIMINAR_PRODUCTO, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					ni.eliminarInventario(idInventarioEliminar);
					dialogMostrarInventarios.adicionarProducto(ni.consultarInventarios(
							dialogMostrarInventarios.getBoxInventario().getSelectedItem().toString()));
				}
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CERRAR_PRINCIPAL)) {
				System.exit(0);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CERRAR_INSERTAR_PRODUCTO)) {
				insertarProducto.dispose();
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CERRAR_EDITAR_PRODUCTO)) {
				dialogEditarProducto.dispose();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error autenticaci�n", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	public Integer getIdProductoEditar() {
		return idProductoEditar;
	}

	public void setIdProductoEditar(Integer idProductoEditar) {
		this.idProductoEditar = idProductoEditar;
	}

	public Integer getIdProductoEliminar() {
		return idProductoEliminar;
	}

	public void setIdProductoEliminar(Integer idProductoEliminar) {
		this.idProductoEliminar = idProductoEliminar;
	}

	public NegocioInventario getNi() {
		return ni;
	}

	public void setNi(NegocioInventario ni) {
		this.ni = ni;
	}

	public Integer getIdInventarioEditar() {
		return idInventarioEditar;
	}

	public void setIdInventarioEditar(Integer idInventarioEditar) {
		this.idInventarioEditar = idInventarioEditar;
	}

	public Integer getIdProductoEliminarInventario() {
		return IdProductoEliminarInventario;
	}

	public void setIdProductoEliminarInventario(Integer idProductoEliminarInventario) {
		IdProductoEliminarInventario = idProductoEliminarInventario;
	}

	public Integer getIdInventarioEliminar() {
		return idInventarioEliminar;
	}

	public void setIdInventarioEliminar(Integer idInventarioEliminar) {
		this.idInventarioEliminar = idInventarioEliminar;
	}
}
