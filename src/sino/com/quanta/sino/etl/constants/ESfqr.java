package com.quanta.sino.etl.constants;

/**
 * <p>
 * 备用卷是否发送卷板信息到PC400 0:未确认1：已确认
 * </p>
 * <p>
 * create: 2011-1-18 上午08:42:49
 * </p>
 * 
 * @author 肖俊勇
 * @version 1.0
 */
public enum ESfqr {

	WQR(ESfqr.KEY_WQR, "未确认"), YQR(ESfqr.KEY_YQR, "已确认");

	/**
	 * 0-未确认
	 */
	public static final String KEY_WQR = "0";
	/**
	 * 1-已确认
	 */
	public static final String KEY_YQR = "1";

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
	ESfqr(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static ESfqr get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (ESfqr scbj : values()) {
			if (scbj.key.equals(key)) {
				return scbj;
			}
		}
		return null;
	}

}
