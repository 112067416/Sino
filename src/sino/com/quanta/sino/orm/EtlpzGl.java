package com.quanta.sino.orm;

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
 * ETL品质管理表
 * <p>
 * </p>
 * <p>
 * create: 2011-1-10 上午11:57:55
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_ETLPZGL")
public class EtlpzGl implements java.io.Serializable {

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
	 * 马口铁分析次数
	 */
	@Column(name = "JYCS_")
	private Integer jycs;

	/**
	 * 2pass使用
	 */
	@Column(name = "PASS_", length = 5)
	private String pass;

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
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;

	/**
	 * 原材卷板No.
	 */
	@Column(name = "JBNO_", length = 7)
	private String jbno;

	/**
	 * 附着量（g/m2）
	 */
	@Column(name = "FUGM_", length = 12)
	private String fugm;

	/**
	 * 镀锡量实测正面
	 */
	@Column(name = "SCZM_", length = 5)
	private String sczm;

	/**
	 * 镀锡量实测反面
	 */
	@Column(name = "SCFM_", length = 5)
	private String scfm;
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
	 * 涂油量目标量
	 */
	@Column(name = "YQTY_", length = 3)
	private String yqty;

	/**
	 * 目标涂油种类
	 */
	@Column(name = "YTYP_", length = 3)
	private String ytyp;

	/**
	 * Oiler比率
	 */
	@Column(name = "OILER_BL_", length = 5)
	private String oilerBl;

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
	 * Chemical电流密度
	 */
	@Column(name = "CHE_DLMDT_", length = 5)
	private String cheDlmdt;

	/**
	 * Chemical电流密度
	 */
	@Column(name = "CHE_DLMDB_", length = 5)
	private String cheDlmdb;

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
	 * 特记
	 */
	@Column(name = "TJ_", length = 2048)
	private String tj;

	/**
	 * 涂油机目视
	 */
	@Column(name = "TYJMS_", length = 15)
	private String tyjms;

	/**
	 * 备注
	 */
	@Column(name = "BZ_", length = 2048)
	private String bz;

	/**
	 * 生产时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "SCSJ_")
	private Date scsj;

	/**
	 * 修改时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 状态
	 */
	@Column(name = "STAT_", length = 2)
	private String stat;

	/**
	 * 判断马口铁分析数据是否为新的记录
	 */
	@Column(name = "NEWED_")
	private boolean newed;

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
	 * 铬上限值
	 */
	@Column(name = "CRS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crs;

	/**
	 * 铬下限值
	 */
	@Column(name = "CRX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crx;

	/**
	 * 涂油上限值
	 */
	@Column(name = "TYS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tys;

	/**
	 * 涂油下限值
	 */
	@Column(name = "TYX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tyx;

	/**
	 * 合金层
	 */
	@Column(name = "HJIN_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double hjin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getFugm() {
		return fugm;
	}

	public void setFugm(String fugm) {
		this.fugm = fugm;
	}

	public String getSczm() {
		return sczm;
	}

	public void setSczm(String sczm) {
		this.sczm = sczm;
	}

	public String getScfm() {
		return scfm;
	}

	public void setScfm(String scfm) {
		this.scfm = scfm;
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

	public String getYqty() {
		return yqty;
	}

	public void setYqty(String yqty) {
		this.yqty = yqty;
	}

	public String getYtyp() {
		return ytyp;
	}

	public void setYtyp(String ytyp) {
		this.ytyp = ytyp;
	}

	public String getOilerBl() {
		return oilerBl;
	}

	public void setOilerBl(String oilerBl) {
		this.oilerBl = oilerBl;
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

	public String getCheDlmdt() {
		return cheDlmdt;
	}

	public void setCheDlmdt(String cheDlmdt) {
		this.cheDlmdt = cheDlmdt;
	}

	public String getCheDlmdb() {
		return cheDlmdb;
	}

	public void setCheDlmdb(String cheDlmdb) {
		this.cheDlmdb = cheDlmdb;
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

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getTyjms() {
		return tyjms;
	}

	public void setTyjms(String tyjms) {
		this.tyjms = tyjms;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Date getScsj() {
		return scsj;
	}

	public void setScsj(Date scsj) {
		this.scsj = scsj;
	}

	public Date getUpda() {
		return upda;
	}

	public void setUpda(Date upda) {
		this.upda = upda;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public boolean isNewed() {
		return newed;
	}

	public void setNewed(boolean newed) {
		this.newed = newed;
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

	public Double getCrs() {
		return crs;
	}

	public void setCrs(Double crs) {
		this.crs = crs;
	}

	public Double getCrx() {
		return crx;
	}

	public void setCrx(Double crx) {
		this.crx = crx;
	}

	public Integer getJycs() {
		return jycs;
	}

	public void setJycs(Integer jycs) {
		this.jycs = jycs;
	}

	public Double getHjin() {
		return hjin;
	}

	public void setHjin(Double hjin) {
		this.hjin = hjin;
	}

	public Double getTys() {
		return tys;
	}

	public void setTys(Double tys) {
		this.tys = tys;
	}

	public Double getTyx() {
		return tyx;
	}

	public void setTyx(Double tyx) {
		this.tyx = tyx;
	}

}
