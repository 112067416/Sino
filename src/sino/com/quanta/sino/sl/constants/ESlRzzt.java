/**
 * 
 */
package com.quanta.sino.sl.constants;

/**
 * <p>
 * SL日志状态
 * </p>
 * <p>
 * create: 2011-1-28 上午11:10:40
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum ESlRzzt {

	/**
	 * <p>
	 * 实绩录入
	 * </p>
	 */
	LR("1", "实绩录入"),

	/**
	 * <p>
	 * 实绩订正
	 * </p>
	 */
	DZ("2", "实绩订正"),

	/**
	 * <p>
	 * 实绩删除
	 * </p>
	 */
	SC("3", "实绩删除");

	/**
	 * <p>
	 * 键
	 * </p>
	 */
	public final String key;

	/**
	 * <p>
	 * 值
	 * </p>
	 */
	public final String memo;

	private ESlRzzt(String key, String memo) {
		this.key = key;
		this.memo = memo;
	}

}
