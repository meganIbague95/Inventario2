package com.inventario.transaccionesbd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inventario.conexion.ConnectionManagerJDBC;
import com.inventario.controlador.ControladorSeguridad;
import com.inventario.enums.GeneroEnum;
import com.inventario.enums.TipoInventarioEnum;
import com.inventario.enums.TipoTablaEnum;
import com.inventario.esquema.Bitacora;
import com.inventario.esquema.Categoria;
import com.inventario.esquema.Entrada;
import com.inventario.esquema.Inventario;
import com.inventario.esquema.InventarioPeriodico;
import com.inventario.esquema.Marca;
import com.inventario.esquema.Origen;
import com.inventario.esquema.Perfil;
import com.inventario.esquema.Persona;
import com.inventario.esquema.Producto;
import com.inventario.esquema.Salida;
import com.inventario.esquema.Tamanio;
import com.inventario.esquema.TipoProducto;
import com.inventario.esquema.Usuario;
import com.inventario.logs.ManejoLogs;
import com.inventario.utilidades.ConstantesSQL;
import com.inventario.utilidades.Utilidades;

public class TransaccionesDAO implements TransaccionesDAOInterface {
	private ManejoLogs log= new ManejoLogs(TransaccionesDAO.class);
	public TransaccionesDAO() throws Exception {

	}


	public void insertarProducto(Producto producto) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.INSERTAR_PRODUCTO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, producto.getNombreProducto().getValorCampo());
			ps.setInt(2, producto.getCategoria().getIdCategoria());
			ps.setString(3, producto.getGenero().name());
			ps.setInt(4, producto.getTipo().getIdTipo());
			ps.setInt(5, producto.getMarca().getIdMarca());
			ps.setBigDecimal(6, new BigDecimal(producto.getPrecioCompra().getValorCampo()));
			ps.setInt(7, producto.getTamanio().getIdTamanio());
			ps.setInt(8, producto.getOrigen().getIdOrigen());
			ps.setInt(9, new Integer(producto.getCantidad().getValorCampo()));
			ps.setBigDecimal(10, new BigDecimal(producto.getPrecioVenta().getValorCampo()));
			ps.setString(11, ControladorSeguridad.usuario.getNombre());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}

	}


	public Producto consultarProducto(Integer codigo) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_PRODUCTO);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		Producto producto = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, codigo);
			set = ps.executeQuery();
			if (set.next()) {
				Marca marca = new Marca(set.getInt(5), set.getString(11));
				Categoria categoria = new Categoria(set.getInt(2), set.getString(12));
				Tamanio tamanio = new Tamanio(set.getInt(7), set.getString(13));
				TipoProducto tipo = new TipoProducto(set.getInt(4), set.getString(15));
				Origen origen = new Origen(set.getInt(9), set.getString(14));
				producto = new Producto(set.getString(1), categoria, GeneroEnum.convertirTipo(set.getString(3)), tipo, marca,
						set.getString(6), tamanio, set.getInt(8), origen,set.getString(10),set.getString(16));
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return producto;
	}


	public void editarProducto(Producto producto) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.EDITAR_PRODUCTO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, producto.getNombreProducto().getValorCampo());
			ps.setInt(2, producto.getCategoria().getIdCategoria());
			ps.setString(3, producto.getGenero().name());
			ps.setInt(4, producto.getTipo().getIdTipo());
			ps.setInt(5, producto.getMarca().getIdMarca());
			ps.setBigDecimal(6, new BigDecimal(producto.getPrecioCompra().getValorCampo()));
			ps.setInt(7, producto.getTamanio().getIdTamanio());
			ps.setInt(8, producto.getOrigen().getIdOrigen());
			ps.setBigDecimal(9, new BigDecimal(producto.getPrecioVenta().getValorCampo()));
			ps.setString(10, ControladorSeguridad.usuario.getNombre());
			ps.setInt(11, producto.getIdProducto());
			
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void eliminarProduto(Integer codigo) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.ELIMINAR_PRODUCTO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, codigo);
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}

		
	public List<Producto> consultarProducto(Producto productoEntrada) throws Exception {
		List<Producto> productos;
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_PRODUCTOS);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			if(productoEntrada.getGenero()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_GENERO);
			}
			if(productoEntrada.getCategoria()!=null && productoEntrada.getCategoria().getIdCategoria()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_CATEGORIA);
			}
			if(productoEntrada.getMarca()!=null && productoEntrada.getMarca().getIdMarca()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_MARCA);
			}
			if(productoEntrada.getOrigen()!=null && productoEntrada.getOrigen().getIdOrigen()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_ORIGEN);
			}
			if(productoEntrada.getTamanio()!=null && productoEntrada.getTamanio().getIdTamanio()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_TAMANIO);
			}
			if(productoEntrada.getTipo()!=null && productoEntrada.getTipo().getIdTipo()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_TIPO);
			}
			sql.append(ConstantesSQL.ORDENAR);
			log.escribirInfo(sql.toString());
			System.out.println(sql.toString());
			ps = conexion.prepareStatement(sql.toString());
			int i=1;
			if(productoEntrada.getGenero()!=null){
			ps.setString(i, productoEntrada.getGenero().name());
			i++;
			}
			
			if(productoEntrada.getCategoria()!=null && productoEntrada.getCategoria().getIdCategoria()!=null){
				ps.setInt(i, productoEntrada.getCategoria().getIdCategoria());
				i++;
			}
			if(productoEntrada.getMarca()!=null && productoEntrada.getMarca().getIdMarca()!=null){
				ps.setInt(i, productoEntrada.getMarca().getIdMarca());
				i++;
			}
			if(productoEntrada.getOrigen()!=null && productoEntrada.getOrigen().getIdOrigen()!=null){
				ps.setInt(i, productoEntrada.getOrigen().getIdOrigen());
				i++;
			}
			if(productoEntrada.getTamanio()!=null && productoEntrada.getTamanio().getIdTamanio()!=null){
				ps.setInt(i, productoEntrada.getTamanio().getIdTamanio());
				i++;
			}
			if(productoEntrada.getTipo()!=null && productoEntrada.getTipo().getIdTipo()!=null){
				ps.setInt(i, productoEntrada.getTipo().getIdTipo());
				i++;
			}
			
			set = ps.executeQuery();
			productos = new ArrayList<Producto>();
			while (set.next()) {
				Marca marca = new Marca(set.getInt(5), set.getString(11));
				Categoria categoria = new Categoria(set.getInt(2), set.getString(12));
				Tamanio tamanio = new Tamanio(set.getInt(7), set.getString(13));
				TipoProducto tipo = new TipoProducto(set.getInt(4), set.getString(15));
				Origen origen = new Origen(set.getInt(9), set.getString(14));
				Producto producto = new Producto(set.getString(1), categoria, GeneroEnum.convertirTipo(set.getString(3)), tipo, marca,
						set.getString(6), tamanio, set.getInt(8), origen,set.getString(10),set.getString(16));
				productos.add(producto);
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return productos;
	}

	

	


	public void insertarObjetos(TipoTablaEnum tipoTabla, String nombre) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.INSERTAR_PARAMETRIZACION);
		sql.append(tipoTabla.getNombreTabla()).append(ConstantesSQL.VALUES);

		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			
			ps.setString(1, nombre);
			ps.setString(2, ControladorSeguridad.usuario.getNombre());
			ps.executeUpdate();
			conexion.commit();

		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public List<Object> consultarObjeto(TipoTablaEnum tipoTabla) throws Exception {
		List<Object> lista = null;
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_OBJETO);
		sql.append(tipoTabla.getNombreColumna()).append(ConstantesSQL.FROM).append(tipoTabla.getNombreTabla());
		System.out.println(sql.toString());
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			set = ps.executeQuery();
			lista = new ArrayList<Object>();
			while (set.next()) {
				if (tipoTabla == TipoTablaEnum.CATEGORIA) {
					Categoria categoria = new Categoria(set.getInt(2), set.getString(1));
					lista.add(categoria);
				} else if (tipoTabla == TipoTablaEnum.MARCA) {
					Marca marca = new Marca(set.getInt(2), set.getString(1));
					lista.add(marca);
				} else if (tipoTabla == TipoTablaEnum.MARCA) {
					Marca marca = new Marca(set.getInt(2), set.getString(1));
					lista.add(marca);
				} else if (tipoTabla == TipoTablaEnum.ORIGEN) {
					Origen origen = new Origen(set.getInt(2), set.getString(1));
					lista.add(origen);
				} else if (tipoTabla == TipoTablaEnum.TAMANIO) {
					Tamanio tamanio = new Tamanio(set.getInt(2), set.getString(1));
					lista.add(tamanio);
				}
				else if (tipoTabla == TipoTablaEnum.TIPO) {
					TipoProducto tipo = new TipoProducto(set.getInt(2), set.getString(1));
					lista.add(tipo);
				}

			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return lista;
	}


	public void editarMarca(Marca marca) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.EDITAR_MARCA);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, marca.getNombre().getValorCampo());
			ps.setString(2, ControladorSeguridad.usuario.getNombre());
			ps.setInt(3, marca.getIdMarca());
			
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void editarCategoria(Categoria categoria) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.EDITAR_CATEGORIA);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, categoria.getNombre().getValorCampo());
			ps.setString(2, ControladorSeguridad.usuario.getNombre());
			ps.setInt(3, categoria.getIdCategoria());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void editarTamanio(Tamanio tamanio) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.EDITAR_TAMANIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, tamanio.getNombre().getValorCampo());
			ps.setString(2, ControladorSeguridad.usuario.getNombre());
			ps.setInt(3, tamanio.getIdTamanio());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void editarTipo(TipoProducto tipo) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.EDITAR_TIPO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, tipo.getNombre().getValorCampo());
			ps.setString(2, ControladorSeguridad.usuario.getNombre());
			ps.setInt(3, tipo.getIdTipo());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void editarOrigen(Origen origen) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.EDITAR_ORIGEN);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, origen.getNombre().getValorCampo());
			ps.setString(2, ControladorSeguridad.usuario.getNombre());
			ps.setInt(3, origen.getIdOrigen());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void eliminarObjeto(TipoTablaEnum tipoTabla, Object objeto) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.ELIMINAR_PARAMETRIZACION);
		sql.append(tipoTabla.getNombreTabla()).append(ConstantesSQL.WHERE).append(tipoTabla.getNombreColumna())
				.append(ConstantesSQL.PARAMETRO);

		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			Integer codigo = Utilidades.obtenerCodigo(tipoTabla, objeto);
			ps.setInt(1, codigo);

			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public Usuario consultarUsuario(String login) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_USUARIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		Usuario usuario = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, login);
			set = ps.executeQuery();
			if (set.next()) {
				Perfil perfil = new Perfil(set.getInt(7), set.getString(8));
				Persona persona = new Persona(set.getString(9), set.getString(10), set.getString(11), set.getString(12),
						set.getString(13));
				usuario = new Usuario(set.getString(1), set.getString(2), new Date(set.getDate(3).getTime()),
						set.getString(4), set.getInt(5), set.getInt(6), perfil, persona);
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return usuario;
	}


	public Usuario consultarUsuarioSeguridad(String login) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_USUARIO_SEGURIDAD);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		Usuario usuario = null;
		log.escribirInfo(sql.toString()+" "+login);
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, login);
			set = ps.executeQuery();
			if (set.next()) {
				usuario = new Usuario(set.getString(1),set.getString(2));
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return usuario;
	}


	public void insertarBitacora(Bitacora bitacora) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.INSERTAR_BITACORA);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, bitacora.getAccion());
			ps.setString(2, bitacora.getTabla());
			ps.setString(3, bitacora.getUsuario());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}

	}


	public Bitacora consultarBitacora(String usuario) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_BITACORA);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		Bitacora bitacora = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, usuario);
			set = ps.executeQuery();
			if (set.next()) {

				bitacora = new Bitacora(set.getInt(1), set.getString(2), set.getString(3),
						new Date(set.getDate(4).getTime()), usuario);
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return bitacora;
	}


	public void insertarUsuario(Usuario usuario) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.INSERTAR_USUARIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getContraseña());
			ps.setInt(3, usuario.getPerfil().getIdPerfil());
			if(usuario.getPersona()!=null){
			ps.setString(4, usuario.getPersona().getTipoDocumento());
			ps.setInt(5, usuario.getPersona().getDocumento());
			}else{
				ps.setNull(4, Types.VARCHAR);
				ps.setNull(5, Types.NUMERIC);
			}
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}

	public void insertarPersona(Persona persona) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.INSERTAR_PERSONA);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, persona.getTipoDocumento());
			ps.setInt(2, persona.getDocumento());
			ps.setString(3, persona.getPrimerNombre());
			ps.setString(4, persona.getSegundoNombre());
			ps.setString(5, persona.getPrimerApellido());
			ps.setString(6, persona.getSegundoApellido());
			ps.setString(7, persona.getCargo());
			ps.setString(8, persona.getCorreo());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void insertarPerfil(Perfil perfil) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.INSERTAR_PERFIL);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, perfil.getNombre());
			ps.setString(2, perfil.getDescripcion());
			ps.setString(3, ControladorSeguridad.usuario.getNombre());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public Persona consultarPersona(String tipoIdentificacion, Integer identificacion) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_PERSONA);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		Persona persona = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, tipoIdentificacion);
			ps.setInt(2, identificacion);
			set = ps.executeQuery();
			if (set.next()) {

				persona = new Persona(set.getString(1), set.getInt(2), set.getString(3), set.getString(4),
						set.getString(5), set.getString(6), set.getString(7), set.getString(8));
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return persona;

	}


	public List<Perfil> consultarPerfiles() throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_PERFILES);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		List<Perfil> perfiles = new ArrayList<Perfil>();
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			set = ps.executeQuery();
			while (set.next()) {
				Perfil perfil = new Perfil(set.getInt(1), set.getString(2), set.getString(3));
				perfiles.add(perfil);
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return perfiles;
	}


	public void editarUsuario(Usuario usuario) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.EDITAR_USUARIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getContraseña());
			ps.setInt(3, usuario.getPerfil().getIdPerfil());
			ps.setString(4, usuario.getEstado());
			ps.setDate(5, new java.sql.Date(usuario.getUltimoIngreso().getTime()));
			ps.setInt(6, usuario.getNumeroIngreso());
			ps.setInt(7, usuario.getIntentosFallidos());
			ps.setString(8, usuario.getEstado());
			ps.setString(9, usuario.getNombre());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void editarPersona(Persona persona) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.EDITAR_PERSONA);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, persona.getTipoDocumento());
			ps.setInt(2, persona.getDocumento());
			ps.setString(3, persona.getPrimerNombre());
			ps.setString(4, persona.getSegundoNombre());
			ps.setString(5, persona.getPrimerApellido());
			ps.setString(6, persona.getSegundoApellido());
			ps.setString(7, persona.getCargo());
			ps.setString(8, persona.getCorreo());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}

	}


	public void editarPerfil(Perfil perfil) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.EDITAR_PERFIL);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, perfil.getNombre());
			ps.setString(2, perfil.getDescripcion());
			ps.setString(3, ControladorSeguridad.usuario.getNombre());
			ps.setInt(4, perfil.getIdPerfil());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}

	}


	public void eliminarUsuario(String usuario) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.ELIMINAR_USUARIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, usuario);
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void eliminarPersona(String tipoIdentificacion, Integer identificacion) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.ELIMINAR_PERSONA);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, tipoIdentificacion);
			ps.setInt(2, identificacion);
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void eliminarPerfil(Integer codigo) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.ELIMINAR_PERFIL);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, codigo);
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void insertarEntrada(Entrada entrada) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.INSERTAR_ENTRADA);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, entrada.getCantidad());
			ps.setBigDecimal(2, entrada.getPrecioEntrada());
			ps.setInt(3, entrada.getIdProducto());
			ps.setDate(4, entrada.getFechaCreacion());
			ps.setString(5, ControladorSeguridad.usuario.getNombre());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void insertarSalida(Salida salida) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.INSERTAR_SALIDA);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, salida.getCantidad());
			ps.setInt(2, salida.getIdProducto());
			ps.setDate(3, salida.getFechaCreacion());
			ps.setString(4, ControladorSeguridad.usuario.getNombre());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void crearInventario(Inventario inventario) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.CREAR_INVENTARIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, inventario.getTipoInventario().name());
			ps.setDate(2, new java.sql.Date(inventario.getFechaInventario().getTime()));
			ps.setString(3, ControladorSeguridad.usuario.getNombre());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public void crearPeriodico(InventarioPeriodico inventarioPeriodico) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.CREAR_INVENTARIO_PERIODICO);
		
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, inventarioPeriodico.getCantidad());
			ps.setInt(2, inventarioPeriodico.getInventario().getIdInventario());
			ps.setInt(3, inventarioPeriodico.getProducto().getIdProducto());
			ps.setString(4, ControladorSeguridad.usuario.getNombre());
			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}


	public Inventario consultarInventario() throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_CODIGO_INVENTARIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		Inventario inventario=null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			set = ps.executeQuery();
			if (set.next()) {
				Integer idInventario=set.getInt(1);
				String tipoInventario=set.getString(2);
				Long milis=set.getDate(4).getTime();
				Date fechaInventario=new Date(milis);
				inventario=new Inventario(idInventario,  TipoInventarioEnum.convertirTipo(tipoInventario), fechaInventario);
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return inventario;
		
		
	}
	public ArrayList<Producto> consultarProductosInventario(Integer idInventario) throws Exception {
		ArrayList<Producto> productos=new ArrayList<Producto>();
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_PRODUCTO_INVENTARIO);
		sql.append(ConstantesSQL.ORDENAR);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		Producto producto = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, idInventario);
			set = ps.executeQuery();
			while(set.next()) {
				Marca marca = new Marca(set.getInt(5), set.getString(11));
				Categoria categoria = new Categoria(set.getInt(2), set.getString(12));
				Tamanio tamanio = new Tamanio(set.getInt(7), set.getString(13));
				TipoProducto tipopro = new TipoProducto(set.getInt(4), set.getString(15));
				Origen origen = new Origen(set.getInt(9), set.getString(14));
				producto = new Producto(set.getString(1), categoria, GeneroEnum.convertirTipo(set.getString(3)), tipopro, marca,
						set.getString(6), tamanio, set.getInt(8), origen,set.getString(10),set.getString(16));
				System.out.println(producto.getNombreProducto());
				productos.add(producto);
				conexion.commit();
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return productos;
	}

	public List<Inventario> consultarInventarios(String tipoInventario) throws Exception {
		ArrayList<Inventario> inventarios =new ArrayList<Inventario>();
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_INVENTARIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setString(1, tipoInventario);
			set = ps.executeQuery();
			while (set.next()) {
				Integer id= set.getInt(1);
				String tipo=set.getString(2);
				java.sql.Date fechaInventario=set.getDate(3);
				Inventario inventario= new Inventario(id, TipoInventarioEnum.convertirTipo(tipo), fechaInventario);
				inventarios.add(inventario);
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return inventarios;
	}

	public List<Producto> consultarProductoPorInventario(Integer idInventario,Producto productoEntrada) throws Exception {
		List<Producto> productos;
		StringBuilder sql = new StringBuilder(ConstantesSQL.CONSULTAR_PRODUCTO_INVENTARIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		ResultSet set = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			if(productoEntrada.getGenero()!=null ){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_GENERO);
			}
			if(productoEntrada.getCategoria()!=null && productoEntrada.getCategoria().getIdCategoria()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_CATEGORIA);
			}
			if(productoEntrada.getMarca()!=null && productoEntrada.getMarca().getIdMarca()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_MARCA);
			}
			if(productoEntrada.getOrigen()!=null && productoEntrada.getOrigen().getIdOrigen()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_ORIGEN);
			}
			if(productoEntrada.getTamanio()!=null && productoEntrada.getTamanio().getIdTamanio()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_TAMANIO);
			}
			if(productoEntrada.getTipo()!=null && productoEntrada.getTipo().getIdTipo()!=null){
				sql.append(ConstantesSQL.CONSULTAR_PRODUCTO_TIPO);
			}
			sql.append(ConstantesSQL.ORDENAR);
			log.escribirInfo(sql.toString());
			System.out.println(sql.toString());
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, idInventario);
			int i=1;
			if(productoEntrada.getGenero()!=null){
				ps.setString(i, productoEntrada.getGenero().name());
				i++;
			}

			if(productoEntrada.getCategoria()!=null && productoEntrada.getCategoria().getIdCategoria()!=null){
				ps.setInt(i, productoEntrada.getCategoria().getIdCategoria());
				i++;
			}
			if(productoEntrada.getMarca()!=null && productoEntrada.getMarca().getIdMarca()!=null){
				ps.setInt(i, productoEntrada.getMarca().getIdMarca());
				i++;
			}
			if(productoEntrada.getOrigen()!=null && productoEntrada.getOrigen().getIdOrigen()!=null){
				ps.setInt(i, productoEntrada.getOrigen().getIdOrigen());
				i++;
			}
			if(productoEntrada.getTamanio()!=null && productoEntrada.getTamanio().getIdTamanio()!=null){
				ps.setInt(i, productoEntrada.getTamanio().getIdTamanio());
				i++;
			}
			if(productoEntrada.getTipo()!=null && productoEntrada.getTipo().getIdTipo()!=null){
				ps.setInt(i, productoEntrada.getTipo().getIdTipo());
				i++;
			}

			set = ps.executeQuery();
			productos = new ArrayList<Producto>();
			while (set.next()) {
				Marca marca = new Marca(set.getInt(5), set.getString(11));
				Categoria categoria = new Categoria(set.getInt(2), set.getString(12));
				Tamanio tamanio = new Tamanio(set.getInt(7), set.getString(13));
				TipoProducto tipo = new TipoProducto(set.getInt(4), set.getString(15));
				Origen origen = new Origen(set.getInt(9), set.getString(14));
				Producto producto = new Producto(set.getString(1), categoria, GeneroEnum.convertirTipo(set.getString(3)), tipo, marca,
						set.getString(6), tamanio, set.getInt(8), origen,set.getString(10),set.getString(16));
				productos.add(producto);
			}

		} finally {
			ConnectionManagerJDBC.closeResultSet(set);
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		return productos;
	}

	public void eliminarProductoInventario(Integer codigo) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.ELIMINAR_INVENTARIO_PERIODICO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, codigo);

			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
	}

	public void eliminarInventarioPeriodico(Integer codigo) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.ELIMINAR_INVENTARIO_PERIODICO_PORINV);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, codigo);

			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}
		
	}

	public void eliminarInventario(Integer codigo) throws Exception {
		StringBuilder sql = new StringBuilder(ConstantesSQL.ELIMINAR_INVENTARIO);
		PreparedStatement ps = null;
		Connection conexion = null;
		try {
			conexion = ConnectionManagerJDBC.getConnection();
			ps = conexion.prepareStatement(sql.toString());
			ps.setInt(1, codigo);

			ps.executeUpdate();
			conexion.commit();
		} finally {
			ConnectionManagerJDBC.closePreparedStatement(ps);
			ConnectionManagerJDBC.closeConnection(conexion);
		}		
	}
}
