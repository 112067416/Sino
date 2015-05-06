/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 基板订购单状态
 * </p>
 * <p>
 * create: 2011-1-11 上午10:31:54
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum JbddItemStat {

	/**
	 * 初始
	 */
	CS("0", "初始"),

	/**
	 * 已制作
	 */
	YZZ("1", "已制作");

	public final String stat;

	public final String name;

	JbddItemStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static JbddItemStat get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (JbddItemStat zt : values()) {
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
		for (JbddItemStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt.name;
			}
		}
		return null;
	}
}
