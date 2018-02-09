package com.inventario.utilidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CargaPropiedades {
	private static CargaPropiedades instance = null;
	private static Properties prop;

	private CargaPropiedades(String ubicacionProperties) throws Exception {
		iniciarProperties(ubicacionProperties);
	}

	private static void iniciarProperties(String ubicacionProperties) throws Exception {
		prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(ubicacionProperties);
			prop.load(input);
		} finally {
			if (input != null) {
				input.close();
			}

		}
	}

	public static String obtenerPropiedad(String propiedad) {
		return prop.getProperty(propiedad);
	}

	public static CargaPropiedades getInstance(String ubicacionProperties) throws Exception {
		if (instance == null) {
			instance = new CargaPropiedades(ubicacionProperties);
		}
		return instance;
	}


}
