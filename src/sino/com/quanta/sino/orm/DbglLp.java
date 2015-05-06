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
 * 端板管理
 * 
 * @author 张红国[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_DbglLp")
public class DbglLp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 卷板No/PlieNo
	 */
	@Id
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	/**
	 * 线别
	 */
	@Column(name = "SLIN_", length = 1)
	private String slin;

	/**
	 * 重量
	 */
	@Column(name = "ZPZL_")
	private Integer zpzl;

	/**
	 * 足
	 */
	@Column(name = "DMFX_", length = 1)
	private String dmfx;
	/**
	 * 捆包形式
	 */
	@Column(name = "KBAO_", length = 3)
	private String kbao;
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
	 * 修正区分
	 */
	@Column(name = "CLQ_", length = 1)
	private String clq;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getSlin() {
		return slin;
	}

	public void setSlin(String slin) {
		this.slin = slin;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public String getDmfx() {
		return dmfx;
	}

	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
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

	public String getClq() {
		return clq;
	}

	public void setClq(String clq) {
		this.clq = clq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
