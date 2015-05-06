package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 卷板缺陷表保管
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_QXBGTP")
public class QxbgTp implements Serializable {

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
	 * 卷板No
	 */
	@Id
	@Column(name = "JBNO_", length = 10)
	private String jbno;
	/**
	 * 原板制造商No
	 */
	@Column(name = "ZZNO_", length = 1)
	private String zzno;
	/**
	 * 指示书No
	 */
	@Column(name = "ZSNO_", length = 6)
	private String zsno;
	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;
	/**
	 * 尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;
	/**
	 * 尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;
	/**
	 * 附着量
	 */
	@Column(name = "FUGM_", length = 12)
	private String fugm;
	/**
	 * 表面精加工符号
	 */
	@Column(name = "FACE_", length = 2)
	private String face;
	/**
	 * 卷板重量(Kg)
	 */
	@Column(name = "JBZL_")
	private Integer jbzl;
	/**
	 * 卷板长
	 */
	@Column(name = "JBCN_")
	private Integer jbcn;
	/**
	 * 卷板缺陷明细表发行完标记
	 */
	@Column(name = "FXWL_", length = 1)
	private String fxwl;

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
	 * @return the jbno
	 */
	public String getJbno() {
		return jbno;
	}

	/**
	 * @param jbno
	 *            the jbno to set
	 */
	public void setJbno(String jbno) {
		this.jbno = jbno;
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
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}

	/**
	 * @param abbr
	 *            the abbr to set
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	/**
	 * @return the houu
	 */
	public Double getHouu() {
		return houu;
	}

	/**
	 * @param houu
	 *            the houu to set
	 */
	public void setHouu(Double houu) {
		this.houu = houu;
	}

	/**
	 * @return the kuan
	 */
	public Double getKuan() {
		return kuan;
	}

	/**
	 * @param kuan
	 *            the kuan to set
	 */
	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	/**
	 * @return the fugm
	 */
	public String getFugm() {
		return fugm;
	}

	/**
	 * @param fugm
	 *            the fugm to set
	 */
	public void setFugm(String fugm) {
		this.fugm = fugm;
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
	 * @return the jbzl
	 */
	public Integer getJbzl() {
		return jbzl;
	}

	/**
	 * @param jbzl
	 *            the jbzl to set
	 */
	public void setJbzl(Integer jbzl) {
		this.jbzl = jbzl;
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
	 * @return the fxwl
	 */
	public String getFxwl() {
		return fxwl;
	}

	/**
	 * @param fxwl
	 *            the fxwl to set
	 */
	public void setFxwl(String fxwl) {
		this.fxwl = fxwl;
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

}
