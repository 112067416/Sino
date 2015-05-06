/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 捆包张数.零头下限单位
 * </p>
 * <p>
 * create: 2011-2-12 下午08:48:07
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum CodeLtdw {

	/**
	 * 张数表示
	 */
	B("B", "张数"),

	/**
	 * 百分比表示
	 */
	P("P", "百分比"),

	/**
	 * 不指定
	 */
	N("N", "不指定");

	public final String key;

	public final String name;

	CodeLtdw(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static CodeLtdw get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeLtdw value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
