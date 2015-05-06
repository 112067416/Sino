package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 内外销码表
 * </p>
 * <p>
 * create: 2011-1-25 上午10:19:38
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum CodeNwx {

	nei("1", "国内", "", "1"), wai("2", "直接出口", "E", "2"), jeck("3", "直接出口", "J",
			"2");

	/**
	 * 码表Key
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	/**
	 * 前缀
	 */
	public final String prefix;

	/**
	 * 类型。区分是1:内销或2:外销
	 */
	public final String type;

	/**
	 * 内销
	 */
	public final static String NX = "1";

	/**
	 * 出口
	 */
	public final static String CK = "2";

	CodeNwx(String key, String name, String prefix, String type) {
		this.key = key;
		this.name = name;
		this.prefix = prefix;
		this.type = type;
	}

	public static CodeNwx get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeNwx value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}

	public static CodeNwx getByPrefix(String prefix) {
		if (prefix == null || prefix.isEmpty()) {
			return null;
		}
		for (CodeNwx value : values()) {
			if (prefix.equals(value.prefix)) {
				return value;
			}
		}
		return null;
	}
}
