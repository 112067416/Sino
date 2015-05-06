package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Pile（分选打包）生产实绩中间包日志
 * 
 * @author 方元龙[F.YuanLong@gmail.com]
 */
@Entity
@Table(name = "SINO_PLSCITEMLP")
public class PlscItemLp implements Serializable {

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
	 * 记录识别,原字段TOFLAG
	 */
	@Column(name = "FLAG_", length = 2)
	private String flag;

	/**
	 * 作成时间（年月日时分秒）,原字段TOCREA
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）,原字段TOUPDA
	 */
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 更新程序名
	 */
	@Column(name = "PROG_", length = 8)
	private String prog;

	/**
	 * 卷板NO/PILENO
	 */
	@Column(name = "JBN_", length = 11)
	private String jbn;
	/**
	 * 现品共通情报-FK
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "PLSCLP_", referencedColumnName = "ID_", nullable = false)
	private PlscLp plscLp;

	/**
	 * PILE区分(2)
	 */
	@Column(name = "PLQ_", length = 1)
	private String plq;

	/**
	 * 张数(2)
	 */
	@Column(name = "ZSU_", columnDefinition = "numeric(4,0)")
	private Integer zsu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getProg() {
		return prog;
	}

	public void setProg(String prog) {
		this.prog = prog;
	}

	public String getJbn() {
		return jbn;
	}

	public void setJbn(String jbn) {
		this.jbn = jbn;
	}

	public PlscLp getPlscLp() {
		return plscLp;
	}

	public void setPlscLp(PlscLp plscLp) {
		this.plscLp = plscLp;
	}

	public String getPlq() {
		return plq;
	}

	public void setPlq(String plq) {
		this.plq = plq;
	}

	public Integer getZsu() {
		return zsu;
	}

	public void setZsu(Integer zsu) {
		this.zsu = zsu;
	}

}
