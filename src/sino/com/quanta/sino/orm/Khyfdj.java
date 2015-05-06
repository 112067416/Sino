package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 客户运输费用单价设置
 * </p>
 * <p>
 * create: 2011-2-13 下午11:17:03
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_KHYFDJ")
public class Khyfdj implements Serializable {

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
	 * 运输方式代码
	 */
	@Column(name = "YSFS_", length = 4)
	private String ysfs;

	/**
	 * 运输方式名称
	 */
	@Column(name = "YSFM_", length = 32)
	private String ysfm;

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
	 * 运费单价单位
	 */
	@Column(name = "DJDW_", length = 16)
	private String djdw;

	/**
	 * 路段
	 */
	@Column(name = "LD_", length = 16)
	private String ld;

	/**
	 * 路段代码
	 */
	@Column(name = "LDCN_", length = 16)
	private String ldcn;

	/**
	 * 运费单价
	 */
	@Column(name = "YFDJ_", length = 64)
	private Double yfdj;

	/**
	 * 运费单价状态
	 */
	@Column(name = "STAT_", length = 2)
	private String stat;

	/**
	 * 创建时间
	 */
	@Column(name = "CREA_")
	private Date crea;

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
	 * @return the ysfm
	 */
	public String getYsfm() {
		return ysfm;
	}

	/**
	 * @param ysfm
	 *            the ysfm to set
	 */
	public void setYsfm(String ysfm) {
		this.ysfm = ysfm;
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
	 * @return the djdw
	 */
	public String getDjdw() {
		return djdw;
	}

	/**
	 * @param djdw
	 *            the djdw to set
	 */
	public void setDjdw(String djdw) {
		this.djdw = djdw;
	}

	/**
	 * @return the yfdj
	 */
	public Double getYfdj() {
		return yfdj;
	}

	/**
	 * @param yfdj
	 *            the yfdj to set
	 */
	public void setYfdj(Double yfdj) {
		this.yfdj = yfdj;
	}

	/**
	 * @return the crea
	 */
	public Date getCrea() {
		return crea;
	}

	/**
	 * @param crea
	 *            the crea to set
	 */
	public void setCrea(Date crea) {
		this.crea = crea;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLd() {
		return ld;
	}

	public void setLd(String ld) {
		this.ld = ld;
	}

	public String getLdcn() {
		return ldcn;
	}

	public void setLdcn(String ldcn) {
		this.ldcn = ldcn;
	}

}
