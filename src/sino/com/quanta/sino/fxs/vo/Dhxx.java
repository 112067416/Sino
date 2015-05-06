package com.quanta.sino.fxs.vo;

/**
 * <p>
 * 钢卷对应的订货信息
 * </p>
 * <p>
 * create: 2011-4-10 下午05:33:47
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class Dhxx {

	/**
	 * 订货No.行号
	 */
	private String dhno;

	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 制品尺寸.厚
	 */
	private Double houu;

	/**
	 * 制品尺寸.宽
	 */
	private Double kuan;

	/**
	 * 制品尺寸.长
	 */
	private Double cang;

	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 表面精加工符号
	 */
	private String face;

	/**
	 * 加工用途条件
	 */
	private String cond;

	/**
	 * 订货目标附着量
	 */
	private String dhfz;

	/**
	 * 目标涂油量
	 */
	private String yqty;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

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

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getDhfz() {
		return dhfz;
	}

	public void setDhfz(String dhfz) {
		this.dhfz = dhfz;
	}

	public String getYqty() {
		return yqty;
	}

	public void setYqty(String yqty) {
		this.yqty = yqty;
	}
}
