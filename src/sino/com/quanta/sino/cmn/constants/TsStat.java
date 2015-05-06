/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 出货制品投诉状态
 * </p>
 * <p>
 * create: 2011-2-24 上午09:34:18
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum TsStat {

	/**
	 * 初始
	 */
	CS("0", "初始"),
	/**
	 * 投诉
	 */
	TS("1", "投诉"),

	/**
	 * 退货
	 */
	TH("2", "退货"),

	/**
	 * 厂外转卖
	 */
	CWZM("3", "厂外转卖"),

	/**
	 * 撤销投诉
	 */
	CX("4", "撤销投诉");

	public final String stat;

	public final String name;

	TsStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static TsStat get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (TsStat zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
