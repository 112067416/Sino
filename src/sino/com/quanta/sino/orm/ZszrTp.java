package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 转入卷板指示
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_ZSZRTP")
@IdClass(ZszrTpPk.class)
public class ZszrTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 记录识别,原字段TOFLAG
	 */
	@Column(name = "FLAG_", length = 2)
	private String flag;

	/**
	 * 作成时间（年月日时分秒）,原字段TOCREA
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）,原字段TOUPDA
	 */
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 更新程序名
	 */
	@Column(name = "PROG_", length = 8)
	private String prog;

	/**
	 * 状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 预备1
	 */
	@Column(name = "RSV1_", length = 40)
	private String rsv1;

	/**
	 * 预备2
	 */
	@Column(name = "RSV2_", columnDefinition = "numeric(20,0)")
	private BigDecimal rsv2;

	/**
	 * 指示书No
	 */
	@Id
	@Column(name = "ZSNO_", length = 6)
	private String zsno;
	/**
	 * 装入卷板No/PileNo
	 */
	@Id
	@Column(name = "ZRJB_", length = 11)
	private String zrjb;
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
	 * 制品重量
	 */
	@Column(name = "ZPZL_")
	private Integer zpzl;
	/**
	 * 卷板长
	 */
	@Column(name = "JBCN_")
	private Integer jbcn;
	/**
	 * 溶接个数.酸洗
	 */
	@Column(name = "RJSX_")
	private Integer rjsx;
	/**
	 * 溶接个数.冷延
	 */
	@Column(name = "RJLY_")
	private Integer rjly;
	/**
	 * 板厚不量有无
	 */
	@Column(name = "BHBL_", length = 1)
	private String bhbl;
	/**
	 * 业连No1
	 */
	@Column(name = "YLNO_", length = 256)
	private String ylno;
	/**
	 * 前工程作业年月日
	 */
	@Column(name = "QGCN_")
	private Date qgcn;
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
	 * 现品尺寸.长
	 */
	@Column(name = "XPCN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpcn;
	/**
	 * 运用规格(现品)
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;
	/**
	 * 附着量.单位(现品)
	 */
	@Column(name = "FUDW_", length = 12)
	private String fudw;
	/**
	 * 附着量.正面(现品)
	 */
	@Column(name = "FUZM_", length = 12)
	private String fuzm;
	/**
	 * 附着量.反面(现品)
	 */
	@Column(name = "FUFM_", length = 12)
	private String fufm;
	/**
	 * 涂油种类(现品)
	 */
	@Column(name = "YTYP_", length = 3)
	private String ytyp;
	/**
	 * 涂油量(现品)
	 */
	@Column(name = "YQTY_", length = 2)
	private String yqty;
	/**
	 * 表面精加工符号(现品)
	 */
	@Column(name = "FACE_", length = 2)
	private String face;
	/**
	 * 等级(现品)
	 */
	@Column(name = "DENG_", length = 3)
	private String deng;

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the crea
	 */
	public Date getCrea() {
		return crea;
	}

	/**
	 * @param crea
	 *            the crea to set
	 */
	public void setCrea(Date crea) {
		this.crea = crea;
	}

	/**
	 * @return the upda
	 */
	public Date getUpda() {
		return upda;
	}

	/**
	 * @param upda
	 *            the upda to set
	 */
	public void setUpda(Date upda) {
		this.upda = upda;
	}

	/**
	 * @return the prog
	 */
	public String getProg() {
		return prog;
	}

	/**
	 * @param prog
	 *            the prog to set
	 */
	public void setProg(String prog) {
		this.prog = prog;
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

	/**
	 * @return the rsv1
	 */
	public String getRsv1() {
		return rsv1;
	}

	/**
	 * @param rsv1
	 *            the rsv1 to set
	 */
	public void setRsv1(String rsv1) {
		this.rsv1 = rsv1;
	}

	/**
	 * @return the zsno
	 */
	public String getZsno() {
		return zsno;
	}

	/**
	 * @param zsno
	 *            the zsno to set
	 */
	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	/**
	 * @return the zrjb
	 */
	public String getZrjb() {
		return zrjb;
	}

	/**
	 * @param zrjb
	 *            the zrjb to set
	 */
	public void setZrjb(String zrjb) {
		this.zrjb = zrjb;
	}

	/**
	 * @return the zzno
	 */
	public String getZzno() {
		return zzno;
	}

	/**
	 * @param zzno
	 *            the zzno to set
	 */
	public void setZzno(String zzno) {
		this.zzno = zzno;
	}

	/**
	 * @return the plqf
	 */
	public String getPlqf() {
		return plqf;
	}

	/**
	 * @param plqf
	 *            the plqf to set
	 */
	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	/**
	 * @return the zpzl
	 */
	public Integer getZpzl() {
		return zpzl;
	}

	/**
	 * @param zpzl
	 *            the zpzl to set
	 */
	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	/**
	 * @return the jbcn
	 */
	public Integer getJbcn() {
		return jbcn;
	}

	/**
	 * @param jbcn
	 *            the jbcn to set
	 */
	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

	/**
	 * @return the rjsx
	 */
	public Integer getRjsx() {
		return rjsx;
	}

	/**
	 * @param rjsx
	 *            the rjsx to set
	 */
	public void setRjsx(Integer rjsx) {
		this.rjsx = rjsx;
	}

	/**
	 * @return the rjly
	 */
	public Integer getRjly() {
		return rjly;
	}

	/**
	 * @param rjly
	 *            the rjly to set
	 */
	public void setRjly(Integer rjly) {
		this.rjly = rjly;
	}

	/**
	 * @return the bhbl
	 */
	public String getBhbl() {
		return bhbl;
	}

	/**
	 * @param bhbl
	 *            the bhbl to set
	 */
	public void setBhbl(String bhbl) {
		this.bhbl = bhbl;
	}

	/**
	 * @return the qgcn
	 */
	public Date getQgcn() {
		return qgcn;
	}

	/**
	 * @param qgcn
	 *            the qgcn to set
	 */
	public void setQgcn(Date qgcn) {
		this.qgcn = qgcn;
	}

	/**
	 * @return the xpho
	 */
	public Double getXpho() {
		return xpho;
	}

	/**
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
	 * @param xpkn
	 *            the xpkn to set
	 */
	public void setXpkn(Double xpkn) {
		this.xpkn = xpkn;
	}

	/**
	 * @return the xpcn
	 */
	public Double getXpcn() {
		return xpcn;
	}

	/**
	 * @param xpcn
	 *            the xpcn to set
	 */
	public void setXpcn(Double xpcn) {
		this.xpcn = xpcn;
	}

	/**
	 * @return the yuny
	 */
	public String getYuny() {
		return yuny;
	}

	/**
	 * @param yuny
	 *            the yuny to set
	 */
	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	/**
	 * @return the fudw
	 */
	public String getFudw() {
		return fudw;
	}

	/**
	 * @param fudw
	 *            the fudw to set
	 */
	public void setFudw(String fudw) {
		this.fudw = fudw;
	}

	/**
	 * @return the fuzm
	 */
	public String getFuzm() {
		return fuzm;
	}

	/**
	 * @param fuzm
	 *            the fuzm to set
	 */
	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	/**
	 * @return the fufm
	 */
	public String getFufm() {
		return fufm;
	}

	/**
	 * @param fufm
	 *            the fufm to set
	 */
	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	/**
	 * @return the ytyp
	 */
	public String getYtyp() {
		return ytyp;
	}

	/**
	 * @param ytyp
	 *            the ytyp to set
	 */
	public void setYtyp(String ytyp) {
		this.ytyp = ytyp;
	}

	/**
	 * @return the yqty
	 */
	public String getYqty() {
		return yqty;
	}

	/**
	 * @param yqty
	 *            the yqty to set
	 */
	public void setYqty(String yqty) {
		this.yqty = yqty;
	}

	/**
	 * @return the face
	 */
	public String getFace() {
		return face;
	}

	/**
	 * @param face
	 *            the face to set
	 */
	public void setFace(String face) {
		this.face = face;
	}

	/**
	 * @return the deng
	 */
	public String getDeng() {
		return deng;
	}

	/**
	 * @param deng
	 *            the deng to set
	 */
	public void setDeng(String deng) {
		this.deng = deng;
	}

	/**
	 * @return the rsv2
	 */
	public BigDecimal getRsv2() {
		return rsv2;
	}

	/**
	 * @param rsv2
	 *            the rsv2 to set
	 */
	public void setRsv2(BigDecimal rsv2) {
		this.rsv2 = rsv2;
	}

	public String getYlno() {
		return ylno;
	}

	public void setYlno(String ylno) {
		this.ylno = ylno;
	}

}
