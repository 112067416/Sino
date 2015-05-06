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
 * 修改业联No.日志表
 * </p>
 * <p>
 * create: 2011-4-20 下午01:51:27
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_YLRZ")
public class Ylrz implements Serializable {

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
	 * 原材卷板ID
	 */
	@Column(name = "YCJB_ID_", length = 11)
	private String ycjbid;
	/**
	 * 制品在库DBID
	 */
	@Column(name = "ZPZK_ID_", length = 11)
	private String zpzkid;
	/**
	 * 订货No.行号
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;
	/**
	 * 制品号
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;
	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;
	/**
	 * 状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;
	/**
	 * Pile区分
	 */
	@Column(name = "PLQF_", length = 1)
	private String plqf;
	/**
	 * 等级
	 */
	@Column(name = "DENG_", length = 3)
	private String deng;
	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;
	/**
	 * 堆场
	 */
	@Column(name = "DUIC_", length = 4)
	private String duic;
	/**
	 * 原板制造商No
	 */
	@Column(name = "ZZNO_", length = 1)
	private String zzno;
	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;
	/**
	 * 制造商卷板No
	 */
	@Column(name = "ZZSJ_", length = 15)
	private String zzsj;
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
	/**
	 * 新业连号NO
	 */
	@Column(name = "NYLN_", length = 256)
	private String nyln;
	/**
	 * 作业停止标记
	 */
	@Column(name = "ZTBJ_", length = 2)
	private String ztbj;
	/**
	 * 旧业连号NO
	 */
	@Column(name = "YLN_", length = 256)
	private String yln;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYcjbid() {
		return ycjbid;
	}

	public void setYcjbid(String ycjbid) {
		this.ycjbid = ycjbid;
	}

	public String getZpzkid() {
		return zpzkid;
	}

	public void setZpzkid(String zpzkid) {
		this.zpzkid = zpzkid;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getPlqf() {
		return plqf;
	}

	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String getZzno() {
		return zzno;
	}

	public void setZzno(String zzno) {
		this.zzno = zzno;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
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

	public String getNyln() {
		return nyln;
	}

	public void setNyln(String nyln) {
		this.nyln = nyln;
	}

	public String getZtbj() {
		return ztbj;
	}

	public void setZtbj(String ztbj) {
		this.ztbj = ztbj;
	}

	public String getYln() {
		return yln;
	}

	public void setYln(String yln) {
		this.yln = yln;
	}

}
