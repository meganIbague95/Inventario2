package com.inventario.interfaz;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.inventario.controlador.ControladorInventario;
import com.inventario.enums.TipoTablaEnum;
import com.inventario.esquema.Categoria;
import com.inventario.esquema.Inventario;
import com.inventario.esquema.Marca;
import com.inventario.esquema.Origen;
import com.inventario.esquema.Tamanio;
import com.inventario.esquema.TipoProducto;
import com.inventario.negocio.NegocioInventario;
import com.inventario.utilidades.ConstantesInterfaz;

public class DialogVisualizarParametros extends JDialog{

	private ControladorInventario controladorInventario;
	private JLabel lblParametros;
	private JComboBox<TipoTablaEnum> boxParametros;

	private JButton btnMostrar;
	private JButton btnCerrar;
	private JTable jtTable;
	private DefaultTableModel dtm ;
	private NegocioInventario negocioInventario; 
	public DialogVisualizarParametros(ControladorInventario controladorInventario) {
		setSize(600, 400);
		setResizable(Boolean.FALSE);
		setLayout(null);
		setTitle("Editar parámetros");
		setLocationRelativeTo(null);
		this.controladorInventario = controladorInventario;
		this.negocioInventario = this.controladorInventario.getNi();
		createJtable();
		setColumnJtable();
		inicializarComponentes();

	}

	public void inicializarComponentes(){
		lblParametros= new JLabel("Parámetro");
		lblParametros.setBounds(30,30,100,25);
		
		boxParametros = new JComboBox<TipoTablaEnum>();
		boxParametros.setBounds(130, 30, 100, 25);

		for (TipoTablaEnum tipoTabla : TipoTablaEnum.values()) {
			boxParametros.addItem(tipoTabla);
		}

		Image mostrar = new ImageIcon(".\\imagenes\\mostrarInventario.jpg").getImage();
		ImageIcon imageMostrar = new ImageIcon(mostrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnMostrar= new JButton("Mostrar");
		btnMostrar.setIcon(imageMostrar);
		btnMostrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnMostrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnMostrar.setBounds(270, 15, 100, 60);
		btnMostrar.addActionListener(this.controladorInventario);
		btnMostrar.setActionCommand(ConstantesInterfaz.MOSTRAR_TABLA_PARAMETROS);
		
		Image cerrar = new ImageIcon(".\\imagenes\\cerrar.jpg").getImage();
		ImageIcon imageCerrar = new ImageIcon(cerrar.getScaledInstance(30,30,Image.SCALE_DEFAULT));
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(imageCerrar);
		btnCerrar.setHorizontalTextPosition( SwingConstants.CENTER );
		btnCerrar.setVerticalTextPosition( SwingConstants.BOTTOM );
		btnCerrar.setBounds(400, 15, 100, 60);
		btnCerrar.addActionListener(this.controladorInventario);
		btnCerrar.setActionCommand(ConstantesInterfaz.CERRAR_PARAMETROS);

		this.add(lblParametros);
		this.add(boxParametros);
		this.add(btnMostrar);
		this.add(btnCerrar);
	}
	private void createJtable() {
		jtTable = new JTable();
		this.dtm = (DefaultTableModel) jtTable.getModel();
		JScrollPane scrollPane = new JScrollPane(jtTable);
		this.add(scrollPane);
		scrollPane.setBounds(50, 100, 500, 250);
	}

	public void addValueJTable(Marca marca) {	
		clearTable();
		dtm.setRowCount(dtm.getRowCount() + 1);		
		dtm.setValueAt(marca.getIdMarca(), dtm.getRowCount() - 1, 0);
		dtm.setValueAt(marca.getNombre().getValorCampo(), dtm.getRowCount() - 1, 1);		
		jtTable.getColumn("Editar").setCellEditor(new BotonEditarMarca(new JCheckBox(),controladorInventario));
		//jtTable.getColumn("Eliminar").setCellEditor(new BotonEliminarInventario(new JCheckBox(),controladorInventario));
	}

	public void setColumnJtable() {
		DefaultTableModel dtm = (DefaultTableModel) jtTable.getModel();
		dtm.addColumn("Identificador");
		dtm.addColumn("Nombre");
		dtm.addColumn("Editar");
//		dtm.addColumn("Eliminar");
	}

	public void adicionarMarca(List<Marca> marcas) {
		clearTable();
		for (int i = 0; i < marcas.size(); i++) {
			addValueJTable(marcas.get(i));
		}
		this.repaint();


	}
	public void addValueJTable(Categoria categoria) {	
		clearTable();
		dtm.setRowCount(dtm.getRowCount() + 1);		
		dtm.setValueAt(categoria.getIdCategoria(), dtm.getRowCount() - 1, 0);
		dtm.setValueAt(categoria.getNombre().getValorCampo(), dtm.getRowCount() - 1, 1);		
		jtTable.getColumn("Editar").setCellEditor(new BotonEditarCategoria(new JCheckBox(),controladorInventario));
//		jtTable.getColumn("Eliminar").setCellEditor(new BotonEliminarInventario(new JCheckBox(),controladorInventario));

	}
	public void adicionarCategoria(List<Categoria> categorias) {
		clearTable();
		for (int i = 0; i < categorias.size(); i++) {
			addValueJTable(categorias.get(i));
		}
		this.repaint();
	}
	
	public void addValueJTable(Tamanio tamanio) {	
		clearTable();
		dtm.setRowCount(dtm.getRowCount() + 1);		
		dtm.setValueAt(tamanio.getIdTamanio(), dtm.getRowCount() - 1, 0);
		dtm.setValueAt(tamanio.getNombre().getValorCampo(), dtm.getRowCount() - 1, 1);		
		jtTable.getColumn("Editar").setCellEditor(new BotonEditarTamanio(new JCheckBox(),controladorInventario));
//		jtTable.getColumn("Eliminar").setCellEditor(new BotonEliminarTamanio(new JCheckBox(),controladorInventario));

	}
	public void adicionarTamanio(List<Tamanio> tamanios) {
		clearTable();
		for (int i = 0; i < tamanios.size(); i++) {
			addValueJTable(tamanios.get(i));
		}
		this.repaint();
	}
	
	public void addValueJTable(Origen origen) {	
		clearTable();
		dtm.setRowCount(dtm.getRowCount() + 1);		
		dtm.setValueAt(origen.getIdOrigen(), dtm.getRowCount() - 1, 0);
		dtm.setValueAt(origen.getNombre().getValorCampo(), dtm.getRowCount() - 1, 1);		
		jtTable.getColumn("Editar").setCellEditor(new BotonEditarOrigen(new JCheckBox(),controladorInventario));
		//jtTable.getColumn("Eliminar").setCellEditor(new BotonEliminarInventario(new JCheckBox(),controladorInventario));

	}
	public void adicionarOrigen(List<Origen> origen) {
		clearTable();
		for (int i = 0; i < origen.size(); i++) {
			addValueJTable(origen.get(i));
		}
		this.repaint();
	}
	
	public void addValueJTable(TipoProducto tipo) {	
		clearTable();
		dtm.setRowCount(dtm.getRowCount() + 1);		
		dtm.setValueAt(tipo.getIdTipo(), dtm.getRowCount() - 1, 0);
		dtm.setValueAt(tipo.getNombre().getValorCampo(), dtm.getRowCount() - 1, 1);		
		jtTable.getColumn("Editar").setCellEditor(new BotonEditarTipo(new JCheckBox(),controladorInventario));
//		jtTable.getColumn("Eliminar").setCellEditor(new BotonEliminarInventario(new JCheckBox(),controladorInventario));

	}
	public void adicionarTipo(List<TipoProducto> tipo) {
		clearTable();
		for (int i = 0; i < tipo.size(); i++) {
			addValueJTable(tipo.get(i));
		}
		this.repaint();
	}
	
	private void clearTable() {
		int aux=dtm.getRowCount();
		for(int i=0; i<aux;i++){
			dtm.removeRow(0);
		}
	}

	public JComboBox<TipoTablaEnum> getBoxParametros() {
		return boxParametros;
	}

	public void setBoxParametros(JComboBox<TipoTablaEnum> boxParametros) {
		this.boxParametros = boxParametros;
	}
	
}
