package com.quanta.sino.dh.constants;

/**
 * <p>
 * 订货DB管理模块调用的常量定义接口类
 * </p>
 * <p>
 * create: 2011-1-4 上午10:48:59
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public interface DhConstants {

	/**
	 * 张数50倍——50
	 */
	public static final Integer ZS_50 = 50;

	/**
	 * 张数100倍——100
	 */
	public static final Integer ZS_100 = 100;

	/**
	 * 张数112倍——112
	 */
	public static final Integer ZS_112 = 112;

	/**
	 * 特B或C或需L（特殊BNO或CNO或用户标签名表示）有B或C仕样或不需要——1
	 */
	public static final String T_BCL = "1";

	/**
	 * 捆包计算 值——1
	 */
	public static final String KBJS_Z = "1";

	/**
	 * 尺寸宽或长 值——850.0
	 */
	public static final Double CC_KOC = 850.0;

	/**
	 * 捆包形式 ——A31
	 */
	public static final String KBXS_ZS = "A31";

	/**
	 * 捆包形式 ——A21
	 */
	public static final String KBXS_ZE = "A21";

	/**
	 * 结算条件.设定标志 未登录 ——0
	 */
	public static final String JSTJ_WDL = "0";

	/**
	 * 结算条件.设定标志 已登录 ——1
	 */
	public static final String JSTJ_DL = "1";

	/**
	 * 卷重单位(捆包指定重量单位)
	 */
	public static final String KBZD_T = "T";

	/**
	 * 捆包指定张数.区分
	 */
	public static final String KBSQ = "9";

	/**
	 * 订货确认书发行标志.(订货确认修改标记);
	 */
	public static final String DHQR = "1";

}
