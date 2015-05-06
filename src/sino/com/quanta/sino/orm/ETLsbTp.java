package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * ETL速报
 * 
 * @author 张红国[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_ETLSBTP")
public class ETLsbTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 日期
	 */
	@Id
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "DASR_")
	private Date dasr;

	/**
	 * 计划生产量
	 */
	@Column(name = "JSZL_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double jszl;

	/**
	 * 操业时间
	 */
	@Column(name = "GZDA_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double gzda;

	/**
	 * 休止I
	 */
	@Column(name = "XZDA_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double xzda;
	/**
	 * 休止II
	 */
	@Column(name = "ZZDA_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double zzda;
	/**
	 * 实动时间
	 */
	@Column(name = "SDSJ_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double sdsj;
	/**
	 * 实动时间1班
	 */
	@Column(name = "SDSJ1_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double sdsj1;
	/**
	 * 实动时间2班
	 */
	@Column(name = "SDSJ2_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double sdsj2;
	/**
	 * 实动时间3班
	 */
	@Column(name = "SDSJ3_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double sdsj3;
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
	 * 是否统计
	 */
	@Column(name = "IFDJ_", length = 1)
	private String ifdj;
	/**
	 * 2pass时间
	 */
	@Column(name = "PASS_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double pass;

	public Date getDasr() {
		return dasr;
	}

	public void setDasr(Date dasr) {
		this.dasr = dasr;
	}

	public Double getJszl() {
		return jszl;
	}

	public void setJszl(Double jszl) {
		this.jszl = jszl;
	}

	public Double getGzda() {
		return gzda;
	}

	public void setGzda(Double gzda) {
		this.gzda = gzda;
	}

	public Double getXzda() {
		return xzda;
	}

	public void setXzda(Double xzda) {
		this.xzda = xzda;
	}

	public Double getZzda() {
		return zzda;
	}

	public void setZzda(Double zzda) {
		this.zzda = zzda;
	}

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

	public Double getSdsj() {
		return sdsj;
	}

	public void setSdsj(Double sdsj) {
		this.sdsj = sdsj;
	}

	public Double getSdsj1() {
		return sdsj1;
	}

	public void setSdsj1(Double sdsj1) {
		this.sdsj1 = sdsj1;
	}

	public Double getSdsj2() {
		return sdsj2;
	}

	public void setSdsj2(Double sdsj2) {
		this.sdsj2 = sdsj2;
	}

	public Double getSdsj3() {
		return sdsj3;
	}

	public void setSdsj3(Double sdsj3) {
		this.sdsj3 = sdsj3;
	}

	public String getIfdj() {
		return ifdj;
	}

	public void setIfdj(String ifdj) {
		this.ifdj = ifdj;
	}

	public Double getPass() {
		return pass;
	}

	public void setPass(Double pass) {
		this.pass = pass;
	}

}
