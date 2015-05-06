package com.coco.core.mvc.cascade.bean;

import java.util.List;

import com.coco.core.bean.Property;

/**
 * <p>
 * 级联Bean执行的结果值
 * </p>
 * <p>
 * create: 2010-12-23 下午02:42:51
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class BeanValue {

	/**
	 * 字段值，null表示全局
	 */
	private String field;

	/**
	 * 返回值
	 */
	private String value;

	/**
	 * 返回下拉列表
	 */
	private List<Property> options;

	public BeanValue(String value) {
		this.value = value;
	}

	public BeanValue(List<Property> options) {
		this.options = options;
	}

	public BeanValue(String field, String value) {
		this.field = field;
		this.value = value;
	}

	public BeanValue(String field, List<Property> options) {
		this.field = field;
		this.options = options;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the options
	 */
	public List<Property> getOptions() {
		return options;
	}

}
