/**
 * 
 */
package com.quanta.sino.fxs.vo;

/**
 * <p>
 * 铬检验范围
 * </p>
 * <p>
 * create: 2011-5-23 上午11:19:29
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum JCr {

	/**
	 * X
	 */
	huac1("311", 4d, 7.99, "4 <= x < 8"),

	/**
	 * 
	 */
	huac2("300", 0.01, 1.99, "0 < x < 2");

	/**
	 * 名称
	 */
	public final String name;

	/**
	 * 最小值
	 */
	public final double min;

	/**
	 * 最大值
	 */
	public final double max;

	/**
	 * 备注
	 */
	public final String description;

	JCr(String name, double min, double max, String description) {
		this.name = name;
		this.min = min;
		this.max = max;
		this.description = description;
	}

	public static JCr get(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		for (JCr bz : values()) {
			if (bz.name.equals(name)) {
				return bz;
			}
		}
		return null;
	}
}
