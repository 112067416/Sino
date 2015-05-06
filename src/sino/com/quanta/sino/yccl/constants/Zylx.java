package com.quanta.sino.yccl.constants;

/**
 * <p>
 * 设定作业类型 0：设定作业停止标记 ，1： 删除作业停止标记"
 * </p>
 * <p>
 * create: 2011-2-15 下午04:49:00
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public enum Zylx {
	/**
	 * 设定作业停止标记
	 */
	ZYSZ("0", " 设定作业停止标记"),
	/**
	 * 删除作业停止标记
	 */
	ZYSC("1", "删除作业停止标记"),
	/**
	 * 设定制品保留标记
	 */
	ZYBL("2", "设定制品保留标记"),
	/**
	 * 删除制品保留标记
	 */
	ZYWL("3", "删除制品保留标记");
	/**
	 * 键
	 */
	public final String key;
	/**
	 * 值
	 */
	public final String value;

	Zylx(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
