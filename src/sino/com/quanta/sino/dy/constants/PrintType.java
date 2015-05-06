package com.quanta.sino.dy.constants;

/**
 * <p>
 * 打印日志类型
 * </p>
 * <p>
 * create: 2011-4-20 下午04:28:38
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum PrintType {

	YCAI("YC", "原材"), ZPNG("ZP", "制品"), DBBJ("DB", "端板标记");

	public final String type;

	public final String description;

	PrintType(String type, String description) {
		this.type = type;
		this.description = description;
	}
}
