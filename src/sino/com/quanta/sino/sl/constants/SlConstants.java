package com.quanta.sino.sl.constants;

/**
 * <p>
 * SL常量
 * </p>
 * <p>
 * create: 2011-1-18 上午08:57:29
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public interface SlConstants {

	/**
	 * <p>
	 * 最多制品端板数，即一个剪材材最多可作成几个端板
	 * </p>
	 */
	public static final int MAX_ZPDB = 1;

	/**
	 * <p>
	 * 实绩终了
	 * </p>
	 */
	public static final String SJ_ZL = "1";

	/**
	 * <p>
	 * 实绩中止
	 * </p>
	 */
	public static final String SJ_ZZ = "1";

	/**
	 * <p>
	 * 端板标记：1-端板
	 * </p>
	 */
	public static final String ZPNG_DBBJ_DB = "1";

	/**
	 * <p>
	 * 制品记录零头可否:可以
	 * </p>
	 */
	public static final String ZPNG_LTKF_K = "1";

	/**
	 * <p>
	 * 镜面检查
	 * </p>
	 */
	public static final String SJ_JMJC = "1";
	/**
	 * <p>
	 * 垫足确认
	 * </p>
	 */
	public static final String SJ_DMQR = "1";
	/**
	 * <p>
	 * 针孔确认
	 * </p>
	 */
	public static final String SJ_ZKQR = "1";

	/**
	 * 非合格品一有零头的时候枚数要求 为112的倍数
	 */
	public static final Integer LTZS = 112;
	/**
	 * 非合格品一有无零头的时候枚数要求 为50的倍数
	 */
	public static final Integer WLTZS = 50;

}
