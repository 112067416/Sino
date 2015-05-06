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
 * ETL特记
 * 
 * @author 张红国[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_ETLVARITP")
public class ETLVariTp implements Serializable {

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
	 * 特记1
	 */
	@Column(name = "VARI1_", length = 200)
	private String vari1;
	/**
	 * 特记2
	 */
	@Column(name = "VARI2_", length = 200)
	private String vari2;
	/**
	 * 特记3
	 */
	@Column(name = "VARI3_", length = 200)
	private String vari3;
	/**
	 * 特记4
	 */
	@Column(name = "VARI4_", length = 200)
	private String vari4;
	/**
	 * 特记5
	 */
	@Column(name = "VARI5_", length = 200)
	private String vari5;
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

	public String getVari1() {
		return vari1;
	}

	public void setVari1(String vari1) {
		this.vari1 = vari1;
	}

	public String getVari2() {
		return vari2;
	}

	public void setVari2(String vari2) {
		this.vari2 = vari2;
	}

	public String getVari3() {
		return vari3;
	}

	public void setVari3(String vari3) {
		this.vari3 = vari3;
	}

	public String getVari4() {
		return vari4;
	}

	public void setVari4(String vari4) {
		this.vari4 = vari4;
	}

	public String getVari5() {
		return vari5;
	}

	public void setVari5(String vari5) {
		this.vari5 = vari5;
	}

}
