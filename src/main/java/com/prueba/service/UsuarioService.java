package com.prueba.service;

import com.prueba.model.Usuario;

public interface UsuarioService {

	public Object listarUsuario();

	public Object obtenerUsuario(Integer id);

	public Object registrarUsuario(Usuario usuario);

	public Object actualizarUsuario(Usuario usuario);

	public Object eliminarUsuario(int idusuario);
}
