package com.quanta.sino.ch.vo;

import java.util.Date;

/**
 * <p>
 * 出货实绩
 * </p>
 * <p>
 * create: 2011-6-3 下午10:10:59
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ChsjVO {
	/**
	 * 件数
	 */
	private Long chsu;

	/**
	 * 制品重量
	 */
	private Double chzl;

	/**
	 * 出货日
	 */
	private Date chri;

	/**
	 * 品种
	 */
	private String pzno;

	/**
	 * 内外销
	 */
	private String nwai;

	/**
	 * 商品编号
	 */
	private String spbh;

	public Long getChsu() {
		return chsu;
	}

	public void setChsu(Long chsu) {
		this.chsu = chsu;
	}

	public Double getChzl() {
		return chzl;
	}

	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	public String getNwai() {
		return nwai;
	}

	public void setNwai(String nwai) {
		this.nwai = nwai;
	}

	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

}
