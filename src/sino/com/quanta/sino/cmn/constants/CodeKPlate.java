package com.quanta.sino.cmn.constants;

/**
 * <p>
 * K-Plate代码
 * </p>
 * <p>
 * create: 2011-2-16 上午11:53:21
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum CodeKPlate {
	/**
	 * <p>
	 * K
	 * </p>
	 */
	K("K", "K");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 说明
	 */
	public final String name;

	/**
	 * 构造
	 * 
	 * @param key
	 * @param value
	 */
	CodeKPlate(String key, String name) {
		this.key = key;
		this.name = name;
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
	public static CodeKPlate get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeKPlate e : values()) {
			if (e.key.equals(key)) {
				return e;
			}
		}
		return null;
	}

}
