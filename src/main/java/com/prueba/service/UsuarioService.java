package com.prueba.service;

import com.prueba.model.Usuario;

public interface UsuarioService {

	public Object listarUsuario();

	public Object obtenerUsuario(String id);

	public Object registrarUsuario(Usuario usuario);

	public Object actualizarUsuario(Usuario usuario);

	public Object eliminarUsuario(String idusuario);
}
