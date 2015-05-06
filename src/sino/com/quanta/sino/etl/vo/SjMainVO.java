package com.quanta.sino.etl.vo;

public class SjMainVO {
	/**
	 * ETL流水线代码（实绩）
	 */
	private String elin;
	/**
	 * ETL流水线名称（实绩）
	 */
	private String scxname;
	/**
	 * 指示书NO
	 */
	private String zsno;
	/**
	 * 卷板NO
	 */
	private String jbno;
	/**
	 * 订货NO
	 */
	private String dhno;
	/**
	 * 客户略称
	 */
	private String abbr;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getElin() {
		return elin;
	}

	public void setElin(String elin) {
		this.elin = elin;
	}

	public String getScxname() {
		return scxname;
	}

	public void setScxname(String scxname) {
		this.scxname = scxname;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}
}
