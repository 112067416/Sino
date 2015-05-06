package com.coco.tag.ui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

/**
 * <p>
 * 布尔值选框
 * </p>
 * <p>
 * create: 2010-12-27 上午10:47:47
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class BoolboxTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String id;

	protected String name;

	protected String value;

	protected boolean checked;

	protected String type = "b";

	@Override
	public int doStartTag() throws JspException {
		if (name == null || name.length() == 0) {
			return SKIP_BODY;
		}
		Object obj = null;
		if (value != null) {
			obj = TagUtil.getBean(pageContext, value);
		}
		else {
			obj = TagUtil.getBean(pageContext, name);
		}
		if (obj != null && obj instanceof String) {
			obj = "true".equals(obj) || "y".equalsIgnoreCase((String) obj);
		}
		StringBuilder content = new StringBuilder();
		content.append("<input type=\"checkbox\" ");
		content.append(type);
		if (id != null) {
			content.append(" id=\"").append(id).append("\"");
		}
		if (name != null) {
			content.append(" name=\"").append(name).append("\"");
		}
		if (checked || obj != null && (obj instanceof Boolean && (Boolean) obj)) {
			content.append(" checked=\"checked\"");
		}
		content.append(" />");
		try {
			pageContext.getOut().write(content.toString());
		}
		catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
