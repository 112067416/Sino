package com.quanta.sino.dh.vo;

/**
 * <p>
 * 购销合同VO
 * </p>
 * <p>
 * create: 2011-7-10 下午08:54:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class GxhtVO {

	/**
	 * 订货号
	 */
	private String dhno;

	/**
	 * 年份
	 */
	private Integer year;

	/**
	 * 月份
	 */
	private Integer month;

	/**
	 * 用户代码
	 */
	private String user;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 合同重量
	 */
	private Double htzl;

	/**
	 * 状态
	 */
	private String stat;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getHtzl() {
		return htzl;
	}

	public void setHtzl(Double htzl) {
		this.htzl = htzl;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
