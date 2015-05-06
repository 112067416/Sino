package com.coco.query.api;

/**
 * <p>
 * 字符串解析器
 * </p>
 * <p>
 * create: 2010-12-21 上午10:29:29
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface StringConverter {

	/**
	 * <p>
	 * 字符串转换成对象
	 * </p>
	 * 
	 * @param <T>
	 * @param value
	 * @param clazz
	 * @return T
	 */
	public <T> T convert(String value, Class<T> clazz);
}
