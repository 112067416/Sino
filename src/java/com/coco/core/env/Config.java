package com.coco.core.env;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * <p>
 * 系统配置
 * </p>
 * <p>
 * create: 2010-12-21 上午09:20:13
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class Config {

	/**
	 * 超级管理员的键名
	 */
	private static final String KEY_ADMIN = "admin";

	/**
	 * 配置键和值列表
	 */
	private static final Map<String, String> PROPERTIES = new HashMap<String, String>();

	static {
		ResourceBundle rb = ResourceBundle.getBundle("coco-config");
		Enumeration<String> enumeration = rb.getKeys();
		String name, value;
		while (enumeration.hasMoreElements()) {
			name = enumeration.nextElement();
			value = rb.getString(name);
			if ((name = name.trim()).length() > 0) {
				if (value == null) {
					value = "";
				}
				else {
					value = value.trim();
				}
				PROPERTIES.put(name.toLowerCase(), value);
			}
		}
	}

	/**
	 * <p>
	 * 获取整型参数
	 * </p>
	 * 
	 * @param key
	 *            String
	 * @return int 若没有配置键名或者值为非数值时，则返回-1
	 */
	public final static int getInt(String key) {
		String value = getString(key);
		if (null == value || value.length() == 0) {
			return -1;
		}
		try {
			return Integer.parseInt(value);
		}
		catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * <p>
	 * 获取长整型参数
	 * </p>
	 * 
	 * @param key
	 * @return long 若没有配置键名或者值为非数值时，则返回-1
	 */
	public final static long getLong(String key) {
		String value = getString(key);
		if (null == value || value.length() == 0) {
			return -1l;
		}
		try {
			return Long.parseLong(value);
		}
		catch (NumberFormatException e) {
			return -1l;
		}
	}

	/**
	 * <p>
	 * 获取字符串参数
	 * </p>
	 * 
	 * @param key
	 * @return String
	 */
	public final static String getString(String key) {
		if (key == null) {
			return null;
		}
		return PROPERTIES.get(key);
	}

	/**
	 * <p>
	 * 获取字符串参数
	 * </p>
	 * 
	 * @param key
	 * @return String
	 */
	public final static String getValue(String key) {
		if (key == null) {
			return null;
		}
		return PROPERTIES.get(key);
	}

	/**
	 * <p>
	 * 判断是否是超级管理员
	 * </p>
	 * 
	 * @param userId
	 *            用户帐号
	 * @return boolean
	 */
	public final static boolean isAdmin(String userId) {
		if (userId == null || userId.isEmpty()) {
			return false;
		}
		return userId.equals(getString(KEY_ADMIN));
	}
}
