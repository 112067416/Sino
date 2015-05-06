package com.quanta.sino.etl.vo;

import java.util.Date;

/**
 * <p>
 * </p>
 * <p>
 * create: 2011-12-8 下午03:31:48
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YxRecordVO {

	private Long varId;

	private String varName;

	private String varValue;

	private Date systime;

	public Long getVarId() {
		return varId;
	}

	public void setVarId(Long varId) {
		this.varId = varId;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getVarValue() {
		return varValue;
	}

	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}

	public Date getSystime() {
		return systime;
	}

	public void setSystime(Date systime) {
		this.systime = systime;
	}

}
