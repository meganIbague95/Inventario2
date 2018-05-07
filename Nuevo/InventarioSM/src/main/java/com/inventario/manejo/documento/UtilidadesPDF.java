package com.inventario.manejo.documento;

import com.itextpdf.text.Paragraph;

public class UtilidadesPDF {

	public static void adicionarLineaVacia(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
