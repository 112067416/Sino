package com.coco.tag.ui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

public class TextareaTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String id;

	protected String name;

	protected String value;

	protected String cssStyle;

	protected String cssClass;

	protected String readonly;

	protected int cols;

	protected int rows;

	protected String title;

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
		StringBuilder html = new StringBuilder();
		html.append("<TEXTAREA ");
		if (id != null) {
			html.append(" id=\"").append(id).append("\"");
		}
		if (name != null) {
			html.append(" name=\"").append(name).append("\"");
		}
		if (cols > 0) {
			html.append(" cols=\"").append(cols).append("\"");
		}
		if (rows > 0) {
			html.append(" rows=\"").append(rows).append("\"");
		}
		if (title != null) {
			html.append(" title=\"").append(title.replaceAll("\"", "\\\\\"")).append("\"");
		}
		html.append(" class=\"normal");
		if (cssClass != null) {
			html.append(" ").append(cssClass);
		}
		html.append("\"");
		if (cssStyle != null) {
			html.append(" style=\"").append(cssStyle).append("\"");
		}
		if (readonly != null) {
			html.append(" readonly=\"").append(readonly).append("\"");
		}
		html.append(" >");
		if (obj != null) {
			html.append(obj);
		}
		html.append("</TEXTAREA>");
		try {
			pageContext.getOut().write(html.toString());
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

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
}
