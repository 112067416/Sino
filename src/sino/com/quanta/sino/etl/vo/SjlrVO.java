package com.quanta.sino.etl.vo;

public class SjlrVO {
	/**
	 * 指示书No
	 */
	private String zsno;
	/**
	 * 原材原板NO
	 */
	private String zrjb;
	/**
	 * 附着量正面
	 */
	private String fuzm;
	/**
	 * 附着量反面
	 */
	private String fufm;
	/**
	 * 出端COIL No.
	 */
	private String jbno;
	/**
	 * 制品尺寸.厚
	 */
	private Double houu;
	/**
	 * 制品尺寸.宽
	 */
	private Double kuan;

	/**
	 * 订货No.行号
	 */
	private String dhno;
	/**
	 * 用户略称
	 */
	private String abbr;
	/**
	 * 班
	 */
	private String ban;
	/**
	 * 组
	 */
	private String zu;
	/**
	 * ETL流水线代码（实绩）
	 */
	private String elin;
	/**
	 * 品种
	 */
	private String pzno;

	public String getElin() {
		return elin;
	}

	public void setElin(String elin) {
		this.elin = elin;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
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

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public Double getHouu() {
		return houu;
	}

	public void setHouu(Double houu) {
		this.houu = houu;
	}

	public String getZsno() {
		return zsno;
	}

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getZrjb() {
		return zrjb;
	}

	public void setZrjb(String zrjb) {
		this.zrjb = zrjb;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

}
