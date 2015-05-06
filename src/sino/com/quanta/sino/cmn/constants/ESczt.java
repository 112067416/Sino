package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 卷的生产状态 分为当前卷和备用卷 在原材卷板中最多只能 有一个当前卷和一个备用卷 0-初始；1-待生产；2-正在生产；3-生产完成
 * </p>
 * <p>
 * create: 2011-2-15 上午11:49:00
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public enum ESczt {
	CS(ESczt.KEY_CS, "初始"), BYJ(ESczt.KEY_BYJ, "备用卷"), DQJ(ESczt.KEY_DQJ, "当前卷"), WCJ(
			ESczt.KEY_WCJ, "生产完成卷");

	/**
	 * 0-初始
	 */
	public static final String KEY_CS = "0";
	/**
	 * 1-备用卷
	 */
	public static final String KEY_BYJ = "1";
	/**
	 * 2-当前卷
	 */
	public static final String KEY_DQJ = "2";
	/**
	 * 3-完成卷
	 */
	public static final String KEY_WCJ = "3";

	/**
	 * 键
	 */
	public final String key;
	/**
	 * 值
	 */
	public final String value;

	/**
	 * 构造
	 * 
	 * @param key
	 * @param value
	 */
	ESczt(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static ESczt get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (ESczt sczt : values()) {
			if (sczt.key.equals(key)) {
				return sczt;
			}
		}
		return null;
	}

}
