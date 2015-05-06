package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 内径保护筒
 * </p>
 * <p>
 * create: 2011-2-14 下午04:21:28
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum CodeNjbh {

	/**
	 * 纸保护筒
	 */
	zhi("1", "纸保护筒"),

	/**
	 * 钢保护筒
	 */
	gang("2", "钢保护筒"),

	/**
	 * 不要
	 */
	N("N", "不要");

	public final String key;

	public final String name;

	CodeNjbh(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static CodeNjbh get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeNjbh value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}

}
