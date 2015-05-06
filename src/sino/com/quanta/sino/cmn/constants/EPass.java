package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 入切
 * </p>
 * <p>
 * create: 2011-12-13 上午11:27:25
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EPass {

	/**
	 * 入
	 */
	R("入"),

	/**
	 * 切
	 */
	Q("切");

	public final String key;

	EPass(String key) {
		this.key = key;
	}

}
