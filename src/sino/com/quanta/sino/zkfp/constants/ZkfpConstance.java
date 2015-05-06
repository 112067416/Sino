package com.quanta.sino.zkfp.constants;

/**
 * <p>
 * 在库分配状态常量
 * </p>
 * <p>
 * create: 2011-1-23 下午01:46:35
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ZkfpConstance {

	/**
	 * 非再选
	 */
	public static final String ZKFP_ZXBB_0 = "0";
	/**
	 * 再选
	 */
	public static final String ZKFP_ZXBB_1 = "1";

	/**
	 * 非剪边
	 */
	public static final String ZKFP_JBKB_0 = "0";
	/**
	 * 剪边
	 */
	public static final String ZKFP_JBKB_1 = "1";

	/**
	 * 非允许超出
	 */
	public static final String ZKFP_CLFP_0 = "0";
	/**
	 * 允许超出
	 */
	public static final String ZKFP_CLFP_1 = "1";

	/**
	 * 非强制分配
	 */
	public static final String ZKFP_QZBJ_0 = "0";
	/**
	 * 强制分配
	 */
	public static final String ZKFP_QZBJ_1 = "1";

	/**
	 * 标签换帖标记
	 */
	public static final String BQHT = "1";

	/**
	 * 没有显示不配匹明细个数
	 */
	public static final int PAGE_SIZE = 5;

	/**
	 * 装入宽上限
	 */
	public static final double zrknS = 10.0;

	/**
	 * 装入宽下限
	 */
	public static final double zrknX = 100.0;

	/**
	 * B表面
	 */
	public static final String B = "B";

	/**
	 * BR表面
	 */
	public static final String BR = "BR";

}
