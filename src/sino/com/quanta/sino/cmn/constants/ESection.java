package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 运输路段
 * </p>
 * <p>
 * create: 2011-7-26 下午05:16:35
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum ESection {

	/**
	 * 货柜海运
	 */
	hghy1("hghy", "hghy1", "中日达到起始港"),

	/**
	 * 
	 */
	hghy2("hghy", "hghy2", "堆场"),

	/**
	 * 
	 */
	hghy3("hghy", "hghy3", "船运到客户"),

	/**
	 * 
	 */
	hghy4("hghy", "hghy4", "船运"),

	/**
	 * 
	 */
	hghy5("hghy", "hghy5", "目的港到客户"),

	/**
	 * 散货海运
	 */
	shhy1("shhy", "shhy1", "中日达到目的港"),

	/**
	 * 
	 */
	shhy2("shhy", "shhy2", "目的港到客户"),

	/**
	 * 公路运输
	 */
	glys1("glys", "glys1", "中日达到客户"),

	/**
	 * 铁路运输
	 */
	tlys1("tlys", "tlys1", "中日达到终点站"),

	/**
	 * 
	 */
	tlys2("tlys", "tlys2", "终点站到客户"),

	/**
	 * 出口
	 */
	ck1("ck", "ck1", "中日达到起始港"),

	/**
	 * 
	 */
	ck2("ck", "ck2", "堆场"),

	/**
	 * 
	 */
	ck3("ck", "ck3", "船运"),

	/**
	 * 
	 */
	ck4("ck", "ck4", "目的港到客户"),

	/**
	 * 自提
	 */
	zt1("zt", "zt1", "中日达到客户");

	/**
	 * 
	 */
	public final String type;

	/**
	 * 
	 */
	public final String key;

	/**
	 * 
	 */
	public final String value;

	ESection(String type, String key, String value) {
		this.type = type;
		this.key = key;
		this.value = value;
	}

}
