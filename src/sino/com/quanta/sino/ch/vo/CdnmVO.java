package com.quanta.sino.ch.vo;

/**
 * <p>
 * 出货按用途统计
 * </p>
 * <p>
 * create: 2011-8-28 下午05:12:20
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CdnmVO {

	/**
	 * 年
	 */
	private int year;

	/**
	 * 厚
	 */
	private Double houu;

	/**
	 * 宽
	 */
	private Double kuan;

	/**
	 * 长
	 */
	private Double cang;

	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 表面
	 */
	private String face;

	/**
	 * 用途代码
	 */
	private String cdnm;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getCdnm() {
		return cdnm;
	}

	public void setCdnm(String cdnm) {
		this.cdnm = cdnm;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

}
