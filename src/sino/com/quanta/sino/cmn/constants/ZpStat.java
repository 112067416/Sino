/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 制品状态
 * </p>
 * <p>
 * create: 2011-1-11 上午10:31:54
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum ZpStat {

	/**
	 * 初始
	 */
	CS("0", "初始"),

	/**
	 * 实绩录入
	 */
	SJLR("1", "实绩录入"),

	/**
	 * 实绩终了
	 */
	SJZL("2", "实绩终了"),

	/**
	 * 实绩中止
	 */
	SJZZ("3", "实绩中止");

	public final String stat;

	public final String name;

	ZpStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static ZpStat get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (ZpStat zt : values()) {
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
		for (ZpStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt.name;
			}
		}
		return null;
	}
}
