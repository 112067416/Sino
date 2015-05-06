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
 * ETL速报
 * 
 * @author 张红国[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_ETLSTOPTP")
public class ETLStopTp implements Serializable {

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
	 * 日期
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "DASR_")
	private Date dasr;
	/**
	 * 停线时间
	 */
	@Column(name = "STOP_", length = 40)
	private String stop;
	/**
	 * 休止I
	 */
	@Column(name = "XZDA_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double xzda;

	/**
	 * 休止II操业时间
	 */
	@Column(name = "XZZY_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double xzzy;

	/**
	 * 休止II机械
	 */
	@Column(name = "XZJX_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double xzjx;
	/**
	 * 休止II电气
	 */
	@Column(name = "XZDJ_", columnDefinition = "numeric(10,3)", scale = 10, precision = 3)
	private Double xzdj;
	/**
	 * 停线理由
	 */
	@Column(name = "REMK_", length = 200)
	private String remk;
	/**
	 * 特记
	 */
	@Column(name = "VARI_", length = 200)
	private String vari;
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
	 * 班
	 */
	@Column(name = "BAN_", length = 1)
	private String ban;

	public Date getDasr() {
		return dasr;
	}

	public void setDasr(Date dasr) {
		this.dasr = dasr;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public Double getXzda() {
		return xzda;
	}

	public void setXzda(Double xzda) {
		this.xzda = xzda;
	}

	public Double getXzzy() {
		return xzzy;
	}

	public void setXzzy(Double xzzy) {
		this.xzzy = xzzy;
	}

	public Double getXzjx() {
		return xzjx;
	}

	public void setXzjx(Double xzjx) {
		this.xzjx = xzjx;
	}

	public Double getXzdj() {
		return xzdj;
	}

	public void setXzdj(Double xzdj) {
		this.xzdj = xzdj;
	}

	public String getRemk() {
		return remk;
	}

	public void setRemk(String remk) {
		this.remk = remk;
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

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVari() {
		return vari;
	}

	public void setVari(String vari) {
		this.vari = vari;
	}

}
