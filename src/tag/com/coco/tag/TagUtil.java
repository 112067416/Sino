package com.coco.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.coco.core.bean.Property;
import com.coco.core.env.Context;
import com.coco.core.util.ReflectUtils;

public class TagUtil {

	private static final Pattern LIST_TRIM = Pattern.compile("^\\s*'|'\\s*$");

	private static final Pattern VAR_REPLACE = Pattern.compile("^\\s*\\{|\\}\\s*$");

	/**
	 * <p>
	 * 获取标签指定的对象
	 * </p>
	 * <ul>
	 * <li>#开头:表示值为字符串</li>
	 * <li>$开头:表示值为登录用户信息</li>
	 * <li>@开头:表示值为类与方法执行的结果信息</li>
	 * <li>否则获取上下文信息</li>
	 * </ul>
	 * 
	 * @param pageContext
	 * @param key
	 * @return Object
	 */
	public static Object getBean(PageContext pageContext, String key) {
		return getBean(pageContext, key, null);
	}

	/**
	 * <p>
	 * 获取标签指定的对象
	 * </p>
	 * <ul>
	 * <li>#开头:表示值为字符串</li>
	 * <li>$开头:表示值为登录用户信息</li>
	 * <li>@开头:表示值为类与方法执行的结果信息</li>
	 * <li>否则获取上下文信息</li>
	 * </ul>
	 * 
	 * @param pageContext
	 * @param key
	 * @param scope
	 * @return Object
	 */
	public static Object getBean(PageContext pageContext, String key,
			String scope) {
		if (key == null) {
			return null;
		}
		key = key.trim();
		if (key.isEmpty()) {
			return null;
		}
		Object root = null;
		char ch = key.charAt(0);
		if (ch == '#') {
			return key.substring(1);
		}
		else if (ch == '$') {
			// 取登录用户信息
			root = Context.getUser((HttpServletRequest) pageContext.getRequest());
			if (root == null) {
				return null;
			}
			key = key.substring(1);
		}
		else if (ch == '@') {
			// 取类与方法执行的结果信息
			String[] keys = key.substring(1).split("@");
			if (keys.length < 2) return null;
			String method = keys[1];
			int dotIndex = method.indexOf('.');
			if (dotIndex > 0) {
				method = method.substring(0, dotIndex);
			}
			root = ReflectUtils.callStaticMethod(keys[0], method);
			if (root == null) {
				return null;
			}
			key = key.replace("@" + keys[0] + "@" + keys[1], "");
		}
		else if (ch == '{') {
			String var = VAR_REPLACE.matcher(key).replaceAll("").trim();
			if ("date".equals(var)) {
				return new java.util.Date();
			}
			return null;
		}
		// key = key.replaceAll("[^a-zA-Z_0-9\\.]|^[^a-zA-Z_]+", "");
		int firstDot = key.indexOf('.');
		String name = firstDot != -1 ? key.substring(0, firstDot) : key;
		String expression = null;
		// 若没有获取指定的根对象，有指定名字到页面请求中取
		if (root == null && !name.isEmpty()) {
			if (scope == null) {
				root = pageContext.getRequest().getAttribute(name);
				if (root == null) {
					root = pageContext.getAttribute(name);
					if (root == null) {
						root = pageContext.getSession().getAttribute(name);
						if (root == null) {
							root = pageContext.getServletContext().getAttribute(name);
						}
					}
				}
			}
			else if ("request".equalsIgnoreCase(scope)) {
				root = pageContext.getRequest().getAttribute(name);
			}
			else if ("session".equalsIgnoreCase(scope)) {
				root = pageContext.getSession().getAttribute(name);
			}
			else if ("page".equalsIgnoreCase(scope)) {
				root = pageContext.getAttribute(name);
			}
			if (firstDot != -1) {
				expression = key.substring(firstDot + 1);
			}
		}
		else {
			expression = key;
		}
		if (root == null) {
			return null;
		}
		if (expression != null && !expression.isEmpty()) {
			return ReflectUtils.eval(root, expression);
		}
		return root;
	}

	/**
	 * <p>
	 * 解析字符串kv列表,字符串形式如 <span
	 * style="border:1px solid #000000;padding:2px 5px;">'1':'男','2':'女'</span>
	 * </p>
	 * 
	 * @param str
	 * @return List<Property>
	 */
	public static List<Property> parseKvs(String str) {
		if (str == null || str.isEmpty()) {
			return new ArrayList<Property>();
		}
		str = LIST_TRIM.matcher(str).replaceAll("");
		List<Property> list = new ArrayList<Property>();
		String[] arr = str.split("'\\s*,\\s*'");
		for (String item : arr) {
			if (item.isEmpty() || (item = item.trim()).isEmpty()) {
				continue;
			}
			String[] kv = item.split("'\\s*:\\s*'");
			list.add(new Property(kv[0], kv.length > 0 ? kv[1] : kv[0]));
		}
		return list;
	}

}
