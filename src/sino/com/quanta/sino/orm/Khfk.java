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
 * 客户付款管理
 * </p>
 * 
 * @author zhangliang, jimsonhappy@126.com create time: 2010-11-23 下午02:45:23
 */
@Entity
@Table(name = "SINO_KHFK")
public class Khfk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 用户中文名称
	 */
	@Column(name = "NAME_", length = 20)
	private String name;

	/**
	 * 付款金额
	 */
	@Column(name = "FKJE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fkje;

	/**
	 * 付款余额
	 */
	@Column(name = "FKYE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fkye;

	/**
	 * 调整金额
	 */
	@Column(name = "TZJE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double tzye;

	/**
	 * 货款金额
	 */
	@Column(name = "HKJE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double hkje;

	/**
	 * 状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 担当者名称
	 */
	@Column(name = "DDNM_", length = 20)
	private String ddnm;

	/**
	 * 付款日期
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新日期
	 */
	@Column(name = "UPDA_")
	private Date upda;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFkje() {
		return fkje;
	}

	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}

	public Double getFkye() {
		return fkye;
	}

	public void setFkye(Double fkye) {
		this.fkye = fkye;
	}

	public Double getTzye() {
		return tzye;
	}

	public void setTzye(Double tzye) {
		this.tzye = tzye;
	}

	public Double getHkje() {
		return hkje;
	}

	public void setHkje(Double hkje) {
		this.hkje = hkje;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public String getDdnm() {
		return ddnm;
	}

	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
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

}
