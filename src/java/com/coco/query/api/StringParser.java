package com.coco.query.api;

/**
 * <p>
 * 字符串解析成对象
 * </p>
 * <p>
 * create: 2010-12-21 上午10:29:56
 * </p>
 * 
 * @param <T>
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface StringParser<T> {

	/**
	 * <p>
	 * 字符串解析成对象
	 * </p>
	 * 
	 * @param value
	 * @return T
	 */
	public T parse(String value);
}
