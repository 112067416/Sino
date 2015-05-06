package com.coco.core.xml.api;

import java.util.List;

/**
 * <p>
 * Xml转换成bean对象
 * </p>
 * <p>
 * create time : 2010-4-12 下午06:13:01
 * </p>
 * 
 * @author 许德建 【xudejian@126.com】
 */
public interface IXmlToBean<T> {

	/**
	 * 
	 * <p>
	 * XML对象（Document、String、Element、InputStream）转换成对象
	 * </p>
	 * 
	 * @param input
	 * @return T
	 */
	public T parseToBean(Object input);

	/**
	 * 
	 * <p>
	 * XML对象（Document、String、Element、InputStream）转换成对象列表
	 * </p>
	 * 
	 * @param input
	 * @return List<T>
	 */
	public List<T> parseToBeans(Object input);

}
