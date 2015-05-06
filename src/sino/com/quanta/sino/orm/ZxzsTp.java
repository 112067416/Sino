package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 装箱指示书
 * </p>
 * <p>
 * create: 2011-2-23 上午11:20:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_ZXZS")
public class ZxzsTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 装箱指示No
	 */
	@Id
	@Column(name = "ID_", length = 10)
	private String zxno;
	/**
	 * 出货日
	 */
	@Column(name = "CHRI_")
	private java.util.Date chri;

	/**
	 * 出货重量
	 */
	@Column(name = "CHZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double chzl;

	/**
	 * 出货制品数
	 */
	@Column(name = "CHSU_")
	private Integer chsu;

	/**
	 * 作成时间（年月日时分秒）,原字段TOCREA
	 */
	@Column(name = "CREA_")
	private java.util.Date crea;

	/**
	 * 送货点代码
	 */
	@Column(name = "SHNO_", length = 2)
	private String shno;

	/**
	 * 送货点略称
	 */
	@Column(name = "SHNM_", length = 16)
	private String shnm;

	/**
	 * 到达地点
	 */
	@Column(name = "ADDR_", length = 126)
	private String addr;

	/**
	 * 状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 更新时间（年月日时分秒）,原字段TOUPDA
	 */
	@Column(name = "UPDA_")
	private java.util.Date upda;

	/**
	 * 用户代码
	 */
	@Column(name = "USER_", length = 4)
	private String user;

	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;

	/**
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 60)
	private String name;

	/**
	 * 运输公司代码
	 */
	@Column(name = "YSGS_", length = 2)
	private String ysgs;

	/**
	 * 运输公司名称
	 */
	@Column(name = "YSNM_", length = 64)
	private String ysnm;

	/**
	 * 运输方式
	 */
	@Column(name = "YSFS_", length = 2)
	private String ysfs;

	/**
	 * 次日出货联络单主键
	 */
	@Column(name = "PID_", length = 40)
	private String pid;

	/**
	 * 打单人
	 */
	@Column(name = "DDR_", length = 32)
	private String ddr;

	/**
	 * @return the zxno
	 */
	public String getZxno() {
		return zxno;
	}

	/**
	 * @param zxno
	 *            the zxno to set
	 */
	public void setZxno(String zxno) {
		this.zxno = zxno;
	}

	/**
	 * @return the chri
	 */
	public java.util.Date getChri() {
		return chri;
	}

	/**
	 * @param chri
	 *            the chri to set
	 */
	public void setChri(java.util.Date chri) {
		this.chri = chri;
	}

	/**
	 * @return the chzl
	 */
	public Double getChzl() {
		return chzl;
	}

	/**
	 * @param chzl
	 *            the chzl to set
	 */
	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	/**
	 * @return the chsu
	 */
	public Integer getChsu() {
		return chsu;
	}

	/**
	 * @param chsu
	 *            the chsu to set
	 */
	public void setChsu(Integer chsu) {
		this.chsu = chsu;
	}

	/**
	 * @return the crea
	 */
	public java.util.Date getCrea() {
		return crea;
	}

	/**
	 * @param crea
	 *            the crea to set
	 */
	public void setCrea(java.util.Date crea) {
		this.crea = crea;
	}

	/**
	 * @return the shno
	 */
	public String getShno() {
		return shno;
	}

	/**
	 * @param shno
	 *            the shno to set
	 */
	public void setShno(String shno) {
		this.shno = shno;
	}

	/**
	 * @return the shnm
	 */
	public String getShnm() {
		return shnm;
	}

	/**
	 * @param shnm
	 *            the shnm to set
	 */
	public void setShnm(String shnm) {
		this.shnm = shnm;
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

	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * @return the upda
	 */
	public java.util.Date getUpda() {
		return upda;
	}

	/**
	 * @param upda
	 *            the upda to set
	 */
	public void setUpda(java.util.Date upda) {
		this.upda = upda;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ysgs
	 */
	public String getYsgs() {
		return ysgs;
	}

	/**
	 * @param ysgs
	 *            the ysgs to set
	 */
	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

	/**
	 * @return the ysnm
	 */
	public String getYsnm() {
		return ysnm;
	}

	/**
	 * @param ysnm
	 *            the ysnm to set
	 */
	public void setYsnm(String ysnm) {
		this.ysnm = ysnm;
	}

	/**
	 * @return the ysfs
	 */
	public String getYsfs() {
		return ysfs;
	}

	/**
	 * @param ysfs
	 *            the ysfs to set
	 */
	public void setYsfs(String ysfs) {
		this.ysfs = ysfs;
	}

	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDdr() {
		return ddr;
	}

	public void setDdr(String ddr) {
		this.ddr = ddr;
	}

}
