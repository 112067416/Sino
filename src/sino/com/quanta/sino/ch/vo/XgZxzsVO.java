package com.quanta.sino.ch.vo;

import java.util.Date;

/**
 * <p>
 * 修改装箱指示书
 * </p>
 * <p>
 * create: 2011-2-21 下午11:20:56
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class XgZxzsVO {

	/**
	 * 出货日
	 */
	private Date chri;

	/**
	 * 装箱指示书号
	 */
	private String zxno;

	/**
	 * 运输公司代码
	 */
	private String ysgs;

	/**
	 * 运输公司名称
	 */
	private String ysnm;

	/**
	 * 送货点代码
	 */
	private String shno;

	/**
	 * 送货点略称
	 */
	private String shnm;

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public String getYsgs() {
		return ysgs;
	}

	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

	public String getYsnm() {
		return ysnm;
	}

	public void setYsnm(String ysnm) {
		this.ysnm = ysnm;
	}

	public String getShno() {
		return shno;
	}

	public void setShno(String shno) {
		this.shno = shno;
	}

	public String getShnm() {
		return shnm;
	}

	public void setShnm(String shnm) {
		this.shnm = shnm;
	}

}
