package com.prueba.dao;

import java.util.List;

import com.prueba.model.Usuario;

public interface UsuarioDao {
	public Integer validarUsuarioExiste(Integer dni);

	public List<Usuario> listarUsuario();

	public Usuario obtenerUsuario(Integer dni);

	public int registrarUsuario(Usuario usuario);

	public int actualizarUsuario(Usuario usuario);

	public int eliminarUsuario(int idusuario);
}
