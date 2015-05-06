package com.coco.core.mvc.cascade.api;

/**
 * <p>
 * 级联查询器
 * </p>
 * <p>
 * create: 2010-12-23 上午10:39:56
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ICascadeExecuter {

	/**
	 * <p>
	 * 执行级联查询
	 * </p>
	 * 
	 * @param module
	 *            模块
	 * @param value
	 *            传入查询条件值
	 * @return String
	 */
	public String execute(String module, String value);

}
