package com.prueba.model;

import lombok.Data;

@Data
public class Usuario {

	public int dni;
	public String nombre;
	public String apellido;
	public String edad;

	public Usuario() {
	}

	public Usuario(int dni, String nombre, String apellido, String edad) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}

	public Usuario(String nombre, String apellido, String edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}
}
