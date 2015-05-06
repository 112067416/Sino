package com.coco.tag.assist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coco.core.util.ReflectUtils;
import com.coco.core.util.StringUtils;

/**
 * <p>
 * 下拉选框生成工具
 * </p>
 * <p>
 * create: 2010-12-27 上午10:39:33
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class OptionUtil {

	/**
	 * 生成Select下拉的Option对象列表串
	 * 
	 * @param param
	 *            下拉对象配置
	 * @return String 下拉option字符串列表
	 */
	public static String toOptions(OptionParam param) {
		if (param.objs == null || param.id == null) {
			return "";
		}
		StringBuffer buf = new StringBuffer();
		Iterator<?> it = param.objs.iterator();
		Object obj;
		String v, t;
		if (param.label == null || param.label.isEmpty()) {
			param.label = param.id;
		}
		String[] names = param.label.split(",");
		for (int i = 0; i < names.length; i++) {
			names[i] = names[i].trim();
		}
		while (it.hasNext()) {
			obj = it.next();
			v = StringUtils.valueOf(ReflectUtils.eval(obj, param.id));
			buf.append("<option value=\"");
			buf.append(v);
			buf.append("\"");
			if (param.value != null && param.value.equals(v)) {
				buf.append(" checked ");
			}
			buf.append(">");
			for (int i = 0; i < names.length; i++) {
				if (names[i].length() == 0) continue;
				t = StringUtils.valueOf(ReflectUtils.eval(obj, names[i]));
				if (t != null) {
					buf.append(t).append(" ");
				}
			}
			buf.append("</option>");
		}
		return buf.toString();
	}

	/**
	 * 生成Select下拉的Option对象列表串
	 * 
	 * @param param
	 *            下拉对象配置
	 * @return String 下拉option字符串列表
	 */
	public static String toTreeOptions(OptionParam param) {
		if (param.objs == null || param.id == null || param.parent == null) {
			return "";
		}
		Iterator<?> it = param.objs.iterator();
		Object obj;
		String v, pv, t;
		if (param.label == null || param.label.isEmpty()) {
			param.label = param.id;
		}
		String[] names = param.label.split(",");
		for (int i = 0; i < names.length; i++) {
			names[i] = names[i].trim();
		}
		Map<String, TreeOption> treeMap = new HashMap<String, TreeOption>();
		Map<String, List<TreeOption>> childrenMap = new HashMap<String, List<TreeOption>>();
		List<TreeOption> roots = new ArrayList<TreeOption>();
		List<TreeOption> children;
		TreeOption o = null;
		while (it.hasNext()) {
			obj = it.next();
			v = StringUtils.valueOf(ReflectUtils.eval(obj, param.id));
			if (v == null) {
				continue;
			}
			pv = StringUtils.valueOf(ReflectUtils.eval(obj, param.parent));
			t = null;
			for (int i = 0; i < names.length; i++) {
				if (names[i].length() == 0) {
					continue;
				}
				String tmp = StringUtils.valueOf(ReflectUtils.eval(obj, names[i]));
				if (tmp != null) {
					if (t == null) {
						t = tmp;
					}
					else {
						t += " " + tmp;
					}
				}
			}
			if (t == null) {
				t = v;
			}
			o = new TreeOption();
			o.id = v;
			o.pid = pv;
			o.value = v;
			o.text = t;
			o.orders = StringUtils.valueOf(ReflectUtils.eval(obj, param.order));
			treeMap.put(o.id, o);
			String pid = o.pid;
			if (o.pid != null && o.pid.length() > 0) {
				// 添加子选项
				children = childrenMap.get(pid);
				if (children == null) {
					children = new ArrayList<TreeOption>();
					childrenMap.put(pid, children);
				}
				children.add(o);
			}
			// 添加根选项
			if (o.pid == null && param.root == null || o.pid != null
					&& o.pid.equals(param.root)) {
				roots.add(o);
			}
		}
		StringBuffer buf = new StringBuffer();
		recurse(roots, childrenMap, buf, param.value, 0);
		return buf.toString();
	}

	private static void recurse(List<TreeOption> options,
			Map<String, List<TreeOption>> childrenMap, StringBuffer buf,
			String value, int deep) {
		if (deep > 20 || options == null || options.size() == 0) {
			return;
		}
		Collections.sort(options, new TreeOptionComparator());
		for (TreeOption option : options) {
			buf.append("<option value=\"").append(option.value).append("\"");
			if (value != null && value.length() > 0
					&& value.equals(option.value)) {
				buf.append(" selected ");
			}
			buf.append(">");
			for (int i = 0; i < deep; i++) {
				buf.append("　");
			}
			if (deep > 0) {
				buf.append("┕ ");
			}
			buf.append(option.text).append("</option>");
			List<TreeOption> children = childrenMap.remove(option.id);
			if (children == null || children.size() == 0) {
				continue;
			}
			option.children = children;
			recurse(children, childrenMap, buf, value, deep + 1);
		}
	}

	/**
	 * 生成Select下拉的Option对象列表串
	 * 
	 * @param param
	 *            下拉对象配置
	 * @return String 下拉option字符串列表
	 */
	public static String toTreeOptionAndGroups(OptionParam param) {
		if (param == null || param.objs == null || param.id == null
				|| param.parent == null) {
			return "";
		}
		Iterator<?> it = param.objs.iterator();
		Object obj;
		String v, pv, t;
		if (param.label == null || param.label.isEmpty()) {
			param.label = param.id;
		}
		String[] names = param.label.split(",");
		for (int i = 0; i < names.length; i++) {
			names[i] = names[i].trim();
		}
		Map<String, TreeOption> treeMap = new HashMap<String, TreeOption>();
		Map<String, List<TreeOption>> childrenMap = new HashMap<String, List<TreeOption>>();
		List<TreeOption> roots = new ArrayList<TreeOption>();
		List<TreeOption> children;
		TreeOption o = null;
		while (it.hasNext()) {
			obj = it.next();
			v = StringUtils.valueOf(ReflectUtils.eval(obj, param.id));
			if (v == null) {
				continue;
			}
			pv = StringUtils.valueOf(ReflectUtils.eval(obj, param.parent));
			t = null;
			for (int i = 0; i < names.length; i++) {
				if (names[i].length() == 0) {
					continue;
				}
				String tmp = StringUtils.valueOf(ReflectUtils.eval(obj, names[i]));
				if (tmp != null) {
					if (t == null) {
						t = tmp;
					}
					else {
						t += " " + tmp;
					}
				}
			}
			if (t == null) {
				t = v;
			}
			o = new TreeOption();
			o.id = v;
			o.pid = pv;
			o.value = v;
			o.text = t;
			o.leaf = StringUtils.valueOf(ReflectUtils.eval(obj, param.leafName));
			o.orders = StringUtils.valueOf(ReflectUtils.eval(obj, param.order));
			treeMap.put(o.id, o);
			String pid = o.pid;
			if (o.pid != null && o.pid.length() > 0) {
				// 添加子选项
				children = childrenMap.get(pid);
				if (children == null) {
					children = new ArrayList<TreeOption>();
					childrenMap.put(pid, children);
				}
				children.add(o);
			}
			// 添加根选项
			if (o.pid == null && param.root == null || o.pid != null
					&& o.pid.equals(param.root)) {
				roots.add(o);
			}
		}
		StringBuffer buf = new StringBuffer();
		recurseGroup(roots, childrenMap, buf, param.value, 0);
		return buf.toString();
	}

	private static void recurseGroup(List<TreeOption> options,
			Map<String, List<TreeOption>> childrenMap, StringBuffer buf,
			String value, int deep) {
		if (deep > 20 || options == null || options.size() == 0) {
			return;
		}
		Collections.sort(options, new TreeOptionComparator());
		for (TreeOption option : options) {
			boolean isLeaf = option.leaf != null
					&& "Y".equalsIgnoreCase(option.leaf);
			if (!isLeaf) {
				buf.append("<OPTGROUP LABEL=\"");
				for (int i = 0; i < deep; i++) {
					buf.append("　");
				}
				if (deep > 0) {
					buf.append("┕ ");
				}
				buf.append(option.text);
				buf.append("\">");
			}
			else {
				buf.append("<option value=\"").append(option.value).append("\"");
				if (value != null && value.length() > 0
						&& value.equals(option.value)) {
					buf.append(" selected ");
				}
				buf.append(">");
				for (int i = 0; i < deep; i++) {
					buf.append("　");
				}
				if (deep > 0) {
					buf.append("┕ ");
				}
				buf.append(option.text).append("</option>");
			}
			List<TreeOption> children = childrenMap.remove(option.id);
			if (children == null || children.size() == 0) {
				continue;
			}
			option.children = children;
			recurseGroup(children, childrenMap, buf, value, deep + 1);
			if (!isLeaf) {
				buf.append("</OPTGROUP>");
			}
		}
	}

}
