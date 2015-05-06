package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * 检查证明DB
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_JCHATP")
public class JchaTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 作成时间（年月日时分秒）,原字段TOCREA
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）,原字段TOUPDA
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 船名
	 */
	@Column(name = "SHIP_", length = 20)
	private String ship;

	/**
	 * 原材卷板No.
	 */
	@Id
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "JBNO_", referencedColumnName = "JBNO_", nullable = false)
	private YcaiTp ycaiTp;

	/**
	 * 制造商卷板NO
	 */
	@Column(name = "ZZSJ_", length = 15)
	private String zzsj;

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
	@Column(name = "YING_", length = 2)
	private String ying;

	/**
	 * 吹练No.
	 */
	@Column(name = "CHUI_", length = 10)
	private String chui;

	/**
	 * 
	 */
	@Column(name = "YP_", columnDefinition = "numeric(4,1)", scale = 4, precision = 1)
	private Double yp;

	/**
	 * 成分值(Sol.Al)
	 */
	@Column(name = "CFSO_", length = 2)
	private String cfso;
	/**
	 * 成分值(预备1)
	 */
	@Column(name = "CFY1_", length = 2)
	private String cfy1;
	/**
	 * 成分值(预备2)
	 */
	@Column(name = "CFY2_", length = 2)
	private String cfy2;
	/**
	 * 成分值(预备3)
	 */
	@Column(name = "CFY3_", length = 2)
	private String cfy3;
	/**
	 * 成分值(预备4)
	 */
	@Column(name = "CFY4_", length = 2)
	private String cfy4;

	/**
	 * 成分值标题1
	 */
	@Column(name = "CFB1_", length = 3)
	private String cfb1;
	/**
	 * 成分值标题2
	 */
	@Column(name = "CFB2_", length = 3)
	private String cfb2;
	/**
	 * 成分值标题3
	 */
	@Column(name = "CFB3_", length = 3)
	private String cfb3;
	/**
	 * 成分值标题4
	 */
	@Column(name = "CFB4_", length = 3)
	private String cfb4;

	/**
	 * 更新程序名
	 */
	@Column(name = "PROG_", length = 8)
	private String prog;

	/**
	 * 记录识别,原字段TOFLAG
	 */
	@Column(name = "FLAG_", length = 2)
	private String flag;

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

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public YcaiTp getYcaiTp() {
		return ycaiTp;
	}

	public void setYcaiTp(YcaiTp ycaiTp) {
		this.ycaiTp = ycaiTp;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	public Double getCfcc() {
		return cfcc;
	}

	public void setCfcc(Double cfcc) {
		this.cfcc = cfcc;
	}

	public String getCfsi() {
		return cfsi;
	}

	public void setCfsi(String cfsi) {
		this.cfsi = cfsi;
	}

	public String getCfmn() {
		return cfmn;
	}

	public void setCfmn(String cfmn) {
		this.cfmn = cfmn;
	}

	public String getCfpp() {
		return cfpp;
	}

	public void setCfpp(String cfpp) {
		this.cfpp = cfpp;
	}

	public String getCfss() {
		return cfss;
	}

	public void setCfss(String cfss) {
		this.cfss = cfss;
	}

	public String getYing() {
		return ying;
	}

	public void setYing(String ying) {
		this.ying = ying;
	}

	public String getChui() {
		return chui;
	}

	public void setChui(String chui) {
		this.chui = chui;
	}

	public Double getYp() {
		return yp;
	}

	public void setYp(Double yp) {
		this.yp = yp;
	}

	public String getCfso() {
		return cfso;
	}

	public void setCfso(String cfso) {
		this.cfso = cfso;
	}

	public String getCfy1() {
		return cfy1;
	}

	public void setCfy1(String cfy1) {
		this.cfy1 = cfy1;
	}

	public String getCfy2() {
		return cfy2;
	}

	public void setCfy2(String cfy2) {
		this.cfy2 = cfy2;
	}

	public String getCfy3() {
		return cfy3;
	}

	public void setCfy3(String cfy3) {
		this.cfy3 = cfy3;
	}

	public String getCfy4() {
		return cfy4;
	}

	public void setCfy4(String cfy4) {
		this.cfy4 = cfy4;
	}

	public String getCfb1() {
		return cfb1;
	}

	public void setCfb1(String cfb1) {
		this.cfb1 = cfb1;
	}

	public String getCfb2() {
		return cfb2;
	}

	public void setCfb2(String cfb2) {
		this.cfb2 = cfb2;
	}

	public String getCfb3() {
		return cfb3;
	}

	public void setCfb3(String cfb3) {
		this.cfb3 = cfb3;
	}

	public String getCfb4() {
		return cfb4;
	}

	public void setCfb4(String cfb4) {
		this.cfb4 = cfb4;
	}

	public String getProg() {
		return prog;
	}

	public void setProg(String prog) {
		this.prog = prog;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getRsv1() {
		return rsv1;
	}

	public void setRsv1(String rsv1) {
		this.rsv1 = rsv1;
	}

	public BigDecimal getRsv2() {
		return rsv2;
	}

	public void setRsv2(BigDecimal rsv2) {
		this.rsv2 = rsv2;
	}

}
