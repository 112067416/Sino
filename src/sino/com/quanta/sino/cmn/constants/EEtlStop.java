package com.quanta.sino.cmn.constants;

/**
 * <p>
 * ETL停线标记
 * </p>
 * <p>
 * create: 2011-12-9 下午03:57:42
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EEtlStop {

	/**
	 * 停机
	 */
	stop("stop", "停机"),

	/**
	 * 启动
	 */
	start("start", "启动");

	public final String key;

	public final String name;

	private EEtlStop(String key, String name) {
		this.key = key;
		this.name = name;
	}
}
