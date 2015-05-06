package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 指示书前缀代码(现有镀锡 / 剪切指示书)
 * </p>
 * <p>create: 2011-1-18 上午08:44:21</p>
 * @author 方元龙
 * @version 1.0
 */
public enum EZsdm {

	/**
	 * 镀锡指示书前缀代码:N
	 */
	ETL("N"),

	/**
	 * 剪切一指示书前缀代码:V
	 */
	SLV("V"),

	/**
	 * 剪切二指示书前缀代码:W
	 */
	SLW("W");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 构造
	 * 
	 * @param key
	 * @param value
	 */
	EZsdm(String key) {
		this.key = key;
	}

}
