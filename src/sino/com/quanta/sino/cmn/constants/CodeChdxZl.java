package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 差厚种类
 * </p>
 * <p>
 * create: 2011-2-14 上午09:39:32
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum CodeChdxZl {

	chemical("D", "Chemical Mark"), paniya("A", "Paniya Mark");

	public final String key;

	public final String name;

	CodeChdxZl(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static CodeChdxZl get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeChdxZl value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
