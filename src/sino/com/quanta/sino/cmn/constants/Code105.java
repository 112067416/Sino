package com.quanta.sino.cmn.constants;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 等级代码（第三位）-形状
 * </p>
 * <p>
 * create: 2011-2-14 下午12:14:35
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum Code105 {

	/**
	 * A级
	 */
	A("A", "A级", new String[] { "A" }, 1),

	/**
	 * B级
	 */
	B("B", "B级", new String[] { "A", "B" }, 2),

	/**
	 * C级
	 */
	C("C", "C级", new String[] { "A", "B", "C" }, 3),

	/**
	 * D级
	 */
	D("D", "D级", new String[] { "A", "B", "C", "D" }, 4);

	/**
	 * S级
	 */
	// S("S", "S级", new String[] {}, 5);

	/**
	 * 码表Key
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	/**
	 * 有效值列表
	 */
	private final List<String> values;

	/**
	 * 级别值
	 */
	public final int value;

	Code105(String key, String name, String[] values, int value) {
		this.key = key;
		this.name = name;
		this.values = Arrays.asList(values);
		this.value = value;
	}

	public static Code105 get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Code105 value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 是否包含指定的值
	 * </p>
	 * 
	 * @param value
	 * @return boolean
	 */
	public boolean contains(String value) {
		return values.contains(value);
	}

	/**
	 * <p>
	 * 是否包含指定的值
	 * </p>
	 * 
	 * @param value
	 * @return boolean
	 */
	public boolean contains(char value) {
		return values.contains(String.valueOf(value));
	}
}
