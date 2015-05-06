package com.coco.tag.f;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

/**
 * <p>
 * </p>
 */
public class KeyValueTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 参考值
	 */
	protected String list;

	/**
	 * 值
	 */
	protected String value;

	/**
	 * 没有找到对应值显示的文本
	 */
	protected String text;

	protected String scope;

	@Override
	public int doStartTag() throws JspException {
		if (list == null || list.length() == 0) {
			return SKIP_BODY;
		}
		Object bean = TagUtil.getBean(pageContext, value, scope);
		String v = bean != null ? bean.toString() : null;
		String val = null;
		if (v != null && !v.isEmpty()) {
			Pattern pattern = Pattern.compile("'" + v
					+ "'\\s*:\\s*'.*?'\\s*(,|$)");
			Matcher matcher = pattern.matcher(list);
			if (matcher.find()) {
				String text = matcher.group();
				if (text != null && text.length() > 0) {
					Pattern pref = Pattern.compile("^'" + v + "'\\s*:\\s*'");
					Pattern last = Pattern.compile("'\\s*(,|$)");
					text = pref.matcher(text).replaceAll("");
					val = last.matcher(text).replaceAll("");
				}
			}
		}
		try {
			if (val != null) {
				pageContext.getOut().write(val);
			} else if (text != null) {
				pageContext.getOut().write(text);
			}
		} catch (Exception ex) {
		}
		return SKIP_BODY;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
