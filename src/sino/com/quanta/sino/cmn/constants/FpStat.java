package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 付款发票状态定义
 * </p>
 * <p>
 * create: 2011-3-3 下午02:39:21
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public enum FpStat {

	CS("0", "初始"), WJS("1", "未结束"), YJS("2", "已结束");

	public final String key;

	public final String name;

	FpStat(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static FpStat get(String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		for (FpStat stat : values()) {
			if (value.equals(stat.key)) {
				return stat;
			}
		}
		return null;
	}
}
