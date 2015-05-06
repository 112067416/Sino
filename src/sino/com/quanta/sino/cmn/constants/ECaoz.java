/**
 * 
 */
package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 操作类型（操作MODE）
 * </p>
 * <p>
 * create: 2011-2-17 下午02:32:24
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum ECaoz {

	/**
	 * <p>
	 * 板材
	 * </p>
	 */
	SHEET("S", "板材"),

	/**
	 * <p>
	 * 卷材
	 * </p>
	 */
	COIL("C", "卷材");

	public final String key;
	public final String name;

	private ECaoz(String key, String name) {
		this.key = key;
		this.name = name;
	}

	/**
	 * <p>
	 * 根据键值取操作类型对象
	 * </p>
	 * 
	 * @param key
	 *            键
	 * @return ECaoz：操作类型
	 */
	public static ECaoz get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (ECaoz e : values()) {
			if (e.key.equalsIgnoreCase(key)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 根据品种判定操作类型
	 * </p>
	 * 
	 * @param pz
	 *            {@link Code118}
	 * @return ECaoz：操作类型
	 */
	public static ECaoz getByPz(Code118 pz) {
		if (pz == null) {
			return null;
		}
		if (pz == Code118.sheet || pz == Code118.scroll) {
			return ECaoz.SHEET;
		}
		// 黄美改：品种第一位为0（母板）或2（钢卷）
		if (pz == Code118.mothor || pz == Code118.coil) {
			return ECaoz.COIL;
		}
		return null;
	}
}
