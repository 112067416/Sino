/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 原板生产状态（工控系统）
 * </p>
 * <p>
 * create: 2011-1-11 上午10:31:54
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum YbScStat {

	/**
	 * 初始
	 */
	stat0("0", "初始"),

	/**
	 * 待生产
	 */
	stat1("1", "待生产"),

	/**
	 * 正在生产
	 */
	stat2("2", "正在生产"),

	/**
	 * 生产完成
	 */
	stat3("3", "生产完成"), ;

	public final String stat;

	public final String name;

	YbScStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static YbScStat get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (YbScStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
