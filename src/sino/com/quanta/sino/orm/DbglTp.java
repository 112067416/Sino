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
 * 端板管理
 * 
 * @author 张红国[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_DbglTP")
public class DbglTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * 捆包时间（年月日时分秒）,原字段TOUPDA
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "KBSJ_")
	private Date kbsj;
	/**
	 * 堆场
	 */
	@Column(name = "DUIC_", length = 1)
	private String duic;
	/**
	 * 库位
	 */
	@Column(name = "KW_", length = 1)
	private String kw;

	/**
	 * 状态
	 */
	@Column(name = "ZT_", length = 2)
	private String zt;

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

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public Date getKbsj() {
		return kbsj;
	}

	public void setKbsj(Date kbsj) {
		this.kbsj = kbsj;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}
