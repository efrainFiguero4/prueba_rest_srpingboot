package com.prueba.service.impl;

import java.util.HashMap;
import java.util.List;

import com.prueba.constant.Constant;
import com.prueba.dao.UsuarioDao;
import com.prueba.model.Mensaje;
import com.prueba.model.Usuario;
import com.prueba.service.UsuarioService;
import com.prueba.util.Util;
import com.prueba.util.UtilLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;

	@Override
	public List<Usuario> listarUsuario() {
		UtilLog.logger("Inicia lista de usuario " + UtilLog.fechaActual(), UtilLog.LOG_INFO, this.getClass());
		return (List<Usuario>) usuarioDao.listarUsuario();
	}

	@Override
	public Object obtenerUsuario(Integer dni) {

		HashMap<String, Object> mensaje = new HashMap<String, Object>();
		mensaje.put("error_code", 1);

		Integer validate = usuarioDao.validarUsuarioExiste(dni);

		System.out.println(validate);

		if (validate.equals(Constant.CODIGO_OK)) {
			Usuario usuario = (Usuario) usuarioDao.obtenerUsuario(dni);
			mensaje.put("data", usuario);
			UtilLog.logger(mensaje.toString(), Constant.LOG_INFO, this.getClass());
		} else {
			mensaje.put("error_code", 0);
			mensaje.put("data", "Usuario no encontrado.");
			UtilLog.logger(mensaje.toString(), Constant.LOG_INFO, this.getClass());
		}

		return mensaje;
	}

	@Override
	public Object registrarUsuario(Usuario usuario) {
		Mensaje mensaje = new Mensaje();
		Integer validate = usuarioDao.validarUsuarioExiste(usuario.dni);
		List<String> validateusuario = Util.validarUsuario(usuario);

		if (validateusuario.size() > 0)
			mensaje.setMensaje(validateusuario);
		else if (validate.equals(Constant.CODIGO_NONOK)) {
			int result = usuarioDao.registrarUsuario(usuario);
			mensaje.setCodigo(result);
			mensaje.setMensaje(result == Constant.CODIGO_OK ? "Registrado correctamente" : "Error al registrar");
		} else
			mensaje.setMensaje("El usuario con DNI " + usuario.dni + " ya existe.");

		return mensaje;
	}

	@Override
	public Mensaje actualizarUsuario(Usuario usuario) {

		Mensaje mensaje = new Mensaje();
		Integer validate = usuarioDao.validarUsuarioExiste(usuario.dni);

		if (validate.equals(Constant.CODIGO_OK)) {
			int result = usuarioDao.actualizarUsuario(usuario);
			mensaje.setCodigo(result);
			mensaje.setMensaje(result == Constant.CODIGO_OK ? "Editado correctamente" : "Error al Editar");
		} else
			mensaje.setMensaje("El usuario con DNI " + usuario.dni + " no existe.");

		return mensaje;
	}

	@Override
	public Mensaje eliminarUsuario(int dni) {

		Mensaje mensaje = new Mensaje();
		int result = usuarioDao.eliminarUsuario(dni);

		mensaje.setCodigo(result);
		mensaje.setMensaje(result == Constant.CODIGO_OK ? "Eliminado correctamente" : "Error al Eliminar");

		return mensaje;
	}

}
