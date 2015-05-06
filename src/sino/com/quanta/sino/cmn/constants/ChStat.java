/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 装箱指示书出货状态
 * </p>
 * <p>
 * create: 2011-2-21 下午11:09:12
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum ChStat {

	/**
	 * 初始
	 */
	WFH("0", "初始"),

	/**
	 * 已发货
	 */
	YFH("1", "已发货"),

	/**
	 * 作废
	 */
	ZF("2", "作废");

	public final String stat;

	public final String name;

	ChStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static ChStat get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (ChStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
