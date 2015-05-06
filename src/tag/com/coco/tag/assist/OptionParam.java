package com.coco.tag.assist;

import java.util.Collection;

/**
 * 
 * <p>
 * </p>
 * <p>
 * create time : 2010-10-8 下午03:17:00
 * </p>
 * 
 * @author 许德建(xudejian@126.com)
 */
public class OptionParam {

	/**
	 * 下拉列表对象
	 */
	public Collection<?> objs;

	/**
	 * 值字段名
	 */
	public String id;

	/**
	 * 父值字段名
	 */
	public String parent;

	/**
	 * 文本字段名
	 */
	public String label;

	/**
	 * 默认值
	 */
	public String value;

	/**
	 * 叶子标志名
	 */
	public String leafName;

	/**
	 * 顺序
	 */
	public String order;

	/**
	 * 根值
	 */
	public String root;

}
