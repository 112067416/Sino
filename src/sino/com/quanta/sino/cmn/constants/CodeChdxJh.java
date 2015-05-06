package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 差厚镀锡面记号
 * </p>
 * <p>
 * create: 2011-2-14 上午09:39:32
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum CodeChdxJh {

	bao("1", "薄"), hou("2", "厚");

	public final String key;

	public final String name;

	CodeChdxJh(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static CodeChdxJh get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeChdxJh value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
