/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 订货合同状态
 * </p>
 * <p>
 * create: 2010-12-11 下午08:21:23
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum DhStat {

	newly("0", "初始"), locked("1", "上锁"), confirm("2", "仕样确认"), assign("3",
			"已分配"), over("4", "已完成");

	public final String key;

	public final String name;

	DhStat(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static DhStat get(String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		for (DhStat stat : values()) {
			if (value.equals(stat.key)) {
				return stat;
			}
		}
		return null;
	}
}
