package com.prueba.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilLog {

	private static Logger LOG;
	public static List<Log4j> LOG4J = new ArrayList<>();
	public static final String LOG_INFO = "INFO";
	public static final String LOG_DEBUG = "DEBUG";
	public static final String LOG_ERROR = "ERROR";

	public static String fechaActual() {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
	}

	public static String formatDate(Date date) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf1.format(date);
	}

	public static String fechaLog() {
		return new SimpleDateFormat("HH:mm:ss:SSS").format(new Date());
	}

	public static <T> void logger(String texto, String type, Class<?> classname) {
		Log4j log = new Log4j(texto, type, classname);
		print(log);
		UtilLog.LOG4J.add(log);
	}

	private static void print(Log4j l) {
		LOG = LoggerFactory.getLogger(l.getClassName());
		if (l.getType().equalsIgnoreCase(LOG_INFO)) {
			LOG.info("********** " + l.getTexto());
		} else if (l.getType().equalsIgnoreCase(LOG_DEBUG)) {
			LOG.debug("********** " + l.getTexto());
		} else if (l.getType().equalsIgnoreCase(LOG_ERROR)) {
			LOG.error("********** " + l.getTexto());
		}
	}

	public static void clearLogger() {
		LOG4J.clear();
	}

	public static byte[] getBytesLog() {
		StringBuilder sb = new StringBuilder();
		for (Log4j l : UtilLog.LOG4J) {
			sb.append(fechaLog() + " ");
			sb.append("[" + l.getType() + "]");
			sb.append("********** ");
			sb.append(l.getTexto());
			sb.append("\n");
		}
		return sb.toString().getBytes();
	}

	public static void logException(String texto, Exception e, Class<?> classname) {
		logger(texto + e.getMessage(), LOG_ERROR, classname);
		for (int i = 0; i < e.getStackTrace().length; i++) {
			logger(e.getStackTrace()[i].toString(), LOG_ERROR, classname);
		}
	}

}