package com.coco.core.exception;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * <p>
 * 异常消息读取器
 * </p>
 * <p>
 * create: 2010-12-21 上午09:37:21
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ExceptionMessageLoader {

	private ResourceBundle resourceBundle = null;

	private Map<String, String> keyValues = new HashMap<String, String>();

	private static ExceptionMessageLoader loader;

	private ExceptionMessageLoader(String classPath) {
		if (classPath == null || (classPath = classPath.trim()).length() == 0) {
			classPath = "coco-exception";
		}
		resourceBundle = ResourceBundle.getBundle(classPath);
	}

	public static void reload(String classPath) {
		if (loader == null) {
			loader = new ExceptionMessageLoader(classPath);
		}
		else {
			loader.resourceBundle = ResourceBundle.getBundle(classPath);
		}
		loader.keyValues.clear();
		Enumeration<String> keys = loader.resourceBundle.getKeys();
		if (keys != null) {
			String key, value;
			while (keys.hasMoreElements()) {
				key = keys.nextElement();
				value = loader.resourceBundle.getString(key);
				loader.keyValues.put(key, value);
			}
		}
	}

	public static String get(String key, String... args) {
		if (loader == null) {
			loader = new ExceptionMessageLoader(null);
		}
		if (loader == null || key == null) {
			return null;
		}
		String value = loader.keyValues.get(key);
		if (value == null) {
			return null;
		}
		if (args == null || args.length == 0) {
			return value;
		}
		for (int i = 0; i < args.length; i++) {
			value = value.replaceAll("\\{\\s*" + i + "\\s*\\}", args[i] != null ? args[i]
					: "");
		}
		return value.replaceAll("\\{\\s*\\d*\\s*\\}", "");
	}
}
