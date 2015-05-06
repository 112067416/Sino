package com.quanta.sino.cmn.constants;

/**
 * <p>
 * PILE区分
 * </p>
 * <p>
 * create: 2011-4-10 下午07:37:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EPlqf {

	/**
	 * 剪切二线。合格品PILE区分
	 */
	B,

	/**
	 * 剪切三线。合格品PILE区分
	 */
	C,

	/**
	 * 剪切二线。选别品PILE区分
	 */
	S,

	/**
	 * 剪切三线。选别品PILE区分
	 */
	T;

	public static EPlqf get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EPlqf e : values()) {
			if (key.equals(e.name())) {
				return e;
			}
		}
		return null;
	}
}
