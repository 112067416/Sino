package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 日志类型【1-ETL实绩；2-SL实绩；3-SS实绩；4-异常处理现品作成】
 * </p>
 * <p>
 * create: 2011-1-18 上午08:42:13
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum ERzlx {

	/**
	 * <p>
	 * ETL实绩
	 * </p>
	 */
	ETL("1"),
	/**
	 * <p>
	 * SL实绩
	 * </p>
	 */
	SL("2"),
	/**
	 * <p>
	 * SS实绩
	 * </p>
	 */
	SS("3"),
	/**
	 * <p>
	 * 异常处理现品作成
	 * </p>
	 */
	YCCL("4");

	public final String key;

	ERzlx(String key) {
		this.key = key;
	}

}
