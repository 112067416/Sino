/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 原板状态
 * </p>
 * <p>
 * create: 2011-1-11 上午10:31:54
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum YbStat {

	/**
	 * 初始
	 */
	CS("0", "初始"),

	/**
	 * 已入库
	 */
	YRK("1", "已入库"),

	/**
	 * 已分配
	 */
	YFP("2", "已分配"),

	/**
	 * 实绩录入
	 */
	SJLR("3", "实绩录入"),

	/**
	 * 实绩终了
	 */
	SJZL("4", "实绩终了"),

	/**
	 * 实绩中止
	 */
	SJZZ("5", "实绩中止");

	public final String stat;

	public final String name;

	YbStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static YbStat get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (YbStat zt : values()) {
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
		for (YbStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt.name;
			}
		}
		return null;
	}
}
