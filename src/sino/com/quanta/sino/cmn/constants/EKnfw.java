package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 尺寸.宽范围
 * </p>
 * <p>
 * create: 2011-7-13 上午10:48:26
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EKnfw {

	x("1", "700mm以下"), s("2", "700mm以上");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 值
	 */
	public final String value;

	EKnfw(String key, String name) {
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
	public static EKnfw get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EKnfw value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
