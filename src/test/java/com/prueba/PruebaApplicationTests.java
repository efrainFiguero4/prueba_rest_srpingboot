package com.prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.constant.Constant;
import com.prueba.controlller.UsuarioController;
import com.prueba.model.Mensaje;
import com.prueba.model.Usuario;
import com.prueba.util.UtilLog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PruebaApplicationTests {

	@Autowired
	UsuarioController usuarioController;

	@Test
	public void obtenerUsuario() {

		Object usuarios = usuarioController.listarUsuario();
		assertNotNull("is not null", usuarios);
		UtilLog.logger(usuarios.toString(), UtilLog.LOG_INFO, this.getClass());
		assertTrue("is valid", usuarios.toString() != null);

	}

	@Test
	@Transactional
	public void registrarUsuario() {
		Usuario usuario = new Usuario("Nuevo", "Nuevo Apellido", "23");

		Mensaje mensaje = (Mensaje) usuarioController.registrarUsuario(usuario);
		assertNotNull(mensaje.codigo);
		assertEquals(Constant.CODIGO_OK, mensaje.codigo);

		UtilLog.logger(mensaje.toString(), UtilLog.LOG_INFO, this.getClass());
	}

	@Test
	@Transactional
	public void actualizarUsuario() {
		Usuario usuario = new Usuario(2, "Editado", "Editar Apellido", "23");

		Mensaje mensaje = (Mensaje) usuarioController.actualizarUsuario(usuario);

		assertNotNull(mensaje.codigo);
		assertEquals(Constant.CODIGO_NONOK, mensaje.codigo);
		UtilLog.logger(mensaje.toString(), UtilLog.LOG_INFO, this.getClass());
	}

	@Test
	@Transactional
	public void EliminarUsuario() {
		int idusuario = 2;

		Mensaje mensaje = (Mensaje) usuarioController.eliminarUsuario(idusuario);
		UtilLog.logger(mensaje.toString(), UtilLog.LOG_INFO, this.getClass());
		assertNotNull(mensaje.codigo);
		assertEquals(Constant.CODIGO_NONOK, mensaje.codigo);
	}

}
