package com.coco.query.api;

/**
 * <p>
 * 值解析器
 * </p>
 * <p>
 * create: 2011-1-17 下午03:43:13
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ValueParser {

	public StringConverter getConverter();

	public <T> T parse(String value, Class<T> clazz);

	public <T> T[] parse(String[] values, Class<T> clazz);

	/**
	 * <p>
	 * 格式化值成字符串
	 * </p>
	 * 
	 * @param obj
	 * @param type
	 * @return String
	 */
	public String format(Object obj, Class<?> type);

	/**
	 * <p>
	 * 格式化值成字符串
	 * </p>
	 * 
	 * @param obj
	 * @param type
	 * @return String
	 */
	public String format(Object obj, Class<?> type, int length, int precision);

}
