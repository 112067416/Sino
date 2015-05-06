/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 打印标记
 * </p>
 * <p>
 * create: 2011-2-21 下午11:09:12
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Dybs {

	/**
	 * 未打印
	 */
	WDY("N", "未打印"),

	/**
	 * 已打印
	 */
	YDY("Y", "已打印");

	public final String key;

	public final String name;

	Dybs(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static Dybs get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Dybs zt : values()) {
			if (key.equals(zt.key)) {
				return zt;
			}
		}
		return null;
	}
}
