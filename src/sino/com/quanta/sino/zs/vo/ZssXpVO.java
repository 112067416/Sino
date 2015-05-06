package com.quanta.sino.zs.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 指示书中的现品信息
 * </p>
 * <p>
 * create: 2011-2-15 下午09:47:09
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class ZssXpVO {

	/**
	 * 卷板号
	 */
	private String jbno;
	/**
	 * 订货号
	 */
	private String dhno;
	/**
	 * 品种代码
	 */
	private String pzno;
	/**
	 * 溶接个数.酸洗
	 */
	private Integer rjsx;
	/**
	 * 溶接个数.冷延
	 */
	private Integer rjly;
	/**
	 * 制品重量
	 */
	private Integer zpzl;
	/**
	 * 原材客户略称
	 */
	private String ycbr;
	/**
	 * 制造商代码
	 */
	private String zzsd;
	/**
	 * 附页NO集合
	 */
	private List<String> kpnos;
	/**
	 * 业连NO集合
	 */
	private List<String> ylnos;
	/**
	 * 现品状态
	 */
	private String stat;

	/**
	 * 卷取长
	 */
	private Integer jbcn;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	public Integer getRjsx() {
		return rjsx;
	}

	public void setRjsx(Integer rjsx) {
		this.rjsx = rjsx;
	}

	public Integer getRjly() {
		return rjly;
	}

	public void setRjly(Integer rjly) {
		this.rjly = rjly;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public String getYcbr() {
		return ycbr;
	}

	public void setYcbr(String ycbr) {
		this.ycbr = ycbr;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public List<String> getKpnos() {
		if (this.kpnos == null) {
			this.kpnos = new ArrayList<String>();
		}
		return kpnos;
	}

	public void setKpnos(List<String> kpnos) {
		this.kpnos = kpnos;
	}

	public List<String> getYlnos() {
		if (this.ylnos == null) {
			this.ylnos = new ArrayList<String>();
		}
		return ylnos;

	}

	public void setYlnos(List<String> ylnos) {
		this.ylnos = ylnos;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Integer getJbcn() {
		return jbcn;
	}

	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

}
