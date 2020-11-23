package com.ar;

import com.ar.servicios.Consultas;
import com.ar.utilidades.Propiedades;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alumno a = new Alumno();
		
		Consultas.guardar(a);
		a.setId(1);
		a.setNombre("Jorge");
		a.setApellido("Meza");
		a.setLegajo(238900);
	
		Consultas.modificar(a);
		
		Consultas.eliminar(a);
		
		Alumno per = (Alumno) Consultas.obtenerPorId(a.getClass(), a);
		System.out.println(per.toString());
		
	
	}

}
