package com.coco.core.mvc.cascade.bean;

import java.util.List;

/**
 * <p>
 * 级联sql模块
 * </p>
 * <p>
 * create: 2010-12-23 上午11:17:55
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SqlModule {

	/**
	 * 全局sql
	 */
	private SqlField all;

	/**
	 * 字段sql
	 */
	private List<SqlField> fields;

	/**
	 * @return the fields
	 */
	public List<SqlField> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List<SqlField> fields) {
		this.fields = fields;
	}

	/**
	 * @return the all
	 */
	public SqlField getAll() {
		return all;
	}

	/**
	 * @param all
	 *            the all to set
	 */
	public void setAll(SqlField all) {
		this.all = all;
	}

}
