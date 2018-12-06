package com.prueba.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.model.Usuario;
import com.prueba.service.UsuarioService;
import com.prueba.util.UtilLog;

@RestController
@CrossOrigin
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@RequestMapping(value = "usuarios", method = { RequestMethod.GET })
	@ResponseStatus(value = HttpStatus.OK)
	public Object listarUsuario() {
		return usuarioService.listarUsuario();
	}

	@RequestMapping(value = "usuario", method = { RequestMethod.GET })
	@ResponseStatus(value = HttpStatus.OK)
	public Object obtenerUsuario(@RequestParam("id") Integer id) {
		return usuarioService.obtenerUsuario(id);
	}

	@RequestMapping(value = "usuario", method = { RequestMethod.POST })
	@ResponseStatus(value = HttpStatus.OK)
	public Object registrarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.registrarUsuario(usuario);
	}

	@RequestMapping(value = "usuario", method = { RequestMethod.PUT })
	@ResponseStatus(value = HttpStatus.OK)
	public Object actualizarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.actualizarUsuario(usuario);
	}

	@RequestMapping(value = "usuario/{idusuario}", method = { RequestMethod.DELETE })
	@ResponseStatus(value = HttpStatus.OK)
	public Object eliminarUsuario(@PathVariable("idusuario") int idusuario) {
		return usuarioService.eliminarUsuario(idusuario);
	}
	
	@RequestMapping(value = "logger", method = { RequestMethod.GET })
	@ResponseStatus(value = HttpStatus.OK)
	public Object obtenerLoggerUsuario() {
		return UtilLog.getBytesLog();
	}
	

}
