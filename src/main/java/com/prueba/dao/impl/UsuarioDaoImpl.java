package com.prueba.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prueba.constant.Constant;
import com.prueba.dao.UsuarioDao;
import com.prueba.model.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Integer validarUsuarioExiste(String dni) {
		return this.jdbcTemplate.queryForObject(Constant.SELECT_COUNT_USUARIO, new Object[] { dni }, Integer.class);
	}

	@Override
	public List<Usuario> listarUsuario() {
		return this.jdbcTemplate.query(Constant.SELECT_LS_USUARIO, Constant.MAPPER_USUARIO);
	}

	@Override
	public Usuario obtenerUsuario(String dni) {
		return this.jdbcTemplate.queryForObject(Constant.SELECT_USUARIO, new Object[] { dni },
				new BeanPropertyRowMapper<Usuario>(Usuario.class));
	}

	@Override
	public int registrarUsuario(Usuario usuario) {
		return this.jdbcTemplate.update(Constant.INSERT_USUARIO, usuario.dni, usuario.nombre, usuario.apellido,
				usuario.edad);
	}

	@Override
	public int actualizarUsuario(Usuario usuario) {
		return this.jdbcTemplate.update(Constant.UPDATE_USUARIO, usuario.nombre, usuario.apellido, usuario.edad,
				usuario.dni);
	}

	@Override
	public int eliminarUsuario(String dni) {
		return this.jdbcTemplate.update(Constant.DELETE_USUARIO, dni);
	}

}
