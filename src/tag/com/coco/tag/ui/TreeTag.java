package com.coco.tag.ui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TreeTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String id;

	protected String tree;

	protected String uri;

	protected String cssClass;

	protected String cssStyle;

	protected String iconPath;

	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("//-->\r\n</script>");
		}
		catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		String $id = id != null ? id.replaceAll("[^_A-Za-z]", "") : "";
		String $tree = tree != null ? tree.replaceAll("[^_A-Za-z]", "") : "";
		String $uri = uri;
		if ($uri == null) $uri = "";
		else $uri = $uri.trim();
		if ($id.isEmpty() || $tree.isEmpty() || $uri.isEmpty()) {
			return SKIP_BODY;
		}
		StringBuilder content = new StringBuilder();
		content.append("<div id=\"");
		content.append($id);
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
		content.append("></div>");
		content.append("<script type=\"text/javascript\" language=\"javascript\" >\r\n<!--\r\n");
		content.append("var ");
		content.append($tree);
		content.append(" = new Cocotree(\"");
		content.append($tree);
		content.append("\");\r\n");
		content.append($tree);
		content.append(".iconPath=\"");
		content.append(pageContext.getServletContext().getContextPath());
		if (iconPath == null || (iconPath = iconPath.trim()).isEmpty()) {
			content.append("/images/tree/\";\r\n");
		}
		else {
			content.append(iconPath).append("/\";\r\n");
		}
		content.append($tree).append(".onload(\"").append($id).append("\",\"");
		if ($uri.startsWith("/")) {
			content.append(pageContext.getServletContext().getContextPath());
		}
		content.append($uri);
		content.append("\");\r\n");
		try {
			pageContext.getOut().write(content.toString());
		}
		catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
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

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
}
