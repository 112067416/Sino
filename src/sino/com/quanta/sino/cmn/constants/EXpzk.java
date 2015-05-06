package com.quanta.sino.cmn.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 现品状况 也作余材状况
 * </p>
 * <p>
 * create: 2011-1-18 上午08:43:07
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum EXpzk {

	/**
	 * 原板
	 */
	SC(1, EXpzk.SC_KEY, "原板"),
	/**
	 * 中间品
	 */
	ZJP(2, EXpzk.ZJP_KEY, "中间品"),
	/**
	 * 卷制品
	 */
	JZP(3, EXpzk.JZP_KEY, "卷制品"),
	/**
	 * 板制品
	 */
	BZP(4, EXpzk.BZP_KEY, "板制品");

	/**
	 * 原材
	 */
	public static final String SC_KEY = "A";
	/**
	 * 中间品
	 */
	public static final String ZJP_KEY = "B";
	/**
	 * 卷材制品
	 */
	public static final String JZP_KEY = "C";
	/**
	 * 板材制品
	 */
	public static final String BZP_KEY = "D";

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
	EXpzk(int no, String key, String value) {
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
	public static List<EXpzk> listXpzk() {
		List<EXpzk> list = new ArrayList<EXpzk>();
		for (EXpzk o : values()) {
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
	public static EXpzk get(String key) {
		for (EXpzk o : values()) {
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
		for (EXpzk o : values()) {
			if (o != null && key.equalsIgnoreCase(o.key)) {
				return o.value;
			}
		}
		return null;
	}
}
