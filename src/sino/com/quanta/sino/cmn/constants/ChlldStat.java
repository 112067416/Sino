package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 次日出货联络单状态
 * </p>
 * <p>
 * create: 2011-5-15 上午01:19:21
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum ChlldStat {
	WS("0", "未锁"), YS("1", "已锁"), YDD("2", "已打单"), YJS("3", "已结束");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 值
	 */
	public final String value;

	ChlldStat(String key, String name) {
		this.key = key;
		this.value = name;

	}

	/**
	 * <p>
	 * 根据键值取对象
	 * </p>
	 * 
	 * @param key
	 *            键
	 * @return ChlldStat：
	 */
	public static ChlldStat get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (ChlldStat value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
