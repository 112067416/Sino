/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 班
 * </p>
 * <p>
 * create: 2011-12-8 下午02:11:20
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Ban {
	/**
	 * 1班(描述：1班的工作时间从当天早上8点到当天下午4点)
	 */
	one("1", "1班", 8, 16, "8:00-16:00"),

	/**
	 * 2班(描述：2班的工作时间从当天下午4点到零晨零点)
	 */
	two("2", "2班", 16, 24, "16:00-00:00"),

	/**
	 * 3班(描述：1班的工作时间从明天早上零晨零点到明天早上8点)
	 */
	three("3", "3班", 0, 8, "00:00-08:00");

	/**
	 * 
	 */
	public final String key;

	/**
	 * 
	 */
	public final String name;

	/**
	 * 
	 */
	public final int begin;

	/**
	 * 
	 */
	public final int end;

	public final String time;

	Ban(String key, String name, int begin, int end, String time) {
		this.key = key;
		this.name = name;
		this.begin = begin;
		this.end = end;
		this.time = time;
	}

	public static Ban get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (Ban bz : values()) {
			if (bz.key.equals(key)) {
				return bz;
			}
		}
		return null;
	}
}
