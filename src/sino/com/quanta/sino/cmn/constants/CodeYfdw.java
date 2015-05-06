package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 客户运费单位
 * </p>
 * <p>
 * create: 2011-2-16 上午09:28:47
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum CodeYfdw {
	/**
	 * RMB/吨
	 */
	rmbD("1", "RMB/吨"),

	/**
	 * RMB/柜
	 */
	rmbG("2", "RMB/柜"),

	/**
	 * USD/柜
	 */
	usdG("3", "USD/柜");

	public final String key;

	public final String name;

	CodeYfdw(String key, String name) {
		this.key = key;
		this.name = name;
	}
}
