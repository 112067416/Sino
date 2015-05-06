package com.quanta.sino.zs.vo;

import java.util.ArrayList;
import java.util.List;

public class ZsdhVVO {
	/**
	 * COIL No
	 */
	private String jbno;
	/**
	 * 订货No
	 */
	private String dhno;
	/**
	 * 品种
	 */
	private String pzno;
	/**
	 * W个数
	 */
	private Integer rjsx;
	/**
	 * W个数
	 */
	private Integer rjly;
	/**
	 * 重量(千克)
	 */
	private Integer zpzl;
	/**
	 * 母卷客户
	 */
	private String ycbr;
	/**
	 * 制造商
	 */
	private String zzsd;
	/**
	 * 业连
	 */
	private List<String> ylns;
	/**
	 * 生产状态
	 */
	private String stat;

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

	public List<String> getYlns() {
		if (this.ylns == null) {
			this.ylns = new ArrayList<String>();
		}
		return ylns;
	}

	public void setYlns(List<String> ylns) {
		this.ylns = ylns;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
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
}
