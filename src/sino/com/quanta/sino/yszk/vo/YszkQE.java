package com.quanta.sino.yszk.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Fkfp;

/**
 * <p>
 * 付款发票查询条件
 * </p>
 * <p>
 * create: 2011-7-4 下午01:03:21
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class YszkQE extends QEntity<Fkfp> {

	/**
	 * 合同用户代码
	 */
	@QF
	private String htdm;

	/**
	 * 发票客户名称
	 */
	@QF(alias = "fpymc", operator = EO.EQ)
	private String fpymc;

	/**
	 * 查询出货日期开始时间段
	 */
	@QF(alias = "chri", operator = EO.GE)
	private Date chriBegin;

	/**
	 * 查询出货日期结束时间段
	 */
	@QF(alias = "chri", operator = EO.LT, addDays = 1)
	private Date chriEnd;

	/**
	 * 状态
	 */
	@QF(alias = "stat", operator = EO.IN)
	private String[] stat;

	/**
	 * 开票吨数
	 */
	@QF(alias = "kfzl", operator = EO.NE)
	private Double kfzl;

	/**
	 * 发票金额
	 */
	@QF(alias = "fpje", operator = EO.NE)
	private Double fpje;

	/**
	 * 品种
	 */
	@QF
	private String pz;

	/**
	 * 等级
	 */
	@QF
	private String deng;

	/**
	 * 内外销
	 */
	@QF
	private String nwai;

	/**
	 * 大于现品厚
	 */
	@QF(alias = "houu", operator = EO.GT)
	private Double houuS;

	/**
	 * 小于现品厚
	 */
	@QF(alias = "houu", operator = EO.LE)
	private Double houuE;

	/**
	 * 订货合同
	 */
	@QF(alias = "dhno", operator = EO.LIKE)
	private String dhno;

	/**
	 * 订货合同
	 */
	@QF(alias = "dhno", operator = EO.NLIKE)
	private String notLikeDhno;

	/**
	 * 发票品种
	 */
	@QF
	private String fppz;

	/**
	 * 发票品种
	 */
	@QF(alias = "fppz", operator = EO.IN)
	private String[] fppzs;

	/**
	 * 发票号码
	 */
	@QF(alias = "fpno", operator = EO.EQ)
	private String eqFpno;

	/**
	 * 业务员代码
	 */
	@QF
	private String yyno;

	public String getHtdm() {
		return htdm;
	}

	public void setHtdm(String htdm) {
		this.htdm = htdm;
	}

	public Date getChriBegin() {
		return chriBegin;
	}

	public void setChriBegin(Date chriBegin) {
		this.chriBegin = chriBegin;
	}

	public Date getChriEnd() {
		return chriEnd;
	}

	public void setChriEnd(Date chriEnd) {
		this.chriEnd = chriEnd;
	}

	public String[] getStat() {
		return stat;
	}

	public void setStat(String[] stat) {
		this.stat = stat;
	}

	public Double getKfzl() {
		return kfzl;
	}

	public void setKfzl(Double kfzl) {
		this.kfzl = kfzl;
	}

	public String getPz() {
		return pz;
	}

	public void setPz(String pz) {
		this.pz = pz;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getNwai() {
		return nwai;
	}

	public void setNwai(String nwai) {
		this.nwai = nwai;
	}

	public Double getHouuS() {
		return houuS;
	}

	public void setHouuS(Double houuS) {
		this.houuS = houuS;
	}

	public Double getHouuE() {
		return houuE;
	}

	public void setHouuE(Double houuE) {
		this.houuE = houuE;
	}

	public String getFpymc() {
		return fpymc;
	}

	public void setFpymc(String fpymc) {
		this.fpymc = fpymc;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getNotLikeDhno() {
		return notLikeDhno;
	}

	public void setNotLikeDhno(String notLikeDhno) {
		this.notLikeDhno = notLikeDhno;
	}

	public String getFppz() {
		return fppz;
	}

	public void setFppz(String fppz) {
		this.fppz = fppz;
	}

	public Double getFpje() {
		return fpje;
	}

	public void setFpje(Double fpje) {
		this.fpje = fpje;
	}

	public String[] getFppzs() {
		return fppzs;
	}

	public void setFppzs(String[] fppzs) {
		this.fppzs = fppzs;
	}

	public String getEqFpno() {
		return eqFpno;
	}

	public void setEqFpno(String eqFpno) {
		this.eqFpno = eqFpno;
	}

	public String getYyno() {
		return yyno;
	}

	public void setYyno(String yyno) {
		this.yyno = yyno;
	}

}
