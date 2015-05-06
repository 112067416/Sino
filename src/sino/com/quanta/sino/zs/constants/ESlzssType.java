/**
 * 
 */
package com.quanta.sino.zs.constants;

import com.quanta.sino.cmn.constants.EZsdm;
import com.quanta.sino.cmn.constants.ZtConstants;

/**
 * <p>
 * SL指示书类型
 * </p>
 * <p>
 * create: 2011-2-14 下午04:06:02
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum ESlzssType {

	/**
	 * <p>
	 * 类型：W【前缀为W】。
	 * </p>
	 */
	W(1, EZsdm.SLW.key, ZtConstants.JBBJ_BJB),

	/**
	 * <p>
	 * 类型：V【前缀为V,需要剪边】。
	 * </p>
	 */
	V(2, EZsdm.SLV.key, ZtConstants.JBBJ_JB),

	/**
	 * <p>
	 * 类型：VJ【前缀为V,需要剪边】。
	 * </p>
	 */
	VJ(3, EZsdm.SLV.key, ZtConstants.JBBJ_JB);

	/**
	 * <p>
	 * 指示书类别
	 * </p>
	 */
	public final int type;

	/**
	 * <p>
	 * 指示书前缀标识
	 * </p>
	 */
	public final String flag;

	/**
	 * <p>
	 * 剪边标记
	 * </p>
	 */
	public final String jbbj;

	/**
	 * 构造
	 * 
	 * @param type
	 *            指示书类型
	 * @param flag
	 *            前缀标识
	 */
	private ESlzssType(int type, String flag, String jbbj) {
		this.type = type;
		this.flag = flag;
		this.jbbj = jbbj;
	}

	/**
	 * <p>
	 * 根据指示书类型获取类型对象
	 * </p>
	 * 
	 * @param type
	 * @return
	 */
	public static ESlzssType get(int type) {
		for (ESlzssType e : values()) {
			if (e.type == type) {
				return e;
			}
		}
		return null;
	}
}
