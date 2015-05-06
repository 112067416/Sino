package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 装箱指示DB2
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_ZNG2TP")
@IdClass(Zng2TpPk.class)
public class Zng2Tp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Zng1Tp zng1Tp;

	/**
	 * 制品No.
	 */
	@Id
	private String jbno;

	/**
	 * 作成时间（年月日时分秒）,原字段TOCREA
	 */
	@Column(name = "CREA_")
	private java.util.Date crea;

	/**
	 * 更新时间（年月日时分秒）,原字段TOUPDA
	 */
	@Column(name = "UPDA_")
	private java.util.Date upda;

	/**
	 * 状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 制品顺序
	 */
	@Column(name = "ZPSS_")
	private Integer zpss;
	/**
	 * 制品重量
	 */
	@Column(name = "ZPZL_")
	private Integer zpzl;
	/**
	 * 堆场代码
	 */
	@Column(name = "DCNO_", length = 4)
	private String dcno;

	/**
	 * 包装张数
	 */
	@Column(name = "ZSHU_")
	private Integer zshu;

	/**
	 * 卷上长
	 */
	@Column(name = "JSCN_")
	private Integer jscn;

	/**
	 * 制造日期
	 */
	@Column(name = "ZZNY_")
	private Date zzny;

	/**
	 * 车牌号
	 */
	@Column(name = "CPNO_", length = 10)
	private String cpno;

	/**
	 * 产量代码
	 */
	@Column(name = "CHAN_", length = 2)
	private String chan;

	/**
	 * Pile区分
	 */
	@Column(name = "PLQF_", length = 1)
	private String plqf;

	/**
	 * 原板制造商No
	 */
	@Column(name = "ZZNO_", length = 1)
	private String zzno;

	/**
	 * 出口包装No.
	 */
	@Column(name = "CKNO_")
	private Integer ckno;

	/**
	 * 计算重量
	 */
	@Column(name = "JSZL_")
	private Integer jszl;

	/**
	 * 实际重量
	 */
	@Column(name = "SJZL_")
	private Integer sjzl;
	/**
	 * 毛重量
	 */
	@Column(name = "MAZL_")
	private Integer mazl;

	/**
	 * 净重量
	 */
	@Column(name = "JNZL_")
	private Integer jnzl;

	/**
	 * 实测硬度值
	 */
	@Column(name = "LLYD_")
	private Integer llyd;

	/**
	 * 实绩附着量.正面
	 */
	@Column(name = "SCZM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double sczm;
	/**
	 * 实绩附着量.反面
	 */
	@Column(name = "SCFM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double scfm;
	/**
	 * 入侧端COIL/PILE
	 */
	@Column(name = "RCZPNO_", length = 11)
	private String rczpno;

	public Zng1Tp getZng1Tp() {
		return zng1Tp;
	}

	public void setZng1Tp(Zng1Tp zng1Tp) {
		this.zng1Tp = zng1Tp;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public java.util.Date getCrea() {
		return crea;
	}

	public void setCrea(java.util.Date crea) {
		this.crea = crea;
	}

	public java.util.Date getUpda() {
		return upda;
	}

	public void setUpda(java.util.Date upda) {
		this.upda = upda;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Integer getZpss() {
		return zpss;
	}

	public void setZpss(Integer zpss) {
		this.zpss = zpss;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public String getDcno() {
		return dcno;
	}

	public void setDcno(String dcno) {
		this.dcno = dcno;
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

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public String getPlqf() {
		return plqf;
	}

	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	public String getZzno() {
		return zzno;
	}

	public void setZzno(String zzno) {
		this.zzno = zzno;
	}

	public Integer getCkno() {
		return ckno;
	}

	public void setCkno(Integer ckno) {
		this.ckno = ckno;
	}

	public Integer getJszl() {
		return jszl;
	}

	public void setJszl(Integer jszl) {
		this.jszl = jszl;
	}

	public Integer getSjzl() {
		return sjzl;
	}

	public void setSjzl(Integer sjzl) {
		this.sjzl = sjzl;
	}

	public Integer getMazl() {
		return mazl;
	}

	public void setMazl(Integer mazl) {
		this.mazl = mazl;
	}

	public Integer getLlyd() {
		return llyd;
	}

	public void setLlyd(Integer llyd) {
		this.llyd = llyd;
	}

	public Integer getJnzl() {
		return jnzl;
	}

	public void setJnzl(Integer jnzl) {
		this.jnzl = jnzl;
	}

	public Integer getJscn() {
		return jscn;
	}

	public void setJscn(Integer jscn) {
		this.jscn = jscn;
	}

	public Integer getZshu() {
		return zshu;
	}

	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	public Double getSczm() {
		return sczm;
	}

	public void setSczm(Double sczm) {
		this.sczm = sczm;
	}

	public Double getScfm() {
		return scfm;
	}

	public void setScfm(Double scfm) {
		this.scfm = scfm;
	}

	public String getRczpno() {
		return rczpno;
	}

	public void setRczpno(String rczpno) {
		this.rczpno = rczpno;
	}

}
