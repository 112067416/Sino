package com.coco.core.util;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.coco.core.util.s2o.IString2Object;

/**
 * <p>
 * 字符串工具类
 * </p>
 * <p>
 * create: 2010-12-21 上午10:25:28
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class StringUtils {

	private static final Map<String, IString2Object<?>> PROPERTIES = new HashMap<String, IString2Object<?>>();

	static {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("coco-s2o");
			if (rb != null) {
				Enumeration<String> enumeration = rb.getKeys();
				IString2Object<?> s2o = null;
				String name, value;
				while (enumeration.hasMoreElements()) {
					name = enumeration.nextElement().toString();
					value = rb.getString(name);
					if ((name = name.trim()).length() > 0 && value != null
							&& (value = value.trim()).length() > 0) {
						try {
							Class<?> clazz = Class.forName(value);
							s2o = (IString2Object<?>) clazz.newInstance();
							PROPERTIES.put(name, s2o);
						}
						catch (Exception ex1) {
						}
					}
				}
			}
		}
		catch (Exception e) {

		}
	}

	/**
	 * <p>
	 * 对象转换成字符串
	 * </p>
	 * 
	 * @param value
	 * @return String
	 */
	public static String valueOf(Object value) {
		if (value == null) {
			return "";
		}
		String text = null;
		if (value instanceof java.util.Date) {
			text = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
			text = text.replaceAll("((\\s|:)00)+$", "");
		}
		else {
			text = value.toString();
		}
		return text;
	}

	/**
	 * <p>
	 * 字符串转换成对象
	 * </p>
	 * 
	 * @param <T>
	 * @param value
	 * @param clazz
	 * @return <T> T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T valueOf(String value, Class<T> clazz) {
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
			if ("1".equals(value)) {
				value = "true";
			}
			else if ("0".equals(value)) {
				value = "false";
			}
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
		IString2Object<?> s2o = PROPERTIES.get(clazz.getName());
		if (s2o != null) {
			return (T) s2o.valueOf(value);
		}
		try {
			Constructor<?> c = clazz.getConstructor(new Class[] { String.class });
			return (T) c.newInstance(value);
		}
		catch (Exception e) {
		}
		return null;
	}

	/**
	 * <p>
	 * 截断字节数,一个中文字包含两个字节数
	 * </p>
	 * 
	 * @param str
	 * @param size
	 * @return String
	 */
	public static String toByteSizeString(String str, int size) {
		return toByteSizeString(str, size, 0);
	}

	/**
	 * <p>
	 * 截断字节数,一个中文字包含两个字节数
	 * </p>
	 * 
	 * @param str
	 * @param size
	 * @param index
	 * @return String
	 */
	public static String toByteSizeString(String str, int size, int index) {
		if (str == null || size <= 0) {
			return "";
		}
		byte[] bs = str.getBytes();
		if (bs.length <= size) {
			return str;
		}
		if (index < 0) {
			index = 0;
		}
		char[] cs = str.toCharArray();
		if (cs.length <= index) {
			return "";
		}
		StringBuilder buf = new StringBuilder();
		for (int i = index; i < cs.length; i++) {
			if (buf.toString().getBytes().length > size) {
				break;
			}
			buf.append(cs[i]);
		}
		if (buf.toString().getBytes().length > size) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * <p>
	 * 查找字符串中符合正则的子串
	 * </p>
	 * 
	 * @param input
	 * @param regex
	 * @return List<String>
	 */
	public static List<String> find(String input, String regex) {
		if (input == null || input.length() == 0 || regex == null
				|| regex.length() == 0) {
			return new ArrayList<String>();
		}
		Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL).matcher(input);
		List<String> groups = new ArrayList<String>();
		while (matcher.find()) {
			groups.add(matcher.group());
		}
		return groups;
	}

	/**
	 * <p>
	 * 是否匹配id 123,456,789,...
	 * </p>
	 * 
	 * @param input
	 * @param id
	 * @return boolean
	 */
	public static boolean matcheId(String input, String id) {
		if (input == null || id == null) return false;
		return Pattern.matches("^(|.+,)" + id + "(,.*+|)$", input);
	}

	/**
	 * <p>
	 * 字符串拆分成Long集合
	 * </p>
	 * 
	 * @param str
	 * @param regex
	 * @return List<Long>
	 */
	public static List<Long> split(String str, String regex) {
		List<Long> list = new ArrayList<Long>();
		if (str != null && (str = str.trim()).length() > 0) {
			if (regex == null || regex.length() == 0) {
				regex = ",";
			}
			String[] arr = str.split(regex);
			for (String a : arr) {
				try {
					list.add(Long.valueOf(a));
				}
				catch (NumberFormatException e) {
				}
			}
		}
		return list;
	}

	/**
	 * <p>
	 * 格式化集合为字符串，默认逗号分割
	 * </p>
	 * 
	 * @param <T>
	 * @param coll
	 * @param seperator
	 * @return String
	 */
	public static <T> String join(Collection<T> coll, String seperator) {
		if (coll == null || coll.isEmpty()) {
			return "";
		}
		if (seperator == null || seperator.length() == 0) {
			seperator = ",";
		}
		StringBuilder buf = new StringBuilder();
		Iterator<T> it = coll.iterator();
		buf.append(it.next());
		while (it.hasNext()) {
			buf.append(seperator).append(it.next());
		}
		return buf.toString();
	}

	/**
	 * <p>
	 * 格式化数值为字符串，默认逗号分割
	 * </p>
	 * 
	 * @param <T>
	 * @param arr
	 * @param seperator
	 * @return String
	 */
	public static <T> String join(T[] arr, String seperator) {
		if (arr == null || arr.length == 0) {
			return "";
		}
		if (seperator == null || seperator.length() == 0) {
			seperator = ",";
		}
		StringBuilder buf = new StringBuilder();
		buf.append(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			buf.append(seperator).append(arr[i]);
		}
		return buf.toString();
	}

	/**
	 * <p>
	 * 截取指定宽度字数串，非ascii码或大写字符作为2个字
	 * </p>
	 * 
	 * @param str
	 * @param length
	 * @return String
	 */
	public static String charLength(String str, int length) {
		if (str == null || length <= 0) {
			return "";
		}
		char[] chs = str.toCharArray();
		if (chs.length * 2 <= length) {
			return str;
		}
		StringBuilder targetBuffer = new StringBuilder();
		int len = 0;
		int chValue;
		for (char ch : chs) {
			chValue = (int) ch;
			if (chValue > 127 || chValue >= 65 && chValue <= 90) {
				len += 2;
			}
			else {
				len++;
			}
			if (len > length) {
				break;
			}
			targetBuffer.append(ch);
		}
		return targetBuffer.toString();
	}

	/**
	 * <p>
	 * 前面补指定字符，若串超过指定长度或者指定长度小于等于零，则返回原串
	 * </p>
	 * 
	 * @param str
	 * @param length
	 * @param chr
	 * @return String
	 */
	public static String fillChar(String str, int length, char chr) {
		if (length <= 0) {
			return str;
		}
		if (str == null) {
			str = "";
		}
		int l = length - str.length();
		if (l <= 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < l; i++) {
			buf.append(chr);
		}
		return buf + str;
	}

	/**
	 * <p>
	 * 转换成输出的js字符串，替换双引号（\"），换行（\n），转义符（\）
	 * </p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String toJsString(String str) {
		if (str == null) {
			return "";
		}
		return str.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"").replaceAll("\n", "\\\\n");
	}

	public static String concat(Object... objects) {
		if (objects == null || objects.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Object object : objects) {
			if (object != null) {
				sb.append(object);
			}
		}
		return sb.toString();
	}

	/***
	 * <p>
	 * 格式字符串
	 * </p>
	 * 
	 * @param i
	 * @param format
	 * @return String
	 */
	public static String format(Integer i, String format) {
		if (i == null || format == null || format.isEmpty()) {
			return "";
		}
		return String.format(format, i);
	}
}
