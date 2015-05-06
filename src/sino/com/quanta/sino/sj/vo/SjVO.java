package com.quanta.sino.sj.vo;

import com.quanta.sino.orm.Xpfmt;
import com.quanta.sino.orm.Zsfmt;

/**
 * <p>
 * 实绩共通数据
 * </p>
 * <p>
 * create: 2011-1-21 下午12:10:39
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SjVO {

	/**
	 * 实绩日志（指示情报共通格式）
	 */
	private Zsfmt zsfmt;

	/**
	 * 实绩日志（现品情报共通格式）
	 */
	private Xpfmt xpfmt;

	/**
	 * @return the zsfmt
	 */
	public Zsfmt getZsfmt() {
		return zsfmt;
	}

	/**
	 * @param zsfmt
	 *            the zsfmt to set
	 */
	public void setZsfmt(Zsfmt zsfmt) {
		this.zsfmt = zsfmt;
	}

	/**
	 * @return the xpfmt
	 */
	public Xpfmt getXpfmt() {
		return xpfmt;
	}

	/**
	 * @param xpfmt
	 *            the xpfmt to set
	 */
	public void setXpfmt(Xpfmt xpfmt) {
		this.xpfmt = xpfmt;
	}

}
