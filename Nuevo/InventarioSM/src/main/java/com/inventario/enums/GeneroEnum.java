package com.inventario.enums;

public enum GeneroEnum {
DAMA,HOMBRE,NIÑO,NIÑA;
	
	public static GeneroEnum convertirTipo(String tipo){
		GeneroEnum genero=null;
		if(tipo.equalsIgnoreCase(DAMA.name())){
			genero=DAMA;
		}else if(tipo.equalsIgnoreCase(HOMBRE.name())){
			genero=HOMBRE;
		}else if(tipo.equalsIgnoreCase(NIÑO.name())){
			genero=NIÑO;
		}else if(tipo.equalsIgnoreCase(NIÑA.name())){
			genero=NIÑA;
		}
		return genero;
	}
}
