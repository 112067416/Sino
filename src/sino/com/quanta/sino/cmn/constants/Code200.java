/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 补正系数
 * </p>
 * <p>
 * create: 2011-3-2 下午03:10:21
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public enum Code200 {

	/**
	 * 补正系数0996
	 */
	BZXS1("1", "0996"),

	/**
	 * 补正系数0980
	 */
	BZXS2("2", "0980"),

	/**
	 * 补正系数0680
	 */
	BZXS3("3", "0680"),

	/**
	 * 补正系数0070
	 */
	BZXS4("4", "0070"),

	/**
	 * 补正系数0965
	 */
	BZXS5("5", "0965"),
	/**
	 * 补正系数0850
	 */
	BZXS6("6", "0850"),
	/**
	 * 补正系数0998
	 */
	BZXS7("7", "0998");

	public final String key;

	public final String name;

	Code200(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static Code200 get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Code200 value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
