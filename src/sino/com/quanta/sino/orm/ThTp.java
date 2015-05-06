package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 退货日志
 * 
 * @author 张良[jimsonhappy@126.com]
 */
@Entity
@Table(name = "SINO_TH")
public class ThTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 投诉对象
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "TSTP_", referencedColumnName = "ID_", nullable = false)
	private TsTp tsTp;

	/**
	 * 退货日期
	 */
	@Column(name = "THRI_")
	private Date thri;

	/**
	 * 备注
	 */
	@Column(name = "BZ_", length = 1024)
	private String bz;

	/**
	 * 原发票号
	 */
	@Column(name = "YFPH_", length = 40)
	private String yfph;

	/**
	 * 发票处置
	 */
	@Column(name = "FPCZ_", length = 2)
	private String fpcz;

	/**
	 * 状态
	 */
	@Column(name = "ZT_", length = 2)
	private String zt;

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
	 * @return the tsTp
	 */
	public TsTp getTsTp() {
		return tsTp;
	}

	/**
	 * @param tsTp
	 *            the tsTp to set
	 */
	public void setTsTp(TsTp tsTp) {
		this.tsTp = tsTp;
	}

	/**
	 * @return the thri
	 */
	public Date getThri() {
		return thri;
	}

	/**
	 * @param thri
	 *            the thri to set
	 */
	public void setThri(Date thri) {
		this.thri = thri;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz
	 *            the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the yfph
	 */
	public String getYfph() {
		return yfph;
	}

	/**
	 * @param yfph
	 *            the yfph to set
	 */
	public void setYfph(String yfph) {
		this.yfph = yfph;
	}

	/**
	 * @return the fpcz
	 */
	public String getFpcz() {
		return fpcz;
	}

	/**
	 * @param fpcz
	 *            the fpcz to set
	 */
	public void setFpcz(String fpcz) {
		this.fpcz = fpcz;
	}

	/**
	 * @return the zt
	 */
	public String getZt() {
		return zt;
	}

	/**
	 * @param zt
	 *            the zt to set
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}

}
