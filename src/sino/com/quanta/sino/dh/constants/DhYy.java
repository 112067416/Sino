package com.quanta.sino.dh.constants;

/**
 * <p>
 * 订货合同压延方向指示标志定义
 * </p>
 * <p>
 * create: 2011-1-18 上午09:51:33
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public enum DhYy {
	/**
	 * 压延（压延方向指示标志）短边——1
	 */
	YY_DB("1", "短边"),
	/**
	 * 压延（压延方向指示标志）长边——2
	 */
	YY_CB("2", "长边");

	public final String key;

	public final String name;

	DhYy(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static DhYy get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (DhYy zt : values()) {
			if (stat.equals(zt.key)) {
				return zt;
			}
		}
		return null;
	}
}
