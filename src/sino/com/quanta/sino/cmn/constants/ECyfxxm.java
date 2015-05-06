package com.quanta.sino.cmn.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 采样分析项目
 * </p>
 * <p>
 * create: 2011-5-8 上午11:13:24
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum ECyfxxm {

	/**
	 * 锡附着量
	 */
	xfzl("XFZL", "锡附着量", "锡附着量(g/m<sup>2</sup>)", 1, "SL_FXS"),

	/**
	 * Cr
	 */
	cr("CR", "Cr", "Cr(mg/m<sup>2</sup>)", 2, "SL_FXS"),

	/**
	 * 涂油量
	 */
	tyl("TYL", "涂油量", "涂油量 (mg/m<sup>2</sup>)", 3, "SL_FXS"),

	/**
	 * 表面六价铬
	 */
	bmljg("BMLJG", "表面六价铬", "表面六价铬", 4, "SL_FXS"),

	/**
	 * ATC
	 */
	atc("ATC", "ATC", "ATC (μA/cm<sup>2</sup>)", 5, "SL_FXS"),

	/**
	 * ISV
	 */
	isv("ISV", "ISV", "ISV (μg/3in<sup>2</sup>)", 6, "SL_FXS"),

	/**
	 * TCS评点
	 */
	tcs("TCS", "TCS评点", "TCS评点", 8, "SL_FXS"),

	/**
	 * TCV
	 */
	tcv("TCV", "TCV", "TCV (mg/dm<sup>2</sup>)", 7, "SL_FXS"),

	/**
	 * Epon Flow
	 */
	epon("EPON", "Epon Flow", "Epon Flow", 9, "SL_FXS"),

	/**
	 * 污点试验
	 */
	wdsy("WDSY", "污点试验", "污点试验", 10, "SL_FXS"),

	/**
	 * 涂料密着性
	 */
	tlmzx("TLMZX", "涂料密着性", "涂料密着性", 11, "SL_FXS"),

	/**
	 * PLT sec
	 */
	plt("PLT", "PLT sec", "PLT sec", 12, "SL"),

	/**
	 * PORE
	 */
	pore("PORE", "PORE", "PORE", 13, "SL_FXS"),

	/**
	 * 电镀铁浓度、沉淀物
	 */
	cdw("CDW", "一班一次", "", 14, "SL_FXS"),

	/**
	 * 硬度
	 */
	yd("YD", "硬度", "", 15, "SL"),

	/**
	 * SN上试验
	 */
	snssd("SNSSD", "SN上升试验", "", 16, "SL");
	/**
	 * 键值
	 */
	public final String key;

	/**
	 * 检验项目名称
	 */
	public final String name;

	/**
	 * 检验项目名称
	 */
	public final String description;

	/**
	 * 序号
	 */
	public final int order;

	/**
	 * 接收采样单线别
	 */
	public final String jsxb;

	/**
	 * 镀锡线
	 */
	public static final String ETL = "ETL";

	/**
	 * 剪切线
	 */
	public static final String SL = "SL";

	/**
	 * 分析室
	 */
	public static final String FXS = "FXS";

	/**
	 * 剪切线和分析室
	 */
	public static final String SL_FXS = "SL_FXS";

	ECyfxxm(String key, String name, String description, int order, String jsxb) {
		this.key = key;
		this.name = name;
		this.description = description;
		this.order = order;
		this.jsxb = jsxb;
	}

	/**
	 * <p>
	 * 根据项目名称，获取项目
	 * </p>
	 * 
	 * @param name
	 * @return ECyfxxm
	 */
	public static ECyfxxm getByName(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		for (ECyfxxm e : values()) {
			if (name.equalsIgnoreCase(e.name)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 检验项目名称集合
	 * </p>
	 * 
	 * @param jsxb
	 * @return
	 */
	public static List<String> xms(String jsxb) {
		List<String> xms = new ArrayList<String>();
		if (jsxb == null || jsxb.isEmpty()) return xms;
		for (ECyfxxm e : values()) {
			if (e.jsxb.equalsIgnoreCase(jsxb)) continue;
			xms.add(e.name);
		}
		return xms;
	}

	/**
	 * <p>
	 * 检验项目名称集合
	 * </p>
	 * 
	 * @return
	 */
	public static List<String> xms() {
		List<String> xms = new ArrayList<String>();
		for (ECyfxxm e : values()) {
			xms.add(e.name);
		}
		return xms;
	}
}
