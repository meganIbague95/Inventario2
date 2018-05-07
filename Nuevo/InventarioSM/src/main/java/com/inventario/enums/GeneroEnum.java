package com.inventario.enums;

public enum GeneroEnum {
DAMA,HOMBRE,NI�O,NI�A;
	
	public static GeneroEnum convertirTipo(String tipo){
		GeneroEnum genero=null;
		if(tipo.equalsIgnoreCase(DAMA.name())){
			genero=DAMA;
		}else if(tipo.equalsIgnoreCase(HOMBRE.name())){
			genero=HOMBRE;
		}else if(tipo.equalsIgnoreCase(NI�O.name())){
			genero=NI�O;
		}else if(tipo.equalsIgnoreCase(NI�A.name())){
			genero=NI�A;
		}
		return genero;
	}
}
