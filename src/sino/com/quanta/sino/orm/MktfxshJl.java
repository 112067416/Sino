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
 * 马口铁分析数据记录表
 * <p>
 * </p>
 * <p>
 * create: 2010-12-28 下午05:00:34
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_MKTFXSJJL")
public class MktfxshJl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 采样日志
	 */
	@Column(name = "CYRZ_ID_", length = 40)
	private String cyrzId;
	/**
	 * 分析日
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "FXR_")
	private Date fxr;

	/**
	 * Coil No.
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;

	/**
	 * 锡附着量C M-Sn 外
	 */
	@Column(name = "XFZL_CMT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlCmt;

	/**
	 * 锡附着量C M-Sn 内
	 */
	@Column(name = "XFZL_CMB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlCmb;
	/**
	 * 锡附着量C M-Alloy外
	 */
	@Column(name = "XFZL_CAT_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double xfzlCat;

	/**
	 * 锡附着量C M-Alloy内
	 */
	@Column(name = "XFZL_CAB_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double xfzlCab;
	/**
	 * 锡附着量C T-Sn外
	 */
	@Column(name = "XFZL_CST_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlCst;

	/**
	 * 锡附着量CT-Sn内
	 */
	@Column(name = "XFZL_CSB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlCsb;

	/**
	 * 锡附着量S M-Sn 外
	 */
	@Column(name = "XFZL_SMT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlSmt;

	/**
	 * 锡附着量S M-Sn 内
	 */
	@Column(name = "XFZL_SMB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlSmb;
	/**
	 * 锡附着量S M-Alloy外
	 */
	@Column(name = "XFZL_SAT_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double xfzlSat;

	/**
	 * 锡附着量S M-Alloy内
	 */
	@Column(name = "XFZL_SAB_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double xfzlSab;
	/**
	 * 锡附着量sT-Sn外
	 */
	@Column(name = "XFZL_SST_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlSst;

	/**
	 * 锡附着量sT-Sn内
	 */
	@Column(name = "XFZL_SSB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlSsb;

	/**
	 * 锡附着量N M-Sn 外
	 */
	@Column(name = "XFZL_NMT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlNmt;

	/**
	 * 锡附着量N M-Sn 内
	 */
	@Column(name = "XFZL_NMB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlNmb;
	/**
	 * 锡附着量N M-Alloy外
	 */
	@Column(name = "XFZL_NAT_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double xfzlNat;

	/**
	 * 锡附着量N M-Alloy内
	 */
	@Column(name = "XFZL_NAB_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double xfzlNab;
	/**
	 * 锡附着量NT-Sn外
	 */
	@Column(name = "XFZL_NST_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlNst;

	/**
	 * 锡附着量NT-Sn内
	 */
	@Column(name = "XFZL_NSB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlNsb;

	/**
	 * Cr S外
	 */
	@Column(name = "CR_ST_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crSt;

	/**
	 * Cr s内
	 */
	@Column(name = "CR_SB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crSb;

	/**
	 * Cr c外
	 */
	@Column(name = "CR_CT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crCt;

	/**
	 * Cr c内
	 */
	@Column(name = "CR_CB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crCb;

	/**
	 * Cr N外
	 */
	@Column(name = "CR_NT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crNt;

	/**
	 * Cr N内
	 */
	@Column(name = "CR_NB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crNb;

	/**
	 * 涂油量目标量
	 */
	@Column(name = "YQTY_", length = 3)
	private String yqty;

	/**
	 * 涂油量S外
	 */
	@Column(name = "TYL_ST_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tylSt;

	/**
	 * 涂油量S内
	 */
	@Column(name = "TYL_SB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tylSb;

	/**
	 * 涂油量C外
	 */
	@Column(name = "TYL_CT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tylCt;

	/**
	 * 涂油量C内
	 */
	@Column(name = "TYL_CB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tylCb;

	/**
	 * 涂油量N外
	 */
	@Column(name = "TYL_NT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tylNt;

	/**
	 * 涂油量N内
	 */
	@Column(name = "TYL_NB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tylNb;

	/**
	 * 表面六价铬S外
	 */
	@Column(name = "BMLJG_ST_", length = 5)
	private String bmljgSt;

	/**
	 * 表面六价铬S内
	 */
	@Column(name = "BMLJG_SB_", length = 5)
	private String bmljgSb;

	/**
	 * 表面六价铬C外
	 */
	@Column(name = "BMLJG_CT_", length = 5)
	private String bmljgCt;

	/**
	 * 表面六价铬C内
	 */
	@Column(name = "BMLJG_CB_", length = 5)
	private String bmljgCb;
	/**
	 * 表面六价铬N外
	 */
	@Column(name = "BMLJG_NT_", length = 5)
	private String bmljgNt;

	/**
	 * 表面六价铬N内
	 */
	@Column(name = "BMLJG_NB_", length = 5)
	private String bmljgNb;
	/**
	 * 型号
	 */
	@Column(name = "XH_", length = 5)
	private String xh;

	/**
	 * ISV值外
	 */
	@Column(name = "ISV_ZT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double isvZt;

	/**
	 * ISV值内
	 */
	@Column(name = "ISV_ZB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double isvZb;

	/**
	 * ATC值外
	 */
	@Column(name = "ATC_ZT_", columnDefinition = "numeric(5,4)", scale = 5, precision = 4)
	private Double atcZt;

	/**
	 * ATC值内
	 */
	@Column(name = "ATC_ZB_", columnDefinition = "numeric(5,4)", scale = 5, precision = 4)
	private Double atcZb;

	/**
	 * TCS评点外
	 */
	@Column(name = "TCS_PD_T_", columnDefinition = "numeric(2,1)", scale = 2, precision = 1)
	private Double tcsPdt;

	/**
	 * TCS评点内
	 */
	@Column(name = "TCS_PD_B_", columnDefinition = "numeric(2,1)", scale = 2, precision = 2)
	private Double tcsPdb;

	/**
	 * ATC福山样无空烧
	 */
	@Column(name = "ATC_FSYWKS_", length = 6)
	private String atcFsywks;

	/**
	 * ATC福山样有空烧
	 */
	@Column(name = "ATC_FSYYKS_", length = 6)
	private String atcFsyyks;

	/**
	 * TCV铁标液
	 */
	@Column(name = "TCV_TBY_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double tcvTby;

	/**
	 * TCV值外
	 */
	@Column(name = "TCV_ZT_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double tcvZt;

	/**
	 * TCV值内
	 */
	@Column(name = "TCV_ZB_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double tcvZb;

	/**
	 * 涂料密着性外
	 */
	@Column(name = "TLMZX_ST_")
	private Integer tlmzxSt;

	/**
	 * 涂料密着性内
	 */
	@Column(name = "TLMZX_SB_")
	private Integer tlmzxSb;

	/**
	 * 涂料密着性外
	 */
	@Column(name = "TLMZX_CT_")
	private Integer tlmzxCt;

	/**
	 * 涂料密着性内
	 */
	@Column(name = "TLMZX_CB_")
	private Integer tlmzxCb;

	/**
	 * 涂料密着性外
	 */
	@Column(name = "TLMZX_NT_")
	private Integer tlmzxNt;

	/**
	 * 涂料密着性内
	 */
	@Column(name = "TLMZX_NB_")
	private Integer tlmzxNb;

	/**
	 * EponS外
	 */
	@Column(name = "EPON_ST_")
	private Integer eponSt;

	/**
	 * EponS内
	 */
	@Column(name = "EPON_SB_")
	private Integer eponSb;

	/**
	 * EponC外
	 */
	@Column(name = "EPON_CT_")
	private Integer eponCt;

	/**
	 * EponC内
	 */
	@Column(name = "EPON_CB_")
	private Integer eponCb;

	/**
	 * EponN外
	 */
	@Column(name = "EPON_NT_")
	private Integer eponNt;

	/**
	 * EponN内
	 */
	@Column(name = "EPON_NB_")
	private Integer eponNb;

	/**
	 * 污点实验外
	 */
	@Column(name = "WDSY_T_")
	private Integer wdsyt;

	/**
	 * 污点实验内
	 */
	@Column(name = "WDSY_B_")
	private Integer wdsyb;

	/**
	 * PLT评点
	 */
	@Column(name = "PLT_PD_", columnDefinition = "numeric(4,2)", scale = 2, precision = 2)
	private Double pltPd;

	/**
	 * PORE评点外
	 */
	@Column(name = "PORE_PDT_")
	private Integer porePdt;

	/**
	 * PORE评点内
	 */
	@Column(name = "PORE_PDB_")
	private Integer porePdb;

	/**
	 * 部位
	 */
	@Column(name = "BW_", length = 1)
	private String bw;

	/**
	 * 附着量 反面
	 */
	@Column(name = "FUFM_", length = 3)
	private String fufm;
	/**
	 * 附着量正面
	 */
	@Column(name = "FUZM_", length = 3)
	private String fuzm;

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

	/**
	 * 分析状态
	 */
	@Column(name = "STAT_", length = 2)
	private String stat;

	/**
	 * 备注
	 */
	@Column(name = "BZ_", length = 512)
	private String bz;

	/**
	 * 化成处理
	 */
	@Column(name = "HUAC_", length = 3)
	private String huac;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCyrzId() {
		return cyrzId;
	}

	public void setCyrzId(String cyrzId) {
		this.cyrzId = cyrzId;
	}

	public Date getFxr() {
		return fxr;
	}

	public void setFxr(Date fxr) {
		this.fxr = fxr;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public Double getXfzlCmt() {
		return xfzlCmt;
	}

	public void setXfzlCmt(Double xfzlCmt) {
		this.xfzlCmt = xfzlCmt;
	}

	public Double getXfzlCmb() {
		return xfzlCmb;
	}

	public void setXfzlCmb(Double xfzlCmb) {
		this.xfzlCmb = xfzlCmb;
	}

	public Double getXfzlCat() {
		return xfzlCat;
	}

	public void setXfzlCat(Double xfzlCat) {
		this.xfzlCat = xfzlCat;
	}

	public Double getXfzlCab() {
		return xfzlCab;
	}

	public void setXfzlCab(Double xfzlCab) {
		this.xfzlCab = xfzlCab;
	}

	public Double getXfzlCst() {
		return xfzlCst;
	}

	public void setXfzlCst(Double xfzlCst) {
		this.xfzlCst = xfzlCst;
	}

	public Double getXfzlCsb() {
		return xfzlCsb;
	}

	public void setXfzlCsb(Double xfzlCsb) {
		this.xfzlCsb = xfzlCsb;
	}

	public Double getXfzlSmt() {
		return xfzlSmt;
	}

	public void setXfzlSmt(Double xfzlSmt) {
		this.xfzlSmt = xfzlSmt;
	}

	public Double getXfzlSmb() {
		return xfzlSmb;
	}

	public void setXfzlSmb(Double xfzlSmb) {
		this.xfzlSmb = xfzlSmb;
	}

	public Double getXfzlSat() {
		return xfzlSat;
	}

	public void setXfzlSat(Double xfzlSat) {
		this.xfzlSat = xfzlSat;
	}

	public Double getXfzlSab() {
		return xfzlSab;
	}

	public void setXfzlSab(Double xfzlSab) {
		this.xfzlSab = xfzlSab;
	}

	public Double getXfzlSst() {
		return xfzlSst;
	}

	public void setXfzlSst(Double xfzlSst) {
		this.xfzlSst = xfzlSst;
	}

	public Double getXfzlSsb() {
		return xfzlSsb;
	}

	public void setXfzlSsb(Double xfzlSsb) {
		this.xfzlSsb = xfzlSsb;
	}

	public Double getXfzlNmt() {
		return xfzlNmt;
	}

	public void setXfzlNmt(Double xfzlNmt) {
		this.xfzlNmt = xfzlNmt;
	}

	public Double getXfzlNmb() {
		return xfzlNmb;
	}

	public void setXfzlNmb(Double xfzlNmb) {
		this.xfzlNmb = xfzlNmb;
	}

	public Double getXfzlNat() {
		return xfzlNat;
	}

	public void setXfzlNat(Double xfzlNat) {
		this.xfzlNat = xfzlNat;
	}

	public Double getXfzlNab() {
		return xfzlNab;
	}

	public void setXfzlNab(Double xfzlNab) {
		this.xfzlNab = xfzlNab;
	}

	public Double getXfzlNst() {
		return xfzlNst;
	}

	public void setXfzlNst(Double xfzlNst) {
		this.xfzlNst = xfzlNst;
	}

	public Double getXfzlNsb() {
		return xfzlNsb;
	}

	public void setXfzlNsb(Double xfzlNsb) {
		this.xfzlNsb = xfzlNsb;
	}

	public Double getCrSt() {
		return crSt;
	}

	public void setCrSt(Double crSt) {
		this.crSt = crSt;
	}

	public Double getCrSb() {
		return crSb;
	}

	public void setCrSb(Double crSb) {
		this.crSb = crSb;
	}

	public Double getCrCt() {
		return crCt;
	}

	public void setCrCt(Double crCt) {
		this.crCt = crCt;
	}

	public Double getCrCb() {
		return crCb;
	}

	public void setCrCb(Double crCb) {
		this.crCb = crCb;
	}

	public Double getCrNt() {
		return crNt;
	}

	public void setCrNt(Double crNt) {
		this.crNt = crNt;
	}

	public Double getCrNb() {
		return crNb;
	}

	public void setCrNb(Double crNb) {
		this.crNb = crNb;
	}

	public String getYqty() {
		return yqty;
	}

	public void setYqty(String yqty) {
		this.yqty = yqty;
	}

	public Double getTylSt() {
		return tylSt;
	}

	public void setTylSt(Double tylSt) {
		this.tylSt = tylSt;
	}

	public Double getTylSb() {
		return tylSb;
	}

	public void setTylSb(Double tylSb) {
		this.tylSb = tylSb;
	}

	public Double getTylCt() {
		return tylCt;
	}

	public void setTylCt(Double tylCt) {
		this.tylCt = tylCt;
	}

	public Double getTylCb() {
		return tylCb;
	}

	public void setTylCb(Double tylCb) {
		this.tylCb = tylCb;
	}

	public Double getTylNt() {
		return tylNt;
	}

	public void setTylNt(Double tylNt) {
		this.tylNt = tylNt;
	}

	public Double getTylNb() {
		return tylNb;
	}

	public void setTylNb(Double tylNb) {
		this.tylNb = tylNb;
	}

	public String getBmljgSt() {
		return bmljgSt;
	}

	public void setBmljgSt(String bmljgSt) {
		this.bmljgSt = bmljgSt;
	}

	public String getBmljgSb() {
		return bmljgSb;
	}

	public void setBmljgSb(String bmljgSb) {
		this.bmljgSb = bmljgSb;
	}

	public String getBmljgCt() {
		return bmljgCt;
	}

	public void setBmljgCt(String bmljgCt) {
		this.bmljgCt = bmljgCt;
	}

	public String getBmljgCb() {
		return bmljgCb;
	}

	public void setBmljgCb(String bmljgCb) {
		this.bmljgCb = bmljgCb;
	}

	public String getBmljgNt() {
		return bmljgNt;
	}

	public void setBmljgNt(String bmljgNt) {
		this.bmljgNt = bmljgNt;
	}

	public String getBmljgNb() {
		return bmljgNb;
	}

	public void setBmljgNb(String bmljgNb) {
		this.bmljgNb = bmljgNb;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public Double getIsvZt() {
		return isvZt;
	}

	public void setIsvZt(Double isvZt) {
		this.isvZt = isvZt;
	}

	public Double getIsvZb() {
		return isvZb;
	}

	public void setIsvZb(Double isvZb) {
		this.isvZb = isvZb;
	}

	public Double getAtcZt() {
		return atcZt;
	}

	public void setAtcZt(Double atcZt) {
		this.atcZt = atcZt;
	}

	public Double getAtcZb() {
		return atcZb;
	}

	public void setAtcZb(Double atcZb) {
		this.atcZb = atcZb;
	}

	public Double getTcsPdt() {
		return tcsPdt;
	}

	public void setTcsPdt(Double tcsPdt) {
		this.tcsPdt = tcsPdt;
	}

	public Double getTcsPdb() {
		return tcsPdb;
	}

	public void setTcsPdb(Double tcsPdb) {
		this.tcsPdb = tcsPdb;
	}

	public String getAtcFsywks() {
		return atcFsywks;
	}

	public void setAtcFsywks(String atcFsywks) {
		this.atcFsywks = atcFsywks;
	}

	public String getAtcFsyyks() {
		return atcFsyyks;
	}

	public void setAtcFsyyks(String atcFsyyks) {
		this.atcFsyyks = atcFsyyks;
	}

	public Double getTcvTby() {
		return tcvTby;
	}

	public void setTcvTby(Double tcvTby) {
		this.tcvTby = tcvTby;
	}

	public Double getTcvZt() {
		return tcvZt;
	}

	public void setTcvZt(Double tcvZt) {
		this.tcvZt = tcvZt;
	}

	public Double getTcvZb() {
		return tcvZb;
	}

	public void setTcvZb(Double tcvZb) {
		this.tcvZb = tcvZb;
	}

	public Integer getTlmzxSt() {
		return tlmzxSt;
	}

	public void setTlmzxSt(Integer tlmzxSt) {
		this.tlmzxSt = tlmzxSt;
	}

	public Integer getTlmzxSb() {
		return tlmzxSb;
	}

	public void setTlmzxSb(Integer tlmzxSb) {
		this.tlmzxSb = tlmzxSb;
	}

	public Integer getTlmzxCt() {
		return tlmzxCt;
	}

	public void setTlmzxCt(Integer tlmzxCt) {
		this.tlmzxCt = tlmzxCt;
	}

	public Integer getTlmzxCb() {
		return tlmzxCb;
	}

	public void setTlmzxCb(Integer tlmzxCb) {
		this.tlmzxCb = tlmzxCb;
	}

	public Integer getTlmzxNt() {
		return tlmzxNt;
	}

	public void setTlmzxNt(Integer tlmzxNt) {
		this.tlmzxNt = tlmzxNt;
	}

	public Integer getTlmzxNb() {
		return tlmzxNb;
	}

	public void setTlmzxNb(Integer tlmzxNb) {
		this.tlmzxNb = tlmzxNb;
	}

	public Integer getEponSt() {
		return eponSt;
	}

	public void setEponSt(Integer eponSt) {
		this.eponSt = eponSt;
	}

	public Integer getEponSb() {
		return eponSb;
	}

	public void setEponSb(Integer eponSb) {
		this.eponSb = eponSb;
	}

	public Integer getEponCt() {
		return eponCt;
	}

	public void setEponCt(Integer eponCt) {
		this.eponCt = eponCt;
	}

	public Integer getEponCb() {
		return eponCb;
	}

	public void setEponCb(Integer eponCb) {
		this.eponCb = eponCb;
	}

	public Integer getEponNt() {
		return eponNt;
	}

	public void setEponNt(Integer eponNt) {
		this.eponNt = eponNt;
	}

	public Integer getEponNb() {
		return eponNb;
	}

	public void setEponNb(Integer eponNb) {
		this.eponNb = eponNb;
	}

	public Integer getWdsyt() {
		return wdsyt;
	}

	public void setWdsyt(Integer wdsyt) {
		this.wdsyt = wdsyt;
	}

	public Integer getWdsyb() {
		return wdsyb;
	}

	public void setWdsyb(Integer wdsyb) {
		this.wdsyb = wdsyb;
	}

	public Double getPltPd() {
		return pltPd;
	}

	public void setPltPd(Double pltPd) {
		this.pltPd = pltPd;
	}

	public Integer getPorePdt() {
		return porePdt;
	}

	public void setPorePdt(Integer porePdt) {
		this.porePdt = porePdt;
	}

	public Integer getPorePdb() {
		return porePdb;
	}

	public void setPorePdb(Integer porePdb) {
		this.porePdb = porePdb;
	}

	public String getBw() {
		return bw;
	}

	public void setBw(String bw) {
		this.bw = bw;
	}

	public String getFufm() {
		return fufm;
	}

	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	public String getFuzm() {
		return fuzm;
	}

	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
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

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getHuac() {
		return huac;
	}

	public void setHuac(String huac) {
		this.huac = huac;
	}

}
