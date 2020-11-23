package com.ar;

import com.ar.servicios.Consultas;
import com.ar.utilidades.Propiedades;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alumno a = new Alumno();
		
		
		a.setId(10);
		a.setNombre("Arsenio");
		a.setApellido("Erico");
		a.setLegajo(201356);
		
		//Alumno per = (Alumno) Consultas.guardar(a);
		
		Consultas.eliminar(a);
		
		//Consultas.eliminar(a);
		
		//Alumno per = (Alumno) Consultas.obtenerPorId(a.getClass(), a);
		//System.out.println(Consultas.obtenerPorId(Alumno.class, a));
		
	
	}

}
