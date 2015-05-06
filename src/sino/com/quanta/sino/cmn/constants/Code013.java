package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 币种
 * </p>
 * <p>
 * create: 2011-7-20 下午09:52:05
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Code013 {

	rmb("1", "人民币"), usd("2", "美元"), gb("3", "港币");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 值
	 */
	public final String value;

	Code013(String key, String name) {
		this.key = key;
		this.value = name;

	}

	/**
	 * <p>
	 * 根据键值取对象
	 * </p>
	 * 
	 * @param key
	 *            键
	 * @return Code103：
	 */
	public static Code013 get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Code013 value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
