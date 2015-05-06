package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 处置代码[CODE106]
 * </p>
 * <p>
 * create: 2011-1-24 上午11:02:12
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum ECzdm {

	/**
	 * <p>
	 * 处置代码[1/2/3]：1
	 * </p>
	 */
	CZ_1("1"),

	/**
	 * <p>
	 * 处置代码[1/2/3]：2
	 * </p>
	 */
	CZ_2("2"),

	/**
	 * <p>
	 * 处置代码[1/2/3]：3
	 * </p>
	 */
	CZ_3("3"),
	/**
	 * <p>
	 * 处置代码[1/2/3]：4
	 * </p>
	 */
	CZ_4("4");

	public final String key;

	ECzdm(String key) {
		this.key = key;
	}

	/**
	 * <p>
	 * 根据键取对象
	 * </p>
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static ECzdm get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (ECzdm e : values()) {
			if (e != null && e.key.equalsIgnoreCase(key)) {
				return e;
			}
		}
		return null;
	}

}
