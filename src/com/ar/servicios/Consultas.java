package com.ar.servicios;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.ar.anotaciones.Columna;
import com.ar.anotaciones.Id;
import com.ar.anotaciones.Tabla;
import com.ar.utilidades.UBean;

public class Consultas {
	
	public static void guardar(Object o) {
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
	
	public static void modificar(Object o) {
		String consulta = "update ";
		String nombreTabla = o.getClass().getAnnotation(Tabla.class).nombre();
		String idTabla = "";
		consulta = consulta + nombreTabla + " set ";
		
		ArrayList<Field> attrs = UBean.obtenerAtributos(o);
		
		for(Field attr: attrs) {

			if(attr.isAnnotationPresent(Id.class)) {
				idTabla = UBean.ejecutarGet(o, attr.getName()).toString();
			} else {
				consulta += attr.getAnnotation(Columna.class).nombre() + "=";
				consulta += "'" + UBean.ejecutarGet(o, attr.getName())+ "' ";	
			}
		}
		
		consulta += "where id=" + idTabla;
		
		System.out.println(consulta);
		
	}
	
	public static void eliminar(Object o) {
		String consulta = "delete from ";
		String nombreTabla = o.getClass().getAnnotation(Tabla.class).nombre();
		String idTabla = "";
		
		consulta = consulta + nombreTabla + " where id=";
		
		ArrayList<Field> attrs = UBean.obtenerAtributos(o);
		
		for(Field attr: attrs) {

			if(attr.isAnnotationPresent(Id.class)) {
				idTabla = UBean.ejecutarGet(o, attr.getName()).toString();
			} 
		}
		
		consulta = consulta + idTabla;
		
		System.out.println(consulta);
		
	}
	
	
}
