package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 制品种类【品种代码第一位】
 * </p>
 * <p>
 * create: 2011-1-13 下午03:57:01
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum Code118 {

	mothor("0", "母板", "", ""), sheet("1", "剪切板", "TIN PLATE SHEET", "S"), coil(
			"2", "钢卷", "TIN PLATE COIL", "M"), scroll("3", "锯齿剪切板",
			"TIN PLATE SCROLL", "S");

	/**
	 * 码表Key
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	/**
	 * 商品名
	 */
	public final String ename;

	/**
	 * 长度单位
	 */
	public final String unit;

	Code118(String key, String name, String ename, String unit) {
		this.key = key;
		this.name = name;
		this.ename = ename;
		this.unit = unit;
	}

	public static Code118 get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Code118 value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
