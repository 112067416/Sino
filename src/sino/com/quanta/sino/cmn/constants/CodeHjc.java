package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 合金层
 * </p>
 * <p>
 * create: 2011-2-16 上午10:06:17
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public enum CodeHjc {
	/**
	 * <p>
	 * K值合金层或K-PLATE使用
	 * </p>
	 */
	K("K", "K"),

	/**
	 * <p>
	 * 5值合金层使用
	 * </p>
	 */
	F("5", "5");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 说明
	 */
	public final String memo;

	/**
	 * 构造
	 * 
	 * @param key
	 * @param value
	 */
	CodeHjc(String key, String memo) {
		this.key = key;
		this.memo = memo;
	}

	/**
	 * <p>
	 * 根据健取对象
	 * </p>
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static CodeHjc get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeHjc e : values()) {
			if (e.key.equals(key)) {
				return e;
			}
		}
		return null;
	}

}
