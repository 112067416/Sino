package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 付款发票品种
 * </p>
 * <p>
 * create: 2011-8-13 下午10:35:33
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EFppz {

	zp("0", "正品"), hz("1", "红字");

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
	EFppz(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static EFppz get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EFppz fpyc : values()) {
			if (fpyc.key.equals(key)) {
				return fpyc;
			}
		}
		return null;
	}

}
