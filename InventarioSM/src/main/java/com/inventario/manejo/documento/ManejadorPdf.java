package com.inventario.manejo.documento;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.inventario.enums.GeneroEnum;
import com.inventario.enums.TipoInventarioEnum;
import com.inventario.esquema.Categoria;
import com.inventario.esquema.Inventario;
import com.inventario.esquema.InventarioPeriodico;
import com.inventario.esquema.Marca;
import com.inventario.esquema.Origen;
import com.inventario.esquema.Producto;
import com.inventario.esquema.Tamanio;
import com.inventario.esquema.TipoProducto;
import com.inventario.utilidades.Constantes;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ManejadorPdf {

	private static String FILE = "documentos/FirstPdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static java.util.List<InventarioPeriodico> listaProductosInventario;
    private static Inventario inventario;
    static Locale locale = new Locale("es","AR"); // elegimos Argentina
    static NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
    public ManejadorPdf(java.util.List<InventarioPeriodico> listaProductosInventario) {
		this.listaProductosInventario=listaProductosInventario;
		this.inventario=listaProductosInventario.get(0).getInventario();
	}
    
    public static void main(String[] args) {
        try {
        	java.util.List<InventarioPeriodico> listaProductosInventario = new ArrayList<InventarioPeriodico>();
        	Producto p = new Producto("Heiress", new Categoria(1, "Categoria"), GeneroEnum.DAMA, new TipoProducto(1, "Tipo"), new Marca(1, "Paris Hilton"),"120000", new Tamanio(1, "100 ml"), new Origen(1,"Origen"), "5", "180000");
        	Inventario i= new Inventario( TipoInventarioEnum.LOCAL, new Date());
        	InventarioPeriodico n = new InventarioPeriodico(5, p, i);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	listaProductosInventario.add(n);
        	
        	ManejadorPdf manejador = new ManejadorPdf(listaProductosInventario);
            Document document = new Document(PageSize.A4.rotate(), 36, 72, 100, 50);
            File a = new File(FILE);
            System.out.println(a.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
            EncabezadoPDF encabezado = new EncabezadoPDF();
            writer.setPageEvent(encabezado);
            document.open();
//            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        UtilidadesPDF.adicionarLineaVacia(preface, 2);
        StringBuilder tipoInventario= new StringBuilder("Tipo de inventario: ");
        tipoInventario.append(inventario.getTipoInventario());
        StringBuilder fechaInventario= new StringBuilder("Fecha de inventario: ");
        fechaInventario.append(Constantes.FORMATO_FECHA_PDF.format(inventario.getFechaInventario()));
        preface.add(new Paragraph(tipoInventario.toString(), catFont));
        UtilidadesPDF.adicionarLineaVacia(preface, 1);
        preface.add(new Paragraph(fechaInventario.toString(), catFont));
        preface.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(preface);
    }

    private static void addContent(Document document) throws DocumentException {
    	
        Paragraph subPara = new Paragraph();
        UtilidadesPDF.adicionarLineaVacia(subPara, 2);
        subPara.add(new Paragraph("Detalle Inventario", subFont));
        subPara.setAlignment(Paragraph.ALIGN_CENTER);
        UtilidadesPDF.adicionarLineaVacia(subPara, 2);
        
        document.add(subPara);
        PdfPTable table = new PdfPTable(8);                
        
		PdfPCell c1 = new PdfPCell(new Phrase("Nombre",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Marca",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Tamaño",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Tipo",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Genero",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Cantidad",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Precio unitario",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Precio total",smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		for (InventarioPeriodico producto : listaProductosInventario) {
			Producto unidad = producto.getProducto();
			c1 = new PdfPCell(new Phrase(unidad.getNombreProducto().getValorCampo()));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(unidad.getMarca().getNombre().getValorCampo()));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(unidad.getTamanio().getNombre().getValorCampo()));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(unidad.getTipo().getNombre().getValorCampo()));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(unidad.getGenero().name()));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(producto.getCantidad().toString()));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(nf.format(unidad.getPrecioCompra())));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(nf.format(unidad.getPrecioCompra())));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

		}
         
        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Final de la tabla"));
         
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(2);
        table.addCell(celdaFinal);
         
        // Agregamos la tabla al documento            
        document.add(table);
        

    }

    
}
