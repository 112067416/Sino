package com.coco.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 类功能操作处理器
 * </p>
 * <p>
 * create time : 2010-3-19 上午08:31:22
 * </p>
 * 
 * @author 许德建 【xudejian@126.com】
 *         <p>
 *         copyright &copy; 海南博事达软件科技有限公司
 *         </p>
 */
public class ReflectUtils {

	/**
	 * <p>
	 * 获取类的类别
	 * </p>
	 * 
	 * @param clazz
	 *            类
	 * @return ClassType 返回指定类的类别
	 */
	public static ClassType classType(Class<?> clazz) {
		if (clazz == null) {
			return ClassType.defalt;
		}
		if (String.class.isAssignableFrom(clazz)) {
			return ClassType.string;
		}
		if (StringBuffer.class.isAssignableFrom(clazz)
				|| Boolean.class.isAssignableFrom(clazz)
				|| boolean.class.isAssignableFrom(clazz)
				|| Character.class.isAssignableFrom(clazz)
				|| char.class.isAssignableFrom(clazz)) {
			return ClassType.tostring;
		}
		if (Byte.class.isAssignableFrom(clazz)
				|| byte.class.isAssignableFrom(clazz)
				|| Short.class.isAssignableFrom(clazz)
				|| short.class.isAssignableFrom(clazz)
				|| Integer.class.isAssignableFrom(clazz)
				|| int.class.isAssignableFrom(clazz)
				|| Long.class.isAssignableFrom(clazz)
				|| long.class.isAssignableFrom(clazz)
				|| Float.class.isAssignableFrom(clazz)
				|| float.class.isAssignableFrom(clazz)
				|| Double.class.isAssignableFrom(clazz)
				|| double.class.isAssignableFrom(clazz)
				|| Number.class.isAssignableFrom(clazz)
				|| BigInteger.class.isAssignableFrom(clazz)
				|| BigDecimal.class.isAssignableFrom(clazz)) {
			return ClassType.number;
		}
		if (Date.class.isAssignableFrom(clazz)) {
			return ClassType.date;
		}
		if (clazz.isArray()) {
			return ClassType.array;
		}
		if (List.class.isAssignableFrom(clazz)) {
			return ClassType.list;
		}
		if (Set.class.isAssignableFrom(clazz)) {
			return ClassType.set;
		}
		if (Map.class.isAssignableFrom(clazz)) {
			return ClassType.map;
		}
		return ClassType.object;
	}

	/**
	 * <p>
	 * 获取类中所有字段,包括父类
	 * </p>
	 * 
	 * @param clazz
	 *            类名
	 * @param deep
	 *            取父类深度，负数取所有字段
	 * @return List [Field]
	 */
	public static List<Field> fields(Class<?> clazz, int deep) {
		if (clazz == null) {
			return null;
		}
		java.util.List<Field> fields = new java.util.ArrayList<Field>();
		Class<?> tClazz = clazz;
		int n = 0;
		while (!tClazz.equals(Object.class) && (deep <= 0 || (n++) < deep)) {
			try {
				Field[] fieldArr = tClazz.getDeclaredFields();
				java.util.Collections.addAll(fields, fieldArr);
			}
			catch (SecurityException e) {
			}
			tClazz = tClazz.getSuperclass();
		}
		return fields;
	}

	/**
	 * <p>
	 * 获取类中所有字段
	 * </p>
	 * 
	 * @param clazz
	 * @return List [Field]
	 */
	public static List<Field> fields(Class<?> clazz) {
		return fields(clazz, -1);
	}

	/**
	 * <p>
	 * 获取类中的属性
	 * </p>
	 * 
	 * @param clazz
	 *            类名
	 * @param fieldName
	 *            字段名称
	 * @return Field 字段属性
	 */
	public static Field field(Class<?> clazz, String fieldName) {
		if (clazz == null || fieldName == null
				|| (fieldName = fieldName.trim()).length() == 0) {
			return null;
		}
		Class<?> tClazz = clazz;
		Field field = null;
		while (!tClazz.equals(Object.class)) {
			try {
				field = tClazz.getDeclaredField(fieldName);
				if (field != null) {
					break;
				}
			}
			catch (Exception e) {
			}
			tClazz = tClazz.getSuperclass();
		}
		return field;
	}

	/**
	 * <p>
	 * 获取类中所有方法,包括父类
	 * </p>
	 * 
	 * @param clazz
	 * @param deep
	 * @return List[Method]
	 */
	public static List<Method> methods(Class<?> clazz, int deep) {
		if (clazz == null) {
			return null;
		}
		java.util.List<Method> methods = new java.util.ArrayList<Method>();
		Class<?> tClazz = clazz;
		int n = 0;
		while (!tClazz.equals(Object.class) && (deep <= 0 || (n++) < deep)) {
			try {
				Method[] fieldArr = tClazz.getDeclaredMethods();
				java.util.Collections.addAll(methods, fieldArr);
			}
			catch (SecurityException e) {
			}
			tClazz = tClazz.getSuperclass();
		}
		return methods;
	}

	/**
	 * <p>
	 * 获取类中所有方法
	 * </p>
	 * 
	 * @param clazz
	 * @return List [Method]
	 */
	public static List<Method> methods(Class<?> clazz) {
		return methods(clazz, -1);
	}

	/**
	 * <p>
	 * 获取类中没有参数的方法
	 * </p>
	 * 
	 * @param clazz
	 *            类名
	 * @param methodName
	 *            方法名称
	 * @return Method 方法
	 */
	public static Method method(Class<?> clazz, String methodName) {
		if (clazz == null || methodName == null
				|| (methodName = methodName.trim()).length() == 0) {
			return null;
		}
		Class<?> tClazz = clazz;
		Method method = null;
		while (!tClazz.equals(Object.class)) {
			try {
				method = tClazz.getDeclaredMethod(methodName);
				if (method != null) {
					break;
				}
			}
			catch (Exception e) {
			}
			tClazz = tClazz.getSuperclass();
		}
		return method;
	}

	/**
	 * <p>
	 * 获取类中指定参数类型的方法
	 * </p>
	 * 
	 * @param clazz
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param parameterTypes
	 *            方法参数
	 * @return Method
	 */
	public static Method method(Class<?> clazz, String methodName,
			Class<?>... parameterTypes) {
		if (parameterTypes == null) {
			return method(clazz, methodName);
		}
		if (clazz == null || methodName == null
				|| (methodName = methodName.trim()).length() == 0) {
			return null;
		}
		Class<?> tClazz = clazz;
		Method method = null;
		while (!tClazz.equals(Object.class)) {
			try {
				method = tClazz.getDeclaredMethod(methodName, parameterTypes);
				if (method != null) {
					break;
				}
			}
			catch (Exception e) {
			}
			tClazz = tClazz.getSuperclass();
		}
		return method;
	}

	/**
	 * <p>
	 * 获取指定方法名和参数个数的类方法列表
	 * </p>
	 * 
	 * @param clazz
	 *            类
	 * @param methodName
	 *            方法名
	 * @param types
	 *            参数个数
	 * @return List<Method>
	 */
	public static List<Method> methods(Class<?> clazz, String methodName,
			int types) {
		if (clazz == null || methodName == null) {
			return null;
		}
		if (types < 0) {
			types = 0;
		}
		java.util.List<Method> methods = new java.util.ArrayList<Method>();
		Class<?> tClazz = clazz;
		while (!tClazz.equals(Object.class)) {
			try {
				Method[] methodArr = tClazz.getDeclaredMethods();
				for (Method method : methodArr) {
					if (method.getName().equals(methodName)
							&& method.getParameterTypes().length == types) {
						methods.add(method);
					}
				}
			}
			catch (SecurityException e) {
			}
			tClazz = tClazz.getSuperclass();
		}
		return methods;
	}

	/**
	 * <p>
	 * 获取指定方法名和参数个数的类方法(若有多个则返回第一个)
	 * </p>
	 * 
	 * @param clazz
	 *            类
	 * @param methodName
	 *            方法名
	 * @param types
	 *            参数个数
	 * @return Method
	 */
	public static Method method(Class<?> clazz, String methodName, int types) {
		if (clazz == null || methodName == null) {
			return null;
		}
		if (types < 0) {
			types = 0;
		}
		Class<?> tClazz = clazz;
		while (!tClazz.equals(Object.class)) {
			try {
				Method[] methodArr = tClazz.getDeclaredMethods();
				for (Method method : methodArr) {
					if (method.getName().equals(methodName)
							&& method.getParameterTypes().length == types) {
						return method;
					}
				}
			}
			catch (SecurityException e) {
			}
			tClazz = tClazz.getSuperclass();
		}
		return null;
	}

	/**
	 * <p>
	 * 格式化字段名称
	 * </p>
	 * 
	 * @param fieldName
	 *            字段名称
	 * @return String 格式化后的字段名称
	 */
	public static String formatFieldName(String fieldName) {
		if (fieldName == null) {
			return null;
		}
		StringBuffer nameBuf = new StringBuffer();
		fieldName = fieldName.trim().toLowerCase().replaceAll(" ", "");
		char ch;
		boolean lastCharIsSpace = false;
		for (int chAt = 0; chAt < fieldName.length(); chAt++) {
			ch = fieldName.charAt(chAt);
			if (ch == '_') {
				lastCharIsSpace = true;
				continue;
			}
			nameBuf.append(lastCharIsSpace ? (char) (ch & 0xDF) : ch);
			lastCharIsSpace = false;
		}
		return nameBuf.toString();
	}

	/**
	 * <p>
	 * 根据字段格式化成获取（get）方法
	 * </p>
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String formatGetMethodName(String fieldName) {
		if (fieldName == null || fieldName.length() == 0) {
			return "get";
		}
		return "get" + (char) (fieldName.charAt(0) & 0xDF)
				+ fieldName.substring(1);
	}

	/**
	 * <p>
	 * 根据字段格式化成设置（set）方法
	 * </p>
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String formatSetMethodName(String fieldName) {
		if (fieldName == null || fieldName.length() == 0) {
			return "set";
		}
		return "set" + (char) (fieldName.charAt(0) & 0xDF)
				+ fieldName.substring(1);
	}

	/**
	 * <p>
	 * 拷贝对象
	 * </p>
	 * 
	 * @param desc
	 *            目标对象
	 * @param orig
	 *            源对象
	 * @param excludeNull
	 *            是否忽略NULL值的字段
	 */
	public static void copy(Object desc, Object orig, boolean excludeNull) {
		if (desc == null || orig == null) {
			return;
		}
		List<Field> descFields = fields(desc.getClass());
		List<Field> origFields = fields(orig.getClass());
		Map<String, Field> descFieldMap = new java.util.HashMap<String, Field>();
		for (Field f : descFields) {
			descFieldMap.put(f.getName(), f);
		}
		Field descField;
		Object oValue;
		for (Field f : origFields) {
			descField = descFieldMap.get(f.getName());
			if (descField != null
					&& (descField.getType().equals(f.getType()) || descField.getType().isAssignableFrom(f.getType()))) {
				try {
					oValue = fieldValue(orig, f);
					if (oValue == null) {
						if (!excludeNull) {
							putFieldValue(desc, descField, null);
						}
					}
					else {
						putFieldValue(desc, descField, oValue);
					}
				}
				catch (Exception e) {
				}
			}
		}
	}

	/**
	 * <p>
	 * 拷贝对象
	 * </p>
	 * 
	 * @param desc
	 *            目标对象
	 * @param orig
	 *            源对象
	 * @param includeFields
	 *            指定字段
	 * @param excludeFields
	 *            忽略字段
	 * @param excludeNull
	 *            是否忽略NULL值的字段
	 */
	public static void copy(Object desc, Object orig,
			Set<String> includeFields, Set<String> excludeFields,
			boolean excludeNull) {
		if (desc == null || orig == null) {
			return;
		}
		List<Field> descFields = fields(desc.getClass());
		List<Field> origFields = fields(orig.getClass());
		Map<String, Field> descFieldMap = new java.util.HashMap<String, Field>();
		for (Field f : descFields) {
			if (includeFields != null && !includeFields.contains(f.getName())
					|| excludeFields != null
					&& excludeFields.contains(f.getName())) {
				continue;
			}
			descFieldMap.put(f.getName(), f);
		}
		Field descField;
		Object oValue;
		for (Field f : origFields) {
			descField = descFieldMap.get(f.getName());
			if (descField != null
					&& (descField.getType().equals(f.getType()) || descField.getType().isAssignableFrom(f.getType()))) {
				try {
					oValue = fieldValue(orig, f);
					if (oValue == null) {
						if (!excludeNull) {
							putFieldValue(desc, descField, null);
						}
					}
					else {
						putFieldValue(desc, descField, oValue);
					}
				}
				catch (Exception e) {
				}
			}
		}
	}

	/**
	 * <p>
	 * 获取字段值
	 * </p>
	 * 
	 * @param obj
	 *            Object
	 * @param field
	 *            Field
	 * @return Object
	 */
	public static Object fieldValue(Object obj, Field field) {
		if (obj == null || field == null) {
			return null;
		}
		if (Modifier.isPublic(field.getModifiers())) {
			try {
				return field.get(obj);
			}
			catch (Exception e) {
				return null;
			}
		}
		Object value = null;
		String methodName = ReflectUtils.formatGetMethodName(field.getName());
		try {
			Method method = obj.getClass().getMethod(methodName);
			if (Modifier.isPublic(method.getModifiers())) {
				value = method.invoke(obj);
			}
		}
		catch (Exception e) {
		}
		if (value == null) {
			if (field.getType() == Boolean.class
					|| field.getType() == boolean.class) {
				methodName = methodName.replace("get", "is");
				try {
					Method method = obj.getClass().getMethod(methodName);
					if (Modifier.isPublic(method.getModifiers())) {
						value = method.invoke(obj);
					}
				}
				catch (Exception e) {
				}
			}
		}
		return value;
	}

	/**
	 * <p>
	 * 执行方法值
	 * </p>
	 * 
	 * @param bean
	 *            Object
	 * @param methodName
	 *            String
	 * @return Object
	 */
	public static Object methodValue(Object bean, String methodName) {
		if (bean == null) {
			return null;
		}
		Method method = method(bean.getClass(), methodName);
		if (method == null) {
			return null;
		}
		try {
			return method.invoke(bean);
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>
	 * 执行方法值
	 * </p>
	 * 
	 * @param bean
	 * @param methodName
	 * @param params
	 * @return Object
	 */
	public static Object methodValue(Object bean, String methodName,
			Object... params) {
		if (bean == null || methodName == null
				|| (methodName = methodName.trim()).length() == 0) {
			return null;
		}
		Method method = null;
		if (params == null) {
			return methodValue(bean, methodName);
		}
		Class<?>[] classes = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			classes[i] = params[i] != null ? params[i].getClass()
					: String.class;
		}
		try {
			method = bean.getClass().getDeclaredMethod(methodName.trim(), classes);
		}
		catch (Exception ex) {
			Method[] methods = bean.getClass().getDeclaredMethods();
			Class<?>[] types = null;
			boolean isType = false;
			for (int i = 0; i < methods.length; i++) {
				if (!methods[i].getName().equals(methodName)) {
					continue;
				}
				types = methods[i].getParameterTypes();
				if (types.length != classes.length) {
					continue;
				}
				isType = false;
				for (int j = 0; j < types.length; j++) {
					if (types[j].isAssignableFrom(classes[j])) {
						isType = true;
						continue;
					}
					isType = false;
					break;
				}
				if (isType) {
					method = methods[i];
					break;
				}
			}

		}
		if (method == null) {
			return null;
		}
		try {
			return method.invoke(bean, params);
		}
		catch (Exception ex1) {
			return null;
		}
	}

	/**
	 * <p>
	 * 执行字段表单式
	 * </p>
	 * 
	 * @param bean
	 *            对象
	 * @param expression
	 *            字段表达式
	 * @return Object
	 */
	public static Object eval(Object bean, String expression) {
		if (bean == null || expression == null) {
			return null;
		}
		int dot = expression.indexOf('.');
		String fn = dot != -1 ? fn = expression.substring(0, dot) : expression;
		Field field = null;
		Class<?> clazz = bean.getClass();
		while (!clazz.equals(Object.class)) {
			try {
				field = clazz.getDeclaredField(fn.replaceAll("\\s", ""));
			}
			catch (Exception e) {
			}
			if (field != null) {
				break;
			}
			clazz = clazz.getSuperclass();
		}
		if (field == null) {
			return methodValue(bean, formatGetMethodName(fn.replaceAll("\\s", "")));
		}
		Object v = fieldValue(bean, field);
		if (v != null && dot > 1) {
			v = eval(v, expression.substring(dot + 1));
		}
		return v;
	}

	/**
	 * <p>
	 * 实例对象
	 * </p>
	 * 
	 * @param className
	 * @param singletonMethod
	 * @param param
	 * @return Object
	 */
	public static Object newInstance(String className, String singletonMethod,
			Object param) {
		if (className == null || (className = className.trim()).length() == 0) {
			return null;
		}
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		}
		catch (ClassNotFoundException ex) {
			return null;
		}
		if (singletonMethod != null
				&& (singletonMethod = singletonMethod.trim()).length() > 0) {
			if (singletonMethod.length() == 0) {
				singletonMethod = "getInstance";
			}
			return callStaticMethod(clazz, singletonMethod, param);
		}
		try {
			return clazz.newInstance();
		}
		catch (Exception ex) {
		}
		return null;
	}

	/**
	 * <p>
	 * 调用静态方法
	 * </p>
	 * 
	 * @param className
	 *            String
	 * @param methodName
	 *            String
	 * @return Object
	 */
	public static Object callStaticMethod(String className, String methodName) {
		if (className == null || (className = className.trim()).length() == 0
				|| methodName == null
				|| (methodName = methodName.trim()).length() == 0) {
			return null;
		}
		try {
			return callStaticMethod(Class.forName(className), methodName);
		}
		catch (ClassNotFoundException ex) {
			return null;
		}
	}

	/**
	 * <p>
	 * 调用静态方法
	 * </p>
	 * 
	 * @param className
	 *            String
	 * @param methodName
	 *            String
	 * @param param
	 *            Object
	 * @return Object
	 */
	public static Object callStaticMethod(String className, String methodName,
			Object param) {
		if (className == null || (className = className.trim()).length() == 0
				|| methodName == null
				|| (methodName = methodName.trim()).length() == 0) {
			return null;
		}
		try {
			return callStaticMethod(Class.forName(className), methodName, param);
		}
		catch (ClassNotFoundException ex) {
			return null;
		}
	}

	/**
	 * <p>
	 * 调用静态方法
	 * </p>
	 * 
	 * @param clazz
	 *            Class
	 * @param methodName
	 *            String
	 * @return Object
	 */
	public static Object callStaticMethod(Class<?> clazz, String methodName) {
		if (clazz == null || methodName == null
				|| (methodName = methodName.trim()).length() == 0) {
			return null;
		}
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName);
			int iModifier = Modifier.PUBLIC | Modifier.STATIC;
			if (method != null
					&& (method.getModifiers() & iModifier) == iModifier) {
				return method.invoke(null);
			}
		}
		catch (Exception ex) {
		}
		return null;
	}

	/**
	 * <p>
	 * 调用静态方法
	 * </p>
	 * 
	 * @param clazz
	 * @param methodName
	 * @param params
	 * @return Object
	 */
	public static Object callStaticMethod(Class<?> clazz, String methodName,
			Object... params) {
		if (clazz == null || methodName == null
				|| (methodName = methodName.trim()).length() == 0) {
			return null;
		}
		if (params == null) {
			return callStaticMethod(clazz, methodName);
		}
		Method method = null;
		Class<?>[] classes = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			classes[i] = params[i] != null ? params[i].getClass()
					: String.class;
		}
		try {
			method = clazz.getDeclaredMethod(methodName, classes);
		}
		catch (Exception ex) {
			Method[] methods = clazz.getDeclaredMethods();
			Class<?>[] types = null;
			boolean isType = false;
			for (int i = 0; i < methods.length; i++) {
				if (!methods[i].getName().equals(methodName)) {
					continue;
				}
				types = methods[i].getParameterTypes();
				if (types.length != classes.length) {
					continue;
				}
				isType = false;
				for (int j = 0; j < types.length; j++) {
					if (types[j].isAssignableFrom(classes[j])) {
						isType = true;
						continue;
					}
					isType = false;
					break;
				}
				if (isType) {
					method = methods[i];
					break;
				}
			}
		}
		int iModifier = Modifier.PUBLIC | Modifier.STATIC;
		if (method != null && (method.getModifiers() & iModifier) == iModifier) {
			try {
				return method.invoke(null, params);
			}
			catch (Exception ex1) {
				return null;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 设置对象指定字段值
	 * </p>
	 * 
	 * @param object
	 * @param fieldName
	 * @param oValue
	 * @return boolean
	 */
	public static boolean putFieldValue(Object object, String fieldName,
			Object oValue) {
		if (object == null) {
			return false;
		}
		Field field = field(object.getClass(), fieldName);
		return putFieldValue(object, field, oValue);
	}

	/**
	 * <p>
	 * 设置对象指定字段值
	 * </p>
	 * 
	 * @param object
	 *            Object
	 * @param field
	 *            Field
	 * @param oValue
	 *            Object
	 * @return boolean
	 */
	public static boolean putFieldValue(Object object, Field field,
			Object oValue) {
		if (object == null || field == null) {
			return false;
		}
		boolean flag = false;
		Class<?> clazz = object.getClass();
		try {
			if (Modifier.isPublic(field.getModifiers())) {
				field.set(object, oValue);
			}
			else {
				String methodName = "set"
						+ ((char) (field.getName().charAt(0) & 0xDF))
						+ field.getName().substring(1);
				Method method = clazz.getMethod(methodName, new Class[] { field.getType() });
				if (Modifier.isPublic(method.getModifiers())) {
					method.invoke(object, new Object[] { oValue });
				}
			}
		}
		catch (Exception e) {
			return false;
		}
		return flag;
	}

	/**
	 * <p>
	 * 设置属性值
	 * </p>
	 * 
	 * @param object
	 * @param fieldName
	 * @param value
	 * @return boolean
	 */
	public static boolean putPropertyValue(Object object, String fieldName,
			String value) {
		if (object == null || fieldName == null) {
			return false;
		}
		boolean flag = false;
		Class<?> clazz = object.getClass();
		try {
			Field field = field(clazz, fieldName);
			if (field == null) {
				String methodName = "set"
						+ ((char) (fieldName.charAt(0) & 0xDF))
						+ fieldName.substring(1);
				Method[] methods = clazz.getMethods();
				Method method;
				for (int i = 0; i < methods.length; i++) {
					method = methods[i];
					if (method.getName().equals(methodName)) {
						Class<?>[] clazzes = method.getParameterTypes();
						if (clazzes != null && clazzes.length == 1) {
							if (Modifier.isPublic(method.getModifiers())) {
								Object oValue = StringUtils.valueOf(value, clazzes[0]);
								method.invoke(object, new Object[] { oValue });
							}
						}
					}
				}
				return false;
			}
			Object oValue = StringUtils.valueOf(value, field.getType());
			if (oValue == null) {
				return false;
			}
			if (Modifier.isPublic(field.getModifiers())) {
				field.set(object, oValue);
			}
			else {
				String methodName = "set"
						+ ((char) (field.getName().charAt(0) & 0xDF))
						+ field.getName().substring(1);
				Method method = clazz.getMethod(methodName, new Class[] { field.getType() });
				if (Modifier.isPublic(method.getModifiers())) {
					method.invoke(object, new Object[] { oValue });
				}
			}
		}
		catch (Exception ex) {
		}
		return flag;
	}

	/**
	 * <p>
	 * 获取泛型属性的实际类型
	 * </p>
	 * 
	 * @param field
	 * @return Class<?>
	 */
	public static Class<?> fetchActualType(Field field) {
		if (field == null) {
			return null;
		}
		try {
			Type t = field.getGenericType();
			if (t != null && t instanceof ParameterizedType) {
				ParameterizedType ptype = (ParameterizedType) t;
				Type[] types = ptype.getActualTypeArguments();
				if (types != null && types.length > 0) {
					return (Class<?>) types[0];
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * 获取泛型属性的实际类型
	 * </p>
	 * 
	 * @param field
	 * @return Class<?>[]
	 */
	public static Class<?>[] fetchActualTypes(Field field) {
		if (field == null) {
			return null;
		}
		Class<?>[] classes = null;
		try {
			Type t = field.getGenericType();
			if (t != null && t instanceof ParameterizedType) {
				ParameterizedType ptype = (ParameterizedType) t;
				Type[] types = ptype.getActualTypeArguments();
				if (types != null && types.length > 0) {
					classes = new Class<?>[types.length];
					for (int i = 0; i < classes.length; i++) {
						classes[i] = (Class<?>) types[i];
					}
				}
			}
		}
		catch (Exception e) {
		}
		return classes;
	}

	/**
	 * <p>
	 * 获取泛型类的实际类型
	 * </p>
	 * 
	 * @param clazz
	 * @return Class<?>
	 */
	public static Class<?> fetchActualType(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}
		try {
			Type t = clazz.getGenericSuperclass();
			if (t != null && t instanceof ParameterizedType) {
				ParameterizedType ptype = (ParameterizedType) t;
				Type[] types = ptype.getActualTypeArguments();
				if (types != null && types.length > 0) {
					return (Class<?>) types[0];
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * 获取泛型类的实际类型
	 * </p>
	 * 
	 * @param clazz
	 * @return Class<?>[]
	 */
	public static Class<?>[] fetchActualTypes(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}
		Class<?>[] classes = null;
		try {
			Type t = clazz.getGenericSuperclass();
			if (t != null && t instanceof ParameterizedType) {
				ParameterizedType ptype = (ParameterizedType) t;
				Type[] types = ptype.getActualTypeArguments();
				if (types != null && types.length > 0) {
					classes = new Class<?>[types.length];
					for (int i = 0; i < classes.length; i++) {
						classes[i] = (Class<?>) types[i];
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return classes;
	}

	/**
	 * <p>
	 * 类型
	 * </p>
	 * <p>
	 * create: 2010-12-21 上午10:50:35
	 * </p>
	 * 
	 * @author 许德建[xudejian@126.com]
	 * @version 1.0
	 */
	public static enum ClassType {
		/**
		 * 缺省类型
		 */
		defalt,

		/**
		 * 字符串类型
		 */
		string,

		/**
		 * 扩展字符串类型
		 */
		tostring,

		/**
		 * 数值类型
		 */
		number,

		/**
		 * 时间类型
		 */
		date,

		/**
		 * 数组类型
		 */
		array,

		/**
		 * set集合类型
		 */
		set,

		/**
		 * list集合类型
		 */
		list,

		/**
		 * map集合映射类型
		 */
		map,

		/**
		 * 其他对象类型
		 */
		object

	}

}
