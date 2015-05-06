package com.quanta.sino.dh.constants;

/**
 * <p>
 * 订货合同化成处理定义
 * </p>
 * <p>
 * create: 2011-1-18 上午09:47:11
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public enum DhHc {

	/**
	 * 化成（化成处理）无电解Chromate处理——300
	 */
	HC_WDJ("300", "无电解Chromate处理"),

	/**
	 * 化成（化成处理）电解Chromate处理——311
	 */
	HC_DJ("311", "电解Chromate处理");

	public final String stat;

	public final String name;

	DhHc(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static DhHc get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (DhHc zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
