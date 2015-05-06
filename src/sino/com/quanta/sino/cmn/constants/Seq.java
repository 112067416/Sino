package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 序号管理标识
 * </p>
 * <p>
 * create: 2011-1-12 下午02:14:44
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Seq {

	/**
	 * 原材卷板号生成
	 */
	ybno("SINO_YBNO", "原材卷板号生成标识"),

	/**
	 * 在库分配No生成
	 */
	fpno("SINO_FPNO", "在库分配No"),

	/**
	 * 采样单No
	 */
	cyrzno("SINO_CYRZ", "采样单No"),

	/**
	 * 订货指示书No生成规则
	 */
	dhzsno("SINO_DHZS", "订货指示书No"),

	/**
	 * 装箱指示书No生成规则
	 */
	zxzsno("SINO_ZXZS", "装箱指示书No"),

	/**
	 * SL指示书No生成规则
	 */
	slzsno("SINO_SLZS", "SL指示书No"),

	/**
	 * 基板订单NO
	 */
	jdddno("SINO_JBDDNO", "基板订单NO"),
	/**
	 * 端板卷板NO
	 */
	dbjbno("SINO_DBNO", "端板卷板NO");

	public final String name;

	public final String description;

	Seq(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
