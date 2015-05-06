package com.quanta.sino.ch.vo;

import java.util.Date;

/**
 * <p>
 * 统计出货件数和重量
 * </p>
 * <p>
 * create: 2011-2-14 上午08:57:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ChtjVO {

	/**
	 * 装箱指示书No
	 */
	private String zxno;

	/**
	 * 状态
	 */
	private String stat;
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

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

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

}
