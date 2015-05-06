package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 分配/余材 用于标记现品是分配品,或者是余材
 * </p>
 * <p>
 * create: 2011-1-18 上午08:42:13
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum EFpyc {

	CS(EFpyc.KEY_CS, "初始"), FP(EFpyc.KEY_FP, "分配品"), YC(EFpyc.KEY_YC, "余材");

	/**
	 * 初始
	 */
	public static final String KEY_CS = "0";
	/**
	 * 分配品
	 */
	public static final String KEY_FP = "1";
	/**
	 * 余材
	 */
	public static final String KEY_YC = "2";

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
	EFpyc(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static EFpyc get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EFpyc fpyc : values()) {
			if (fpyc.key.equals(key)) {
				return fpyc;
			}
		}
		return null;
	}

}
