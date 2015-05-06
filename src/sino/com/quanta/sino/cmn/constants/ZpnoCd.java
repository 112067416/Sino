package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 卷板长度 （根据卷板NO长度判断是制品还是卷板）
 * </p>
 * <p>
 * create: 2011-3-9 上午10:55:28
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@126.com]
 * @version 1.0
 */
public enum ZpnoCd {
	/**
	 * 长度为6
	 */
	six(6, ZpnoCd.YCAI),

	/**
	 * 长度为7
	 */
	seven(7, ZpnoCd.YCAI),

	/**
	 * 长度为8
	 */
	eight(8, ZpnoCd.ZP),

	/**
	 * 长度为9
	 */
	nine(9, ZpnoCd.ZP),

	/**
	 * 长度为11
	 */
	eleven(11, ZpnoCd.ZP);

	/**
	 * 长度
	 */
	public final int len;

	/**
	 * 类型
	 */
	public final String type;

	public static final String YCAI = "0";

	public static final String ZP = "1";

	ZpnoCd(int len, String type) {
		this.len = len;
		this.type = type;

	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param len
	 * @return ZpnoCd：
	 */
	public static ZpnoCd get(int len) {
		if (len == 0) {
			return null;
		}
		for (ZpnoCd value : values()) {
			if (value.len == len) {
				return value;
			}
		}
		return null;
	}
}
