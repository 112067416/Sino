/**
 * 
 */
package com.quanta.sino.cmn.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 堆场代码
 * </p>
 * <p>
 * create: 2011-1-6 上午10:51:07
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum DC {
	/**
	 * X
	 */
	X("X", "X堆场"),

	/**
	 * 
	 */
	A("A", "A堆场"),

	/**
	 * 
	 */
	B("B", "B堆场"),

	/**
	 * 
	 */
	C("C", "C堆场"),

	/**
	 * 
	 */
	D("D", "D堆场"),

	/**
	 * 
	 */
	E("E", "E堆场"),

	/**
	 * 
	 */
	F("F", "F堆场"),

	/**
	 * 
	 */
	G("G", "G堆场");

	public final String name;

	public final String description;

	DC(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public static DC get(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		for (DC dc : values()) {
			if (dc.name.equals(name)) {
				return dc;
			}
		}
		return null;
	}

	public static String get() {
		StringBuilder builder = new StringBuilder();
		for (DC dc : values()) {
			if (builder.length() > 0) {
				builder.append(",");
			}
			builder.append(dc.name);
		}
		return builder.toString();
	}

	/**
	 * <p>
	 * 堆场名称集合
	 * </p>
	 * 
	 * @return
	 */
	public static List<String> dcs() {
		List<String> dcs = new ArrayList<String>();
		for (DC dc : values()) {
			dcs.add(dc.name);
		}
		return dcs;
	}
}
