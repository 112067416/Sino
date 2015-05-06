/**
 * 
 */
package com.quanta.sino.ss.vo;

import java.util.Date;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * SL实绩查询数据VO
 * </p>
 * <p>
 * create: 2011-1-27 下午04:16:36
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class SlsjVO {

	/**
	 * <p>
	 * 卷板号
	 * </p>
	 */
	@QF(alias = "jbno")
	private String jbno;

	/**
	 * <p>
	 * 原板制造商NO
	 * </p>
	 */
	@QF(alias = "zzno")
	private String zzno;

	/**
	 * <p>
	 * PILE区分
	 * </p>
	 */
	@QF(alias = "plqf")
	private String plqf;
	/**
	 * <p>
	 * SL生产线别
	 * </p>
	 */
	@QF(alias = "slin")
	private String slin;

	/**
	 * <p>
	 * 班别
	 * </p>
	 */
	@QF(alias = "ban")
	private String ban;

	/**
	 * <p>
	 * 组别
	 * </p>
	 */
	@QF(alias = "zu")
	private String zu;
	/**
	 * <p>
	 * 指示书号
	 * </p>
	 */
	@QF(alias = "zsno")
	private String zsno;

	/**
	 * <p>
	 * 订货行号
	 * </p>
	 */
	@QF(alias = "dhno")
	private String dhno;

	/**
	 * <p>
	 * 用户略称
	 * </p>
	 */
	@QF(alias = "abbr")
	private String abbr;
	/**
	 * <p>
	 * 实际硬度
	 * </p>
	 */
	@QF(alias = "ying")
	private Integer ying;
	/**
	 * <p>
	 * 制品尺寸·厚
	 * </p>
	 */
	@QF(alias = "houu")
	private Double houu;
	/**
	 * <p>
	 * 制品尺寸·宽
	 * </p>
	 */
	@QF(alias = "kuan")
	private Double kuan;
	/**
	 * <p>
	 * 制品尺寸·长
	 * </p>
	 */
	@QF(alias = "cang")
	private Double cang;
	/**
	 * <p>
	 * 制品重量
	 * </p>
	 */
	@QF(alias = "zpzl")
	private Integer zpzl;

	/**
	 * <p>
	 * 产量代码
	 * </p>
	 */
	@QF(alias = "chan")
	private String chan;

	/**
	 * <p>
	 * 等级
	 * </p>
	 */
	@QF(alias = "deng")
	private String deng;

	/**
	 * <p>
	 * 枚数
	 * </p>
	 */
	@QF(alias = "zshu")
	private Integer zshu;

	/**
	 * <p>
	 * 附着量.正面
	 * </p>
	 */
	@QF(alias = "fuzm")
	private Double fuzm;

	/**
	 * <p>
	 * 附着量·反面
	 * </p>
	 */
	@QF(alias = "fufm")
	private Double fufm;

	/**
	 * <p>
	 * 删除标记
	 * </p>
	 */
	@QF(alias = "scbj")
	private String scbj;
	/**
	 * <p>
	 * 状态
	 * </p>
	 */
	@QF(alias = "stat")
	private String stat;

	/**
	 * <p>
	 * 作成时间
	 * </p>
	 */
	@QF(alias = "crea")
	private Date crea;

	/**
	 * <p>
	 * 更新时间
	 * </p>
	 */
	@QF(alias = "upda")
	private Date upda;

	/**
	 * <p>
	 * 表面
	 * </p>
	 */
	@QF(alias = "face")
	private String face;

	/**
	 * <p>
	 * 规格代码
	 * </p>
	 */
	@QF(alias = "ggno")
	private String ggno;
	/**
	 * <p>
	 * 实际重量
	 * </p>
	 */
	@QF(alias = "sjzl")
	private Integer sjzl;

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getSlin() {
		return slin;
	}

	public void setSlin(String slin) {
		this.slin = slin;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
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

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
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

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public Integer getZshu() {
		return zshu;
	}

	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	public Double getFuzm() {
		return fuzm;
	}

	public void setFuzm(Double fuzm) {
		this.fuzm = fuzm;
	}

	public Double getFufm() {
		return fufm;
	}

	public void setFufm(Double fufm) {
		this.fufm = fufm;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public Date getUpda() {
		return upda;
	}

	public void setUpda(Date upda) {
		this.upda = upda;
	}

	public String getZzno() {
		return zzno;
	}

	public void setZzno(String zzno) {
		this.zzno = zzno;
	}

	public String getPlqf() {
		return plqf;
	}

	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public Integer getSjzl() {
		return sjzl;
	}

	public void setSjzl(Integer sjzl) {
		this.sjzl = sjzl;
	}

}
