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
public enum SczsStat {

	newly("0", "初始"), assign("1", "已分配"), over("2", "分配结束");

	public final String key;

	public final String name;

	SczsStat(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static SczsStat get(String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		for (SczsStat stat : values()) {
			if (value.equals(stat.key)) {
				return stat;
			}
		}
		return null;
	}
}
