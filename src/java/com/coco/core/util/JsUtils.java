package com.coco.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.coco.core.util.ReflectUtils.ClassType;

/**
 * <p>
 * javascript工具类
 * </p>
 * <p>
 * create: 2010-12-21 上午10:05:36
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class JsUtils {

	/**
	 * 时间格式化
	 */
	private static final Pattern PATTERN_DATE = Pattern.compile("(00)*(:00)*\\.000$");

	private static final SimpleDateFormat SDF_LONG = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * <p>
	 * 生产单一对象串
	 * </p>
	 * 
	 * @param obj
	 * @return String
	 */
	public static String toSimpleObject(Object obj) {
		return toSimpleObject(obj, null, null, null, null);
	}

	/**
	 * <p>
	 * 生成Map对象串
	 * </p>
	 * 
	 * @param map
	 * @return String
	 */
	public static String toMap(Map<?, ?> map) {
		if (map == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Set<?> keys = map.keySet();
		for (Object key : keys) {
			if (key == null) {
				continue;
			}
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(key).append(":");
			sb.append(valueOf(map.get(key)));
		}

		return "{" + sb.toString() + "}";
	}

	/**
	 * <p>
	 * 转换值
	 * </p>
	 * 
	 * @param obj
	 * @return String
	 */
	private static String valueOf(Object obj) {
		if (obj == null) {
			return "null";
		}
		if (obj instanceof Boolean) {
			return obj.toString();
		}
		String value;
		ClassType classType = ReflectUtils.classType(obj.getClass());
		if (ClassType.number == classType) {
			return String.valueOf(obj);
		}
		if (ClassType.date == classType) {
			value = SDF_LONG.format((Date) obj);
			value = PATTERN_DATE.matcher(value).replaceFirst("").trim();
			return "\"" + value + "\"";
		}
		if (ClassType.string == classType || ClassType.tostring == classType) {
			return "\"" + StringUtils.toJsString(obj.toString()) + "\"";
		}
		if (ClassType.object == classType) {
			return toSimpleObject(obj);
		}
		if (ClassType.array == classType) {
			Object[] items = (Object[]) obj;
			value = "";
			for (Object item : items) {
				if (!value.isEmpty()) {
					value += ",";
				}
				value += valueOf(item);
			}
			return "[" + value + "]";
		}
		if (ClassType.list == classType || ClassType.set == classType) {
			@SuppressWarnings("unchecked")
			Collection<Object> items = (Collection<Object>) obj;
			value = "";
			for (Object item : items) {
				if (!value.isEmpty()) {
					value += ",";
				}
				value += valueOf(item);
			}
			return "[" + value + "]";
		}
		if (ClassType.map == classType) {
			return toMap((Map<?, ?>) obj);
		}
		return "null";
	}

	/**
	 * <p>
	 * 指定字段或者忽略字段生成单一对象串
	 * </p>
	 * 
	 * @param obj
	 * @param customFields
	 * @param ignoreFields
	 * @return String
	 */
	public static String toSimpleObject(Object obj, Set<String> customFields,
			Set<String> ignoreFields) {
		return toSimpleObject(obj, customFields, ignoreFields, null, null);
	}

	/**
	 * <p>
	 * 指定忽略字段生成单一对象串
	 * </p>
	 * 
	 * @param obj
	 * @param ignoreFields
	 * @param exprs
	 * @return String
	 */
	public static String toSimpleObject(Object obj, Set<String> customFields,
			Set<String> ignoreFields, Set<String> exprs) {
		return toSimpleObject(obj, customFields, ignoreFields, exprs, null);
	}

	/**
	 * <p>
	 * 指定忽略键名生成Map串
	 * </p>
	 * 
	 * @param map
	 * @param ignoreKeys
	 * @return String
	 */
	public static String toMap(Map<?, ?> map, Set<String> ignoreKeys) {
		return toMap(map, ignoreKeys, null);
	}

	/**
	 * <p>
	 * 指定忽略键名和对象路径生成Map串
	 * </p>
	 * 
	 * @param map
	 * @param ignoreKeys
	 * @param path
	 * @return String
	 */
	public static String toMap(Map<?, ?> map, Set<String> ignoreKeys,
			String path) {
		if (map == null) {
			return "";
		}
		if (path == null) {
			path = "";
		}
		StringBuilder sb = new StringBuilder();
		Set<?> keys = map.keySet();
		for (Object key : keys) {
			if (key == null || ignoreKeys != null
					&& ignoreKeys.contains(path + key)) {
				continue;
			}
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(key).append(":");
			sb.append(valueOf(map.get(key)));
		}

		return "{" + sb.toString() + "}";
	}

	/**
	 * <p>
	 * 指定忽略键名和对象路径生成单一对象串
	 * </p>
	 * 
	 * @param obj
	 * @param ignoreFields
	 * @param exprs
	 * @param path
	 * @return String
	 */
	private static String toSimpleObject(Object obj, Set<String> customFields,
			Set<String> ignoreFields, Set<String> exprs, String path) {
		if (obj == null) {
			return "";
		}
		if (path == null) {
			path = "";
		}
		ClassType classType = ReflectUtils.classType(obj.getClass());
		if (ClassType.object != classType) {
			return valueOf(obj);
		}

		List<Field> fields = ReflectUtils.fields(obj.getClass(), -1);

		StringBuilder sb = new StringBuilder();
		for (Field field : fields) {
			if (customFields != null
					&& !customFields.contains(path + field.getName())
					|| ignoreFields != null
					&& ignoreFields.contains(path + field.getName())
					|| Modifier.isStatic(field.getModifiers())
					|| field.getName().startsWith("CGLIB")) {
				continue;
			}
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(field.getName()).append(":");
			sb.append(valueOf(ReflectUtils.fieldValue(obj, field), customFields, ignoreFields, exprs, path, field.getName()));
		}
		if (exprs != null && !exprs.isEmpty()) {
			for (String expr : exprs) {
				if (expr.isEmpty()) {
					continue;
				}
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append(expr).append(":");
				sb.append(valueOf(ReflectUtils.eval(obj, expr)));
			}
		}

		return "{" + sb.toString() + "}";
	}

	/**
	 * <p>
	 * 转换值
	 * </p>
	 * 
	 * @param obj
	 * @param ignoreFields
	 * @param exprs
	 * @param path
	 * @param fieldName
	 * @return String
	 */
	private static String valueOf(Object obj, Set<String> customFields,
			Set<String> ignoreFields, Set<String> exprs, String path,
			String fieldName) {
		if (obj == null) {
			return "null";
		}
		if (obj instanceof Boolean) {
			return obj.toString();
		}
		String value;
		ClassType classType = ReflectUtils.classType(obj.getClass());
		if (ClassType.number == classType) {
			return String.valueOf(obj);
		}
		if (ClassType.date == classType) {
			value = SDF_LONG.format((Date) obj);
			value = PATTERN_DATE.matcher(value).replaceFirst("").trim();
			return "\"" + value + "\"";
		}
		if (ClassType.string == classType || ClassType.tostring == classType) {
			return "\"" + StringUtils.toJsString(obj.toString()) + "\"";
		}
		if (ClassType.object == classType) {
			return toSimpleObject(obj, customFields, ignoreFields, exprs, path
					+ fieldName + ".");
		}
		if (ClassType.array == classType) {
			Object[] items = (Object[]) obj;
			value = "";
			for (Object item : items) {
				if (!value.isEmpty()) {
					value += ",";
				}
				value += valueOf(item, customFields, ignoreFields, exprs, path, fieldName);
			}
			return "[" + value + "]";
		}
		if (ClassType.list == classType || ClassType.set == classType) {
			@SuppressWarnings("unchecked")
			Collection<Object> items = (Collection<Object>) obj;
			value = "";
			for (Object item : items) {
				if (!value.isEmpty()) {
					value += ",";
				}
				value += valueOf(item, customFields, ignoreFields, exprs, path, fieldName);
			}
			return "[" + value + "]";
		}
		if (ClassType.map == classType) {
			return toMap((Map<?, ?>) obj, ignoreFields, path + fieldName + ".");
		}
		return "null";
	}
}
