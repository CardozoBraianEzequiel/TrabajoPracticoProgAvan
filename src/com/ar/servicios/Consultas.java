package com.ar.servicios;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.ar.anotaciones.Columna;
import com.ar.anotaciones.Tabla;
import com.ar.utilidades.UBean;

public class Consultas {
	public void guardar(Object o) {
		String consulta = "insert into ";
		String nombreTabla = o.getClass().getAnnotation(Tabla.class).nombre();
		
		consulta = consulta + nombreTabla + " (";
		ArrayList<Field> attrs = UBean.obtenerAtributos(o);
		
		for(Field attr: attrs) {
			consulta = consulta + attr.getAnnotation(Columna.class).nombre() + ",";
		}
		
		consulta = consulta.substring(0, consulta.length()-1);
		
		consulta = consulta += ") values (";
		
		for(Field a:attrs) {
			consulta = consulta += "?,";
		}
		
		consulta = consulta.substring(0, consulta.length()-1);
		consulta = consulta += ")";
		
		System.out.println(consulta);
		
		
	}
}
