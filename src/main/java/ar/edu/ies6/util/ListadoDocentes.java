package ar.edu.ies6.util;

import java.util.ArrayList;
import java.util.List;

import ar.edu.ies6.model.Docente;

public class ListadoDocentes {
    private static List<Docente> listado2 = new ArrayList<>();
	
	public ListadoDocentes() {
		// TODO Auto-generated constructor stub
	}

	public static List<Docente> getListado2() {
		return listado2;
	}

	public static void setListado2(List<Docente> listado2) {
		ListadoDocentes.listado2 = listado2;
	}

}
