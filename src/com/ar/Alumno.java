package com.ar;

import com.ar.anotaciones.*;

@Tabla(nombre="tmt_alumno")
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
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNombre() {
		return nombre;
	}
	@Override
	public String toString() {
		return "Alumno [Id=" + Id + ", nombre=" + nombre + ", apellido=" + apellido + ", legajo=" + legajo + "]";
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	
	
}
