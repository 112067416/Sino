package com.coco.core.mvc.cascade.bean;

/**
 * <p>
 * 字段与sql语句配置
 * </p>
 * <p>
 * create: 2010-12-23 上午11:08:37
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SqlField {

	/**
	 * 字段名称
	 */
	private String field;

	/**
	 * SQL语句
	 */
	private String sql;

	/**
	 * 下拉框标志
	 */
	private boolean select;

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql
	 *            the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the select
	 */
	public boolean isSelect() {
		return select;
	}

	/**
	 * @param select
	 *            the select to set
	 */
	public void setSelect(boolean select) {
		this.select = select;
	}

}
