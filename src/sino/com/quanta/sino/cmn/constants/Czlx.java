package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 现品操作类型
 * </p>
 * <p>
 * create: 2011-1-23 下午12:51:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Czlx {

	/**
	 * 分配
	 */
	key1("1", "分配"),

	/**
	 * 分配取消
	 */
	key2("2", "分配取消"),

	/**
	 * 余材化
	 */
	key3("3", "余材化"),

	/**
	 * ETL指示书撤消
	 */
	key4("4", "ETL指示书撤消"),

	/**
	 * SL指示书撤消
	 */
	key5("5", "SL指示书撤消"),
	/**
	 * 6-中止余材
	 */
	key6("6", "6-中止余材"),
	/**
	 * 7-降级余材
	 */
	key7("7", "7-降级余材");

	public final String key;

	public final String name;

	Czlx(String key, String name) {
		this.key = key;
		this.name = name;

	}

	public static Czlx get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Czlx value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
