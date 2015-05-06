package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * 分析作业日志表
 * <p>
 * </p>
 * <p>
 * create: 2010-12-29 下午12:13:03
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_FXZYRZ")
public class FxzyRz implements Serializable {

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
	 * 碱浸液
	 */
	@Column(name = "JJY_")
	private Integer jjy;
	/**
	 * 碱电解液
	 */
	@Column(name = "JDJY_")
	private Integer jdjy;
	/**
	 * 酸电解液H2SO4
	 */
	@Column(name = "SDJY_HSO_")
	private Integer sdjyHso;
	/**
	 * 酸电解液Fe
	 */
	@Column(name = "SDJY_FE_")
	private Integer sdjyFe;
	/**
	 * 锡电镀液Sn
	 */
	@Column(name = "XDDY_SN_")
	private Integer xddySn;
	/**
	 * 锡电镀液Acid
	 */
	@Column(name = "XDDY_ACID_")
	private Integer xddyAcid;
	/**
	 * 锡电镀液ENSA
	 */
	@Column(name = "XDDY_ENSA_")
	private Integer xddyEnsa;
	/**
	 * 锡电镀液Fe
	 */
	@Column(name = "XDDY_FE_")
	private Integer xddyFe;
	/**
	 * 锡电镀液Sludge
	 */
	@Column(name = "XDDY_SLUD_")
	private Integer xddySlud;
	/**
	 * 拖出液Sn
	 */
	@Column(name = "TCY_SN_")
	private Integer tcySn;
	/**
	 * 拖出液Acid
	 */
	@Column(name = "TCY_ACID_")
	private Integer tcyAcid;
	/**
	 * 化学处理液Cr
	 */
	@Column(name = "HXCLY_CR_")
	private Integer hxclyCr;
	/**
	 * 化学处理液pH
	 */
	@Column(name = "HXCLY_PH_")
	private Integer hxclyPh;
	/**
	 * 化学处理液Sludge
	 */
	@Column(name = "HXCLY_SLUD_")
	private Integer hxclySlud;
	/**
	 * 昨日未完成锡附着量
	 */
	@Column(name = "XFZL_ZR_")
	private Integer xfzlZr;
	/**
	 * 今日接收锡附着量
	 */
	@Column(name = "XFZL_JRJS_")
	private Integer xfzlJrjs;

	/**
	 * 今日完成锡附着量
	 */
	@Column(name = "XFZL_JRWC_")
	private Integer xfzlJrwc;
	/**
	 * 今日未完成锡附着量
	 */
	@Column(name = "XFZL_JRWW_")
	private Integer xfzlJrww;
	/**
	 * 昨日未完成铬附着量
	 */
	@Column(name = "GFZL_ZR_")
	private Integer gfzlZr;
	/**
	 * 今日接收铬附着量
	 */
	@Column(name = "GFZL_JRJS_")
	private Integer gfzlJrjs;
	/**
	 * 今日完成铬附着量
	 */
	@Column(name = "GFZL_JRWC_")
	private Integer gfzlJrwc;
	/**
	 * 今日未完成铬附着量
	 */
	@Column(name = "GFZL_JRWW_")
	private Integer gfzlJrww;
	/**
	 * 昨日未完成涂油量
	 */
	@Column(name = "TYL_ZR_")
	private Integer tylZr;
	/**
	 * 今日接收涂油量
	 */
	@Column(name = "TYL_JRJS_")
	private Integer tylJrjs;
	/**
	 * 今日完成涂油量
	 */
	@Column(name = "TYL_JRWC_")
	private Integer tylJrwc;
	/**
	 * 今日未完成涂油量
	 */
	@Column(name = "TYL_JRWW_")
	private Integer tylJrww;
	/**
	 * 昨日未完成表面六价铬
	 */
	@Column(name = "BMLJG_ZR_")
	private Integer bmljgZr;
	/**
	 * 今日接收表面六价铬
	 */
	@Column(name = "BMLJG_JRJS_")
	private Integer bmljgJrjs;
	/**
	 * 今日完成表面六价铬
	 */
	@Column(name = "BMLJG_JRWC_")
	private Integer bmljgJrwc;
	/**
	 * 今日未完成表面六价铬
	 */
	@Column(name = "BMLJG_JRWW_")
	private Integer bmljgJrww;
	/**
	 * 昨日未完成ISV
	 */
	@Column(name = "ISV_ZR_")
	private Integer isvZr;
	/**
	 * 今日接收ISV
	 */
	@Column(name = "ISV_JRJS_")
	private Integer isvJrjs;
	/**
	 * 今日完成ISV
	 */
	@Column(name = "ISV_JRWC_")
	private Integer isvJrwc;
	/**
	 * 今日未完成ISV
	 */
	@Column(name = "ISV_JRWW_")
	private Integer isvJrww;
	/**
	 * 昨日未完成TCS
	 */
	@Column(name = "TCS_ZR_")
	private Integer tcsZr;
	/**
	 * 今日接收TCS
	 */
	@Column(name = "TCS_JRJS_")
	private Integer tcsJrjs;
	/**
	 * 今日完成TCS
	 */
	@Column(name = "TCS_JRWC_")
	private Integer tcsJrwc;
	/**
	 * 今日未完成TCS
	 */
	@Column(name = "TCS_JRWW_")
	private Integer tcsJrww;
	/**
	 * 昨日未完成ATC
	 */
	@Column(name = "ATC_ZR_")
	private Integer atcZr;
	/**
	 * 今日接收ATC
	 */
	@Column(name = "ATC_JRJS_")
	private Integer atcJrjs;
	/**
	 * 今日完成ATC
	 */
	@Column(name = "ATC_JRWC_")
	private Integer atcJrwc;
	/**
	 * 今日未完成ATC
	 */
	@Column(name = "ATC_JRWW_")
	private Integer atcJrww;
	/**
	 * 昨日未完成TCV
	 */
	@Column(name = "TCV_ZR_")
	private Integer tcvZr;
	/**
	 * 今日接收TCV
	 */
	@Column(name = "TCV_JRJS_")
	private Integer tcvJrjs;
	/**
	 * 今日完成TCV
	 */
	@Column(name = "TCV_JRWC_")
	private Integer tcvJrwc;
	/**
	 * 今日未完成TCV
	 */
	@Column(name = "TCV_JRWW_")
	private Integer tcvJrww;
	/**
	 * 昨日未完成PLT
	 */
	@Column(name = "PLT_ZR_")
	private Integer pltZr;
	/**
	 * 今日接收PLT
	 */
	@Column(name = "PLT_JRJS_")
	private Integer pltJrjs;
	/**
	 * 今日完成PLT
	 */
	@Column(name = "PLT_JRWC_")
	private Integer pltJrwc;
	/**
	 * 今日未完成PLT
	 */
	@Column(name = "PLT_JRWW_")
	private Integer pltJrww;
	/**
	 * 昨日未完成涂料密着性
	 */
	@Column(name = "TLMZX_ZR_")
	private Integer tlmzxZr;
	/**
	 * 今日接收涂料密着性
	 */
	@Column(name = "TLMZX_JRJS_")
	private Integer tlmzxJrjs;
	/**
	 * 今日完成涂料密着性
	 */
	@Column(name = "TLMZX_JRWC_")
	private Integer tlmzxJrwc;
	/**
	 * 今日未完成涂料密着性
	 */
	@Column(name = "TLMZX_JRWW_")
	private Integer tlmzxJrww;
	/**
	 * 昨日未完成Epon Flow
	 */
	@Column(name = "EPFL_ZR_")
	private Integer epflZr;
	/**
	 * 今日接收Epon Flow
	 */
	@Column(name = "EPFL_JRJS_")
	private Integer epflJrjs;
	/**
	 * 今日完成Epon Flow
	 */
	@Column(name = "EPFL_JRWC_")
	private Integer epflJrwc;
	/**
	 * 今日未完成Epon Flow
	 */
	@Column(name = "EPFL_JRWW_")
	private Integer epflJrww;
	/**
	 * 昨日未完成Smudge
	 */
	@Column(name = "SMUD_ZR_")
	private Integer smudZr;
	/**
	 * 今日接收Smudge
	 */
	@Column(name = "SMUD_JRJS_")
	private Integer smudJrjs;
	/**
	 * 今日完成Smudge
	 */
	@Column(name = "SMUD_JRWC_")
	private Integer smudJrwc;
	/**
	 * 今日未完成Smudge
	 */
	@Column(name = "SMUD_JRWW_")
	private Integer smudJrww;
	/**
	 * 特别事项
	 */

	@Column(name = "TBSX_", length = 3072)
	private String tbsx;

	/**
	 * 其他
	 */
	@Column(name = "QT_", length = 3072)
	private String qt;

	/**
	 * 备注
	 */
	@Column(name = "YCSX_", length = 3072)
	private String ycsx;

	/**
	 * 级别
	 */
	@Column(name = "banzu_", length = 2)
	private String banzu;
	/**
	 * 填报日期
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "TBRQ_")
	private Date tbrq;
	/**
	 * 填报星期
	 */
	@Column(name = "TBXQ_", length = 2)
	private String tbxq;

	/**
	 * 纯水时间1
	 */
	@Column(name = "CSSJ1_", length = 5)
	private String cssj1;

	/**
	 * 纯水时间2
	 */
	@Column(name = "CSS2_", length = 5)
	private String cssj2;

	/**
	 * 导电度1
	 */
	@Column(name = "DDD1_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double ddd1;

	/**
	 * 导电度2
	 */
	@Column(name = "DDD2_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double ddd2;

	/**
	 * ph1
	 */
	@Column(name = "PH1_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double ph1;

	/**
	 * ph2
	 */
	@Column(name = "PH2_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double ph2;

	/**
	 * 创建人代码
	 */
	@Column(name = "CJNO_", length = 3)
	private String cjno;

	/**
	 * 创建人名称
	 */
	@Column(name = "CJNM_", length = 20)
	private String cjnm;

	/**
	 * 创建时间
	 */
	@Column(name = "CJSJ_")
	private Date cjsj;

	/**
	 * 修改人代码
	 */
	@Column(name = "XGNO_", length = 3)
	private String xgno;

	/**
	 * 修改人名称
	 */
	@Column(name = "XGNM_", length = 20)
	private String xgnm;

	/**
	 * 修改时间
	 */
	@Column(name = "XGSJ_")
	private Date xgsj;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getJjy() {
		return jjy;
	}

	public void setJjy(Integer jjy) {
		this.jjy = jjy;
	}

	public Integer getJdjy() {
		return jdjy;
	}

	public void setJdjy(Integer jdjy) {
		this.jdjy = jdjy;
	}

	public Integer getSdjyHso() {
		return sdjyHso;
	}

	public void setSdjyHso(Integer sdjyHso) {
		this.sdjyHso = sdjyHso;
	}

	public Integer getSdjyFe() {
		return sdjyFe;
	}

	public void setSdjyFe(Integer sdjyFe) {
		this.sdjyFe = sdjyFe;
	}

	public Integer getXddySn() {
		return xddySn;
	}

	public void setXddySn(Integer xddySn) {
		this.xddySn = xddySn;
	}

	public Integer getXddyAcid() {
		return xddyAcid;
	}

	public void setXddyAcid(Integer xddyAcid) {
		this.xddyAcid = xddyAcid;
	}

	public Integer getXddyEnsa() {
		return xddyEnsa;
	}

	public void setXddyEnsa(Integer xddyEnsa) {
		this.xddyEnsa = xddyEnsa;
	}

	public Integer getXddyFe() {
		return xddyFe;
	}

	public void setXddyFe(Integer xddyFe) {
		this.xddyFe = xddyFe;
	}

	public Integer getXddySlud() {
		return xddySlud;
	}

	public void setXddySlud(Integer xddySlud) {
		this.xddySlud = xddySlud;
	}

	public Integer getTcySn() {
		return tcySn;
	}

	public void setTcySn(Integer tcySn) {
		this.tcySn = tcySn;
	}

	public Integer getTcyAcid() {
		return tcyAcid;
	}

	public void setTcyAcid(Integer tcyAcid) {
		this.tcyAcid = tcyAcid;
	}

	public Integer getHxclyCr() {
		return hxclyCr;
	}

	public void setHxclyCr(Integer hxclyCr) {
		this.hxclyCr = hxclyCr;
	}

	public Integer getHxclyPh() {
		return hxclyPh;
	}

	public void setHxclyPh(Integer hxclyPh) {
		this.hxclyPh = hxclyPh;
	}

	public Integer getHxclySlud() {
		return hxclySlud;
	}

	public void setHxclySlud(Integer hxclySlud) {
		this.hxclySlud = hxclySlud;
	}

	public Integer getXfzlZr() {
		return xfzlZr;
	}

	public void setXfzlZr(Integer xfzlZr) {
		this.xfzlZr = xfzlZr;
	}

	public Integer getXfzlJrjs() {
		return xfzlJrjs;
	}

	public void setXfzlJrjs(Integer xfzlJrjs) {
		this.xfzlJrjs = xfzlJrjs;
	}

	public Integer getXfzlJrwc() {
		return xfzlJrwc;
	}

	public void setXfzlJrwc(Integer xfzlJrwc) {
		this.xfzlJrwc = xfzlJrwc;
	}

	public Integer getXfzlJrww() {
		return xfzlJrww;
	}

	public void setXfzlJrww(Integer xfzlJrww) {
		this.xfzlJrww = xfzlJrww;
	}

	public Integer getGfzlZr() {
		return gfzlZr;
	}

	public void setGfzlZr(Integer gfzlZr) {
		this.gfzlZr = gfzlZr;
	}

	public Integer getGfzlJrjs() {
		return gfzlJrjs;
	}

	public void setGfzlJrjs(Integer gfzlJrjs) {
		this.gfzlJrjs = gfzlJrjs;
	}

	public Integer getGfzlJrwc() {
		return gfzlJrwc;
	}

	public void setGfzlJrwc(Integer gfzlJrwc) {
		this.gfzlJrwc = gfzlJrwc;
	}

	public Integer getGfzlJrww() {
		return gfzlJrww;
	}

	public void setGfzlJrww(Integer gfzlJrww) {
		this.gfzlJrww = gfzlJrww;
	}

	public Integer getTylZr() {
		return tylZr;
	}

	public void setTylZr(Integer tylZr) {
		this.tylZr = tylZr;
	}

	public Integer getTylJrjs() {
		return tylJrjs;
	}

	public void setTylJrjs(Integer tylJrjs) {
		this.tylJrjs = tylJrjs;
	}

	public Integer getTylJrwc() {
		return tylJrwc;
	}

	public void setTylJrwc(Integer tylJrwc) {
		this.tylJrwc = tylJrwc;
	}

	public Integer getTylJrww() {
		return tylJrww;
	}

	public void setTylJrww(Integer tylJrww) {
		this.tylJrww = tylJrww;
	}

	public Integer getBmljgZr() {
		return bmljgZr;
	}

	public void setBmljgZr(Integer bmljgZr) {
		this.bmljgZr = bmljgZr;
	}

	public Integer getBmljgJrjs() {
		return bmljgJrjs;
	}

	public void setBmljgJrjs(Integer bmljgJrjs) {
		this.bmljgJrjs = bmljgJrjs;
	}

	public Integer getBmljgJrwc() {
		return bmljgJrwc;
	}

	public void setBmljgJrwc(Integer bmljgJrwc) {
		this.bmljgJrwc = bmljgJrwc;
	}

	public Integer getBmljgJrww() {
		return bmljgJrww;
	}

	public void setBmljgJrww(Integer bmljgJrww) {
		this.bmljgJrww = bmljgJrww;
	}

	public Integer getIsvZr() {
		return isvZr;
	}

	public void setIsvZr(Integer isvZr) {
		this.isvZr = isvZr;
	}

	public Integer getIsvJrjs() {
		return isvJrjs;
	}

	public void setIsvJrjs(Integer isvJrjs) {
		this.isvJrjs = isvJrjs;
	}

	public Integer getIsvJrwc() {
		return isvJrwc;
	}

	public void setIsvJrwc(Integer isvJrwc) {
		this.isvJrwc = isvJrwc;
	}

	public Integer getIsvJrww() {
		return isvJrww;
	}

	public void setIsvJrww(Integer isvJrww) {
		this.isvJrww = isvJrww;
	}

	public Integer getTcsZr() {
		return tcsZr;
	}

	public void setTcsZr(Integer tcsZr) {
		this.tcsZr = tcsZr;
	}

	public Integer getTcsJrjs() {
		return tcsJrjs;
	}

	public void setTcsJrjs(Integer tcsJrjs) {
		this.tcsJrjs = tcsJrjs;
	}

	public Integer getTcsJrwc() {
		return tcsJrwc;
	}

	public void setTcsJrwc(Integer tcsJrwc) {
		this.tcsJrwc = tcsJrwc;
	}

	public Integer getTcsJrww() {
		return tcsJrww;
	}

	public void setTcsJrww(Integer tcsJrww) {
		this.tcsJrww = tcsJrww;
	}

	public Integer getAtcZr() {
		return atcZr;
	}

	public void setAtcZr(Integer atcZr) {
		this.atcZr = atcZr;
	}

	public Integer getAtcJrjs() {
		return atcJrjs;
	}

	public void setAtcJrjs(Integer atcJrjs) {
		this.atcJrjs = atcJrjs;
	}

	public Integer getAtcJrwc() {
		return atcJrwc;
	}

	public void setAtcJrwc(Integer atcJrwc) {
		this.atcJrwc = atcJrwc;
	}

	public Integer getAtcJrww() {
		return atcJrww;
	}

	public void setAtcJrww(Integer atcJrww) {
		this.atcJrww = atcJrww;
	}

	public Integer getTcvZr() {
		return tcvZr;
	}

	public void setTcvZr(Integer tcvZr) {
		this.tcvZr = tcvZr;
	}

	public Integer getTcvJrjs() {
		return tcvJrjs;
	}

	public void setTcvJrjs(Integer tcvJrjs) {
		this.tcvJrjs = tcvJrjs;
	}

	public Integer getTcvJrwc() {
		return tcvJrwc;
	}

	public void setTcvJrwc(Integer tcvJrwc) {
		this.tcvJrwc = tcvJrwc;
	}

	public Integer getTcvJrww() {
		return tcvJrww;
	}

	public void setTcvJrww(Integer tcvJrww) {
		this.tcvJrww = tcvJrww;
	}

	public Integer getPltZr() {
		return pltZr;
	}

	public void setPltZr(Integer pltZr) {
		this.pltZr = pltZr;
	}

	public Integer getPltJrjs() {
		return pltJrjs;
	}

	public void setPltJrjs(Integer pltJrjs) {
		this.pltJrjs = pltJrjs;
	}

	public Integer getPltJrwc() {
		return pltJrwc;
	}

	public void setPltJrwc(Integer pltJrwc) {
		this.pltJrwc = pltJrwc;
	}

	public Integer getPltJrww() {
		return pltJrww;
	}

	public void setPltJrww(Integer pltJrww) {
		this.pltJrww = pltJrww;
	}

	public Integer getTlmzxZr() {
		return tlmzxZr;
	}

	public void setTlmzxZr(Integer tlmzxZr) {
		this.tlmzxZr = tlmzxZr;
	}

	public Integer getTlmzxJrjs() {
		return tlmzxJrjs;
	}

	public void setTlmzxJrjs(Integer tlmzxJrjs) {
		this.tlmzxJrjs = tlmzxJrjs;
	}

	public Integer getTlmzxJrwc() {
		return tlmzxJrwc;
	}

	public void setTlmzxJrwc(Integer tlmzxJrwc) {
		this.tlmzxJrwc = tlmzxJrwc;
	}

	public Integer getTlmzxJrww() {
		return tlmzxJrww;
	}

	public void setTlmzxJrww(Integer tlmzxJrww) {
		this.tlmzxJrww = tlmzxJrww;
	}

	public Integer getEpflZr() {
		return epflZr;
	}

	public void setEpflZr(Integer epflZr) {
		this.epflZr = epflZr;
	}

	public Integer getEpflJrjs() {
		return epflJrjs;
	}

	public void setEpflJrjs(Integer epflJrjs) {
		this.epflJrjs = epflJrjs;
	}

	public Integer getEpflJrwc() {
		return epflJrwc;
	}

	public void setEpflJrwc(Integer epflJrwc) {
		this.epflJrwc = epflJrwc;
	}

	public Integer getEpflJrww() {
		return epflJrww;
	}

	public void setEpflJrww(Integer epflJrww) {
		this.epflJrww = epflJrww;
	}

	public Integer getSmudZr() {
		return smudZr;
	}

	public void setSmudZr(Integer smudZr) {
		this.smudZr = smudZr;
	}

	public Integer getSmudJrjs() {
		return smudJrjs;
	}

	public void setSmudJrjs(Integer smudJrjs) {
		this.smudJrjs = smudJrjs;
	}

	public Integer getSmudJrwc() {
		return smudJrwc;
	}

	public void setSmudJrwc(Integer smudJrwc) {
		this.smudJrwc = smudJrwc;
	}

	public Integer getSmudJrww() {
		return smudJrww;
	}

	public void setSmudJrww(Integer smudJrww) {
		this.smudJrww = smudJrww;
	}

	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public Date getTbrq() {
		return tbrq;
	}

	public void setTbrq(Date tbrq) {
		this.tbrq = tbrq;
	}

	public String getTbxq() {
		return tbxq;
	}

	public void setTbxq(String tbxq) {
		this.tbxq = tbxq;
	}

	public String getCssj1() {
		return cssj1;
	}

	public void setCssj1(String cssj1) {
		this.cssj1 = cssj1;
	}

	public String getCssj2() {
		return cssj2;
	}

	public void setCssj2(String cssj2) {
		this.cssj2 = cssj2;
	}

	public Double getDdd1() {
		return ddd1;
	}

	public void setDdd1(Double ddd1) {
		this.ddd1 = ddd1;
	}

	public Double getDdd2() {
		return ddd2;
	}

	public void setDdd2(Double ddd2) {
		this.ddd2 = ddd2;
	}

	public Double getPh1() {
		return ph1;
	}

	public void setPh1(Double ph1) {
		this.ph1 = ph1;
	}

	public Double getPh2() {
		return ph2;
	}

	public void setPh2(Double ph2) {
		this.ph2 = ph2;
	}

	public String getCjno() {
		return cjno;
	}

	public void setCjno(String cjno) {
		this.cjno = cjno;
	}

	public String getCjnm() {
		return cjnm;
	}

	public void setCjnm(String cjnm) {
		this.cjnm = cjnm;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getXgno() {
		return xgno;
	}

	public void setXgno(String xgno) {
		this.xgno = xgno;
	}

	public String getXgnm() {
		return xgnm;
	}

	public void setXgnm(String xgnm) {
		this.xgnm = xgnm;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public String getBanzu() {
		return banzu;
	}

	public void setBanzu(String banzu) {
		this.banzu = banzu;
	}

	public String getTbsx() {
		return tbsx;
	}

	public void setTbsx(String tbsx) {
		this.tbsx = tbsx;
	}

	public String getYcsx() {
		return ycsx;
	}

	public void setYcsx(String ycsx) {
		this.ycsx = ycsx;
	}

}
