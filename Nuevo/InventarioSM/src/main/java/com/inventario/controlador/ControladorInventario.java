package com.inventario.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.inventario.enums.GeneroEnum;
import com.inventario.enums.TipoInventarioEnum;
import com.inventario.enums.TipoTablaEnum;
import com.inventario.esquema.Categoria;
import com.inventario.esquema.Inventario;
import com.inventario.esquema.InventarioPeriodico;
import com.inventario.esquema.Marca;
import com.inventario.esquema.Origen;
import com.inventario.esquema.Perfil;
import com.inventario.esquema.Persona;
import com.inventario.esquema.Producto;
import com.inventario.esquema.Tamanio;
import com.inventario.esquema.TipoProducto;
import com.inventario.esquema.Usuario;
import com.inventario.interfaz.DialogAgregarProductos;
import com.inventario.interfaz.DialogAgregarProductosNuevos;
import com.inventario.interfaz.DialogCrearInventario;
import com.inventario.interfaz.DialogCrearObjeto;
import com.inventario.interfaz.DialogCrearUsuario;
import com.inventario.interfaz.DialogEditarParametros;
import com.inventario.interfaz.DialogEditarProducto;
import com.inventario.interfaz.DialogInsertarProducto;
import com.inventario.interfaz.DialogMostrarInventarios;
import com.inventario.interfaz.DialogMostrarProductos;
import com.inventario.interfaz.DialogVisualizarParametros;
import com.inventario.manejo.documento.ManejadorPdf;
import com.inventario.negocio.NegocioInventario;
import com.inventario.negocio.SeguridadInventario;
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
	private Integer idMarcaEditar;
	private Integer idTipoEditar;
	private Integer idOrigenEditar;
	private Integer idCategoriaEditar;
	private Integer idMarca;
	private Integer idTamanioEditar;
	private Integer IdProductoEliminarInventario;
	private Integer idMarcaEliminar;
	private Integer idTipoEliminar;
	private Integer idOrigenEliminar;
	private Integer idCategoriaEliminar;
	private Integer idTamanioEliminar;
	private ArrayList<Producto> codigosProductos;
	private DialogEditarProducto dialogEditarProducto;
	private DialogCrearInventario dialogCrearInventario;
	private DialogAgregarProductos dialogAgregarProductos;
	private DialogMostrarInventarios dialogMostrarInventarios;
	private DialogAgregarProductosNuevos agregarProductosNuevos;
	private DialogVisualizarParametros dialogVisualizarParametros;
	private DialogEditarParametros dialogEditarParametros;
	private DialogCrearUsuario dialogCrearUsuario;
	private DialogAgregarProductosNuevos da;
	private Boolean isAgregarProductos;
	private String inventario;
	private int parametrización;
	private Inventario inventarioActual;
	SeguridadInventario s;

	public ControladorInventario() {
		ni = new NegocioInventario();
		codigosProductos = new ArrayList<Producto>();
		isAgregarProductos = Boolean.FALSE;
		parametrización = 0;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals(ConstantesInterfaz.CREAR)) {
				if (insertarProducto.getTxtCantidad().getText().length() == 0
						|| insertarProducto.getTxtNombre().getText().length() == 0
						|| insertarProducto.getTxtPrecio().getText().length() == 0
						|| insertarProducto.getTxtPrecioVenta().getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "No puede enviar campos vacíos");
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
						String cantidad = insertarProducto.getTxtCantidad().getText();
						String precioVenta = insertarProducto.getTxtPrecioVenta().getText();
						Producto producto = new Producto(insertarProducto.getTxtNombre().getText(), categoria, genero,
								tipo, marca, precioCompra, tamanio, origen, cantidad, precioVenta);
						ni.crearProducto(producto);
						insertarProducto.getTxtCantidad().setText("");
						insertarProducto.getTxtNombre().setText("");
						insertarProducto.getTxtPrecio().setText("");
						insertarProducto.getTxtPrecioVenta().setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Ingrese campos válidos");
					}

				}

			} else if (e.getActionCommand().equals(ConstantesInterfaz.CERRAR)) {
				System.out.println("holaaa");
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CREAR_OBJETO)) {
				if (objetoDialog.getTxtNombre().getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "No puede enviar campos vacíos");
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
				consultarProducto();
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
				if (dialogEditarProducto.getTxtNombre().getText() != null
						&& !dialogEditarProducto.getTxtNombre().getText().trim().equals("")) {
					String nombre = dialogEditarProducto.getTxtNombre().getText();
					producto.setNombreProducto(nombre);
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
				consultarProducto();
			} else if (e.getActionCommand().equals(ConstantesInterfaz.ABRIR_VENTANA_EDITAR)) {
				System.out.println("editar productosS");
				Producto producto = ni.consultarProducto(idProductoEditar);
				dialogEditarProducto = new DialogEditarProducto(this, producto);
				dialogEditarProducto.setVisible(Boolean.TRUE);

			} else if (e.getActionCommand().equals(ConstantesInterfaz.ABRIR_EDITAR_MARCA)) {
				Marca marca = (Marca) ni.consultarParametro(TipoTablaEnum.MARCA, idMarcaEditar);
				dialogEditarParametros = new DialogEditarParametros(this, marca);
				dialogEditarParametros.setVisible(Boolean.TRUE);
				parametrización = 1;

			} else if (e.getActionCommand().equals(ConstantesInterfaz.ABRIR_EDITAR_CATEGORIA)) {
				Categoria categoria = (Categoria) ni.consultarParametro(TipoTablaEnum.CATEGORIA, idCategoriaEditar);
				dialogEditarParametros = new DialogEditarParametros(this, categoria);
				System.out.println(categoria.getNombre().getValorCampo());
				dialogEditarParametros.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.ABRIR_EDITAR_TIPO)) {
				TipoProducto tipo = (TipoProducto) ni.consultarParametro(TipoTablaEnum.TIPO, idTipoEditar);
				dialogEditarParametros = new DialogEditarParametros(this, tipo);
				dialogEditarParametros.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.ABRIR_EDITAR_ORIGEN)) {
				Origen origen = (Origen) ni.consultarParametro(TipoTablaEnum.ORIGEN, idOrigenEditar);
				dialogEditarParametros = new DialogEditarParametros(this, origen);
				dialogEditarParametros.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.ABRIR_EDITAR_TAMANIO)) {
				Tamanio tamanio = (Tamanio) ni.consultarParametro(TipoTablaEnum.TAMANIO, idTamanioEditar);
				dialogEditarParametros = new DialogEditarParametros(this, tamanio);
				dialogEditarParametros.setVisible(Boolean.TRUE);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.EDITAR_CATEGORIA)) {
				Categoria categoria = dialogEditarParametros.getCategoria();
				categoria.setNombre(dialogEditarParametros.getTxtNombre().getText());
				ni.editarCategoria(categoria);
				dialogEditarParametros.dispose();
				List<Categoria> categorias = (List)ni.listarObjetos(TipoTablaEnum.CATEGORIA);
				dialogVisualizarParametros.adicionarCategoria(categorias);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.EDITAR_TAMANIO)) {
				Tamanio tamanio = dialogEditarParametros.getTamanio();
				tamanio.setNombre(dialogEditarParametros.getTxtNombre().getText());
				ni.editarTamanio(tamanio);
				dialogEditarParametros.dispose();
				List<Tamanio> tamanios = (List)ni.listarObjetos(TipoTablaEnum.TAMANIO);
				dialogVisualizarParametros.adicionarTamanio(tamanios);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.EDITAR_ORIGEN)) {
				Origen origen = dialogEditarParametros.getOrigen();
				origen.setNombre(dialogEditarParametros.getTxtNombre().getText());
				ni.editarOrigen(origen);
				dialogEditarParametros.dispose();
				List<Origen> origenes = (List)ni.listarObjetos(TipoTablaEnum.ORIGEN);
				dialogVisualizarParametros.adicionarOrigen(origenes);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.EDITAR_MARCA)) {
				Marca marca = dialogEditarParametros.getMarca();
				marca.setNombre(dialogEditarParametros.getTxtNombre().getText());
				ni.editarMarca(marca);
				dialogEditarParametros.dispose();
				List<Marca> marcas = (List)ni.listarObjetos(TipoTablaEnum.MARCA);
				dialogVisualizarParametros.adicionarMarca(marcas);
				dialogVisualizarParametros.getBtnEditarMarca().stopCellEditing();
			} else if (e.getActionCommand().equals(ConstantesInterfaz.EDITAR_TIPO)) {
				TipoProducto tipo = dialogEditarParametros.getTipo();
				tipo.setNombre(dialogEditarParametros.getTxtNombre().getText());
				ni.editarTipo(tipo);
				dialogEditarParametros.dispose();
				List<TipoProducto> tipos = (List)ni.listarObjetos(TipoTablaEnum.TIPO);
				dialogVisualizarParametros.adicionarTipo(tipos);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.ELIMINAR_PRODUCTO)) {
				int result = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar este producto?",
						ConstantesInterfaz.CONFIRMAR_ELIMINAR_PRODUCTO, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					ni.eliminarProducto(idProductoEliminar);
					consultarProducto();
				}

			} else if (e.getActionCommand().equals(ConstantesInterfaz.ELIMINAR_TAMANIO)) {
				int result = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar este Tamaño?",
						ConstantesInterfaz.CONFIRMAR_ELIMINAR_TAMANIO, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					ni.eliminarObjeto(TipoTablaEnum.TAMANIO, idTamanioEliminar);
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
							JOptionPane.showMessageDialog(null, "Ese producto ya se agregó");
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
						Integer cantidad = new Integer(
								dialogAgregarProductos.extraerDatos().getCantidad().getValorCampo());
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
				inventarioActual = new Inventario(tipoInventario, fechaSql);
				ni.crearInventario(inventarioActual);
				inventarioActual = ni.consultarInventario();
				for (int i = 0; i < codigosProductos.size(); i++) {
					Integer cantidad = new Integer((dialogCrearInventario.getJtTable().getValueAt(i, 1).toString()));
					InventarioPeriodico inventarioPeriodico = new InventarioPeriodico(cantidad, codigosProductos.get(i),
							inventarioActual);
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
						JOptionPane.showMessageDialog(null, "Ese producto ya se agregó");
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
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CERRAR_FILTRO)) {
				dialogMostrarProductos.dispose();
			} else if (e.getActionCommand().equals(ConstantesInterfaz.SALIR_AGREGAR_PRO)) {
				dialogAgregarProductos.dispose();
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CERRAR_EDITAR_PRODUCTO)) {
				dialogEditarProducto.dispose();
			} else if (e.getActionCommand().equals(ConstantesInterfaz.CREAR_USUARIO)) {
				if (dialogCrearUsuario.getTxtPrimerNombre().getText().length() == 0
						|| dialogCrearUsuario.getTxtPrimerApellido().getText().length() == 0
						|| dialogCrearUsuario.getTxtCargo().getText().length() == 0
						|| dialogCrearUsuario.getTxtCorreo().getText().length() == 0
						|| dialogCrearUsuario.getTxtIdentificacion().getText().length() == 0
						|| dialogCrearUsuario.getTxtUsuario().getText().length() == 0
						|| dialogCrearUsuario.getTxtContrasenia().getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "No puede enviar campos vacíos");
				} else {
					String primerNombre = dialogCrearUsuario.getTxtPrimerNombre().getText();
					String segundoNombre = dialogCrearUsuario.getTxtSegundoNombre().getText();
					String primerApellido = dialogCrearUsuario.getTxtPrimerApellido().getText();
					String segundoApellido = dialogCrearUsuario.getTxtSegundoApellido().getText();
					String cargo = dialogCrearUsuario.getTxtCargo().getText();
					String correo = dialogCrearUsuario.getTxtCorreo().getText();
					String tipo = dialogCrearUsuario.getBoxTipoIdentificacion().getSelectedItem().toString();
					String identificacion = dialogCrearUsuario.getTxtIdentificacion().getText();
					String nombre = dialogCrearUsuario.getTxtUsuario().getText();
					String contrasenia = dialogCrearUsuario.getTxtContrasenia().getText();
					Perfil perfil = (Perfil) dialogCrearUsuario.getBoxPerfil().getSelectedItem();
					Persona persona = new Persona(tipo, Integer.valueOf(identificacion), primerNombre, segundoNombre,
							primerApellido, segundoApellido, cargo, correo);
					Usuario usuario = new Usuario(nombre, contrasenia, perfil, persona);
					s.crearPersona(persona);
					s.crearUsuario(usuario);
				}
			} else if (e.getActionCommand().equals(ConstantesInterfaz.DIALOG_CREAR_USUARIO)) {
				dialogCrearUsuario = new DialogCrearUsuario(this);
				dialogCrearUsuario.setVisible(true);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.DIALOG_EDITAR_PARAMETROS)) {
				dialogVisualizarParametros = new DialogVisualizarParametros(this);
				dialogVisualizarParametros.setVisible(true);
			} else if (e.getActionCommand().equals(ConstantesInterfaz.MOSTRAR_TABLA_PARAMETROS)) {
				if (dialogVisualizarParametros.getBoxParametros().getSelectedItem().equals(TipoTablaEnum.MARCA)) {
					List<Marca> marcas = (List)ni.listarObjetos(TipoTablaEnum.MARCA);
					dialogVisualizarParametros.adicionarMarca(marcas);
				} else if (dialogVisualizarParametros.getBoxParametros().getSelectedItem()
						.equals(TipoTablaEnum.CATEGORIA)) {
					List<Categoria> categorias = (List)ni.listarObjetos(TipoTablaEnum.CATEGORIA);
					dialogVisualizarParametros.adicionarCategoria(categorias);
				} else if (dialogVisualizarParametros.getBoxParametros().getSelectedItem()
						.equals(TipoTablaEnum.TAMANIO)) {
					List<Tamanio> tamanios = (List)ni.listarObjetos(TipoTablaEnum.TAMANIO);
					dialogVisualizarParametros.adicionarTamanio(tamanios);
				} else if (dialogVisualizarParametros.getBoxParametros().getSelectedItem()
						.equals(TipoTablaEnum.ORIGEN)) {
					List<Origen> origenes = (List)ni.listarObjetos(TipoTablaEnum.ORIGEN);
					dialogVisualizarParametros.adicionarOrigen(origenes);
				} else if (dialogVisualizarParametros.getBoxParametros().getSelectedItem().equals(TipoTablaEnum.TIPO)) {
					ArrayList<TipoProducto> tipos = new ArrayList<>();
					for (int i = 0; i < ni.listarObjetos(TipoTablaEnum.TIPO).size(); i++) {
						TipoProducto tipo = (TipoProducto) ni.listarObjetos(TipoTablaEnum.TIPO).get(i);
						tipos.add(tipo);
					}
					dialogVisualizarParametros.adicionarTipo(tipos);
				}
			}else if(e.getActionCommand().equals(ConstantesInterfaz.EXPORTAR_PDF)){
				if(inventarioActual!=null){

					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//					fc.setCurrentDirectory(new File(path));
						
						int returnVal = fc.showOpenDialog(null);
						String directorioDescarga=null;
				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				        	
				        	 directorioDescarga = fc.getSelectedFile().getAbsolutePath();
				        	
				        } 
					
				List<InventarioPeriodico> listaProductosInventario = ni.consultarInventarioPeriodico(inventarioActual);
				ManejadorPdf manejadorPdf = new ManejadorPdf(listaProductosInventario,directorioDescarga);
				manejadorPdf.crearPdf();
				}else{
					JOptionPane.showMessageDialog(null, "No se ha creado el inventario");
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error autenticación", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void consultarProducto(){
		Categoria categoria = ((Categoria) dialogMostrarProductos.getBoxCategoria().getSelectedItem());
		Marca marca = ((Marca) dialogMostrarProductos.getBoxMarca().getSelectedItem());
		TipoProducto tipo = ((TipoProducto) dialogMostrarProductos.getBoxTipo().getSelectedItem());
		Tamanio tamanio = ((Tamanio) dialogMostrarProductos.getBoxTamanio().getSelectedItem());
		Origen origen = ((Origen) dialogMostrarProductos.getBoxOrigen().getSelectedItem());
		GeneroEnum genero = (GeneroEnum) dialogMostrarProductos.getBoxGenero().getSelectedItem();
		Producto producto = new Producto(categoria, genero, tipo, marca, tamanio, origen);
		dialogMostrarProductos.adicionarProducto(ni.consultarProducto(producto));
		dialogMostrarProductos.repaint();
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

	public Integer getIdMarcaEditar() {
		return idMarcaEditar;
	}

	public void setIdMarcaEditar(Integer idMarcaEditar) {
		this.idMarcaEditar = idMarcaEditar;
	}

	public Integer getIdTipoEditar() {
		return idTipoEditar;
	}

	public void setIdTipoEditar(Integer idTipoEditar) {
		this.idTipoEditar = idTipoEditar;
	}

	public Integer getIdOrigenEditar() {
		return idOrigenEditar;
	}

	public void setIdOrigenEditar(Integer idOrigenEditar) {
		this.idOrigenEditar = idOrigenEditar;
	}

	public Integer getIdCategoriaEditar() {
		return idCategoriaEditar;
	}

	public void setIdCategoriaEditar(Integer idCategoriaEditar) {
		this.idCategoriaEditar = idCategoriaEditar;
	}

	public Integer getIdTamanioEditar() {
		return idTamanioEditar;
	}

	public void setIdTamanioEditar(Integer idTamanioEditar) {
		this.idTamanioEditar = idTamanioEditar;
	}

	public Integer getIdMarcaEliminar() {
		return idMarcaEliminar;
	}

	public void setIdMarcaEliminar(Integer idMarcaEliminar) {
		this.idMarcaEliminar = idMarcaEliminar;
	}

	public Integer getIdTipoEliminar() {
		return idTipoEliminar;
	}

	public void setIdTipoEliminar(Integer idTipoEliminar) {
		this.idTipoEliminar = idTipoEliminar;
	}

	public Integer getIdOrigenEliminar() {
		return idOrigenEliminar;
	}

	public void setIdOrigenEliminar(Integer idOrigenEliminar) {
		this.idOrigenEliminar = idOrigenEliminar;
	}

	public Integer getIdCategoriaEliminar() {
		return idCategoriaEliminar;
	}

	public void setIdCategoriaEliminar(Integer idCategoriaEliminar) {
		this.idCategoriaEliminar = idCategoriaEliminar;
	}

	public Integer getIdTamanioEliminar() {
		return idTamanioEliminar;
	}

	public void setIdTamanioEliminar(Integer idTamanioEliminar) {
		this.idTamanioEliminar = idTamanioEliminar;
	}

}
