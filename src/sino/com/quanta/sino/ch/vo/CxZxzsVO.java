package com.quanta.sino.ch.vo;

import java.util.Date;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * 查询装箱指示书VO
 * </p>
 * <p>
 * create: 2010-12-30 上午09:59:57
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CxZxzsVO {

	/**
	 * 装箱指示NO
	 */
	@QF(alias = "a.zxno")
	private String zxno;

	/**
	 * 出货日期
	 */
	@QF(alias = "a.chri")
	private Date chri;

	/**
	 * 用户略称
	 */
	@QF(alias = "a.abbr")
	private String abbr;

	/**
	 * 用户代码
	 */
	@QF(alias = "a.user")
	private String user;

	/**
	 * 用户名称
	 */
	@QF(alias = "a.name")
	private String name;

	/**
	 * 运输公司名称
	 */
	@QF(alias = "a.ysnm")
	private String ysnm;

	/**
	 * 运输公司代码
	 */
	@QF(alias = "a.ysgs")
	private String ysgs;

	/**
	 * 送货点名称
	 */
	@QF(alias = "a.shnm")
	private String shnm;

	/**
	 * 送货点代码
	 */
	@QF(alias = "a.shno")
	private String shno;

	/**
	 * 到达地点
	 */
	@QF(alias = "a.addr")
	private String addr;

	/**
	 * 合计重量
	 */
	@QF(alias = "sum(a.chzl)")
	private Double chzl;

	/**
	 * 合计数量
	 */
	@QF(alias = "sum(a.chsu)")
	private Long chsu;

	/**
	 * 状态
	 */
	@QF(alias = "a.stat")
	private String stat;

	/**
	 * @return the zxno
	 */
	public String getZxno() {
		return zxno;
	}

	/**
	 * @param zxno
	 *            the zxno to set
	 */
	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	/**
	 * @return the chri
	 */
	public Date getChri() {
		return chri;
	}

	/**
	 * @param chri
	 *            the chri to set
	 */
	public void setChri(Date chri) {
		this.chri = chri;
	}

	/**
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}

	/**
	 * @param abbr
	 *            the abbr to set
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ysnm
	 */
	public String getYsnm() {
		return ysnm;
	}

	/**
	 * @param ysnm
	 *            the ysnm to set
	 */
	public void setYsnm(String ysnm) {
		this.ysnm = ysnm;
	}

	/**
	 * @return the ysgs
	 */
	public String getYsgs() {
		return ysgs;
	}

	/**
	 * @param ysgs
	 *            the ysgs to set
	 */
	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

	/**
	 * @return the shnm
	 */
	public String getShnm() {
		return shnm;
	}

	/**
	 * @param shnm
	 *            the shnm to set
	 */
	public void setShnm(String shnm) {
		this.shnm = shnm;
	}

	/**
	 * @return the shno
	 */
	public String getShno() {
		return shno;
	}

	/**
	 * @param shno
	 *            the shno to set
	 */
	public void setShno(String shno) {
		this.shno = shno;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the chzl
	 */
	public Double getChzl() {
		return chzl;
	}

	/**
	 * @param chzl
	 *            the chzl to set
	 */
	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	/**
	 * @return the chsu
	 */
	public Long getChsu() {
		return chsu;
	}

	/**
	 * @param chsu
	 *            the chsu to set
	 */
	public void setChsu(Long chsu) {
		this.chsu = chsu;
	}

	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

}
