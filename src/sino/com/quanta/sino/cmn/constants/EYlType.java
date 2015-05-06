package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 业连类型【业连号的前缀类型】，业连号形如：'1-123456'
 * </p>
 * <p>
 * create: 2011-1-18 上午08:42:13
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum EYlType {

	/**
	 * <p>
	 * ETL业连号：前缀为1
	 * </p>
	 */
	ETL(1, "1"),

	/**
	 * <p>
	 * SL业连号：前缀为2
	 * </p>
	 */
	SL(2, "2"),

	/**
	 * <p>
	 * ETL和SL业连号：前缀为3
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
	EYlType(int no, String key) {
		this.no = no;
		this.key = key;
	}

	/**
	 * <p>
	 * 根据KEY值取对象
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public static EYlType get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EYlType t : values()) {
			if (t.key.equals(key)) {
				return t;
			}
		}
		return null;
	}
}
