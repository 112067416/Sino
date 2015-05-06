/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 制品等级【品种代码第二位】
 * </p>
 * <p>
 * create: 2011-2-11 下午09:26:48
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Code119 {

	/**
	 * 一级品（合格品）
	 */
	prime("1", "PRIME"),

	/**
	 * O/R1级
	 */
	grade2("2", "GRADE-2"),

	/**
	 * O/R2级
	 */
	grade3("3", "GRADE-3"),

	/**
	 * 发 生品
	 */
	grade4("4", "GRADE-4"),

	/**
	 * 端板
	 */
	grade5("5", "GRADE-5");

	public final String key;

	public final String name;

	Code119(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static Code119 get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Code119 value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
