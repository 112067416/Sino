package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 订货合同是否匹配分配的现品信息
 * </p>
 * <p>
 * create: 2011-1-23 下午12:51:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Sfpp {

	/**
	 * 不匹配
	 */
	nmatch("0", "不匹配"),
	/**
	 * 匹配
	 */
	match("1", "匹配"),
	/**
	 * 非分配操作
	 */
	ndis("2", "非分配操作");

	public final String key;

	public final String name;

	Sfpp(String key, String name) {
		this.key = key;
		this.name = name;

	}

	public static Sfpp get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Sfpp value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
