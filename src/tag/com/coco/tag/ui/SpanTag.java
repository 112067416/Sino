package com.coco.tag.ui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

public class SpanTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String id;

	protected String name;

	protected String value;

	protected String cssClass;

	protected String cssStyle;

	@Override
	public int doStartTag() throws JspException {
		Object obj = null;
		if (value != null) {
			obj = TagUtil.getBean(pageContext, value);
		} else {
			obj = TagUtil.getBean(pageContext, name);
		}
		String $value = obj != null ? obj.toString() : null;
		StringBuilder content = new StringBuilder();
		content.append("<SPAN xu.f=\"\"");
		if (id != null) {
			content.append(" id=\"");
			content.append(id.replaceAll("\\.", "_"));
			content.append("\"");
		}
		content.append(" name=\"");
		if (name != null) {
			content.append(name);
		}
		content.append("\"");
		if (cssClass != null) {
			content.append(" class=\"");
			content.append(cssClass);
			content.append("\"");
		}
		if (cssStyle != null) {
			content.append(" style=\"");
			content.append(cssStyle);
			content.append("\"");
		}
		content.append(" >");
		content.append($value);
		content.append("</SPAN>");
		try {
			pageContext.getOut().write(content.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

}
