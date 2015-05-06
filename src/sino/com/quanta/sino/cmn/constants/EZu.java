package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 组别
 * </p>
 * <p>
 * create: 2011-12-15 下午12:29:50
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EZu {

	/**
	 * A组
	 */
	A("A", "A组"),

	/**
	 * B组
	 */
	B("B", "B组"),

	/**
	 * C组
	 */
	C("C", "C组"),

	/**
	 * D组
	 */
	D("D", "D组");

	/**
	 * 
	 */
	public final String key;

	/**
	 * 
	 */
	public final String name;

	EZu(String key, String name) {
		this.key = key;
		this.name = name;
	}
}
