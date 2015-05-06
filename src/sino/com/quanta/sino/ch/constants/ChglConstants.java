package com.quanta.sino.ch.constants;

/**
 * <p>
 * 出货管理常量定义
 * </p>
 * <p>
 * create: 2010-12-29 上午11:21:07
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ChglConstants {

	/**
	 * 装箱指示书明细初始状态
	 */
	public static final String ZXZS2_START = "0";

	/**
	 * 装箱指示书明细退货状态
	 */
	public static final String ZXZS2_TH = "1";

	/**
	 * 退货日志 发票处置状态 0 未处置
	 */
	public static final String TH_CZZT_WCZ = "0";

	/**
	 * 退货日志 发票处置状态 1 已处置
	 */
	public static final String TH_CZZT_YCZ = "1";

	/**
	 * 退货日志状态 0 初始
	 */
	public static final String TH_ZT_CS = "0";

	/**
	 * 退货日志状态 1 撤销
	 */
	public static final String TH_ZT_CX = "1";

	/**
	 * 出货日志状态 0表示初始
	 */
	public static final String CHRZ_START_STAT = "0";

	/**
	 * 制品检查证明书每页打印条数
	 */
	public static final Integer JCZMS_SIZE = 18;

	/**
	 * 装箱指示书每页打印的条数
	 */
	public static final Integer ZXZS_SIZE = 20;

	/**
	 * 送货单每页打印条数
	 */
	public static final Integer SHD_SIZE = 10;

	/**
	 * 包装清单每页打印条数
	 */
	public static final Integer BZQD_SIZE = 30;

}
