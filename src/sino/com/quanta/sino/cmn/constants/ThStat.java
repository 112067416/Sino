/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 出货制品退货状态
 * </p>
 * <p>
 * create: 2011-2-24 上午09:34:18
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum ThStat {

	/**
	 * 退货
	 */
	TH("0", "退货"),

	/**
	 * 撤销退货
	 */
	CX("1", "撤销退货");

	public final String stat;

	public final String name;

	ThStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static ThStat get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (ThStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
