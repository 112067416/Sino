package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 附页KPNO类型
 * </p>
 * <p>
 * create: 2011-1-18 上午08:42:13
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum EKpType {

	/**
	 * <p>
	 * ETL附页：前缀为1
	 * </p>
	 */
	ETL(1, "1"),

	/**
	 * <p>
	 * SL附页：前缀为2
	 * </p>
	 */
	SL(2, "2"),

	/**
	 * <p>
	 * ETL和SL附页：前缀为3
	 * </p>
	 */
	BOTH(3, "3");

	/**
	 * 序号
	 */
	public final int no;

	/**
	 * 键【1-ETL；2-SL；3-ETL、SL】
	 */
	public final String key;

	/**
	 * 构造
	 * 
	 * @param key
	 * @param value
	 */
	EKpType(int no, String key) {
		this.no = no;
		this.key = key;
	}

	/**
	 * <p>
	 * 根据键取对象
	 * </p>
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static EKpType get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EKpType e : values()) {
			if (e != null && e.key.equalsIgnoreCase(key)) {
				return e;
			}
		}
		return null;
	}

}
