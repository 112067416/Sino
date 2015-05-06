package com.quanta.sino.ch.vo;

import java.util.Date;

/**
 * <p>
 * 包装清单VO
 * </p>
 * <p>
 * create: 2011-1-17 上午09:28:28
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class BzqdVO {

	/**
	 * 订货号
	 */
	private String dhno;

	/**
	 * 行号
	 */
	private String line;

	/**
	 * 装箱指示书号
	 */
	private String zxno;

	/**
	 * 用户代码
	 */
	private String user;

	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 出货日
	 */
	private Date chri;

	/**
	 * 所选制品
	 */
	private String zhip;

	/**
	 * 内外销
	 */
	private String nwai;

	/**
	 * 品名
	 */
	private String pm;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public String getZhip() {
		return zhip;
	}

	public void setZhip(String zhip) {
		this.zhip = zhip;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getNwai() {
		return nwai;
	}

	public void setNwai(String nwai) {
		this.nwai = nwai;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

}
