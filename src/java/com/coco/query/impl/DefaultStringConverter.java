package com.coco.query.impl;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.coco.query.api.StringConverter;
import com.coco.query.api.StringParser;

/**
 * <p>
 * 字符串解析器
 * </p>
 * <p>
 * create time : 2010-6-8 上午08:32:53
 * </p>
 * 
 * @author 许德建 【xudejian@126.com】
 */
public class DefaultStringConverter implements StringConverter {

	/**
	 * 时间字符串解析器
	 */
	private static final StringParser<Date> DEFAULT_DATE_PARSER = new DateStringParser();

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
	@SuppressWarnings("unchecked")
	public <T> T convert(String value, Class<T> clazz) {
		if (value == null || clazz == null) {
			return null;
		}
		if (String.class.equals(clazz)) {
			return (T) value;
		}
		value = value.trim();
		if (value.length() == 0) {
			return null;
		}
		if (Boolean.class.equals(clazz) || boolean.class.equals(clazz)) {
			return (T) Boolean.valueOf(value);
		}
		if (Byte.class.equals(clazz) || byte.class.equals(clazz)) {
			value = value.replaceAll(",", "");
			return (T) Byte.valueOf(value);
		}
		if (Character.class.equals(clazz) || char.class.equals(clazz)) {
			return (T) new Character(value.length() > 0 ? value.charAt(0)
					: '\0');
		}
		if (Short.class.equals(clazz) || short.class.equals(clazz)) {
			value = value.replaceAll(",", "");
			return (T) Short.valueOf(value);
		}
		if (Integer.class.equals(clazz) || int.class.equals(clazz)) {
			value = value.replaceAll(",", "");
			return (T) Integer.valueOf(value);
		}
		if (Long.class.equals(clazz) || long.class.equals(clazz)) {
			value = value.replaceAll(",", "");
			return (T) Long.valueOf(value);
		}
		if (Float.class.equals(clazz) || float.class.equals(clazz)) {
			value = value.replaceAll(",", "");
			return (T) Float.valueOf(value);
		}
		if (Double.class.equals(clazz) || double.class.equals(clazz)) {
			value = value.replaceAll(",", "");
			return (T) Double.valueOf(value);
		}

		if (BigDecimal.class.equals(clazz)) {
			value = value.replaceAll(",", "");
			return (T) new BigDecimal(value);
		}
		if (clazz == byte[].class) {
			return (T) value.getBytes();
		}

		if (Collection.class.isAssignableFrom(clazz)) {
			return null;
		}
		if (Map.class.isAssignableFrom(clazz)) {
			return null;
		}
		if (clazz.isArray()) {
			return null;
		}
		StringParser<?> s2o = null;
		if (Date.class.isAssignableFrom(clazz)) {
			s2o = DEFAULT_DATE_PARSER;
		}
		if (s2o != null) {
			return (T) s2o.parse(value);
		}
		try {
			Constructor<?> c = clazz.getConstructor(new Class[] { String.class });
			return (T) c.newInstance(value);
		}
		catch (Exception e) {
		}
		return null;
	}
}
