package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 各库表状态常量
 * </p>
 * <p>
 * create: 2011-1-18 上午08:44:43
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public interface ZtConstants {

	/**
	 * 订货指示状态,0:未指示
	 */
	public static final String ZSDH_STAT_WZS = "0";
	/**
	 * 订货指示状态,1:已指示
	 */
	public static final String ZSDH_STAT_YZS = "1";

	/**
	 * 剪边标记:不剪边
	 */
	public static final String JBBJ_BJB = "0";
	/**
	 * 剪边标记:剪边
	 */
	public static final String JBBJ_JB = "1";
	/**
	 * <p>
	 * 原板原材分配状态1-分配
	 * </p>
	 */
	public static final String YCAI_FP_1 = "1";
	/**
	 * <p>
	 * 原板原材分配状态2-余材
	 * </p>
	 */
	public static final String YCAI_FP_2 = "1";
	/**
	 * 原材卷板确定标记,1：实绩中止或者终了
	 */
	public static final String YCAI_QDBJ_ZL = "1";
	/**
	 * 原材卷板确定标记,0：未实绩中止或者未终了
	 */
	public static final String YCAI_QDBJ_WL = "0";

	/*
	 * ========================订货指示DB ==========================
	 */
	/**
	 * 状态：0-未分派
	 */
	public static final String DHZS_STAT_WFP = "0";
	/**
	 * 状态：1-已分派
	 */
	public static final String DHZS_STAT_YFP = "1";

	/**
	 * 是否实绩：0-未实绩
	 */
	public static final String DHZS_SFSJ_WSJ = "0";
	/**
	 * 是否实绩：1-已实绩
	 */
	public static final String DHZS_SFSJ_YSJ = "1";

	/**
	 * 指示完了标记，即指示书中的所有卷已实绩完成：0-未完成
	 */
	public static final String DHZS_ZSBJ_WWC = "0";

	/**
	 * 指示完了标记，即指示书中的所有卷已实绩完成：1-已完成
	 */
	public static final String DHZS_ZSBJ_YWC = "1";

	/**
	 * 指示书发行完标记，即是否打印：0-未打印
	 */
	public static final String DHZS_ZSFX_WDY = "0";
	/**
	 * 指示书发行完标记，即是否打印：1-已打印
	 */
	public static final String DHZS_ZSFX_YDY = "1";

	/**
	 * 是否紧急：0-不紧急
	 */
	public static final String DHZS_SFJJ_BJJ = "0";
	/**
	 * 是否紧急：1-紧急
	 */
	public static final String DHZS_SFJJ_JJ = "1";

	/**
	 * 剪边标记：0-不剪边
	 */
	public static final String DHZS_JBBJ_BJB = "0";
	/**
	 * 剪边标记：1-剪边
	 */
	public static final String DHZS_JBBJ_JB = "1";

	/**
	 * 操作MODE：C-卷材
	 */
	public static final String DHZS_CAOZ_C = "C";
	/**
	 * 操作MODE：S-板材
	 */
	public static final String DHZS_CAOZ_S = "S";
	/**
	 * <p>
	 * 销售溶接部不可 0-不可溶接
	 * </p>
	 */
	public static final String DHUO_RJIE_A = "0";
	/**
	 * <p>
	 * 销售溶接部不可 1-可溶接
	 * </p>
	 */
	public static final String DHUO_RJIE_B = "1";
	/**
	 * <p>
	 * 装入中止标记 0-未中止
	 * </p>
	 */
	public static final String DHZS_ZHRU_WZ = "0";
	/**
	 * <p>
	 * 装入中止标记 1-装入中止
	 * </p>
	 */
	public static final String DHZS_ZHRU_YZ = "1";
	/**
	 * <p>
	 * 超量分配标记 0
	 * </p>
	 */
	public static final String DHZS_CLFP_WZL = "0";
	/**
	 * <p>
	 * 锯齿可否 1-是
	 * </p>
	 */
	public static final String DHZS_JCKF_YJC = "1";
	/**
	 * <p>
	 * 锯齿可否 0-否
	 * </p>
	 */
	public static final String DHZS_JCKF_WJC = "0";
	/**
	 * reflow要否
	 */
	public static final String CODE_FLOW = "X";
	/**
	 * ETL指示书中最多包含多少个剪切材 <br />
	 * 默认: 5个
	 */
	public static final int MAX_COIL_IN_ETL = 5;
	/**
	 * SL指示书中最多包含多少个剪切材 <br />
	 * 默认: 6个
	 */
	public static final int MAX_COIL_IN_SL = 5;

	/**
	 * 判定SL指示书的剪缀条件:换算尺寸 <br />
	 * 默认: 制品换算尺寸.厚 > 0.4MM时,指示书第一位为V
	 */
	public static final float HSCC_SLV = 0.4f;
	/**
	 * <p>
	 * 交货地点1
	 * </p>
	 */
	public static final String DHZS_Jhdd_1 = "1";
	/**
	 * <p>
	 * 交货地点 2
	 * </p>
	 */
	public static final String DHZS_Jhdd_2 = "2";
	/*
	 * ========================制品在库DB ==========================
	 */
	/**
	 * <p>
	 * DMARK,1-表
	 * </p>
	 */
	public static final String DMARK_B = "1";
	/**
	 * <p>
	 * DMARK，2-里
	 * </p>
	 */
	public static final String DMARK_L = "2";

	/**
	 * <p>
	 * 制品，确定标记：0-初始
	 * </p>
	 */
	public static final String ZPNG_QDBJ_CS = "0";
	/**
	 * <p>
	 * 制品，确定标记：1-确定（即已实绩完成）
	 * </p>
	 */
	public static final String ZPNG_QDBJ_QD = "1";

	/**
	 * 进度标记.SL指示: 初始
	 */
	public static final String ZPNG_JD_SLZS_WZS = "0";
	/**
	 * 进度标记.SL指示: 已指示
	 */
	public static final String ZPNG_JD_SLZS_YZS = "1";

	/**
	 * 进度标记.SL实绩: 未实绩
	 */
	public static final String ZPNG_JD_SLSJ_WSJ = "0";
	/**
	 * 进度标记.SL实绩: 已实绩
	 */
	public static final String ZPNG_JD_SLSJ_YSJ = "1";

	/**
	 * 进度标记.ETL指示: 0-未ETL指示
	 */
	public static final String ZPNG_JD_ETLZS_WZS = "0";
	/**
	 * 进度标记.ETL指示: 1-已ETL指示
	 */
	public static final String ZPNG_JD_ETLZS_YZS = "1";

	/**
	 * 进度标记.ETL实绩: 0-未ETL实绩
	 */
	public static final String ZPNG_JD_ETLSJ_WSJ = "0";
	/**
	 * 进度标记.ETL实绩: 1-已ETL实绩
	 */
	public static final String ZPNG_JD_ETLSJ_YSJ = "1";

	/**
	 * 进度标记.精整实绩-已精整实绩
	 */
	public static final String ZPNG_JD_JDJS = "1";

	/**
	 * 进度标记.精整实绩-已精整指示
	 */
	public static final String ZPNG_JD_JDJZ = "1";

	/**
	 * 删除标志：0-初始
	 */
	public static final String ZPNG_SC_CSZ = "0";
	/**
	 * 删除标志：1-已实绩终了
	 */
	public static final String ZPNG_SC_YSJ = "1";
	/**
	 * 删除标志：2-已消灭
	 */
	public static final String ZPNG_SC_YXM = "2";
	/**
	 * 删除标志：3-已出货
	 */
	public static final String ZPNG_SC_YCH = "3";
	/**
	 * 删除标志：4-异常处理删除
	 */
	public static final String ZPNG_SC_YSC = "4";

	/**
	 * 翻面标志：要求翻面
	 */
	public static final String ZPNG_SFFM_FM = "1";
	/**
	 * 实绩录入次数，默认8次。
	 */
	public static final char CODE_CHS9 = '9';
	/**
	 * 处理标志新增1。
	 */
	public static final String ZPNG_CLQ_ADD = "1";
	/**
	 * 处理标志修改2。
	 */
	public static final String ZPNG_CLQ_MODY = "2";
	/**
	 * 处理标志删除3。
	 */
	public static final String ZPNG_CLQ_DEL = "3";

	/*
	 * ========================指示DB(装入卷板)==========================
	 */
	/**
	 * 指示DB状态 0-未生产
	 */
	public static final String ZSZR_STAT_WSCH = "0";
	/**
	 * 指示DB状态 1-已实绩
	 */
	public static final String ZSZR_STAT_YSJ = "1";
	/*
	 * ========================指示对象DB==========================
	 */
	/**
	 * 指示对象DB分配No
	 */
	public static final String ZSDX_FP_NO = "B00000";
	/**
	 * 指示对象状态,0:未指示
	 */
	public static final String ZSDX_STAT_WZS = "0";

	/**
	 * 指示对象状态,1:已指示
	 */
	public static final String ZSDX_STAT_YZS = "1";

	/**
	 * 指示对象状态,2:取消分配
	 */
	public static final String ZSDX_STAT_QX = "2";

	/**
	 * <p>
	 * 指示对象强制标记：0-非强制
	 * </p>
	 */
	public static final String ZSDX_QZBJ_FQZ = "0";

	/**
	 * <p>
	 * 指示对象强制标记：1-强制
	 * </p>
	 */
	public static final String ZSDX_QZBJ_QZ = "1";
	/**
	 * <p>
	 * 木工主库
	 * </p>
	 */
	public static final String MG_KW = "A";
}
