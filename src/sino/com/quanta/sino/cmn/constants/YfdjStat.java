package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 运费单价状态
 * </p>
 * <p>
 * create: 2011-2-15 下午10:47:19
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum YfdjStat {

	/**
	 * 无效
	 */
	YX("0", "有效"),

	/**
	 * 无效
	 */
	WX("1", "无效");

	public final String stat;

	public final String name;

	YfdjStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}
}
