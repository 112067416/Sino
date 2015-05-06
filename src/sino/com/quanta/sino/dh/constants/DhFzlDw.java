package com.quanta.sino.dh.constants;

/**
 * <p>
 * 订货合同附着量单位定义
 * </p>
 * <p>
 * create: 2011-1-18 上午09:59:17
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public enum DhFzlDw {
	/**
	 * 附着量.单位——GM
	 */
	FZLDW_GM("GM", "GM单位"),
	/**
	 * 附着量.单位——WB
	 */
	FZLDW_WB("WB", "WB单位");

	public final String stat;

	public final String name;

	DhFzlDw(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static DhFzlDw get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (DhFzlDw zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
