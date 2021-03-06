package com.inventario.negocio;

import java.util.ArrayList;
import java.util.List;

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
import com.inventario.logs.ManejoLogs;
import com.inventario.transaccionesbd.TransaccionesDAO;
import com.inventario.utilidades.CargaPropiedades;
import com.inventario.utilidades.Constantes;
import com.inventario.utilidades.Utilidades;

public class NegocioInventario {

	private TransaccionesDAO dao;
	private ManejoLogs log = new ManejoLogs(NegocioInventario.class);

	public NegocioInventario() {
		try {
			dao = new TransaccionesDAO();
		} catch (Exception e) {
			log.escribirError("Error al instanciar dao", e);
		}
	}

	public List<Object> listarObjetos(TipoTablaEnum tipoTabla) {
		try {
			return dao.consultarObjeto(tipoTabla);
		} catch (Exception e) {
			log.escribirError("Error al listar marcas", e);
			return null;
		}

	}
	public List<Object> listarObjetosCompletos(TipoTablaEnum tipoTabla) {
		try {
			return dao.consultarObjetoCompleto(tipoTabla);
		} catch (Exception e) {
			log.escribirError("Error al listar marcas", e);
			return null;
		}

	}

	public void crearProducto(Producto producto) throws Exception {
		validarCampos(producto);
		dao.insertarProducto(producto);
	}

	private void validarCampos(Producto producto) throws Exception {
		try {

			if (!Utilidades.validarCampoObligatorio(producto.getNombreProducto())) {
				throw new Exception("El nombre del producto es obligatorio");
			}
			if (!Utilidades.validarCampoObligatorio(producto.getPrecioCompra())) {
				throw new Exception("El precio de compra es obligatorio");
			}
			if (!Utilidades.validarCampoObligatorio(producto.getCantidad())) {
				throw new Exception("La cantidad es obligatoria");
			}
		} catch (NumberFormatException e) {
			throw new Exception("Hay campos n�mericos con caracteres");
		}
	}

	public void crearObjeto(TipoTablaEnum tipoTabla, String nombre) throws Exception {
		if (!Utilidades.validarCampoObligatorio(nombre)) {
			throw new Exception("El nombre es obligatorio");
		}
		dao.insertarObjetos(tipoTabla, nombre);

	}

	public List<Producto> consultarProducto(Producto producto) {
		List<Producto> lista = new ArrayList<Producto>();
		try {
			lista = dao.consultarProducto(producto);
		} catch (Exception e) {
			log.escribirError("Error al consultar Producto " + producto, e);
		}
		return lista;
	}

	public void editarProducto(Producto producto) {
		try {
			dao.editarProducto(producto);
		} catch (Exception e) {
			e.printStackTrace();
			log.escribirError("Error al editar Producto " + producto, e);
		}
	}

	public Producto consultarProducto(Integer idProducto) {
		Producto producto = null;
		try {
			producto = dao.consultarProducto(idProducto);
		} catch (Exception e) {
			e.printStackTrace();
			log.escribirError("Error al consultar Producto " + producto, e);
		}
		return producto;
	}

	public void eliminarProducto(Integer idProducto) {
		try {
			dao.eliminarProduto(idProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void eliminarObjeto(TipoTablaEnum tablaEnum,Integer codigo) {
		try {
			dao.eliminarObjeto(tablaEnum, codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void crearInventario(Inventario inventario) {
		try {
			dao.crearInventario(inventario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crearInventarioPeriodico(InventarioPeriodico inventarioPeriodico) {
		try {
			dao.crearPeriodico(inventarioPeriodico);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Inventario consultarInventario() {
		Inventario inventario = null;
		try {
			inventario = dao.consultarInventario();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventario;

	}

	public List<Producto> consultarProductoInventario(Integer idInventario) {
		List<Producto> lista = new ArrayList<Producto>();
		try {
			lista = dao.consultarProductosInventario(idInventario);
		} catch (Exception e) {
			log.escribirError("Error al consultar Producto " + idInventario, e);
		}
		return lista;
	}

	public List<Inventario> consultarInventarios(String tipo) {
		List<Inventario> lista = new ArrayList<Inventario>();
		try {
			lista = dao.consultarInventarios(tipo);
		} catch (Exception e) {
			log.escribirError("Error al consultar Producto " + tipo, e);
		}
		return lista;
	}

	public List<Producto> consultarProductoPorInventario(Integer idinventario, Producto producto) {
		List<Producto> lista = new ArrayList<Producto>();
		try {
			lista = dao.consultarProductoPorInventario(idinventario, producto);
		} catch (Exception e) {
			log.escribirError("Error al consultar Producto " + producto, e);
		}
		return lista;
	}

	public void eliminarInventarioPeriodico(Integer idProducto) {
		try {
			dao.eliminarProductoInventario(idProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void eliminarInventario(Integer idProducto) {
		try {
			dao.eliminarInventarioPeriodico(idProducto);
			dao.eliminarInventario(idProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cargarPropiedades() {
		try {
			CargaPropiedades.getInstance(Constantes.ARCHIVO_PROPIEDADES_CONFIGURACION);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Perfil> consultarPerfil(){
		List<Perfil> perfiles= new ArrayList<Perfil>();
		try {
			perfiles=dao.consultarPerfiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return perfiles;
	}
	public void insertarUsuario(Usuario usuario){
		try {
			dao.insertarUsuario(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Object consultarParametro(TipoTablaEnum tablaEnum,Integer identificacion) {
		Object object = null;
		try {
			object = dao.consultarObjetoId(tablaEnum, identificacion);
		} catch (Exception e) {
			e.printStackTrace();
			log.escribirError("Error al consultar Producto " + object, e);
		}
		return object;
	}
	public void crearPersona(Persona persona) {
		try {
			dao.insertarPersona(persona);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void editarMarca(Marca marca) {
		try {
			dao.editarMarca(marca);
		} catch (Exception e) {
			e.printStackTrace();
			log.escribirError("Error al editar Producto " + marca, e);
		}
	}
	public void editarCategoria(Categoria categoria) {
		try {
			dao.editarCategoria(categoria);
		} catch (Exception e) {
			e.printStackTrace();
			log.escribirError("Error al editar Producto " + categoria, e);
		}
	}
	public void editarTipo(TipoProducto tipo) {
		try {
			dao.editarTipo(tipo);
		} catch (Exception e) {
			e.printStackTrace();
			log.escribirError("Error al editar Producto " + tipo, e);
		}
	}
	public void editarTamanio(Tamanio tamanio) {
		try {
			dao.editarTamanio(tamanio);
		} catch (Exception e) {
			e.printStackTrace();
			log.escribirError("Error al editar Producto " + tamanio, e);
		}
	}
	public void editarOrigen(Origen origen) {
		try {
			dao.editarOrigen(origen);
		} catch (Exception e) {
			e.printStackTrace();
			log.escribirError("Error al editar Producto " + origen, e);
		}
	}

	public List<InventarioPeriodico> consultarInventarioPeriodico(Inventario inventario) {
		List<InventarioPeriodico> lista = null;
		try {
			lista = dao.consultarInventarioPeriodico(inventario);
		} catch (Exception e) {
			e.printStackTrace();
			log.escribirError("Error al editar Producto ", e);
		}
		return lista;
	}
}
