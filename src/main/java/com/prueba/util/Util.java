package com.prueba.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.model.Usuario;

public class Util {

	public static final String[] FIELD_VALIDATE = { "dni", "nombre", "apellido" };

	public static List<String> validarUsuario(Usuario usuario) {
		List<String> mensajes = new ArrayList<>();

		@SuppressWarnings("unchecked")
		Map<String, Object> fields = new ObjectMapper().convertValue(usuario, Map.class);

		for (String fieldvalidate : FIELD_VALIDATE) {
			UtilLog.logger(fieldvalidate.toString() + "=" + fields.get(fieldvalidate), UtilLog.LOG_INFO, Util.class);

			if (StringUtils
					.isEmpty((fields.get(fieldvalidate) == null ? "" : fields.get(fieldvalidate).toString().trim())))
				mensajes.add("campo " + StringUtils.capitalize(fieldvalidate) + " no debe estar vacio");
		}

		UtilLog.logger(mensajes.toString(), UtilLog.LOG_INFO, Util.class);

		return mensajes;
	}

}
