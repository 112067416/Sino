package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 制品检查证明书
 * 
 * @author 张良[jimsonhappy@126.com]
 */
@Entity
@Table(name = "SINO_JCZMS")
public class Jczms implements Serializable {

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
	 * 证明书号
	 */
	@Column(name = "JCNO_", length = 40)
	private String jcno;

	/**
	 * 装箱指示书号
	 */
	@Column(name = "ZXNO_", length = 10)
	private String zxno;

	/**
	 * 用户编码
	 */
	@Column(name = "USER_", length = 4)
	private String user;

	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;

	/**
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 16)
	private String name;

	/**
	 * 商社编号
	 */
	@Column(name = "SSNO_", length = 4)
	private String ssno;

	/**
	 * 商社名称
	 */
	@Column(name = "SSMC_", length = 512)
	private String ssmc;

	/**
	 * 品名
	 */
	@Column(name = "PM_", length = 60)
	private String pm;

	/**
	 * 制造商规格略称
	 */
	@Column(name = "GGNM_", length = 40)
	private String ggnm;

	/**
	 * 制作日期
	 */
	@Column(name = "ZZRI_")
	private java.util.Date zzri;

	/**
	 * 附着量.单位
	 */
	@Column(name = "FUDW_", length = 2)
	private String fudw;

	/**
	 * 附着量.正面
	 */
	@Column(name = "FUZM_", length = 3)
	private String fuzm;

	/**
	 * 附着量.反面
	 */
	@Column(name = "FUFM_", length = 3)
	private String fufm;

	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 更新时间（年月日时分秒）
	 */
	@Column(name = "UPDA_")
	private java.util.Date upda;

	/**
	 * 更新者
	 */
	@Column(name = "GXNO_", length = 3)
	private String gxno;

	/**
	 * 检查证明书页数
	 */
	@Column(name = "JCHA_")
	private Integer jcha;

	/**
	 * 出货重量
	 */
	@Column(name = "CHZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double chzl;

	/**
	 * 出货制品数
	 */
	@Column(name = "CHSU_")
	private Integer chsu;

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
	 * 中文备注
	 */
	@Column(name = "ZWBZ_", length = 126)
	private String zwbz;

	/**
	 * 英文备注
	 */
	@Column(name = "YWBZ_", length = 126)
	private String ywbz;
	/**
	 * 硬度.上限值
	 */
	@Column(name = "YMAX_", length = 2)
	private String ymax;
	/**
	 * 硬度.下限值
	 */
	@Column(name = "YMIN_", length = 2)
	private String ymin;
	/**
	 * 附着面.正面.上限值
	 */
	@Column(name = "FUZS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fuzs;
	/**
	 * 附着面.正面.下限值
	 */
	@Column(name = "FUZX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fuzx;
	/**
	 * 附着面.反面.上限值
	 */
	@Column(name = "FUFS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fufs;
	/**
	 * 附着面.反面.下限值
	 */
	@Column(name = "FUFX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fufx;

	/**
	 * 检查证明书提示符
	 */
	@Column(name = "JCTS_", length = 2)
	private String jcts;

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
	 * @return the jcno
	 */
	public String getJcno() {
		return jcno;
	}

	/**
	 * jcno String
	 * 
	 * @param jcno
	 *            the jcno to set
	 */
	public void setJcno(String jcno) {
		this.jcno = jcno;
	}

	/**
	 * @return the zxno
	 */
	public String getZxno() {
		return zxno;
	}

	/**
	 * zxno String
	 * 
	 * @param zxno
	 *            the zxno to set
	 */
	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * user String
	 * 
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
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
	 * @return the ssno
	 */
	public String getSsno() {
		return ssno;
	}

	/**
	 * ssno String
	 * 
	 * @param ssno
	 *            the ssno to set
	 */
	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	/**
	 * @return the ssmc
	 */
	public String getSsmc() {
		return ssmc;
	}

	/**
	 * ssmc String
	 * 
	 * @param ssmc
	 *            the ssmc to set
	 */
	public void setSsmc(String ssmc) {
		this.ssmc = ssmc;
	}

	/**
	 * @return the pm
	 */
	public String getPm() {
		return pm;
	}

	/**
	 * pm String
	 * 
	 * @param pm
	 *            the pm to set
	 */
	public void setPm(String pm) {
		this.pm = pm;
	}

	/**
	 * @return the ggnm
	 */
	public String getGgnm() {
		return ggnm;
	}

	/**
	 * ggnm String
	 * 
	 * @param ggnm
	 *            the ggnm to set
	 */
	public void setGgnm(String ggnm) {
		this.ggnm = ggnm;
	}

	/**
	 * @return the zzri
	 */
	public java.util.Date getZzri() {
		return zzri;
	}

	/**
	 * zzri java.util.Date
	 * 
	 * @param zzri
	 *            the zzri to set
	 */
	public void setZzri(java.util.Date zzri) {
		this.zzri = zzri;
	}

	/**
	 * @return the fudw
	 */
	public String getFudw() {
		return fudw;
	}

	/**
	 * fudw String
	 * 
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
	 * fuzm String
	 * 
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
	 * fufm String
	 * 
	 * @param fufm
	 *            the fufm to set
	 */
	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	/**
	 * @return the ddno
	 */
	public String getDdno() {
		return ddno;
	}

	/**
	 * ddno String
	 * 
	 * @param ddno
	 *            the ddno to set
	 */
	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	/**
	 * @return the upda
	 */
	public java.util.Date getUpda() {
		return upda;
	}

	/**
	 * upda java.util.Date
	 * 
	 * @param upda
	 *            the upda to set
	 */
	public void setUpda(java.util.Date upda) {
		this.upda = upda;
	}

	/**
	 * @return the gxno
	 */
	public String getGxno() {
		return gxno;
	}

	/**
	 * gxno String
	 * 
	 * @param gxno
	 *            the gxno to set
	 */
	public void setGxno(String gxno) {
		this.gxno = gxno;
	}

	public Integer getJcha() {
		return jcha;
	}

	public void setJcha(Integer jcha) {
		this.jcha = jcha;
	}

	public Double getChzl() {
		return chzl;
	}

	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	public Integer getChsu() {
		return chsu;
	}

	public void setChsu(Integer chsu) {
		this.chsu = chsu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getZwbz() {
		return zwbz;
	}

	public void setZwbz(String zwbz) {
		this.zwbz = zwbz;
	}

	public String getYwbz() {
		return ywbz;
	}

	public void setYwbz(String ywbz) {
		this.ywbz = ywbz;
	}

	public String getYmax() {
		return ymax;
	}

	public void setYmax(String ymax) {
		this.ymax = ymax;
	}

	public String getYmin() {
		return ymin;
	}

	public void setYmin(String ymin) {
		this.ymin = ymin;
	}

	public Double getFuzs() {
		return fuzs;
	}

	public void setFuzs(Double fuzs) {
		this.fuzs = fuzs;
	}

	public Double getFuzx() {
		return fuzx;
	}

	public void setFuzx(Double fuzx) {
		this.fuzx = fuzx;
	}

	public Double getFufs() {
		return fufs;
	}

	public void setFufs(Double fufs) {
		this.fufs = fufs;
	}

	public Double getFufx() {
		return fufx;
	}

	public void setFufx(Double fufx) {
		this.fufx = fufx;
	}

	public String getJcts() {
		return jcts;
	}

	public void setJcts(String jcts) {
		this.jcts = jcts;
	}

}
