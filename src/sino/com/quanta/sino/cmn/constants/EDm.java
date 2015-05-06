package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 垫木方向
 * </p>
 * <p>
 * create: 2011-1-18 上午08:42:13
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum EDm {

	/**
	 * <p>
	 * 垫木方向：L-长边平行
	 * </p>
	 */
	L("L", "长边平行"),

	/**
	 * <p>
	 * 垫木方向：L-短边平行
	 * </p>
	 */
	C("C", "短边平行");

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
	EDm(String key, String memo) {
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
	public static EDm get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EDm e : values()) {
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
