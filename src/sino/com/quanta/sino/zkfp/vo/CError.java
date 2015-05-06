package com.quanta.sino.zkfp.vo;

/**
 * <p>
 * 现品与订货信息不配匹的记录
 * </p>
 * <p>
 * create: 2011-3-2 下午02:46:16
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CError {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 字段1值
	 */
	private String field1;

	/**
	 * 字段2值
	 */
	private String field2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
}
