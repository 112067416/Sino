package com.quanta.sino.ch.vo;

import java.util.Date;

/**
 * <p>
 * 装箱指示书做成
 * </p>
 * <p>
 * create: 2010-12-30 上午09:59:57
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZxzsVO {

	/**
	 * 订货NO
	 */
	private String dhno;

	/**
	 * 行号
	 */
	private String line;

	/**
	 * 装箱指示NO
	 */
	private String zxno;

	/**
	 * 出货日期
	 */
	private Date chqi;

	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 用户代码
	 */
	private String user;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 运输公司名称
	 */
	private String ysnm;

	/**
	 * 运输公司代码
	 */
	private String ysgs;

	/**
	 * 送货点名称
	 */
	private String shnm;

	/**
	 * 送货点代码
	 */
	private String shno;

	/**
	 * 到达地点
	 */
	private String addr;

	/**
	 * 合计重量
	 */
	private Double chzl;

	/**
	 * 合计数量
	 */
	private Integer chsu;

	/**
	 * 所选择的制品字符串
	 */
	private String[] jbnos;

	/**
	 * 次日出货联络单ID
	 */
	private String pid;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	public Date getChqi() {
		return chqi;
	}

	public void setChqi(Date chqi) {
		this.chqi = chqi;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
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

	public String getYsnm() {
		return ysnm;
	}

	public void setYsnm(String ysnm) {
		this.ysnm = ysnm;
	}

	public String getYsgs() {
		return ysgs;
	}

	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

	public String getShnm() {
		return shnm;
	}

	public void setShnm(String shnm) {
		this.shnm = shnm;
	}

	public String getShno() {
		return shno;
	}

	public void setShno(String shno) {
		this.shno = shno;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Double getChzl() {
		return chzl;
	}

	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	public Integer getChsu() {
		return chsu;
	}

	public void setChsu(Integer chsu) {
		this.chsu = chsu;
	}

	public String[] getJbnos() {
		return jbnos;
	}

	public void setZpnos(String zpnos) {
		if (zpnos == null || zpnos.isEmpty()) {
			this.jbnos = null;
			return;
		}
		zpnos = zpnos.replaceAll("，", ",");
		this.jbnos = zpnos.split(",");
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
