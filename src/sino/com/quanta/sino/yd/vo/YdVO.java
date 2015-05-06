/**
 * 
 */
package com.quanta.sino.yd.vo;

import java.util.Date;

/**
 * <p>
 * 硬度维护现品查询数据VO
 * </p>
 * <p>
 * create: 2011-5-5 下午10:33:09
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YdVO {

	/**
	 * 卷板号
	 */
	private String jbno;

	/**
	 * 原板制造商NO
	 */
	private String zzsd;

	/**
	 * 订货NO
	 */
	private String dhno;

	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 制品尺寸·厚
	 */
	private Double houu;

	/**
	 * 制品尺寸·宽
	 */
	private Double kuan;

	/**
	 * 制品尺寸·长
	 */
	private Double cang;

	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 等级
	 */
	private String fpdj;

	/**
	 * 表面
	 */
	private String face;

	/**
	 * 附着量·正面
	 */
	private String fuzm;

	/**
	 * 附着量·反面
	 */
	private String fufm;

	/**
	 * 生产时间
	 */
	private Date sjsj;

	/**
	 * 硬度录入时间
	 */
	private Date ydsj;

	/**
	 * 硬度.下限值
	 */
	private String ymin;

	/**
	 * 硬度.上限值
	 */
	private String ymax;

	/**
	 * 实际硬度
	 */
	private Integer ying;

	/**
	 * 鉴定员
	 */
	private String yjdr;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
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

	public String getFpdj() {
		return fpdj;
	}

	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
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

	public Date getSjsj() {
		return sjsj;
	}

	public void setSjsj(Date sjsj) {
		this.sjsj = sjsj;
	}

	public Date getYdsj() {
		return ydsj;
	}

	public void setYdsj(Date ydsj) {
		this.ydsj = ydsj;
	}

	public String getYmin() {
		return ymin;
	}

	public void setYmin(String ymin) {
		this.ymin = ymin;
	}

	public String getYmax() {
		return ymax;
	}

	public void setYmax(String ymax) {
		this.ymax = ymax;
	}

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
	}

	public String getYjdr() {
		return yjdr;
	}

	public void setYjdr(String yjdr) {
		this.yjdr = yjdr;
	}

}
