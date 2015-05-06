package com.quanta.sino.zk.vo;

import java.util.Date;

import javax.persistence.Column;

/**
 * <p>
 * ETL品质记录VO
 * </p>
 * <p>
 * create: 2011-6-15 下午10:55:21
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlpzGlVO {

	/**
	 * 唯一标识
	 */
	private String id;

	/**
	 * 马口铁分析次数
	 */
	private Integer jycs;

	/**
	 * 2pass使用
	 */
	private String pass;

	/**
	 * 班别
	 */
	private String ban;

	/**
	 * 组别
	 */
	private String zu;

	/**
	 * 制造商代码
	 */
	private String zzsd;

	/**
	 * 原材卷板No.
	 */
	private String jbno;

	/**
	 * 附着量（g/m2）
	 */
	private String fugm;

	/**
	 * 镀锡量实测正面
	 */
	private String sczm;

	/**
	 * 镀锡量实测反面
	 */
	private String scfm;
	/**
	 * 锡附着量C M-Sn 外
	 */
	private Double xfzlCmt;

	/**
	 * 锡附着量C M-Sn 内
	 */
	private Double xfzlCmb;

	/**
	 * 锡附着量C M-Alloy外
	 */
	private Double xfzlCat;

	/**
	 * 锡附着量C M-Alloy内
	 */
	private Double xfzlCab;

	/**
	 * 锡附着量S M-Sn 外
	 */
	private Double xfzlSmt;

	/**
	 * 锡附着量S M-Sn 内
	 */
	private Double xfzlSmb;

	/**
	 * 锡附着量S M-Alloy外
	 */
	private Double xfzlSat;

	/**
	 * 锡附着量S M-Alloy内
	 */
	private Double xfzlSab;

	/**
	 * 锡附着量N M-Sn 外
	 */
	private Double xfzlNmt;

	/**
	 * 锡附着量N M-Sn 内
	 */
	private Double xfzlNmb;

	/**
	 * 锡附着量N M-Alloy外
	 */
	private Double xfzlNat;

	/**
	 * 锡附着量N M-Alloy内
	 */
	private Double xfzlNab;

	/**
	 * 涂油量目标量
	 */
	private String yqty;

	/**
	 * 目标涂油种类
	 */
	private String ytyp;

	/**
	 * Oiler比率
	 */
	private String oilerBl;

	/**
	 * 涂油量S外
	 */
	private Double tylSt;

	/**
	 * 涂油量S内
	 */
	private Double tylSb;

	/**
	 * 涂油量C外
	 */
	private Double tylCt;

	/**
	 * 涂油量C内
	 */
	private Double tylCb;

	/**
	 * 涂油量N外
	 */
	private Double tylNt;

	/**
	 * 涂油量N内
	 */
	private Double tylNb;

	/**
	 * Chemical电流密度
	 */
	private String cheDlmdt;

	/**
	 * Chemical电流密度
	 */
	private String cheDlmdb;

	/**
	 * Cr S外
	 */
	private Double crSt;

	/**
	 * Cr s内
	 */
	private Double crSb;

	/**
	 * Cr c外
	 */
	private Double crCt;

	/**
	 * Cr c内
	 */
	private Double crCb;

	/**
	 * Cr N外
	 */
	private Double crNt;

	/**
	 * Cr N内
	 */
	private Double crNb;
	/**
	 * 特记
	 */
	private String tj;

	/**
	 * 涂油机目视
	 */
	private String tyjms;

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * 生产时间
	 */
	@Column(name = "SCSJ_")
	private Date scsj;

	/**
	 * 修改时间
	 */
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 状态
	 */
	private String stat;

	/**
	 * 判断马口铁分析数据是否为新的记录
	 */
	private boolean newed;

	/**
	 * 附着面.正面.上限值
	 */
	private Double fuzs;

	/**
	 * 附着面.正面.下限值
	 */
	private Double fuzx;

	/**
	 * 附着面.反面.上限值
	 */
	private Double fufs;

	/**
	 * 附着面.反面.下限值
	 */
	private Double fufx;

	/**
	 * 铬上限值
	 */
	private Double crs;

	/**
	 * 铬下限值
	 */
	private Double crx;

	/**
	 * 涂油上限值
	 */
	private Double tys;

	/**
	 * 涂油下限值
	 */
	private Double tyx;

	/**
	 * 合金层
	 */
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
