package com.quanta.sino.cmn.constants;


/**
 * <p>
 * 等级代码（第一位）-订货等级
 * </p>
 * <p>
 * create: 2011-2-14 下午12:14:35
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum Code103 {

	G("G", "PRIME"), K("K", "O/R"), P("P", "PRIME");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 值
	 */
	public final String value;

	Code103(String key, String name) {
		this.key = key;
		this.value = name;

	}

	/**
	 * <p>
	 * 根据键值取对象
	 * </p>
	 * 
	 * @param key
	 *            键
	 * @return Code103：
	 */
	public static Code103 get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Code103 value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
