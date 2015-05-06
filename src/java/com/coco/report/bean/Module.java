package com.coco.report.bean;

import java.util.List;

public class Module {

	/**
	 * 模块名称
	 */
	private String name;

	/**
	 * 模块SQL
	 */
	private String sql;

	/**
	 * 换行字段数列表
	 */
	private int[] newlines;

	/**
	 * 参数
	 */
	private List<Param> params;

	/**
	 * 分组参数
	 */
	private List<String> groupNames;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(List<String> groupNames) {
		this.groupNames = groupNames;
	}

	/**
	 * @return the newlines
	 */
	public int[] getNewlines() {
		return newlines;
	}

	/**
	 * @param newlines
	 *            the newlines to set
	 */
	public void setNewlines(int[] newlines) {
		this.newlines = newlines;
	}

}
