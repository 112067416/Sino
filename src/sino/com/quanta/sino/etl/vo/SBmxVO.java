package com.quanta.sino.etl.vo;

import java.util.Date;

/**
 * <p>
 * 速报明细信息实体类
 * </p>
 * <p>
 * create: 2011-11-22 下午01:57:17
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class SBmxVO {
	/**
	 * 日期
	 */
	private Date dasr;
	/**
	 * 计划生产量
	 */
	private Double jszl;
	/**
	 * 操业时间
	 */
	private Double gzda;
	/**
	 * 休止I
	 */
	private Double xzda;
	/**
	 * 休止II
	 */
	private Double zzda;

	public Date getDasr() {
		return dasr;
	}

	public void setDasr(Date dasr) {
		this.dasr = dasr;
	}

	public Double getJszl() {
		return jszl;
	}

	public void setJszl(Double jszl) {
		this.jszl = jszl;
	}

	public Double getGzda() {
		return gzda;
	}

	public void setGzda(Double gzda) {
		this.gzda = gzda;
	}

	public Double getXzda() {
		return xzda;
	}

	public void setXzda(Double xzda) {
		this.xzda = xzda;
	}

	public Double getZzda() {
		return zzda;
	}

	public void setZzda(Double zzda) {
		this.zzda = zzda;
	}

}
