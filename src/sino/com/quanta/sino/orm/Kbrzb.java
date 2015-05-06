package com.quanta.sino.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 捆包日志表
 * </p>
 * <p>
 * create: 2011-1-12 上午09:17:01
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_KBRZB")
public class Kbrzb {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * Coil No. 制品NO
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	/**
	 * 作成时间（年月日时分秒）
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 是否捆包 1：为捆包、0：为撤销捆包
	 */
	@Column(name = "ISKB_", length = 1)
	private String iskb;

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
	 * 担当者所在班组
	 */
	@Column(name = "BANZ_", length = 2)
	private String banz;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public String getIskb() {
		return iskb;
	}

	public void setIskb(String iskb) {
		this.iskb = iskb;
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

	public String getBanz() {
		return banz;
	}

	public void setBanz(String banz) {
		this.banz = banz;
	}

}
