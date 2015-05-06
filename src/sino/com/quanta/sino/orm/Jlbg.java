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
 * 记录变更日志
 * </p>
 * <p>
 * create: 2011-2-9 上午10:17:13
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_JLBG")
public class Jlbg implements Serializable {
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
	 * 卷板ID
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;
	/**
	 * 新卷板ID
	 */
	@Column(name = "NJBNO_", length = 11)
	private String njbno;
	/**
	 * 项目名称
	 */
	@Column(name = "XMMC_", length = 20)
	private String xmmc;
	/**
	 * 变更前值
	 */
	@Column(name = "BGQZ_", length = 64)
	private String bgqz;
	/**
	 * 变更后值
	 */
	@Column(name = "BGHZ_", length = 64)
	private String bghz;
	/**
	 * 模块类型
	 */
	@Column(name = "MKLX_", length = 2)
	private String mklx;
	/**
	 * 修改人
	 */
	@Column(name = "XGR_", length = 10)
	private String xgr;
	/**
	 * 修改时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "XGSJ_")
	private Date xgsj;

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

	public String getNjbno() {
		return njbno;
	}

	public void setNjbno(String njbno) {
		this.njbno = njbno;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getBgqz() {
		return bgqz;
	}

	public void setBgqz(String bgqz) {
		this.bgqz = bgqz;
	}

	public String getBghz() {
		return bghz;
	}

	public void setBghz(String bghz) {
		this.bghz = bghz;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

}
