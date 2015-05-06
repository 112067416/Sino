package com.quanta.sino.dy.vo;

/**
 * <p>
 * 包装清单打印VO
 * </p>
 * <p>
 * create: 2011-2-24 下午02:34:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class PbzqdVO {

	/**
	 * 出口包装No.
	 */
	private Integer ckno;

	/**
	 * 卷板No/PlieNo
	 */
	private String jbno;

	/**
	 * 张数（包装张数）
	 */
	private Integer zshu;

	/**
	 * 卷板长
	 */
	private Integer jscn;

	/**
	 * 毛重量
	 */
	private Integer mazl;

	/**
	 * 制品重量
	 */
	private Integer zpzl;

	/**
	 * 订货合同
	 */
	private String dhno;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public Integer getMazl() {
		return mazl;
	}

	public void setMazl(Integer mazl) {
		this.mazl = mazl;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public Integer getCkno() {
		return ckno;
	}

	public void setCkno(Integer ckno) {
		this.ckno = ckno;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public Integer getZshu() {
		return zshu;
	}

	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	public Integer getJscn() {
		return jscn;
	}

	public void setJscn(Integer jscn) {
		this.jscn = jscn;
	}

}
