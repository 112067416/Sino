/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 马口铁分析状态
 * </p>
 * <p>
 * create: 2011-5-9 上午09:42:43
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum MktfxStat {

	/**
	 * 初始
	 */
	CS("0", "未检验"),

	/**
	 * 合格
	 */
	HG("1", "合格"),

	/**
	 * 不合格
	 */
	BHG("2", "不合格");

	/**
	 * 状态值
	 */
	public final String stat;

	/**
	 * 名称
	 */
	public final String name;

	MktfxStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static MktfxStat get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (MktfxStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}

	public static String getName(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (MktfxStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt.name;
			}
		}
		return null;
	}
}
