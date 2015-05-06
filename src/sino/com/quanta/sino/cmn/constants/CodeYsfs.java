package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 出货运输方式
 * </p>
 * <p>
 * create: 2011-2-15 下午02:39:27
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum CodeYsfs {

	/**
	 * 货柜海运
	 */
	HGHY("1", "货柜海运"),

	/**
	 * 散货海运
	 */
	SHHY("2", "散货海运"),

	/**
	 * 公路运输
	 */
	GLYS("3", "公路运输"),

	/**
	 * 铁路运输
	 */
	TLYS("4", "铁路运输"),

	/**
	 * 出口
	 */
	CK("5", "出口"),

	/**
	 * 自提
	 */
	ZT("6", "自提");

	/**
	 * 码表Key
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	CodeYsfs(String key, String name) {
		this.key = key;
		this.name = name;

	}

	public static CodeYsfs get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (CodeYsfs value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
