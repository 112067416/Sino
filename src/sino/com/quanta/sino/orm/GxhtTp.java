package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 购销合同
 * </p>
 * <p>
 * create: 2011-1-3 下午02:57:43
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_GXHTTP")
public class GxhtTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 订货No
	 */
	@Id
	@Column(name = "DHNO_", length = 7)
	private String dhno;

	/***
	 * 用户代码
	 */
	@Column(name = "USER_", length = 4)
	private String user;

	/**
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 60)
	private String name;

	/***
	 * 货物品名
	 */
	@Column(name = "HWPM_", length = 256)
	private String hwpm;

	/***
	 * 交货地点及运输方式
	 */
	@Column(name = "JHFS_", length = 256)
	private String jhfs;

	/***
	 * 运输及到达港和费用负担
	 */
	@Column(name = "YSFS_", length = 256)
	private String ysfs;

	/***
	 * 包装标准及包装物的供应和回收
	 */
	@Column(name = "BZFS_", length = 256)
	private String bzfs;

	/**
	 * 结算方式及期限
	 */
	@Column(name = "JSFS_", length = 256)
	private String jsfs;

	/**
	 * 约定事项
	 */
	@Column(name = "YDSX_", length = 256)
	private String ydsx;

	/**
	 * 签约时间
	 */
	@Column(name = "QRSJ_")
	private Date qrsj;

	/**
	 * 开户行
	 */
	@Column(name = "KHH_", length = 128)
	private String khh;

	/**
	 * 帐号
	 */
	@Column(name = "ZH_", length = 32)
	private String zh;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getHwpm() {
		return hwpm;
	}

	public void setHwpm(String hwpm) {
		this.hwpm = hwpm;
	}

	public String getJhfs() {
		return jhfs;
	}

	public void setJhfs(String jhfs) {
		this.jhfs = jhfs;
	}

	public String getYsfs() {
		return ysfs;
	}

	public void setYsfs(String ysfs) {
		this.ysfs = ysfs;
	}

	public String getBzfs() {
		return bzfs;
	}

	public void setBzfs(String bzfs) {
		this.bzfs = bzfs;
	}

	public String getJsfs() {
		return jsfs;
	}

	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}

	public String getYdsx() {
		return ydsx;
	}

	public void setYdsx(String ydsx) {
		this.ydsx = ydsx;
	}

	public Date getQrsj() {
		return qrsj;
	}

	public void setQrsj(Date qrsj) {
		this.qrsj = qrsj;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKhh() {
		return khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

}
