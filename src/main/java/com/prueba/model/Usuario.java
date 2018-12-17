package com.prueba.model;

import lombok.Data;

@Data
public class Usuario {

	public String dni;
	public String nombre;
	public String apellido;
	public String edad;
	public Boolean active = false;

	public Usuario() {
	}

	public Usuario(String dni, String nombre, String apellido, String edad) {
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
