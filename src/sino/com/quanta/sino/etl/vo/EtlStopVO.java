package com.quanta.sino.etl.vo;

import java.util.Date;

/**
 * <p>
 * ETL生产线停线信息实体类
 * </p>
 * <p>
 * create: 2011-12-9 上午11:14:13
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlStopVO {

	/**
	 * 特记1
	 */
	private String vari1;

	/**
	 * 特记2
	 */
	private String vari2;

	/**
	 * 特记3
	 */
	private String vari3;

	/**
	 * 特记4
	 */
	private String vari4;

	/**
	 * 特记5
	 */
	private String vari5;

	/**
	 * 停线日期
	 */
	private Date dasr;

	/**
	 * 班
	 */
	private String ban;

	/**
	 * 某天停线的明细（如：一个也可以停线多次）
	 */
	private EtlStopMxVO[] items;

	public EtlStopMxVO[] getItems() {
		return items;
	}

	public void setItems(EtlStopMxVO[] items) {
		this.items = items;
	}

	public String getVari1() {
		return vari1;
	}

	public void setVari1(String vari1) {
		this.vari1 = vari1;
	}

	public String getVari2() {
		return vari2;
	}

	public void setVari2(String vari2) {
		this.vari2 = vari2;
	}

	public String getVari3() {
		return vari3;
	}

	public void setVari3(String vari3) {
		this.vari3 = vari3;
	}

	public String getVari4() {
		return vari4;
	}

	public void setVari4(String vari4) {
		this.vari4 = vari4;
	}

	public String getVari5() {
		return vari5;
	}

	public void setVari5(String vari5) {
		this.vari5 = vari5;
	}

	public Date getDasr() {
		return dasr;
	}

	public void setDasr(Date dasr) {
		this.dasr = dasr;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

}
