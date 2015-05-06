/**
 * 
 */
package com.quanta.sino.fxs.vo;

/**
 * <p>
 * 涂油量检验范围
 * </p>
 * <p>
 * create: 2011-5-23 上午11:19:29
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum JTyl {

	/**
	 * 
	 */
	D20("D", "20", 0.5, 3.5, "0.5 <= x <= 3.5"),

	/**
	 * 
	 */
	D30("D", "30", 1.5, 4.5, "0.5 <= x <= 4.5"),

	/**
	 * 
	 */
	D40("D", "40", 2.5, 5.5, "2.5 <= x <= 5.5"),

	/**
	 * 
	 */
	D50("D", "50", 3.5, 6.5, "3.5 <= x <= 6.5"),

	/**
	 * 
	 */
	D60("D", "60", 4.5, 7.5, "4.5 <= x <= 7.5"),

	/**
	 * 
	 */
	D80("D", "80", 5.0, 15.0, "5 <= x <= 15"),

	/**
	 * 
	 */
	$D80("D", "$80", 3.0, 10.0, "3 <= x <= 10");

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

	JTyl(String type, String name, double min, double max, String description) {
		this.type = type;
		this.name = name;
		this.min = min;
		this.max = max;
		this.description = description;
	}

	public static JTyl get(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		for (JTyl bz : values()) {
			if (bz.name.equals(name)) {
				return bz;
			}
		}
		return null;
	}
}
