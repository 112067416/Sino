package com.quanta.sino.dy.vo;

import java.util.Date;

/**
 * <p>
 * 原板标签打印VO
 * </p>
 * <p>
 * create: 2010-12-15 下午03:21:15
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YcaibqVO {

	/**
	 * 中日达卷板NO
	 */
	private String jbno;
	/**
	 * 制造商卷板NO
	 */
	private String zzsj;
	/**
	 * 制造商规格略称
	 */
	private String yblc;
	/**
	 * 制造商生产年月
	 */
	private Date zzny;
	/**
	 * 供应商合同号
	 */
	private String ybno;
	/**
	 * 合同行号
	 */
	private String line;
	/**
	 * 现品尺寸.厚
	 */
	private Double xpho;
	/**
	 * 现品尺寸.宽
	 */
	private Double xpkn;
	/**
	 * 卷板重量
	 */
	private Integer zpzl;
	/**
	 * 卷板内径
	 */
	private String njno;
	/**
	 * 分配等级
	 */
	private String deng;
	/**
	 * 表面精加工符号
	 */
	private String face;
	/**
	 * 船名
	 */
	private String ship;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	public Date getZzny() {
		return zzny;
	}

	public void setZzny(Date zzny) {
		this.zzny = zzny;
	}

	public String getYbno() {
		return ybno;
	}

	public void setYbno(String ybno) {
		this.ybno = ybno;
	}

	public Double getXpho() {
		return xpho;
	}

	public void setXpho(Double xpho) {
		this.xpho = xpho;
	}

	public Double getXpkn() {
		return xpkn;
	}

	public void setXpkn(Double xpkn) {
		this.xpkn = xpkn;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public String getNjno() {
		return njno;
	}

	public void setNjno(String njno) {
		this.njno = njno;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getYblc() {
		return yblc;
	}

	public void setYblc(String yblc) {
		this.yblc = yblc;
	}

}
