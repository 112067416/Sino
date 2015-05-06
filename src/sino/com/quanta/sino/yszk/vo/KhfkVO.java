package com.quanta.sino.yszk.vo;

import java.util.Date;

/**
 * <p>
 * 付款冲帐VO
 * </p>
 * <p>
 * create: 2011-6-30 下午10:30:17
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhfkVO {

	/**
	 * 多个付款发票id的串联通过","
	 */
	private String[] ids;

	/**
	 * 客户付款记录主键
	 */
	private String id;

	/**
	 * 客户付款记录主键
	 */
	private String[] khfkIds;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 付款金额
	 */
	private Double fkje;

	/**
	 * 付款余额
	 */
	private Double fkye;

	/**
	 * 付款日期
	 */
	private Date crea;

	/**
	 * 调整金额
	 */
	private Double tzye;

	public String[] getIds() {
		return ids;
	}

	public void setIdStr(String ids) {
		if (ids == null || ids.isEmpty()) return;
		this.ids = ids.split(",");
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFkje() {
		return fkje;
	}

	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}

	public Double getFkye() {
		return fkye;
	}

	public void setFkye(Double fkye) {
		this.fkye = fkye;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public Double getTzye() {
		return tzye;
	}

	public void setTzye(Double tzye) {
		this.tzye = tzye;
	}

	public String[] getKhfkIds() {
		return khfkIds;
	}

	public void setKhfkIds(String[] khfkIds) {
		this.khfkIds = khfkIds;
	}

	public void setKhfkIdStr(String khfkIds) {
		if (khfkIds == null || khfkIds.isEmpty()) return;
		this.khfkIds = khfkIds.split(",");
	}
}
