package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 实绩品种等级
 * </p>
 * <p>
 * create: 2011-1-18 下午03:23:36
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public enum SjDeng {

	SJD1("1", ""), SJD2("2", ""), SJD3("3", ""), SJD4("4", ""), SJD5("5", "");

	/**
	 * 码表Key
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	SjDeng(String key, String name) {
		this.key = key;
		this.name = name;

	}

	public static SjDeng get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (SjDeng value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
