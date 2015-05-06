package com.coco.core.persistence.converter;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * 转换器
 * </p>
 * <p>
 * create: 2010-12-21 上午09:51:36
 * </p>
 * 
 * @param <T>
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface Converter<T> {

	/**
	 * <p>
	 * 变换
	 * </p>
	 * 
	 * @param o
	 *            要转换对象
	 * @param x
	 *            转换方式
	 * @return T
	 */
	public T convert(T o, QF x);

}
