package com.inventario.manejo.documento;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class EncabezadoPDF extends PdfPageEventHelper {
	private Image imagen;
	private PdfPTable table = new PdfPTable(2);
	protected PdfNumber orientation = PdfPage.PORTRAIT;

	public EncabezadoPDF() {
		PdfPCell celda1 = new PdfPCell(new Phrase("Industrias OverPass"));
		PdfPCell celda2 = new PdfPCell(new Phrase("Departamento de RH"));

		try {
			imagen = Image.getInstance("imagenes/encabezado.jpg");

			imagen.scaleAbsolute(150, 100);
			imagen.setAbsolutePosition(50, 460);

			celda1.setBorder(Rectangle.BOTTOM);
			celda2.setBorder(Rectangle.BOTTOM);
			celda2.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);

			table.addCell(celda1);
			table.addCell(celda2);

			table.setTotalWidth(350f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onStartPage(PdfWriter writer, Document document) {
		try {
			BaseFont bf = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte cb = writer.getDirectContent();
			cb.beginText();
			cb.setTextMatrix(400, 500);
			cb.setFontAndSize(bf, 18);
			cb.showText("INVENTARIO PERFUMERIA VALERIA");
			cb.endText();
			Paragraph preface1 = new Paragraph();
			UtilidadesPDF.adicionarLineaVacia(preface1, 4);
			document.add(preface1);
			document.add(imagen);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
