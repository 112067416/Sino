package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 重内值
 * </p>
 * <p>
 * create: 2011-1-18 下午03:23:36
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public enum ZlnrCode {
	nr1("1", "net"), nr2("2", "net + 梱包材料"), nr3("3", "net+梱包材料+垫木重量"), nr6(
			"6", "net"), nr7("7", "net +梱包材料"), nr8("8", "net+梱包材料+垫木重量");

	/**
	 * 码表Key
	 */
	public final String key;

	/**
	 * 名称
	 */
	public final String name;

	ZlnrCode(String key, String name) {
		this.key = key;
		this.name = name;
	}

	/**
	 * <p>
	 * 根据键取对象
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public static ZlnrCode get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (ZlnrCode value : values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 根据重内代码获取操作类型
	 * </p>
	 * 
	 * @param key
	 * @return String
	 */
	public static String getForCaoz(String key) {
		if (nr1.key.equals(key) || nr2.key.equals(key) || nr3.key.equals(key)) {
			return ZtConstants.DHZS_CAOZ_C;
		}
		if (nr6.key.equals(key) || nr7.key.equals(key) || nr8.key.equals(key)) {
			return ZtConstants.DHZS_CAOZ_S;
		}
		return null;
	}
}
