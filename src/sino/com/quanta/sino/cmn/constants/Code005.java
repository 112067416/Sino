package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 表面加工精度
 * </p>
 * <p>
 * create: 2011-1-31 下午04:00:20
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public enum Code005 {

	B("B", "BRIGHT"), BE("BE", "BRIGHTEXTRA"), BR("BR", "BRIGHTSTONE"), BN(
			"BN", "BN"), M("M", "MATTE"), R1("R1", "STONE"), R2("R2",
			"SUPERSTONE"), S1("S1", "SILVER"), SA("SA", "SATIN"), S3("S3",
			"SATIN"), W("W", "SUPERSTONE");

	/**
	 * 码表Key
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	Code005(String key, String name) {
		this.key = key;
		this.name = name;

	}

	public static Code005 get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Code005 value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}
}
