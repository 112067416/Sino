package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ETL（镀锡）实绩日志。 删除掉“纵向挠度-符号（ZNDF_）”，“横向挠度-符号（HNDF_）”
 * 
 * @author 方元龙[F.YuanLong@gmail.com]
 */
@Entity
@Table(name = "SINO_ETLSLP")
public class EtlsLp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

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
	@Column(name = "RSV2_")
	private Integer rsv2;

	/**
	 * 指示情报-FK
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "ZSFMT_", referencedColumnName = "ID_", nullable = false)
	private Zsfmt zsfmt;

	/**
	 * 现品共通情报-FK
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "XPFMT_", referencedColumnName = "ID_", nullable = false)
	private Xpfmt xpfmt;

	/**
	 * 装入终止
	 */
	@Column(name = "ZRZZ_", length = 1)
	private String zrzz;

	/**
	 * 卷上长
	 */
	@Column(name = "JSCN_")
	private Integer jscn;

	/**
	 * CUT长
	 */
	@Column(name = "CUTC_")
	private Integer cutc;

	/**
	 * LOSS长
	 */
	@Column(name = "LOSC_")
	private Integer losc;
	/**
	 * LOSS长缺陷
	 */
	@Column(name = "LOSQ_", length = 2)
	private String losq;
	/**
	 * Loss长
	 */
	@Column(name = "LOSC2_")
	private Integer losc2;
	/**
	 * Loss长缺陷
	 */
	@Column(name = "LOSQ2_", length = 2)
	private String losq2;

	/**
	 * 检定员
	 */
	@Column(name = "JDYN_", length = 2)
	private String jdyn;

	/**
	 * 计数员
	 */
	@Column(name = "JSYN_", length = 2)
	private String jsyn;

	/**
	 * P-H个数
	 */
	@Column(name = "PHGS_")
	private Integer phgs;

	/**
	 * 溶接个数
	 */
	@Column(name = "RJGS_")
	private Integer rjgs;

	/**
	 * 板厚有无不良
	 */
	@Column(name = "BHBL_", length = 1)
	private String bhbl;

	/**
	 * 卷上TRNo
	 */
	@Column(name = "JSNO_", length = 3)
	private String jsno;

	/**
	 * 边波纹OP-高度
	 */
	@Column(name = "BOPG_", length = 2)
	private String bopg;

	/**
	 * 边波纹OP-间隔
	 */
	@Column(name = "BOPJ_", length = 3)
	private String bopj;

	/**
	 * 边波纹DP-高度
	 */
	@Column(name = "BDRG_", length = 2)
	private String bdrg;

	/**
	 * 边波纹DP-间隔
	 */
	@Column(name = "BDRJ_", length = 3)
	private String bdrj;
	/**
	 * 中波纹高度
	 */
	@Column(name = "ZBOG_", length = 2)
	private String zbog;
	/**
	 * 中波纹间隔
	 */
	@Column(name = "ZBOJ_", length = 3)
	private String zboj;

	/**
	 * 纵向挠度-值
	 */
	@Column(name = "ZNDZ_", length = 3)
	private String zndz;

	/**
	 * 横向挠度-值
	 */
	@Column(name = "HNDZ_", length = 3)
	private String hndz;

	/**
	 * 中波纹等级
	 */
	@Column(name = "ZBWD_", length = 1)
	private String zbwd;

	/**
	 * VARIABLE
	 */
	@Column(name = "VARI_", length = 10)
	private String vari;

	/**
	 * 班别
	 */
	@Column(name = "BAN_", length = 1)
	private String ban;
	/**
	 * 组别
	 */
	@Column(name = "ZU_", length = 1)
	private String zu;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	 * @return the rsv2
	 */
	public Integer getRsv2() {
		return rsv2;
	}

	/**
	 * @param rsv2
	 *            the rsv2 to set
	 */
	public void setRsv2(Integer rsv2) {
		this.rsv2 = rsv2;
	}

	/**
	 * @return the zsfmt
	 */
	public Zsfmt getZsfmt() {
		return zsfmt;
	}

	/**
	 * @param zsfmt
	 *            the zsfmt to set
	 */
	public void setZsfmt(Zsfmt zsfmt) {
		this.zsfmt = zsfmt;
	}

	/**
	 * @return the xpfmt
	 */
	public Xpfmt getXpfmt() {
		return xpfmt;
	}

	/**
	 * @param xpfmt
	 *            the xpfmt to set
	 */
	public void setXpfmt(Xpfmt xpfmt) {
		this.xpfmt = xpfmt;
	}

	/**
	 * @return the jscn
	 */
	public Integer getJscn() {
		return jscn;
	}

	/**
	 * @param jscn
	 *            the jscn to set
	 */
	public void setJscn(Integer jscn) {
		this.jscn = jscn;
	}

	/**
	 * @return the cutc
	 */
	public Integer getCutc() {
		return cutc;
	}

	/**
	 * @param cutc
	 *            the cutc to set
	 */
	public void setCutc(Integer cutc) {
		this.cutc = cutc;
	}

	/**
	 * @return the losc
	 */
	public Integer getLosc() {
		return losc;
	}

	/**
	 * @param losc
	 *            the losc to set
	 */
	public void setLosc(Integer losc) {
		this.losc = losc;
	}

	/**
	 * @return the losq
	 */
	public String getLosq() {
		return losq;
	}

	/**
	 * @param losq
	 *            the losq to set
	 */
	public void setLosq(String losq) {
		this.losq = losq;
	}

	/**
	 * @return the jdyn
	 */
	public String getJdyn() {
		return jdyn;
	}

	/**
	 * @param jdyn
	 *            the jdyn to set
	 */
	public void setJdyn(String jdyn) {
		this.jdyn = jdyn;
	}

	/**
	 * @return the jsyn
	 */
	public String getJsyn() {
		return jsyn;
	}

	/**
	 * @param jsyn
	 *            the jsyn to set
	 */
	public void setJsyn(String jsyn) {
		this.jsyn = jsyn;
	}

	/**
	 * @return the phgs
	 */
	public Integer getPhgs() {
		return phgs;
	}

	/**
	 * @param phgs
	 *            the phgs to set
	 */
	public void setPhgs(Integer phgs) {
		this.phgs = phgs;
	}

	/**
	 * @return the rjgs
	 */
	public Integer getRjgs() {
		return rjgs;
	}

	/**
	 * @param rjgs
	 *            the rjgs to set
	 */
	public void setRjgs(Integer rjgs) {
		this.rjgs = rjgs;
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
	 * @return the jsno
	 */
	public String getJsno() {
		return jsno;
	}

	/**
	 * @param jsno
	 *            the jsno to set
	 */
	public void setJsno(String jsno) {
		this.jsno = jsno;
	}

	public String getBopg() {
		return bopg;
	}

	public void setBopg(String bopg) {
		this.bopg = bopg;
	}

	public String getBopj() {
		return bopj;
	}

	public void setBopj(String bopj) {
		this.bopj = bopj;
	}

	public String getBdrg() {
		return bdrg;
	}

	public void setBdrg(String bdrg) {
		this.bdrg = bdrg;
	}

	public String getBdrj() {
		return bdrj;
	}

	public void setBdrj(String bdrj) {
		this.bdrj = bdrj;
	}

	public String getZndz() {
		return zndz;
	}

	public void setZndz(String zndz) {
		this.zndz = zndz;
	}

	public String getHndz() {
		return hndz;
	}

	public void setHndz(String hndz) {
		this.hndz = hndz;
	}

	/**
	 * @return the zbwd
	 */
	public String getZbwd() {
		return zbwd;
	}

	/**
	 * @param zbwd
	 *            the zbwd to set
	 */
	public void setZbwd(String zbwd) {
		this.zbwd = zbwd;
	}

	public String getVari() {
		return vari;
	}

	public void setVari(String vari) {
		this.vari = vari;
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

	public String getZrzz() {
		return zrzz;
	}

	public void setZrzz(String zrzz) {
		this.zrzz = zrzz;
	}

	public String getZbog() {
		return zbog;
	}

	public void setZbog(String zbog) {
		this.zbog = zbog;
	}

	public String getZboj() {
		return zboj;
	}

	public void setZboj(String zboj) {
		this.zboj = zboj;
	}

	public Integer getLosc2() {
		return losc2;
	}

	public void setLosc2(Integer losc2) {
		this.losc2 = losc2;
	}

	public String getLosq2() {
		return losq2;
	}

	public void setLosq2(String losq2) {
		this.losq2 = losq2;
	}

}
