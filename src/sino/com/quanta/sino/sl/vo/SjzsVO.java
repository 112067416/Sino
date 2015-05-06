/**
 * 
 */
package com.quanta.sino.sl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 实绩录入指示【包括订货的附页、备注；制品的业连】
 * </p>
 * <p>
 * create: 2011-1-15 下午12:26:48
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class SjzsVO {

	/**
	 * <p>
	 * 业连号【制品】
	 * </p>
	 */
	private List<String> ylnos;
	/**
	 * <p>
	 * 附页KPNO【订货】
	 * </p>
	 */
	private List<String> kpnos;

	/**
	 * <p>
	 * etl、木工所和sl备注
	 * </p>
	 */
	private String bz1;

	/**
	 * <p>
	 * SL备注【订货】
	 * </p>
	 */
	private String bz2;

	/**
	 * <p>
	 * etl、木工所和sl备注【订货】
	 * </p>
	 */
	private String bz3;

	public List<String> getYlnos() {
		if (this.ylnos == null) {
			this.ylnos = new ArrayList<String>();
		}
		return ylnos;
	}

	public void setYlnos(List<String> ylnos) {
		this.ylnos = ylnos;
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

	public String getBz2() {
		return bz2;
	}

	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}

	public String getBz3() {
		return bz3;
	}

	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}

	public String getBz1() {
		return bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

}
