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
 * <p>
 * 入侧日志
 * </p>
 * <p>
 * create: 2011-3-10 下午02:24:00
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_RCRZ")
public class Rcrz implements Serializable {

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
	 * 操作员
	 */
	@Column(name = "CZY_", length = 10)
	private String czy;
	/**
	 * 组
	 */
	@Column(name = "BZ_", length = 1)
	private String bz;
	/**
	 * 班组
	 */
	@Column(name = "BAN_", length = 1)
	private String ban;
	/**
	 * 卷板号
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;
	/**
	 * 系统用户
	 */
	@Column(name = "XTYH_", length = 10)
	private String xtyh;
	/**
	 * 操作时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "CZSJ_")
	private Date czsj;
	/**
	 * SL流水线代码（实绩）
	 */
	@Column(name = "SLIN_", length = 1)
	private String slin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCzy() {
		return czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getXtyh() {
		return xtyh;
	}

	public void setXtyh(String xtyh) {
		this.xtyh = xtyh;
	}

	public Date getCzsj() {
		return czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public String getSlin() {
		return slin;
	}

	public void setSlin(String slin) {
		this.slin = slin;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

}
