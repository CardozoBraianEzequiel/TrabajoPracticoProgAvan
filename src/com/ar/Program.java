package com.ar;

import com.ar.servicios.Consultas;
import com.ar.utilidades.Propiedades;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alumno a = new Alumno();
		
		
		a.setId(10);
		a.setNombre("Sergio");
		a.setApellido("Aguero");
		a.setLegajo(256123);
		
		Alumno per = (Alumno) Consultas.guardar(a);
		
		//Consultas.modificar(a);
		
		//Consultas.eliminar(a);
		
		//Alumno per = (Alumno) Consultas.obtenerPorId(a.getClass(), a);
		System.out.println(per.toString());
		
	
	}

}
