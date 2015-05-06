package com.quanta.sino.dh.constants;

/**
 * <p>
 * 订货合同差厚定义
 * </p>
 * <p>
 * create: 2011-1-18 上午09:41:58
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public enum DhCh {

	/**
	 * 差厚——A1
	 */
	CH_AO("A1", "A1差厚"),

	/**
	 * 差厚——A2
	 */
	CH_AT("A2", "A2差厚"),

	/**
	 * 差厚——D1
	 */
	CH_DO("D1", "D1差厚"),

	/**
	 * 差厚——D2
	 */
	CH_DT("D2", "D2差厚");

	public final String stat;

	public final String name;

	/**
	 * D-Mark标记
	 */
	public final static String D = "D";

	/**
	 * A-Mark标记
	 */
	public final static String A = "A";

	/**
	 * 薄面
	 */
	public final static String B = "1";

	/**
	 * 厚面
	 */
	public final static String H = "2";

	DhCh(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static DhCh get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (DhCh zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
