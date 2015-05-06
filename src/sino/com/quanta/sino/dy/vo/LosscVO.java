package com.quanta.sino.dy.vo;

/**
 * <p>
 * 不良扣除联络单
 * </p>
 * <p>
 * create: 2011-8-29 下午05:01:09
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class LosscVO {

	/**
	 * 日期
	 */
	private String upda;

	/**
	 * 制造商代码
	 */
	private String zzsd;

	/**
	 * 制品No.
	 */
	private String jbno;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 
	 */
	private Integer jbcn;

	/**
	 * 不良名称
	 */
	private String losq;

	/**
	 * 扣除长度
	 */
	private Integer losc;

	/**
	 * 连续性
	 */
	private String lxx;

	/**
	 * 间断性
	 */
	private String jdx;

	/**
	 * 零星
	 */
	private String lx;

	public String getUpda() {
		return upda;
	}

	public void setUpda(String upda) {
		this.upda = upda;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLosq() {
		return losq;
	}

	public void setLosq(String losq) {
		this.losq = losq;
	}

	public Integer getLosc() {
		return losc;
	}

	public void setLosc(Integer losc) {
		this.losc = losc;
	}

	public String getLxx() {
		return lxx;
	}

	public void setLxx(String lxx) {
		this.lxx = lxx;
	}

	public String getJdx() {
		return jdx;
	}

	public void setJdx(String jdx) {
		this.jdx = jdx;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public Integer getJbcn() {
		return jbcn;
	}

	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

}
