package com.quanta.sino.orm;

import java.io.Serializable;

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
 * 用户送货地址，用户主文件子表
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_YONG_SHDZ")
public class YongShdz implements Serializable {

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
	 * 关联用户主文件
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "YONG_", referencedColumnName = "USER_", nullable = false)
	private YongMp yong;

	/**
	 * 送货地址代码
	 */
	@Column(name = "DZDM_", length = 4)
	private String dzdm;

	/**
	 * 简称
	 */
	@Column(name = "ABBR_", length = 10)
	private String abbr;

	/**
	 * 送货地址
	 */
	@Column(name = "ADDR_", length = 512, nullable = false)
	private String addr;

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
	 * @return the yong
	 */
	public YongMp getYong() {
		return yong;
	}

	/**
	 * @param yong
	 *            the yong to set
	 */
	public void setYong(YongMp yong) {
		this.yong = yong;
	}

	/**
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}

	/**
	 * @param abbr
	 *            the abbr to set
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDzdm() {
		return dzdm;
	}

	public void setDzdm(String dzdm) {
		this.dzdm = dzdm;
	}

}
