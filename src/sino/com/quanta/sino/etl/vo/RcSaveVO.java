package com.quanta.sino.etl.vo;

import java.util.Date;

/**
 * <p>
 * 保存入侧录入信息
 * </p>
 * <p>
 * create: 2010-12-27 下午02:54:57
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class RcSaveVO {

	/**
	 * 操作员
	 */
	private String czy;
	/**
	 * 指示书No
	 */
	private String zsno;
	/**
	 * 原材原板NO
	 */
	private String jbno;
	/**
	 * 系统用户
	 */
	private String xtyh;
	/**
	 * sl流水线代码（实绩）
	 */
	private String slin;

	/**
	 * 班
	 */
	private String bz;
	/**
	 * 组
	 */
	private String ban;
	/**
	 * 操作时间
	 */
	private Date czsj;

	public Date getCzsj() {
		return czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public String getCzy() {
		return czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
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

	public String getXtyh() {
		return xtyh;
	}

	public void setXtyh(String xtyh) {
		this.xtyh = xtyh;
	}

	public String getSlin() {
		return slin;
	}

	public void setSlin(String slin) {
		this.slin = slin;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

}