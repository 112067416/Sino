package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 合并包记录表
 * </p>
 * <p>
 * create: 2011-1-20 下午10:20:14
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_HBBJL")
public class Hbbjl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * UUID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * Pile生产实绩关联ID
	 */
	@Column(name = "PLSC_", length = 40)
	private String plsc;

	/**
	 * 新包号
	 */
	@Column(name = "XBH_", length = 11)
	private String xbh;

	/**
	 * 中间包号
	 */
	@Column(name = "ZJBH_", length = 11)
	private String zjbh;

	/**
	 * 序号
	 */
	@Column(name = "XH_")
	private Integer xh;

	/**
	 * 张数
	 */
	@Column(name = "ZS_")
	private Integer zs;

	/**
	 * pile 区分
	 */
	@Column(name = "PLQF_", length = 1)
	private String plqf;

	/**
	 * 原板制造商No
	 */
	@Column(name = "YBZZNO_", length = 1)
	private String ybzzno;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXbh() {
		return xbh;
	}

	public void setXbh(String xbh) {
		this.xbh = xbh;
	}

	public String getZjbh() {
		return zjbh;
	}

	public void setZjbh(String zjbh) {
		this.zjbh = zjbh;
	}

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public Integer getZs() {
		return zs;
	}

	public void setZs(Integer zs) {
		this.zs = zs;
	}

	public String getPlqf() {
		return plqf;
	}

	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	public String getYbzzno() {
		return ybzzno;
	}

	public void setYbzzno(String ybzzno) {
		this.ybzzno = ybzzno;
	}

	/**
	 * @return the plsc
	 */
	public String getPlsc() {
		return plsc;
	}

	/**
	 * @param plsc
	 *            the plsc to set
	 */
	public void setPlsc(String plsc) {
		this.plsc = plsc;
	}
}
