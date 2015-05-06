package com.coco.core.xml;

import com.coco.core.xml.api.IXmlToBean;

/**
 * <p>
 * Xml统一操作类
 * </p>
 * <p>
 * create time : 2010-4-12 下午07:08:18
 * </p>
 * 
 * @author 许德建 【xudejian@126.com】
 */
public class XMLHelper {

	/**
	 * <p>
	 * 创建节点转换成Bean对象的转换器
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 * @return IXmlToBean<T>
	 */
	public static <T> IXmlToBean<T> createNode2Bean(Class<T> clazz) {
		return new XmlNodeToBean<T>(clazz);
	}

	/**
	 * <p>
	 * 创建节点属性转换成Bean对象的转换器
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 * @return IXmlToBean<T>
	 */
	public static <T> IXmlToBean<T> createAttr2Bean(Class<T> clazz) {
		return new XmlAttrToBean<T>(clazz);
	}

	/**
	 * <p>
	 * 格式化字符串
	 * </p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String formatXmlValue(String str) {
		if (str == null) {
			return "";
		}
		// return str.replaceAll("&", "&amp;").replaceAll("\"",
		// "&quot;").replaceAll("\'", "&apos; ").replaceAll("<",
		// "&lt; ").replaceAll(">", "&gt;");
		return str;
	}

}
