package com.coco.core.xml;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Element;

import com.coco.core.util.ReflectUtils;
import com.coco.core.util.ReflectUtils.ClassType;
import com.coco.core.util.StringUtils;
import com.coco.core.xml.api.AbsXmlToBean;

public class XmlNodeToBean<T> extends AbsXmlToBean<T> {

	private Class<T> rootClass;

	public XmlNodeToBean() {

	}

	public XmlNodeToBean(Class<T> rootClass) {
		this.rootClass = rootClass;
	}

	@Override
	public T parse(Element element) {
		if (element == null) {
			return null;
		}
		if (rootClass == null) {
			return null;
		}
		return (T) nodeToBean(element, rootClass);
	}

	/**
	 * <p>
	 * 根据XML节点解析成指定类的对象,对象的字段值表现在子节点
	 * </p>
	 * 
	 * @param <V>
	 * @param element
	 * @param clazz
	 * @return V
	 */
	@SuppressWarnings("unchecked")
	private <V> V nodeToBean(Element element, Class<V> clazz) {
		if (element == null || clazz == null) {
			return null;
		}
		V obj = null;
		try {
			obj = clazz.newInstance();
		}
		catch (Exception e) {
			return null;
		}
		Method[] methods = clazz.getMethods();
		List<Element> elements = element.elements();
		String fieldName, methodName;
		Field field = null;
		Method method = null;
		ClassType type = null;
		Class<?> fieldClass;
		for (Element eField : elements) {
			fieldName = ReflectUtils.formatFieldName(eField.getName());
			if (fieldName == null) {
				continue;
			}
			field = ReflectUtils.field(clazz, fieldName);
			method = null;
			fieldClass = null;
			if (field == null) {
				methodName = ReflectUtils.formatSetMethodName(fieldName);
				for (Method m : methods) {
					if (m.getName().equals(methodName)
							&& m.getParameterTypes() != null
							&& m.getParameterTypes().length == 1) {
						method = m;
						fieldClass = m.getParameterTypes()[0];
						break;
					}
				}
			}
			else {
				fieldClass = field.getType();
			}
			if (fieldClass == null) {
				continue;
			}

			type = ReflectUtils.classType(fieldClass);
			if (ClassType.number.equals(type)
					|| ClassType.tostring.equals(type)
					|| ClassType.date.equals(type)) {
				try {
					Object oValue = StringUtils.valueOf(eField.getTextTrim(), fieldClass);
					if (field != null) {
						ReflectUtils.putFieldValue(obj, field, oValue);
					}
					else {
						method.invoke(obj, oValue);
					}
				}
				catch (Exception e) {

				}
			}
			else if (ClassType.string.equals(type)) {
				if (field != null) {
					ReflectUtils.putFieldValue(obj, field, eField.getTextTrim());
				}
				else {
					try {
						method.invoke(obj, eField.getTextTrim());
					}
					catch (Exception e) {
					}
				}
			}
			else if (ClassType.map.equals(type)) {
			}
			else if (ClassType.array.equals(type)) {
				List<Element> items = eField.elements();
				Class<?> cls = fieldClass.getComponentType();
				try {
					Object[] objs = (Object[]) Array.newInstance(cls, items.size());
					ClassType collType = ReflectUtils.classType(cls);
					if (ClassType.number.equals(collType)
							|| ClassType.string.equals(collType)
							|| ClassType.date.equals(collType)
							|| ClassType.tostring.equals(collType)) {
						for (int i = 0; i < items.size(); i++) {
							objs[i] = StringUtils.valueOf(items.get(i).getTextTrim(), cls);
						}
					}
					else {
						for (int i = 0; i < items.size(); i++) {
							objs[i] = nodeToBean(items.get(i), cls);
						}
					}
					if (field != null) {
						ReflectUtils.putFieldValue(obj, field, objs);
					}
					else {
						try {
							method.invoke(obj, objs);
						}
						catch (Exception e) {
						}
					}
				}
				catch (Exception e) {
				}
			}
			else if (ClassType.set.equals(type)) {
				Class<?> cls = ReflectUtils.fetchActualType(field);
				if (cls == null || "java.lang.Object".equals(cls.getName())) {
					continue;
				}
				List<Element> items = eField.elements();
				Set<Object> objs = new HashSet<Object>();
				Object o;
				ClassType collType = ReflectUtils.classType(cls);
				if (ClassType.number.equals(collType)
						|| ClassType.string.equals(collType)
						|| ClassType.date.equals(collType)
						|| ClassType.tostring.equals(collType)) {
					for (Element item : items) {
						o = StringUtils.valueOf(item.getTextTrim(), cls);
						if (o != null) {
							objs.add(o);
						}
					}
				}
				else {
					for (Element item : items) {
						o = nodeToBean(item, cls);
						if (o != null) {
							objs.add(o);
						}
					}
				}
				if (field != null) {
					ReflectUtils.putFieldValue(obj, field, objs);
				}
				else {
					try {
						method.invoke(obj, objs);
					}
					catch (Exception e) {
					}
				}
			}
			else if (ClassType.list.equals(type)) {
				Class<?> cls = ReflectUtils.fetchActualType(field);
				if (cls == null || "java.lang.Object".equals(cls.getName())) {
					continue;
				}
				List<Element> items = eField.elements();
				List<Object> objs = new ArrayList<Object>();
				Object o;
				ClassType collType = ReflectUtils.classType(cls);
				if (ClassType.number.equals(collType)
						|| ClassType.string.equals(collType)
						|| ClassType.date.equals(collType)
						|| ClassType.tostring.equals(collType)) {
					for (Element item : items) {
						o = StringUtils.valueOf(item.getTextTrim(), cls);
						if (o != null) {
							objs.add(o);
						}
					}
				}
				else {
					for (Element item : items) {
						o = nodeToBean(item, cls);
						if (o != null) {
							objs.add(o);
						}
					}
				}
				if (field != null) {
					ReflectUtils.putFieldValue(obj, field, objs);
				}
				else {
					try {
						method.invoke(obj, objs);
					}
					catch (Exception e) {
					}
				}
			}
			else if (ClassType.object.equals(type)) {
				Object oValue = null;
				try {
					oValue = nodeToBean(eField, fieldClass);
					if (field != null) {
						ReflectUtils.putFieldValue(obj, field, oValue);
					}
					else {
						try {
							method.invoke(obj, oValue);
						}
						catch (Exception e) {
						}
					}
				}
				catch (Exception e) {

				}
			}
		}
		return obj;
	}

	public Class<T> getRootClass() {
		return rootClass;
	}

	public void setRootClass(Class<T> rootClass) {
		this.rootClass = rootClass;
	}
}
