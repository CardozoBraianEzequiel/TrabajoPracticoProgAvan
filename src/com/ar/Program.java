package com.ar;

import java.util.ArrayList;

import com.ar.servicios.Consultas;
import com.ar.utilidades.Propiedades;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alumno a = new Alumno();
		
		
		a.setId(10);
		a.setNombre("German");
		a.setApellido("Denis");
		a.setLegajo(180234);
		
		//Alumno per = (Alumno) Consultas.guardar(a);
		
		//Consultas.eliminar(a);
		
		ArrayList<Object> array = Consultas.obtenerTodos(Alumno.class);
		
		for(Object obj:array) {
			System.out.println(obj.toString());
		}
		
		//Alumno per = (Alumno) Consultas.obtenerPorId(a.getClass(), a);
		//System.out.println(Consultas.obtenerPorId(Alumno.class, a));
		
	
	}

}
