package com.quanta.sino.yygl.vo;

import java.util.Date;

/**
 * <p>
 * 订货信息VO
 * </p>
 * <p>
 * create: 2011-6-15 下午10:57:58
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class SczsdDhVO {
	/**
	 * 尺寸.厚
	 */
	private Double houu;

	/**
	 * 尺寸.宽
	 */
	private Double kuan;

	/**
	 * 尺寸.长
	 */
	private Double cang;

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
	 * 附着量.单位
	 */
	private String fudw;

	/**
	 * 附着量.正面
	 */
	private String fuzm;

	/**
	 * 附着量.反面
	 */
	private String fufm;

	/**
	 * 表面加工精度
	 */
	private String face;

	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 合同重量
	 */
	private Double htzl;

	/**
	 * 交货期
	 */
	private Date jhqi;

	/**
	 * 
	 */
	private Double ybkc;

	public Double getHouu() {
		return houu;
	}

	public void setHouu(Double houu) {
		this.houu = houu;
	}

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
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

	public String getFudw() {
		return fudw;
	}

	public void setFudw(String fudw) {
		this.fudw = fudw;
	}

	public String getFuzm() {
		return fuzm;
	}

	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	public String getFufm() {
		return fufm;
	}

	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public Double getHtzl() {
		return htzl;
	}

	public void setHtzl(Double htzl) {
		this.htzl = htzl;
	}

	public Date getJhqi() {
		return jhqi;
	}

	public void setJhqi(Date jhqi) {
		this.jhqi = jhqi;
	}

	public Double getYbkc() {
		return ybkc;
	}

	public void setYbkc(Double ybkc) {
		this.ybkc = ybkc;
	}

}
