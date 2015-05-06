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
 * 基板订单主表
 * 
 * @author 张红国 2010-11-11
 */
@Entity
@Table(name = "SINO_JBDD")
public class Jbdd implements Serializable {

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
	 * 基板订单编号
	 */
	@Column(name = "JBNO_", length = 16, nullable = true)
	private String jbno;

	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 担当者名称
	 */
	@Column(name = "DDNM_", length = 16)
	private String ddnm;

	/**
	 * 作成时间（年月日时分秒）
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）
	 */
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 基板订单状态
	 */
	@Column(name = "STAT_", length = 2)
	private String stat;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the jbno
	 */
	public String getJbno() {
		return jbno;
	}

	/**
	 * @param jbno
	 *            the jbno to set
	 */
	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	/**
	 * @return the ddno
	 */
	public String getDdno() {
		return ddno;
	}

	/**
	 * @param ddno
	 *            the ddno to set
	 */
	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	/**
	 * @return the ddnm
	 */
	public String getDdnm() {
		return ddnm;
	}

	/**
	 * @param ddnm
	 *            the ddnm to set
	 */
	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

	/**
	 * @return the crea
	 */
	public Date getCrea() {
		return crea;
	}

	/**
	 * @param crea
	 *            the crea to set
	 */
	public void setCrea(Date crea) {
		this.crea = crea;
	}

	/**
	 * @return the upda
	 */
	public Date getUpda() {
		return upda;
	}

	/**
	 * @param upda
	 *            the upda to set
	 */
	public void setUpda(Date upda) {
		this.upda = upda;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
