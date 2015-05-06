package com.quanta.sino.ycai.constants;

/**
 * <p>
 * 原板操作类型
 * </p>
 * <p>
 * create: 2011-6-2 上午11:56:09
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum OperateType {

	/**
	 * 原板维护
	 */
	wh("1", "原板维护"),
	/**
	 * 原板查看
	 */
	ck("2", "原板查看");

	/**
	 * 类型
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	OperateType(String key, String name) {
		this.key = key;
		this.name = name;

	}

	public static OperateType get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (OperateType value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
