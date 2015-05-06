package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * SL/清整实绩日志
 * 
 * @author 方元龙[F.YuanLong@gmail.com]
 */
@Entity
@Table(name = "SINO_SLJZLP")
public class SljzLp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	@Column(name = "RSV2_", columnDefinition = "numeric(20,0)")
	private BigDecimal rsv2;
	/**
	 * 指示情报-FK
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "ZSFMT_", referencedColumnName = "ID_", nullable = false)
	private Zsfmt zsfmt;
	/**
	 * 处理区分
	 */
	@Column(name = "CLQF_", length = 1)
	private String clqf;
	/**
	 * PILENO(装入中止卷板NO)
	 */
	@Column(name = "PLNO_", length = 10)
	private String plno;
	/**
	 * 原板制造商NO
	 */
	@Column(name = "ZZNO_", length = 1)
	private String zzno;
	/**
	 * PILE区分
	 */
	@Column(name = "PLQF_", length = 1)
	private String plqf;
	/**
	 * 计算重量
	 */
	@Column(name = "JSZL_", columnDefinition = "numeric(5,0)")
	private Integer jszl;
	/**
	 * 实际重量
	 */
	@Column(name = "SJZL_", columnDefinition = "numeric(5,0)")
	private Integer sjzl;
	/**
	 * 净重量
	 */
	@Column(name = "JNZL_", columnDefinition = "numeric(5,0)")
	private Integer jnzl;
	/**
	 * 毛重量
	 */
	@Column(name = "MAZL_", columnDefinition = "numeric(5,0)")
	private Integer mazl;
	/**
	 * 制品重量
	 */
	@Column(name = "ZPZL_", columnDefinition = "numeric(5,0)")
	private Integer zpzl;
	/**
	 * 张数
	 */
	@Column(name = "ZSHU_", columnDefinition = "numeric(4,0)")
	private Integer zshu;
	/**
	 * 现品尺寸-厚
	 */
	@Column(name = "XPHO_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double xpho;
	/**
	 * 现品尺寸-宽
	 */
	@Column(name = "XPKN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpkn;
	/**
	 * 现品尺寸-长
	 */
	@Column(name = "XPCN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpcn;
	/**
	 * 产量
	 */
	@Column(name = "CHAN_", length = 1)
	private String chan;
	/**
	 * 等级
	 */
	@Column(name = "DENG_", length = 3)
	private String deng;
	/**
	 * 处置代码
	 */
	@Column(name = "CZDM_", length = 1)
	private String czdm;
	/**
	 * 缺陷代码
	 */
	@Column(name = "QQDM_", length = 2)
	private String qqdm;
	/**
	 * 实绩附着量-正面
	 */
	@Column(name = "SCZM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double sczm;
	/**
	 * 实绩附着量-反面
	 */
	@Column(name = "SCFM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double scfm;
	/**
	 * 硬度
	 */
	@Column(name = "YING_", columnDefinition = "numeric(3,0)")
	private Integer ying;
	/**
	 * 装入中止卷板标记
	 */
	@Column(name = "ZRZZ_", length = 1)
	private String zrzz;
	/**
	 * SL流水代码
	 */
	@Column(name = "SLLN_", length = 1)
	private String slln;
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
	 * 采取PILER
	 */
	@Column(name = "CQPL_", length = 1)
	private String cqpl;
	/**
	 * 实测宽
	 */
	@Column(name = "SCKN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double sckn;
	/**
	 * 实测剪断长
	 */
	@Column(name = "JDCN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double jdcn;
	/**
	 * 实绩品种等级
	 */
	@Column(name = "SPDJ_", length = 1)
	private String spdj;
	/**
	 * 中途PILENO(1)
	 */
	@Column(name = "PLN1_", length = 10)
	private String pln1;
	/**
	 * PILE区分(1)
	 */
	@Column(name = "PLQ1_", length = 1)
	private String plq1;
	/**
	 * 原板制造商NO(1)
	 */
	@Column(name = "ZZN1_", length = 1)
	private String zzn1;
	/**
	 * 张数(1)
	 */
	@Column(name = "ZSU1_", columnDefinition = "numeric(4,0)")
	private Integer zsu1;
	/**
	 * 中途PILENO(2)
	 */
	@Column(name = "PLN2_", length = 10)
	private String pln2;
	/**
	 * PILE区分(2)
	 */
	@Column(name = "PLQ2_", length = 1)
	private String plq2;
	/**
	 * 原板制造商NO(2)
	 */
	@Column(name = "ZZN2_", length = 1)
	private String zzn2;
	/**
	 * 张数(2)
	 */
	@Column(name = "ZSU2_", columnDefinition = "numeric(4,0)")
	private Integer zsu2;
	/**
	 * 中途PILENO(3)
	 */
	@Column(name = "PLN3_", length = 10)
	private String pln3;
	/**
	 * PILE区分(3)
	 */
	@Column(name = "PLQ3_", length = 1)
	private String plq3;
	/**
	 * 原板制造商NO(3)
	 */
	@Column(name = "ZZN3_", length = 1)
	private String zzn3;
	/**
	 * 张数(3)
	 */
	@Column(name = "ZSU3_", columnDefinition = "numeric(4,0)")
	private Integer zsu3;
	/**
	 * 缺陷1-缺陷代码
	 */
	@Column(name = "QXN1_", length = 2)
	private String qxn1;
	/**
	 * 缺陷1-等级
	 */
	@Column(name = "QXD1_", length = 1)
	private String qxd1;
	/**
	 * 缺陷1-张数
	 */
	@Column(name = "QXZ1_", precision = 0, scale = 4)
	private String qxz1;
	/**
	 * 缺陷2-缺陷代码
	 */
	@Column(name = "QXN2_", length = 2)
	private String qxn2;
	/**
	 * 缺陷2-等级
	 */
	@Column(name = "QXD2_", length = 1)
	private String qxd2;
	/**
	 * 缺陷2-张数
	 */
	@Column(name = "QXZ2_", precision = 0, scale = 4)
	private String qxz2;
	/**
	 * 缺陷3-缺陷代码
	 */
	@Column(name = "QXN3_", length = 2)
	private String qxn3;
	/**
	 * 缺陷3-等级
	 */
	@Column(name = "QXD3_", length = 1)
	private String qxd3;
	/**
	 * 缺陷3-张数
	 */
	@Column(name = "QXZ3_", precision = 0, scale = 4)
	private String qxz3;
	/**
	 * 缺陷4-缺陷代码
	 */
	@Column(name = "QXN4_", length = 2)
	private String qxn4;
	/**
	 * 缺陷4-等级
	 */
	@Column(name = "QXD4_", length = 1)
	private String qxd4;
	/**
	 * 缺陷4-张数
	 */
	@Column(name = "QXZ4_", precision = 0, scale = 4)
	private String qxz4;
	/**
	 * 缺陷5-缺陷代码
	 */
	@Column(name = "QXN5_", length = 2)
	private String qxn5;
	/**
	 * 缺陷5-等级
	 */
	@Column(name = "QXD5_", length = 1)
	private String qxd5;
	/**
	 * 缺陷5-张数
	 */
	@Column(name = "QXZ5_", precision = 0, scale = 4)
	private String qxz5;
	/**
	 * 缺陷6-缺陷代码
	 */
	@Column(name = "QXN6_", length = 2)
	private String qxn6;
	/**
	 * 缺陷6-等级
	 */
	@Column(name = "QXD6_", length = 1)
	private String qxd6;
	/**
	 * 缺陷6-张数
	 */
	@Column(name = "QXZ6_", precision = 0, scale = 4)
	private String qxz6;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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
	 * @return the clqf
	 */
	public String getClqf() {
		return clqf;
	}

	/**
	 * @param clqf
	 *            the clqf to set
	 */
	public void setClqf(String clqf) {
		this.clqf = clqf;
	}

	/**
	 * @return the plno
	 */
	public String getPlno() {
		return plno;
	}

	/**
	 * @param plno
	 *            the plno to set
	 */
	public void setPlno(String plno) {
		this.plno = plno;
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
	 * @return the jszl
	 */
	public Integer getJszl() {
		return jszl;
	}

	/**
	 * @param jszl
	 *            the jszl to set
	 */
	public void setJszl(Integer jszl) {
		this.jszl = jszl;
	}

	/**
	 * @return the sjzl
	 */
	public Integer getSjzl() {
		return sjzl;
	}

	/**
	 * @param sjzl
	 *            the sjzl to set
	 */
	public void setSjzl(Integer sjzl) {
		this.sjzl = sjzl;
	}

	/**
	 * @return the jnzl
	 */
	public Integer getJnzl() {
		return jnzl;
	}

	/**
	 * @param jnzl
	 *            the jnzl to set
	 */
	public void setJnzl(Integer jnzl) {
		this.jnzl = jnzl;
	}

	/**
	 * @return the mazl
	 */
	public Integer getMazl() {
		return mazl;
	}

	/**
	 * @param mazl
	 *            the mazl to set
	 */
	public void setMazl(Integer mazl) {
		this.mazl = mazl;
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
	 * @return the zshu
	 */
	public Integer getZshu() {
		return zshu;
	}

	/**
	 * @param zshu
	 *            the zshu to set
	 */
	public void setZshu(Integer zshu) {
		this.zshu = zshu;
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
	 * @return the chan
	 */
	public String getChan() {
		return chan;
	}

	/**
	 * @param chan
	 *            the chan to set
	 */
	public void setChan(String chan) {
		this.chan = chan;
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
	 * @return the czdm
	 */
	public String getCzdm() {
		return czdm;
	}

	/**
	 * @param czdm
	 *            the czdm to set
	 */
	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}

	/**
	 * @return the qqdm
	 */
	public String getQqdm() {
		return qqdm;
	}

	/**
	 * @param qqdm
	 *            the qqdm to set
	 */
	public void setQqdm(String qqdm) {
		this.qqdm = qqdm;
	}

	/**
	 * @return the sczm
	 */
	public Double getSczm() {
		return sczm;
	}

	/**
	 * @param sczm
	 *            the sczm to set
	 */
	public void setSczm(Double sczm) {
		this.sczm = sczm;
	}

	/**
	 * @return the scfm
	 */
	public Double getScfm() {
		return scfm;
	}

	/**
	 * @param scfm
	 *            the scfm to set
	 */
	public void setScfm(Double scfm) {
		this.scfm = scfm;
	}

	/**
	 * @return the ying
	 */
	public Integer getYing() {
		return ying;
	}

	/**
	 * @param ying
	 *            the ying to set
	 */
	public void setYing(Integer ying) {
		this.ying = ying;
	}

	/**
	 * @return the zrzz
	 */
	public String getZrzz() {
		return zrzz;
	}

	/**
	 * @param zrzz
	 *            the zrzz to set
	 */
	public void setZrzz(String zrzz) {
		this.zrzz = zrzz;
	}

	/**
	 * @return the slln
	 */
	public String getSlln() {
		return slln;
	}

	/**
	 * @param slln
	 *            the slln to set
	 */
	public void setSlln(String slln) {
		this.slln = slln;
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
	 * @return the cqpl
	 */
	public String getCqpl() {
		return cqpl;
	}

	/**
	 * @param cqpl
	 *            the cqpl to set
	 */
	public void setCqpl(String cqpl) {
		this.cqpl = cqpl;
	}

	/**
	 * @return the sckn
	 */
	public Double getSckn() {
		return sckn;
	}

	/**
	 * @param sckn
	 *            the sckn to set
	 */
	public void setSckn(Double sckn) {
		this.sckn = sckn;
	}

	/**
	 * @return the jdcn
	 */
	public Double getJdcn() {
		return jdcn;
	}

	/**
	 * @param jdcn
	 *            the jdcn to set
	 */
	public void setJdcn(Double jdcn) {
		this.jdcn = jdcn;
	}

	/**
	 * @return the spdj
	 */
	public String getSpdj() {
		return spdj;
	}

	/**
	 * @param spdj
	 *            the spdj to set
	 */
	public void setSpdj(String spdj) {
		this.spdj = spdj;
	}

	/**
	 * @return the pln1
	 */
	public String getPln1() {
		return pln1;
	}

	/**
	 * @param pln1
	 *            the pln1 to set
	 */
	public void setPln1(String pln1) {
		this.pln1 = pln1;
	}

	/**
	 * @return the plq1
	 */
	public String getPlq1() {
		return plq1;
	}

	/**
	 * @param plq1
	 *            the plq1 to set
	 */
	public void setPlq1(String plq1) {
		this.plq1 = plq1;
	}

	/**
	 * @return the zzn1
	 */
	public String getZzn1() {
		return zzn1;
	}

	/**
	 * @param zzn1
	 *            the zzn1 to set
	 */
	public void setZzn1(String zzn1) {
		this.zzn1 = zzn1;
	}

	/**
	 * @return the zsu1
	 */
	public Integer getZsu1() {
		return zsu1;
	}

	/**
	 * @param zsu1
	 *            the zsu1 to set
	 */
	public void setZsu1(Integer zsu1) {
		this.zsu1 = zsu1;
	}

	/**
	 * @return the pln2
	 */
	public String getPln2() {
		return pln2;
	}

	/**
	 * @param pln2
	 *            the pln2 to set
	 */
	public void setPln2(String pln2) {
		this.pln2 = pln2;
	}

	/**
	 * @return the plq2
	 */
	public String getPlq2() {
		return plq2;
	}

	/**
	 * @param plq2
	 *            the plq2 to set
	 */
	public void setPlq2(String plq2) {
		this.plq2 = plq2;
	}

	/**
	 * @return the zzn2
	 */
	public String getZzn2() {
		return zzn2;
	}

	/**
	 * @param zzn2
	 *            the zzn2 to set
	 */
	public void setZzn2(String zzn2) {
		this.zzn2 = zzn2;
	}

	/**
	 * @return the zsu2
	 */
	public Integer getZsu2() {
		return zsu2;
	}

	/**
	 * @param zsu2
	 *            the zsu2 to set
	 */
	public void setZsu2(Integer zsu2) {
		this.zsu2 = zsu2;
	}

	/**
	 * @return the pln3
	 */
	public String getPln3() {
		return pln3;
	}

	/**
	 * @param pln3
	 *            the pln3 to set
	 */
	public void setPln3(String pln3) {
		this.pln3 = pln3;
	}

	/**
	 * @return the plq3
	 */
	public String getPlq3() {
		return plq3;
	}

	/**
	 * @param plq3
	 *            the plq3 to set
	 */
	public void setPlq3(String plq3) {
		this.plq3 = plq3;
	}

	/**
	 * @return the zzn3
	 */
	public String getZzn3() {
		return zzn3;
	}

	/**
	 * @param zzn3
	 *            the zzn3 to set
	 */
	public void setZzn3(String zzn3) {
		this.zzn3 = zzn3;
	}

	/**
	 * @return the zsu3
	 */
	public Integer getZsu3() {
		return zsu3;
	}

	/**
	 * @param zsu3
	 *            the zsu3 to set
	 */
	public void setZsu3(Integer zsu3) {
		this.zsu3 = zsu3;
	}

	/**
	 * @return the qxn1
	 */
	public String getQxn1() {
		return qxn1;
	}

	/**
	 * @param qxn1
	 *            the qxn1 to set
	 */
	public void setQxn1(String qxn1) {
		this.qxn1 = qxn1;
	}

	/**
	 * @return the qxd1
	 */
	public String getQxd1() {
		return qxd1;
	}

	/**
	 * @param qxd1
	 *            the qxd1 to set
	 */
	public void setQxd1(String qxd1) {
		this.qxd1 = qxd1;
	}

	/**
	 * @return the qxz1
	 */
	public String getQxz1() {
		return qxz1;
	}

	/**
	 * @param qxz1
	 *            the qxz1 to set
	 */
	public void setQxz1(String qxz1) {
		this.qxz1 = qxz1;
	}

	/**
	 * @return the qxn2
	 */
	public String getQxn2() {
		return qxn2;
	}

	/**
	 * @param qxn2
	 *            the qxn2 to set
	 */
	public void setQxn2(String qxn2) {
		this.qxn2 = qxn2;
	}

	/**
	 * @return the qxd2
	 */
	public String getQxd2() {
		return qxd2;
	}

	/**
	 * @param qxd2
	 *            the qxd2 to set
	 */
	public void setQxd2(String qxd2) {
		this.qxd2 = qxd2;
	}

	/**
	 * @return the qxz2
	 */
	public String getQxz2() {
		return qxz2;
	}

	/**
	 * @param qxz2
	 *            the qxz2 to set
	 */
	public void setQxz2(String qxz2) {
		this.qxz2 = qxz2;
	}

	/**
	 * @return the qxn3
	 */
	public String getQxn3() {
		return qxn3;
	}

	/**
	 * @param qxn3
	 *            the qxn3 to set
	 */
	public void setQxn3(String qxn3) {
		this.qxn3 = qxn3;
	}

	/**
	 * @return the qxd3
	 */
	public String getQxd3() {
		return qxd3;
	}

	/**
	 * @param qxd3
	 *            the qxd3 to set
	 */
	public void setQxd3(String qxd3) {
		this.qxd3 = qxd3;
	}

	/**
	 * @return the qxz3
	 */
	public String getQxz3() {
		return qxz3;
	}

	/**
	 * @param qxz3
	 *            the qxz3 to set
	 */
	public void setQxz3(String qxz3) {
		this.qxz3 = qxz3;
	}

	/**
	 * @return the qxn4
	 */
	public String getQxn4() {
		return qxn4;
	}

	/**
	 * @param qxn4
	 *            the qxn4 to set
	 */
	public void setQxn4(String qxn4) {
		this.qxn4 = qxn4;
	}

	/**
	 * @return the qxd4
	 */
	public String getQxd4() {
		return qxd4;
	}

	/**
	 * @param qxd4
	 *            the qxd4 to set
	 */
	public void setQxd4(String qxd4) {
		this.qxd4 = qxd4;
	}

	/**
	 * @return the qxz4
	 */
	public String getQxz4() {
		return qxz4;
	}

	/**
	 * @param qxz4
	 *            the qxz4 to set
	 */
	public void setQxz4(String qxz4) {
		this.qxz4 = qxz4;
	}

	/**
	 * @return the qxn5
	 */
	public String getQxn5() {
		return qxn5;
	}

	/**
	 * @param qxn5
	 *            the qxn5 to set
	 */
	public void setQxn5(String qxn5) {
		this.qxn5 = qxn5;
	}

	/**
	 * @return the qxd5
	 */
	public String getQxd5() {
		return qxd5;
	}

	/**
	 * @param qxd5
	 *            the qxd5 to set
	 */
	public void setQxd5(String qxd5) {
		this.qxd5 = qxd5;
	}

	/**
	 * @return the qxz5
	 */
	public String getQxz5() {
		return qxz5;
	}

	/**
	 * @param qxz5
	 *            the qxz5 to set
	 */
	public void setQxz5(String qxz5) {
		this.qxz5 = qxz5;
	}

	/**
	 * @return the qxn6
	 */
	public String getQxn6() {
		return qxn6;
	}

	/**
	 * @param qxn6
	 *            the qxn6 to set
	 */
	public void setQxn6(String qxn6) {
		this.qxn6 = qxn6;
	}

	/**
	 * @return the qxd6
	 */
	public String getQxd6() {
		return qxd6;
	}

	/**
	 * @param qxd6
	 *            the qxd6 to set
	 */
	public void setQxd6(String qxd6) {
		this.qxd6 = qxd6;
	}

	/**
	 * @return the qxz6
	 */
	public String getQxz6() {
		return qxz6;
	}

	/**
	 * @param qxz6
	 *            the qxz6 to set
	 */
	public void setQxz6(String qxz6) {
		this.qxz6 = qxz6;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
