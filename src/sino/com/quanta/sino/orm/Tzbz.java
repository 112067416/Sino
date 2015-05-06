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
 * 台帐备注表
 * </p>
 * <p>
 * create: 2011-8-31 下午02:13:57
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_TZBZ")
public class Tzbz implements Serializable {

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
	 * 报表名称
	 */
	@Column(name = "BBM_", length = 11)
	private String bbm;

	/**
	 * 现品状况
	 */
	@Column(name = "XPZK_", length = 2)
	private String xpzk;

	/**
	 * 年月
	 */
	@Column(name = "NY", length = 6)
	private String ny;

	/**
	 * 商品编号
	 */
	@Column(name = "SPBH_", length = 16)
	private String spbh;
	/**
	 * 备注
	 */
	@Column(name = "BZ_", length = 256)
	private String bz;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBbm() {
		return bbm;
	}

	public void setBbm(String bbm) {
		this.bbm = bbm;
	}

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

}
