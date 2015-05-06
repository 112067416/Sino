package com.coco.report.bean;

import java.util.List;

/**
 * <p>
 * Excel变量定义
 * </p>
 * <p>
 * create: 2010-12-21 上午10:34:52
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class Variable {

	/**
	 * 变量名
	 */
	private String name;

	/**
	 * 变量数值值下标位置
	 */
	private int index;

	/**
	 * 变量值中的字段值
	 */
	private String field;

	/**
	 * 分组值列表
	 */
	private List<String> keys;

	/**
	 * 是否是列表
	 */
	private boolean isList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public boolean isList() {
		return isList;
	}

	public void setList(boolean isList) {
		this.isList = isList;
	}

}
