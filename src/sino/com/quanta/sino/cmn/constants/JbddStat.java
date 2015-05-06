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
public enum JbddStat {

	/**
	 * 未发送
	 */
	WFS("0", "未发送"),

	/**
	 * 已发送
	 */
	YFS("1", "已发送");

	public final String stat;

	public final String name;

	JbddStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static JbddStat get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (JbddStat zt : values()) {
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
		for (JbddStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt.name;
			}
		}
		return null;
	}
}
