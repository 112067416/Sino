package com.quanta.sino.dh.constants;

/**
 * <p>
 * 订货合同区分定义
 * </p>
 * <p>
 * create: 2011-1-18 上午09:44:47
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public enum DhQf {
	/**
	 * 区分——W
	 */
	QF_W("W", "区分W"),
	/**
	 * 区分——T
	 */
	QF_T("T", "区分T");

	public final String stat;

	public final String name;

	DhQf(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static DhQf get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (DhQf zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
