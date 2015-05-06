package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 图形报表类型
 * </p>
 * <p>
 * create: 2011-7-7 下午08:56:01
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Chart {

	/**
	 * 饼状图
	 */
	pie("PIE", "饼状图"),

	/**
	 * 柱状图
	 */
	bar("bar", "柱状图");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 值
	 */
	public final String name;

	Chart(String key, String name) {
		this.key = key;
		this.name = name;

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
	public static Chart get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Chart value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
