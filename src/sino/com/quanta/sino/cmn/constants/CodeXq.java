package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 星期
 * </p>
 * <p>
 * create: 2011-4-9 下午03:13:26
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum CodeXq {

	/**
	 * 星期日
	 */
	SUNDAY("1", "日"),

	/**
	 * 星期一
	 */
	MONDAY("2", "一"),

	/**
	 * 星期二
	 */
	TUESDAY("3", "二"),

	/**
	 * 星期三
	 */
	WEDNESDAY("4", "三"),

	/**
	 * 星期四
	 */
	THURSDAY("5", "四"),

	/**
	 * 星期五
	 */
	FRIDAY("6", "五"),

	/**
	 * 星期六
	 */
	SATURDAY("7", "六");

	public final String key;

	public final String name;

	CodeXq(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static CodeXq get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeXq value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}

}
