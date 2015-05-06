package com.quanta.sino.dh.constants;

public enum DhJzqf {

	/**
	 * 卷重区分（梱包指定重量.区分）——5
	 */
	JZ_5("5", "卷重5"),
	/**
	 * 卷重区分（梱包指定重量.区分）——6
	 */
	JZ_6("6", "卷重6"),
	/**
	 * 卷重区分（梱包指定重量.区分）——8
	 */
	JZ_8("8", "卷重8");

	public final String stat;

	public final String name;

	DhJzqf(String stat, String name) {
		this.stat = stat;
		this.name = name;
	}

	public static DhJzqf get(String stat) {
		if (stat == null || stat.isEmpty()) {
			return null;
		}
		for (DhJzqf zt : values()) {
			if (stat.equals(zt.stat)) {
				return zt;
			}
		}
		return null;
	}
}
