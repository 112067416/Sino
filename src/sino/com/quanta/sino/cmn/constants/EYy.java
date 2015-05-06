package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 压延方向
 * </p>
 * <p>
 * create: 2011-1-18 上午08:42:13
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum EYy {

	/**
	 * <p>
	 * 压延方向：1-短边压延方向
	 * </p>
	 */
	YY_1("1", "短边"),

	/**
	 * <p>
	 * 压延方向：2-长边压延方向
	 * </p>
	 */
	YY_2("2", "长边");

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
	EYy(String key, String memo) {
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
	public static EYy get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EYy e : values()) {
			if (e == null) {
				continue;
			}
			if (e.key.equalsIgnoreCase(key)) {
				return e;
			}
		}
		return null;
	}
}
