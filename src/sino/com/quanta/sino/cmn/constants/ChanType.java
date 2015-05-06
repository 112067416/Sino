package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 产量类型
 * </p>
 * <p>
 * create: 2011-1-18 下午03:23:36
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public enum ChanType {

	/**
	 * 合格
	 */
	hg("1", "合格"),
	/**
	 * 不合格
	 */
	bhg("3", "不合格"),
	/**
	 * 发生
	 */
	fs("4", "发生"),
	/**
	 * 端板
	 */
	ns("7", "端板"),
	/**
	 * 保留
	 */
	bl("9", "保留"),
	/**
	 * 废材
	 */
	fc("S", "S材");

	/**
	 * 码表Key
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	ChanType(String key, String name) {
		this.key = key;
		this.name = name;

	}

	public static ChanType get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (ChanType value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
