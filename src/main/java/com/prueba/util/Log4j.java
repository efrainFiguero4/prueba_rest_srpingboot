package com.prueba.util;

import lombok.Data;

@Data
public class Log4j {
	private String texto;
	private String type;
	private Class<?> className;

	public Log4j() {
	}

	public Log4j(String texto, String type, Class<?> className) {
		this.texto = texto;
		this.type = type;
		this.className = className;
	}
}
