/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 剪边
 * </p>
 * <p>
 * create: 2011-2-12 上午11:50:39
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum CodeJian {

	/**
	 * 剪边
	 */
	T("T", "剪边"),

	/**
	 * 不剪边
	 */
	N("N", "不剪边");

	public final String key;

	public final String name;

	CodeJian(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static CodeJian get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeJian value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}

}
