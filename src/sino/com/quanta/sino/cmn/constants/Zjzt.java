package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 原板订购单追加状态
 * </p>
 * <p>
 * create: 2011-1-23 下午12:51:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Zjzt {

	/**
	 * 不追加
	 */
	no("0", "不追加"),

	/**
	 * 追加
	 */
	yes("1", "追加");

	public final String key;

	public final String name;

	Zjzt(String key, String name) {
		this.key = key;
		this.name = name;

	}

	public static Zjzt get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Zjzt value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
