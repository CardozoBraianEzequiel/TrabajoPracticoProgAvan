package com.ar;

import com.ar.anotaciones.*;

@Tabla(nombre="alumno")
public class Alumno {
	@Id
	@Columna(nombre="idAlumno")
	private int Id;
	@Columna(nombre="nombre")
	private String nombre;
	@Columna(nombre="apellido")
	private String apellido;
	@Columna(nombre="nroLegajo")
	private int legajo;
}
