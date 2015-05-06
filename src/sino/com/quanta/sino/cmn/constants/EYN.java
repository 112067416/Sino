package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 记录加锁标识
 * </p>
 * <p>
 * create: 2011-10-18 下午04:40:55
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EYN {

	/**
	 * 已锁
	 */
	Y("Y", "已锁"),

	/**
	 * 未锁
	 */
	N("N", "未锁");

	/**
	 * 键
	 */
	public final String key;
	/**
	 * 值
	 */
	public final String value;

	/**
	 * 构造
	 * 
	 * @param key
	 * @param value
	 */
	EYN(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static EYN get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EYN scbj : values()) {
			if (scbj.key.equals(key)) {
				return scbj;
			}
		}
		return null;
	}

}
