package com.coco.core.bean;

/**
 * <p>
 * 属性类，提供通用具有属性类型的基础类
 * </p>
 * <p>
 * create: 2010-12-21 上午09:03:35
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class Property {

	/**
	 * 键名
	 */
	private String key;

	/**
	 * 键值
	 */
	private String value;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 备注
	 */
	private String memo;

	public Property() {

	}

	public Property(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public Property(String key, String value, String description) {
		this.key = key;
		this.value = value;
		this.description = description;
	}

	public Property(String key, String value, String description, String memo) {
		this.key = key;
		this.value = value;
		this.description = description;
		this.memo = memo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
