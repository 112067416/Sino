package com.quanta.sino.cmn.constants;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 相等的表面精加工
 * </p>
 * <p>
 * create: 2011-5-7 下午03:23:37
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EFace {

	/**
	 * 
	 */
	B("B", "BRIGHT", new String[] { "B", "BR" }),

	/**
	 * 
	 */
	BR("BR", "BRIGHTSTONE", new String[] { "B", "BR" }),

	/**
	 * 
	 */
	BE("BE", "BRIGHTEXTRA", new String[] { "BE" }),

	/**
	 * 
	 */
	BN("BN", "BN", new String[] { "BN" }),

	/**
	 * 
	 */
	M("M", "MATTE", new String[] { "S1", "M" }),

	/**
	 * 
	 */
	R1("R1", "STONE", new String[] { "R1" }),

	/**
	 * 
	 */
	R2("R2", "SUPERSTONE", new String[] { "R2", "W" }),

	/**
	 * 
	 */
	S1("S1", "SILVER", new String[] { "S1", "M" }),

	/**
	 * 
	 */
	S3("S3", "SATIN", new String[] { "S3", "SA" }),

	/**
	 * 
	 */
	SA("SA", "SATIN", new String[] { "S3", "SA" }),

	/**
	 * 
	 */
	W("W", "SUPERSTONE", new String[] { "R2", "W" });

	/**
	 * 码表Key
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	/**
	 * 有效值列表
	 */
	public final List<String> values;

	EFace(String key, String name, String[] values) {
		this.key = key;
		this.name = name;
		this.values = Arrays.asList(values);
	}

	public static EFace get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EFace e : values()) {
			if (key.equalsIgnoreCase(e.key)) {
				return e;
			}
		}
		return null;
	}

	public boolean contain(String value) {
		if (value == null || value.isEmpty()) {
			return false;
		}
		return values.contains(value);
	}
}
