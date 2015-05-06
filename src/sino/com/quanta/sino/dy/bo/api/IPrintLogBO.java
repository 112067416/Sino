package com.quanta.sino.dy.bo.api;

/**
 * <p>
 * 打印日志业务接口
 * </p>
 * <p>
 * create: 2011-4-20 下午03:10:12
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IPrintLogBO {

	/**
	 * <p>
	 * 保存打印数据
	 * </p>
	 * 
	 * @param nos
	 * @param type
	 * @param operator
	 */
	public void save(String[] nos, String type, String operator);
}
