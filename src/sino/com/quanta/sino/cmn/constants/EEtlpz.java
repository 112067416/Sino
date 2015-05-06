package com.quanta.sino.cmn.constants;

/**
 * <p>
 * ETL品质记录状态
 * </p>
 * <p>
 * create: 2011-4-19 上午11:08:29
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EEtlpz {

	/**
	 * 当前卷
	 */
	dqj("01", "当前卷"),

	/**
	 * 已完成
	 */
	ywc("02", "已完成");

	public final String key;
	public final String name;

	EEtlpz(String key, String name) {
		this.key = key;
		this.name = name;
	}

}
