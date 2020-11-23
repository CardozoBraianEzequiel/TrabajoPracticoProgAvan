package com.ar;

import com.ar.servicios.Consultas;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alumno a = new Alumno();
		
		Consultas.guardar(a);
		a.setId(1);
		a.setNombre("Braian");
		a.setApellido("Cardozo");
		a.setLegajo(102857);
	
		Consultas.modificar(a);
	}

}
