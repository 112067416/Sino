package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 涂油种类
 * </p>
 * <p>
 * create: 2011-2-14 上午11:50:41
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum CodeTyzl {

	DOS("D", "DOS", "40"), CSO("C", "CSO", "30"), OTHER("", "其他", "");

	public final String key;

	public final String name;

	public final String value;

	CodeTyzl(String key, String name, String value) {
		this.key = key;
		this.name = name;
		this.value = value;
	}

	public static CodeTyzl get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeTyzl value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}

	public static CodeTyzl getByName(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		for (CodeTyzl value : values()) {
			if (value.name.equals(name)) {
				return value;
			}
		}
		return null;
	}
}
