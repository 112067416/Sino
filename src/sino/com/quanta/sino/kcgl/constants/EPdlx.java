package com.quanta.sino.kcgl.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 盘点类型
 * </p>
 * <p>
 * create: 2011-5-31 上午11:53:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum EPdlx {

	/**
	 * 素材
	 */
	SC(1, EPdlx.SC_KEY, "素材"),
	/**
	 * 板制
	 */
	ZP(4, EPdlx.ZP_KEY, "制品");

	/**
	 * 素板/原材
	 */
	public static final String SC_KEY = "A";
	/**
	 * /** 制品
	 */
	public static final String ZP_KEY = "B";

	/**
	 * 序号
	 */
	public final int no;
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
	 * @param no
	 * @param key
	 * @param value
	 */
	EPdlx(int no, String key, String value) {
		this.no = no;
		this.key = key;
		this.value = value;
	}

	/**
	 * <p>
	 * 取所有现品状况
	 * </p>
	 * 
	 * @return
	 */
	public static List<EPdlx> listXpzk() {
		List<EPdlx> list = new ArrayList<EPdlx>();
		for (EPdlx o : values()) {
			if (o == null) {
				continue;
			}
			list.add(o);
		}
		return list;
	}

	/**
	 * <p>
	 * 根据键取对象
	 * </p>
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static EPdlx get(String key) {
		for (EPdlx o : values()) {
			if (o != null && key.equalsIgnoreCase(o.key)) {
				return o;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 根据键取值
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		for (EPdlx o : values()) {
			if (o != null && key.equalsIgnoreCase(o.key)) {
				return o.value;
			}
		}
		return null;
	}
}
