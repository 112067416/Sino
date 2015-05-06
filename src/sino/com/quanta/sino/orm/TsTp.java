package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 投诉日志
 * 
 * @author 张良[jimsonhappy@126.com]
 */
@Entity
@Table(name = "SINO_TS")
public class TsTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 卷板No/PileNo
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	/**
	 * 订货No
	 */
	@Column(name = "DHNO_", length = 7)
	private String dhno;

	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 2)
	private String line;

	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;

	/**
	 * 现品尺寸.长
	 */
	@Column(name = "XPCN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpcn;

	/**
	 * 现品尺寸.厚
	 */
	@Column(name = "XPHO_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double xpho;

	/**
	 * 现品尺寸.宽
	 */
	@Column(name = "XPKN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpkn;

	/**
	 * 等级
	 */
	@Column(name = "DENG_", length = 3)
	private String deng;

	/**
	 * 表面精加工
	 */
	@Column(name = "FACE_", length = 2)
	private String face;

	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;

	/**
	 * 运输公司名称
	 */
	@Column(name = "YSNM_", length = 64)
	private String ysnm;
	/**
	 * 制造年月日
	 */
	@Column(name = "ZZNY_")
	private Date zzny;

	/**
	 * 车牌号
	 */
	@Column(name = "CPNO_", length = 10)
	private String cpno;
	/**
	 * 原板制造商No
	 */
	@Column(name = "ZZNO_", length = 1)
	private String zzno;
	/**
	 * Pile区分
	 */
	@Column(name = "PLQF_", length = 1)
	private String plqf;
	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;
	/**
	 * 附着量.单位
	 */
	@Column(name = "FUDW_", length = 2)
	private String fudw;

	/**
	 * 实绩附着量.反面
	 */
	@Column(name = "SCFM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double scfm;

	/**
	 * 实绩附着量.正面
	 */
	@Column(name = "SCZM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double sczm;

	/**
	 * 制品重量
	 */
	@Column(name = "ZPZL_")
	private Integer zpzl;

	/**
	 * 张数(包装单价)
	 */
	@Column(name = "ZSHU_")
	private Integer zshu;

	/**
	 * 出货日期
	 */
	@Column(name = "CHRI_")
	private Date chri;

	/**
	 * 投诉日期
	 */
	@Column(name = "TSRI_")
	private Date tsri;
	/**
	 * 备注
	 */
	@Column(name = "BZ_", length = 1024)
	private String bz;
	/**
	 * 装箱指示书号
	 */
	@Column(name = "SHNO_", length = 10)
	private String shno;
	/**
	 * 退货日期
	 */
	@Column(name = "THRI_")
	private Date thri;
	/**
	 * 投诉状态 0：投诉 1：退货 2：厂外转卖 3:撤销投诉
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * id String
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the jbno
	 */
	public String getJbno() {
		return jbno;
	}

	/**
	 * jbno String
	 * 
	 * @param jbno
	 *            the jbno to set
	 */
	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	/**
	 * @return the dhno
	 */
	public String getDhno() {
		return dhno;
	}

	/**
	 * dhno String
	 * 
	 * @param dhno
	 *            the dhno to set
	 */
	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	/**
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}

	/**
	 * abbr String
	 * 
	 * @param abbr
	 *            the abbr to set
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	/**
	 * @return the xpcn
	 */
	public Double getXpcn() {
		return xpcn;
	}

	/**
	 * xpcn Double
	 * 
	 * @param xpcn
	 *            the xpcn to set
	 */
	public void setXpcn(Double xpcn) {
		this.xpcn = xpcn;
	}

	/**
	 * @return the xpho
	 */
	public Double getXpho() {
		return xpho;
	}

	/**
	 * xpho Double
	 * 
	 * @param xpho
	 *            the xpho to set
	 */
	public void setXpho(Double xpho) {
		this.xpho = xpho;
	}

	/**
	 * @return the xpkn
	 */
	public Double getXpkn() {
		return xpkn;
	}

	/**
	 * xpkn Double
	 * 
	 * @param xpkn
	 *            the xpkn to set
	 */
	public void setXpkn(Double xpkn) {
		this.xpkn = xpkn;
	}

	/**
	 * @return the ggno
	 */
	public String getGgno() {
		return ggno;
	}

	/**
	 * ggno String
	 * 
	 * @param ggno
	 *            the ggno to set
	 */
	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	/**
	 * @return the scfm
	 */
	public Double getScfm() {
		return scfm;
	}

	/**
	 * scfm Double
	 * 
	 * @param scfm
	 *            the scfm to set
	 */
	public void setScfm(Double scfm) {
		this.scfm = scfm;
	}

	/**
	 * @return the sczm
	 */
	public Double getSczm() {
		return sczm;
	}

	/**
	 * sczm Double
	 * 
	 * @param sczm
	 *            the sczm to set
	 */
	public void setSczm(Double sczm) {
		this.sczm = sczm;
	}

	/**
	 * @return the zpzl
	 */
	public Integer getZpzl() {
		return zpzl;
	}

	/**
	 * zpzl Integer
	 * 
	 * @param zpzl
	 *            the zpzl to set
	 */
	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	/**
	 * @return the zshu
	 */
	public Integer getZshu() {
		return zshu;
	}

	/**
	 * zshu Integer
	 * 
	 * @param zshu
	 *            the zshu to set
	 */
	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	/**
	 * @return the chri
	 */
	public Date getChri() {
		return chri;
	}

	/**
	 * chri String
	 * 
	 * @param chri
	 *            the chri to set
	 */
	public void setChri(Date chri) {
		this.chri = chri;
	}

	/**
	 * @return the tsri
	 */
	public Date getTsri() {
		return tsri;
	}

	/**
	 * tsri String
	 * 
	 * @param tsri
	 *            the tsri to set
	 */
	public void setTsri(Date tsri) {
		this.tsri = tsri;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * bz String
	 * 
	 * @param bz
	 *            the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * line String
	 * 
	 * @param line
	 *            the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the deng
	 */
	public String getDeng() {
		return deng;
	}

	/**
	 * deng String
	 * 
	 * @param deng
	 *            the deng to set
	 */
	public void setDeng(String deng) {
		this.deng = deng;
	}

	/**
	 * @return the face
	 */
	public String getFace() {
		return face;
	}

	/**
	 * face String
	 * 
	 * @param face
	 *            the face to set
	 */
	public void setFace(String face) {
		this.face = face;
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
	 * @return the thri
	 */
	public Date getThri() {
		return thri;
	}

	/**
	 * @param thri
	 *            the thri to set
	 */
	public void setThri(Date thri) {
		this.thri = thri;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getYsnm() {
		return ysnm;
	}

	public void setYsnm(String ysnm) {
		this.ysnm = ysnm;
	}

	public Date getZzny() {
		return zzny;
	}

	public void setZzny(Date zzny) {
		this.zzny = zzny;
	}

	public String getCpno() {
		return cpno;
	}

	public void setCpno(String cpno) {
		this.cpno = cpno;
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

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getFudw() {
		return fudw;
	}

	public void setFudw(String fudw) {
		this.fudw = fudw;
	}

}
