package com.ar;

import com.ar.servicios.Consultas;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alumno a = new Alumno();
		
		Consultas c = new Consultas();
		c.guardar(a);
	}

}
