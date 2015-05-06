package com.quanta.sino.yygl.vo;

import java.util.Date;

/**
 * <p>
 * 成品出库单
 * </p>
 * <p>
 * create: 2011-6-15 上午09:58:29
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CpckdVO {

	/**
	 * 出货日期
	 */
	private Date chqi;

	/**
	 * 送货单号码
	 */
	private String shnos;

	/**
	 * 马品铁切板0.15~0.29数量
	 */
	private Double bsl1;

	/**
	 * 马品铁切板0.30~0.49数量
	 */
	private Double bsl2;

	/**
	 * 马品铁切板0.50~1.00数量
	 */
	private Double bsl3;

	/**
	 * 马品铁卷材0.15~0.29数量
	 */
	private Double jsl1;

	/**
	 * 马品铁卷材0.30~0.49数量
	 */
	private Double jsl2;

	/**
	 * 马品铁卷材0.50~1.00数量
	 */
	private Double jsl3;

	/**
	 * 合计
	 */
	private Double hj;

	/**
	 * 出口马品铁切板0.15~0.29数量
	 */
	private Double cbsl1;

	/**
	 * 出口马品铁切板0.30~0.49数量
	 */
	private Double cbsl2;

	/**
	 * 出口马品铁切板0.50~1.00数量
	 */
	private Double cbsl3;

	/**
	 * 出口马品铁卷材0.15~0.29数量
	 */
	private Double cjsl1;

	/**
	 * 出口马品铁卷材0.30~0.49数量
	 */
	private Double cjsl2;

	/**
	 * 出口马品铁卷材0.50~1.00数量
	 */
	private Double cjsl3;

	/**
	 * 内销合计
	 */
	private Double nhj;

	public Date getChqi() {
		return chqi;
	}

	public void setChqi(Date chqi) {
		this.chqi = chqi;
	}

	public String getShnos() {
		return shnos;
	}

	public void setShnos(String shnos) {
		this.shnos = shnos;
	}

	public Double getBsl1() {
		return bsl1;
	}

	public void setBsl1(Double bsl1) {
		this.bsl1 = bsl1;
	}

	public Double getBsl2() {
		return bsl2;
	}

	public void setBsl2(Double bsl2) {
		this.bsl2 = bsl2;
	}

	public Double getBsl3() {
		return bsl3;
	}

	public void setBsl3(Double bsl3) {
		this.bsl3 = bsl3;
	}

	public Double getJsl1() {
		return jsl1;
	}

	public void setJsl1(Double jsl1) {
		this.jsl1 = jsl1;
	}

	public Double getJsl2() {
		return jsl2;
	}

	public void setJsl2(Double jsl2) {
		this.jsl2 = jsl2;
	}

	public Double getJsl3() {
		return jsl3;
	}

	public void setJsl3(Double jsl3) {
		this.jsl3 = jsl3;
	}

	public Double getCbsl1() {
		return cbsl1;
	}

	public void setCbsl1(Double cbsl1) {
		this.cbsl1 = cbsl1;
	}

	public Double getCbsl2() {
		return cbsl2;
	}

	public void setCbsl2(Double cbsl2) {
		this.cbsl2 = cbsl2;
	}

	public Double getCbsl3() {
		return cbsl3;
	}

	public void setCbsl3(Double cbsl3) {
		this.cbsl3 = cbsl3;
	}

	public Double getCjsl1() {
		return cjsl1;
	}

	public void setCjsl1(Double cjsl1) {
		this.cjsl1 = cjsl1;
	}

	public Double getCjsl2() {
		return cjsl2;
	}

	public void setCjsl2(Double cjsl2) {
		this.cjsl2 = cjsl2;
	}

	public Double getCjsl3() {
		return cjsl3;
	}

	public void setCjsl3(Double cjsl3) {
		this.cjsl3 = cjsl3;
	}

	public Double getNhj() {
		return nhj;
	}

	public void setNhj(Double nhj) {
		this.nhj = nhj;
	}

	public Double getHj() {
		return hj;
	}

	public void setHj(Double hj) {
		this.hj = hj;
	}

}
