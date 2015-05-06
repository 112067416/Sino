package com.quanta.sino.yygl.vo;

import java.util.Date;

/**
 * <p>
 * 为联络单的设置及分解页面设定的vo
 * </p>
 * <p>
 * create: 2010-12-29 下午02:46:40
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public class ChlldVO {

	/**
	 * 
	 */
	private String[] ids;
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
	 * 送货点名称
	 */
	private String shnm;
	/**
	 * 重量
	 */
	private Double chzl;

	/**
	 * 未出货重量
	 */
	private Double wczl;

	/**
	 * 订货号
	 */
	private String dhno;

	/**
	 * 行号
	 */
	private String line;

	/**
	 * 出货日期
	 */
	private Date chqi;

	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 主键
	 */
	private String pid;

	/**
	 * 用户代码
	 */
	private String user;

	/**
	 * 到达地点
	 */
	private String addr;

	/**
	 * 到达地点组
	 */
	private String addrs;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 备货状况
	 */
	private String bhzk;

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

	public Double getChzl() {
		return chzl;
	}

	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrs() {
		return addrs;
	}

	public void setAddrs(String addrs) {
		this.addrs = addrs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Double getWczl() {
		return wczl;
	}

	public void setWczl(Double wczl) {
		this.wczl = wczl;
	}

	public String getBhzk() {
		return bhzk;
	}

	public void setBhzk(String bhzk) {
		this.bhzk = bhzk;
	}

}
