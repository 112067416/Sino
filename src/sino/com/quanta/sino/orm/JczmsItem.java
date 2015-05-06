package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 制品检查证明书明细表
 * 
 * @author 张良[jimsonhappy@126.com]
 */
@Entity
@Table(name = "SINO_JCZMS_ITEM")
public class JczmsItem implements Serializable {

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
	 * 引用制品检查证明书主表主键
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "JCZMS_", referencedColumnName = "ID_", nullable = false)
	private Jczms jczms;

	/**
	 * 行号
	 */
	@Column(name = "ZPNO_", length = 40)
	private String zpno;

	/**
	 * 证明书号
	 */
	@Column(name = "CHUI_", length = 10)
	private String chui;

	/**
	 * 现品尺寸.长
	 */
	@Column(name = "CUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cpcn;

	/**
	 * 现品尺寸.宽
	 */
	@Column(name = "CPKN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cpkn;

	/**
	 * 现品尺寸.厚
	 */
	@Column(name = "CPHO_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double cpho;

	/**
	 * 成分值(C)
	 */
	@Column(name = "CFCC_", columnDefinition = "numeric(4,1)", scale = 4, precision = 1)
	private Double cfcc;

	/**
	 * 成分值(Si)
	 */
	@Column(name = "CFSI_", length = 2)
	private String cfsi;

	/**
	 * 成分值(Mn)
	 */
	@Column(name = "CFMN_", length = 2)
	private String cfmn;

	/**
	 * 成分值(P)
	 */
	@Column(name = "CFPP_", length = 2)
	private String cfpp;

	/**
	 * 成分值(S)
	 */
	@Column(name = "CFSS_", length = 2)
	private String cfss;

	/**
	 * 硬度
	 */
	@Column(name = "YING_")
	private Integer ying;

	/**
	 * 计算重量
	 */
	@Column(name = "JSZL_")
	private Integer jszl;

	/**
	 * 制品数
	 */
	@Column(name = "JSSU_")
	private Integer jssu;

	/**
	 * 计算毛重
	 */
	@Column(name = "JSMZ_")
	private Integer jsmz;

	/**
	 * 包装张数
	 */
	@Column(name = "BZZS_")
	private Integer bzzs;

	/**
	 * 卷板长
	 */
	@Column(name = "JBCN_")
	private Integer jbcn;

	/**
	 * 成分值SOLAL
	 */
	@Column(name = "CFSO_", length = 2)
	private String cfso;

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
	 * 成分值预备1（记录硬度是否做过修改。1：表示修改;反之，没有修改）
	 */
	@Column(name = "CFY1_", length = 2)
	private String cfy1;

	/**
	 * 成分值预备2
	 */
	@Column(name = "CFY2_", length = 2)
	private String cfy2;

	/**
	 * 成分值预备3
	 */
	@Column(name = "CFY3_", length = 2)
	private String cfy3;

	/**
	 * 成分值预备4
	 */
	@Column(name = "CFY4_", length = 2)
	private String cfy4;

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
	 * @return the jczms
	 */
	public Jczms getJczms() {
		return jczms;
	}

	/**
	 * @param jczms
	 *            the jczms to set
	 */
	public void setJczms(Jczms jczms) {
		this.jczms = jczms;
	}

	/**
	 * @return the zpno
	 */
	public String getZpno() {
		return zpno;
	}

	/**
	 * @param zpno
	 *            the zpno to set
	 */
	public void setZpno(String zpno) {
		this.zpno = zpno;
	}

	/**
	 * @return the chui
	 */
	public String getChui() {
		return chui;
	}

	/**
	 * @param chui
	 *            the chui to set
	 */
	public void setChui(String chui) {
		this.chui = chui;
	}

	/**
	 * @return the cpcn
	 */
	public Double getCpcn() {
		return cpcn;
	}

	/**
	 * @param cpcn
	 *            the cpcn to set
	 */
	public void setCpcn(Double cpcn) {
		this.cpcn = cpcn;
	}

	/**
	 * @return the cpkn
	 */
	public Double getCpkn() {
		return cpkn;
	}

	/**
	 * @param cpkn
	 *            the cpkn to set
	 */
	public void setCpkn(Double cpkn) {
		this.cpkn = cpkn;
	}

	/**
	 * @return the cpho
	 */
	public Double getCpho() {
		return cpho;
	}

	/**
	 * @param cpho
	 *            the cpho to set
	 */
	public void setCpho(Double cpho) {
		this.cpho = cpho;
	}

	/**
	 * @return the cfcc
	 */
	public Double getCfcc() {
		return cfcc;
	}

	/**
	 * @param cfcc
	 *            the cfcc to set
	 */
	public void setCfcc(Double cfcc) {
		this.cfcc = cfcc;
	}

	/**
	 * @return the cfsi
	 */
	public String getCfsi() {
		return cfsi;
	}

	/**
	 * @param cfsi
	 *            the cfsi to set
	 */
	public void setCfsi(String cfsi) {
		this.cfsi = cfsi;
	}

	/**
	 * @return the cfmn
	 */
	public String getCfmn() {
		return cfmn;
	}

	/**
	 * @param cfmn
	 *            the cfmn to set
	 */
	public void setCfmn(String cfmn) {
		this.cfmn = cfmn;
	}

	/**
	 * @return the cfpp
	 */
	public String getCfpp() {
		return cfpp;
	}

	/**
	 * @param cfpp
	 *            the cfpp to set
	 */
	public void setCfpp(String cfpp) {
		this.cfpp = cfpp;
	}

	/**
	 * @return the cfss
	 */
	public String getCfss() {
		return cfss;
	}

	/**
	 * @param cfss
	 *            the cfss to set
	 */
	public void setCfss(String cfss) {
		this.cfss = cfss;
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
	 * @return the jssu
	 */
	public Integer getJssu() {
		return jssu;
	}

	/**
	 * @param jssu
	 *            the jssu to set
	 */
	public void setJssu(Integer jssu) {
		this.jssu = jssu;
	}

	/**
	 * @return the jsmz
	 */
	public Integer getJsmz() {
		return jsmz;
	}

	/**
	 * @param jsmz
	 *            the jsmz to set
	 */
	public void setJsmz(Integer jsmz) {
		this.jsmz = jsmz;
	}

	/**
	 * @return the cfso
	 */
	public String getCfso() {
		return cfso;
	}

	/**
	 * @param cfso
	 *            the cfso to set
	 */
	public void setCfso(String cfso) {
		this.cfso = cfso;
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
	 * @return the cfy1
	 */
	public String getCfy1() {
		return cfy1;
	}

	/**
	 * @param cfy1
	 *            the cfy1 to set
	 */
	public void setCfy1(String cfy1) {
		this.cfy1 = cfy1;
	}

	/**
	 * @return the cfy2
	 */
	public String getCfy2() {
		return cfy2;
	}

	/**
	 * @param cfy2
	 *            the cfy2 to set
	 */
	public void setCfy2(String cfy2) {
		this.cfy2 = cfy2;
	}

	/**
	 * @return the cfy3
	 */
	public String getCfy3() {
		return cfy3;
	}

	/**
	 * @param cfy3
	 *            the cfy3 to set
	 */
	public void setCfy3(String cfy3) {
		this.cfy3 = cfy3;
	}

	/**
	 * @return the cfy4
	 */
	public String getCfy4() {
		return cfy4;
	}

	/**
	 * @param cfy4
	 *            the cfy4 to set
	 */
	public void setCfy4(String cfy4) {
		this.cfy4 = cfy4;
	}

	public Integer getBzzs() {
		return bzzs;
	}

	public void setBzzs(Integer bzzs) {
		this.bzzs = bzzs;
	}

	public Integer getJbcn() {
		return jbcn;
	}

	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

}
