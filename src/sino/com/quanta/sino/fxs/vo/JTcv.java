/**
 * 
 */
package com.quanta.sino.fxs.vo;

/**
 * <p>
 * TCV检验范围
 * </p>
 * <p>
 * create: 2011-5-23 上午11:19:29
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum JTcv {

	/**
	 * 5以下
	 */
	$25("25", "025", 0.01, 4.99, "0 < x < 5"),

	/**
	 * 5以下
	 */
	$50("50", "050", 0.01, 4.99, "0 < x < 5"),

	/**
	 * 3以下
	 */
	$75("75", "075", 0.01, 2.99, "0 < x < 3"),

	/**
	 * 2以下
	 */
	$100("100", "100", 0.01, 1.99, "0 < x < 2");

	/**
	 * 类型
	 */
	public final String type;

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

	JTcv(String type, String name, double min, double max, String description) {
		this.type = type;
		this.name = name;
		this.min = min;
		this.max = max;
		this.description = description;
	}

	public static JTcv get(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		for (JTcv bz : values()) {
			if (bz.name.equals(name)) {
				return bz;
			}
		}
		return null;
	}
}
