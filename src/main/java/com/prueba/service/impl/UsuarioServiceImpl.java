package com.prueba.service.impl;

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

		Mensaje mensaje = new Mensaje();
		Integer validate = usuarioDao.validarUsuarioExiste(dni);

		if (validate.equals(Constant.CODIGO_OK)) {
			Usuario usuario = (Usuario) usuarioDao.obtenerUsuario(dni);
			mensaje.setCodigo(Constant.CODIGO_OK);
			mensaje.setMensaje(usuario);
			UtilLog.logger(mensaje.toString(), UtilLog.LOG_INFO, this.getClass());
		} else {
			mensaje.setMensaje("Usuario no encontrado");
			UtilLog.logger(mensaje.toString(), UtilLog.LOG_INFO, this.getClass());
		}

		return mensaje;
	}

	@Override
	public Object registrarUsuario(Usuario usuario) {
		Mensaje mensaje = new Mensaje();
		Integer validate = usuarioDao.validarUsuarioExiste(usuario.dni);

		if (validate.equals(Constant.CODIGO_NONOK)) {
			List<String> validateusuario = Util.validarUsuario(usuario);

			if (validateusuario.size() > 0)
				mensaje.setMensaje(validateusuario);
			else {
				int result = usuarioDao.registrarUsuario(usuario);
				mensaje.setCodigo(result);
				mensaje.setMensaje(result == Constant.CODIGO_OK ? "Registrado correctamente" : "Error al registrar");
			}
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
			mensaje.setMensaje(result == Constant.CODIGO_OK ? "Editado correctamente." : "Error al Editar.");
		} else
			mensaje.setMensaje("El usuario con DNI " + usuario.dni + " no existe.");

		return mensaje;
	}

	@Override
	public Mensaje eliminarUsuario(int dni) {

		Mensaje mensaje = new Mensaje();
		Integer validateexist = usuarioDao.validarUsuarioExiste(dni);

		if (validateexist.equals(Constant.CODIGO_OK)) {
			int result = usuarioDao.eliminarUsuario(dni);
			mensaje.setCodigo(result);
			mensaje.setMensaje(result == Constant.CODIGO_OK ? "Eliminado correctamente." : "Error al Eliminar.");
		} else
			mensaje.setMensaje("Usuario a eliminar no existe.");

		return mensaje;
	}

}
