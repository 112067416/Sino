package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 端板状态
 * </p>
 * <p>
 * create: 2011-11-25 下午08:59:43
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EDbzt {

	CS("0", "初始"), YCH("1", "已出货");

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
	EDbzt(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static EDbzt get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EDbzt scbj : values()) {
			if (scbj.key.equals(key)) {
				return scbj;
			}
		}
		return null;
	}

}
