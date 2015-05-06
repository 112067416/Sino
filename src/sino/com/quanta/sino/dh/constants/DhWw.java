package com.quanta.sino.dh.constants;

/**
 * <p>
 * 订货合同外W（销售溶接部不可）定义
 * </p>
 * <p>
 * create: 2011-1-18 上午10:04:00
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public enum DhWw {
	/**
	 * 外W（销售溶接部不可）不可以保护——0
	 */
	WW_BKY("0", "不可以保护"),

	/**
	 * 外W（销售溶接部不可）可以一个——1
	 */
	WW_KYY("1", "可以一个"),

	/**
	 * 外W（销售溶接部不可）可以二个——2
	 */
	WW_KYE("2", "可以二个");

	public final String stat;

	public final String name;

	DhWw(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static DhWw get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (DhWw zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
