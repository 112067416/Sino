package com.quanta.sino.ch.vo;

import java.util.Date;

/**
 * <p>
 * 装箱对照
 * </p>
 * <p>
 * create: 2011-1-5 下午02:45:25
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZxdzVO {

	/**
	 * 装箱指示书号
	 */
	private String zxno;

	/**
	 * 出货日期
	 */
	private Date chri;

	/**
	 * 应有数量
	 */
	private Integer yysl;

	/**
	 * 打单人
	 */
	private String ddr;

	/**
	 * 集合
	 */
	private ZxdzItem[] items;

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public Integer getYysl() {
		return yysl;
	}

	public void setYysl(Integer yysl) {
		this.yysl = yysl;
	}

	public ZxdzItem[] getItems() {
		return items;
	}

	public void setItems(ZxdzItem[] items) {
		this.items = items;
	}

	public String getDdr() {
		return ddr;
	}

	public void setDdr(String ddr) {
		this.ddr = ddr;
	}

}
