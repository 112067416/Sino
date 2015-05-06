package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 商品编号
 * </p>
 * <p>
 * create: 2011-6-15 上午10:53:23
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum CodeSpbh {

	/**
	 * 商品编号1
	 */
	spbh1("1", 0.1, 0.3, "0.1< =厚< 0.3"),

	/**
	 * 商品编号2
	 */
	spbh2("2", 0.3, 0.5, "0.3< =厚< 0.5"),

	/**
	 * 商品编号3
	 */
	spbh3("3", 0.5, 1d, "0.5< =厚< 1");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 最小值
	 */
	public final Double min;

	/**
	 * 最大值
	 */
	public final Double max;

	/**
	 * 说明
	 */
	public final String description;

	CodeSpbh(String key, Double min, Double max, String description) {
		this.key = key;
		this.min = min;
		this.max = max;
		this.description = description;

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
	public static CodeSpbh get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeSpbh value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}

	public static String getByHouu(Double houu) {
		if (houu == null || houu.doubleValue() == 0) {
			return null;
		}
		for (CodeSpbh value : values()) {
			if (value.min <= houu && value.max > houu) {
				return value.key;
			}
		}
		return null;
	}
}
