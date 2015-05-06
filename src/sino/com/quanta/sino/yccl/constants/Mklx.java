package com.quanta.sino.yccl.constants;

/**
 * <p>
 * 用于模块之间的区分，0：现品信息修正、1：现品信息作成、2：现品信息删除,
 * </p>
 * <p>
 * create: 2011-1-18 下午05:47:03
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public enum Mklx {
	/**
	 * 现品信息修正
	 */
	XPXZ("0", "现品信息修正"),
	/**
	 * 现品信息作
	 */
	XPZC("1", "现品信息作成"),
	/**
	 * 现品信息删除
	 */
	XPSC("2", "现品信息删除");

	/**
	 * 键
	 */
	public final String key;

	/**
	 * 值
	 */
	public final String value;

	Mklx(String key, String value) {
		this.key = key;
		this.value = value;
	}

}
