package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 客户运费单价设置情况
 * </p>
 * <p>
 * create: 2011-2-15 下午10:47:19
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum KhyfStat {

	/**
	 * 初始
	 */
	CS("0", "初始"),

	/**
	 * 已设置
	 */
	WC("1", "已设置");

	public final String stat;

	public final String name;

	KhyfStat(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}
}
