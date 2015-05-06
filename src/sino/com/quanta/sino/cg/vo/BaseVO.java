/**
 * 
 */
package com.quanta.sino.cg.vo;

/**
 * <p>
 * 原板采购单价基础参数
 * </p>
 * <p>
 * create: 2011-7-8 下午11:29:00
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class BaseVO {

	/**
	 * 利息利率
	 */
	private Double lxll;

	/**
	 * 天数
	 */
	private Integer days;

	/**
	 * 运费
	 */
	private Double yf;

	/**
	 * 基础费用
	 */
	private Double base;

	public Double getLxll() {
		return lxll;
	}

	public void setLxll(Double lxll) {
		this.lxll = lxll;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Double getYf() {
		return yf;
	}

	public void setYf(Double yf) {
		this.yf = yf;
	}

	public Double getBase() {
		return base;
	}

	public void setBase(Double base) {
		this.base = base;
	}
}
