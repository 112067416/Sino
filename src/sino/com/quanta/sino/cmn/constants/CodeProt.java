package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 保护板
 * </p>
 * <p>
 * create: 2011-2-14 下午04:21:28
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum CodeProt {

	/**
	 * 上下部
	 */
	W("W", "上下部"),

	/**
	 * 仅上部
	 */
	T("T", "仅上部"),

	/**
	 * 不要
	 */
	N("N", "不要");

	public final String key;

	public final String name;

	CodeProt(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static CodeProt get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeProt value : values()) {
			if (value.key.equalsIgnoreCase(key)) {
				return value;
			}
		}
		return null;
	}

}
