package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 高耐蚀性马口铁出荷判定表(#75以上)
 * </p>
 * <p>
 * create: 2011-8-26 上午11:13:47
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_CHPDB")
public class Chpdb implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Coil No.
	 */
	@Id
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	/**
	 * 订货No
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;

	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;
	/**
	 * 附着量.单位
	 */
	@Column(name = "FUDW_", length = 2)
	private String fudw;
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
	 * 订货尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;

	/**
	 * 订货尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;

	/**
	 * 订货尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cang;

	/**
	 * 加工用途条件
	 */
	@Column(name = "COND_", length = 4)
	private String cond;

	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 64)
	private String abbr;
	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;

	/**
	 * 表面加工精度
	 */
	@Column(name = "FACE_", length = 2)
	private String face;
	/**
	 * 锡附着量C M-Sn 外
	 */
	@Column(name = "XFZL_MT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlMt;

	/**
	 * 锡附着量C M-Sn 内
	 */
	@Column(name = "XFZL_MB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlMb;
	/**
	 * 锡附着量C M-Alloy外
	 */
	@Column(name = "XFZL_AT_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double xfzlAt;

	/**
	 * 锡附着量C M-Alloy内
	 */
	@Column(name = "XFZL_AB_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double xfzlAb;
	/**
	 * 锡附着量C T-Sn外
	 */
	@Column(name = "XFZL_ST_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlSt;

	/**
	 * 锡附着量CT-Sn内
	 */
	@Column(name = "XFZL_SB_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xfzlSb;

	/**
	 * Cr S外
	 */
	@Column(name = "CR_T_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crT;

	/**
	 * Cr s内
	 */
	@Column(name = "CR_B_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double crB;

	/**
	 * ISV值外
	 */
	@Column(name = "ISV_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double isv;

	/**
	 * ATC值外
	 */
	@Column(name = "ATC_", columnDefinition = "numeric(5,4)", scale = 5, precision = 4)
	private Double atc;

	/**
	 * TCS评点外
	 */
	@Column(name = "TCS_", columnDefinition = "numeric(2,1)", scale = 2, precision = 1)
	private Double tcs;

	/**
	 * TCV值外
	 */
	@Column(name = "TCV_", columnDefinition = "numeric(3,2)", scale = 3, precision = 2)
	private Double tcv;

	/**
	 * PLT评点
	 */
	@Column(name = "PLT_PD_", columnDefinition = "numeric(4,2)", scale = 2, precision = 2)
	private Double pltPd;

	/**
	 * PORE评点外
	 */
	@Column(name = "PORE_")
	private Integer pore;

	/**
	 * 创建时间
	 */
	@Column(name = "CJSJ_")
	private Date cjsj;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
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

	public Double getHouu() {
		return houu;
	}

	public void setHouu(Double houu) {
		this.houu = houu;
	}

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Double getXfzlMt() {
		return xfzlMt;
	}

	public void setXfzlMt(Double xfzlMt) {
		this.xfzlMt = xfzlMt;
	}

	public Double getXfzlMb() {
		return xfzlMb;
	}

	public void setXfzlMb(Double xfzlMb) {
		this.xfzlMb = xfzlMb;
	}

	public Double getXfzlAt() {
		return xfzlAt;
	}

	public void setXfzlAt(Double xfzlAt) {
		this.xfzlAt = xfzlAt;
	}

	public Double getXfzlAb() {
		return xfzlAb;
	}

	public void setXfzlAb(Double xfzlAb) {
		this.xfzlAb = xfzlAb;
	}

	public Double getXfzlSt() {
		return xfzlSt;
	}

	public void setXfzlSt(Double xfzlSt) {
		this.xfzlSt = xfzlSt;
	}

	public Double getXfzlSb() {
		return xfzlSb;
	}

	public void setXfzlSb(Double xfzlSb) {
		this.xfzlSb = xfzlSb;
	}

	public Double getCrT() {
		return crT;
	}

	public void setCrT(Double crT) {
		this.crT = crT;
	}

	public Double getCrB() {
		return crB;
	}

	public void setCrB(Double crB) {
		this.crB = crB;
	}

	public Double getIsv() {
		return isv;
	}

	public void setIsv(Double isv) {
		this.isv = isv;
	}

	public Double getAtc() {
		return atc;
	}

	public void setAtc(Double atc) {
		this.atc = atc;
	}

	public Double getTcs() {
		return tcs;
	}

	public void setTcs(Double tcs) {
		this.tcs = tcs;
	}

	public Double getTcv() {
		return tcv;
	}

	public void setTcv(Double tcv) {
		this.tcv = tcv;
	}

	public Double getPltPd() {
		return pltPd;
	}

	public void setPltPd(Double pltPd) {
		this.pltPd = pltPd;
	}

	public Integer getPore() {
		return pore;
	}

	public void setPore(Integer pore) {
		this.pore = pore;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getFudw() {
		return fudw;
	}

	public void setFudw(String fudw) {
		this.fudw = fudw;
	}

}
