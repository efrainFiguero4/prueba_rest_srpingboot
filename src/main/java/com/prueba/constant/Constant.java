package com.prueba.constant;

import org.springframework.jdbc.core.RowMapper;

import com.prueba.model.Usuario;

public class Constant {
	public static final int CODIGO_NONOK = 0;
	public static final int CODIGO_OK = 1;

	/*
	 * CRUD TABLE USUARIO
	 */
	
	public static final String SELECT_LS_USUARIO = "SELECT * FROM USUARIO";
	public static final String SELECT_USUARIO = "SELECT * FROM USUARIO WHERE DNI = ?";
	public static final String SELECT_COUNT_USUARIO = "SELECT COUNT(1) FROM USUARIO WHERE DNI = ?";
	public static final String INSERT_USUARIO = "INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, EDAD) values ( ?, ?, ?, ?)";
	public static final String UPDATE_USUARIO = "UPDATE USUARIO SET NOMBRE = ?, APELLIDO=?, EDAD=? WHERE DNI = ?";
	public static final String DELETE_USUARIO = "DELETE FROM USUARIO WHERE DNI = ?";

	public static final RowMapper<Usuario> MAPPER_USUARIO = (rs, i) -> new Usuario(rs.getString("dni"),
			rs.getString("nombre"), rs.getString("apellido"), rs.getString("edad"));

}
