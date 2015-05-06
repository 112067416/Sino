/**
 * 
 */
package com.quanta.sino.sl.constants;

/**
 * <p>
 * 堆垛(PILER)
 * </p>
 * <p>
 * create: 2011-2-18 下午05:06:53
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum EPiler {

	/**
	 * 堆垛1
	 */
	P1("1"),
	/**
	 * 堆垛2
	 */
	P2("2"),
	/**
	 * 堆垛3
	 */
	P3("3"),
	/**
	 * 堆垛4
	 */
	P4("4");

	public final String piler;

	EPiler(String piler) {
		this.piler = piler;
	}

	/**
	 * <p>
	 * 根据piler值取对象
	 * </p>
	 * 
	 * @param piler
	 *            堆垛
	 * @return EPiler：堆垛枚举对象
	 */
	public static EPiler get(String piler) {
		if (piler == null || piler.isEmpty()) {
			return null;
		}
		for (EPiler e : values()) {
			if (e.piler.equals(piler)) {
				return e;
			}
		}
		return null;
	}
}
