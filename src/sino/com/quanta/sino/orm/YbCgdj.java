package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 采购单价表
 * </p>
 * <p>
 * create: 2011-7-8 下午02:21:45
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_YB_CGDJ")
public class YbCgdj implements Serializable {

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
	 * 尺寸.厚
	 */
	@Column(name = "XPHO_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double xpho;

	/**
	 * 尺寸.宽
	 */
	@Column(name = "XPKN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpkn;

	/**
	 * 尺寸宽范围
	 */
	@Column(name = "KNFW_", length = 1)
	private String knfw;

	/**
	 * 用途
	 */
	@Column(name = "YUNY_", length = 4)
	private String yuny;

	/**
	 * 基价
	 */
	@Column(name = "BASE_", columnDefinition = "numeric(8,2)", scale = 8, precision = 2)
	private Double base;

	/**
	 * 差价
	 */
	@Column(name = "EXTRA_", columnDefinition = "numeric(8,2)", scale = 8, precision = 2)
	private Double extra;

	/**
	 * FOB
	 */
	@Column(name = "FOB_", columnDefinition = "numeric(8,2)", scale = 8, precision = 2)
	private Double fob;

	/**
	 * freight
	 */
	@Column(name = "FREIGHT_", columnDefinition = "numeric(8,2)", scale = 8, precision = 2)
	private Double freight;

	/**
	 * 运费
	 */
	@Column(name = "YF_", columnDefinition = "numeric(8,2)", scale = 8, precision = 2)
	private Double yf;

	/**
	 * CFR
	 */
	@Column(name = "CFR_", columnDefinition = "numeric(8,2)", scale = 8, precision = 2)
	private Double cfr;

	/**
	 * 天数
	 */
	@Column(name = "DAYS_")
	private Integer days;

	/**
	 * 利息利率
	 */
	@Column(name = "LXLL_", columnDefinition = "numeric(8,6)", scale = 8, precision = 6)
	private Double lxll;

	/**
	 * 采购单价
	 */
	@Column(name = "CGDJ_", columnDefinition = "numeric(8,2)", scale = 8, precision = 2)
	private Double cgdj;

	/**
	 * 作成时间（年月日时分秒）
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 类型
	 */
	@Column(name = "TYPE_", length = 1)
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getXpho() {
		return xpho;
	}

	public void setXpho(Double xpho) {
		this.xpho = xpho;
	}

	public Double getXpkn() {
		return xpkn;
	}

	public void setXpkn(Double xpkn) {
		this.xpkn = xpkn;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public Double getBase() {
		return base;
	}

	public void setBase(Double base) {
		this.base = base;
	}

	public Double getExtra() {
		return extra;
	}

	public void setExtra(Double extra) {
		this.extra = extra;
	}

	public Double getFob() {
		return fob;
	}

	public void setFob(Double fob) {
		this.fob = fob;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getYf() {
		return yf;
	}

	public void setYf(Double yf) {
		this.yf = yf;
	}

	public Double getCfr() {
		return cfr;
	}

	public void setCfr(Double cfr) {
		this.cfr = cfr;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Double getLxll() {
		return lxll;
	}

	public void setLxll(Double lxll) {
		this.lxll = lxll;
	}

	public Double getCgdj() {
		return cgdj;
	}

	public void setCgdj(Double cgdj) {
		this.cgdj = cgdj;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKnfw() {
		return knfw;
	}

	public void setKnfw(String knfw) {
		this.knfw = knfw;
	}

}
