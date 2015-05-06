package com.coco.core.util.s2o;

/**
 * <p>
 * 字符串转换成对象
 * </p>
 * <p>
 * create time : 2010-3-19 上午10:34:24
 * </p>
 * 
 * @author 许德建 【xudejian@126.com】
 */
public interface IString2Object<T> {

	/**
	 * <p>
	 * 字符串转换成对象
	 * </p>
	 * 
	 * @param value
	 * @return T
	 */
	public T valueOf(String value);
}
