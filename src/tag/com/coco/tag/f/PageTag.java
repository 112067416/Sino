package com.coco.tag.f;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.persistence.query.QEntity;
import com.coco.tag.TagUtil;

public class PageTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * 分页对象名称
	 * </p>
	 */
	protected String name;

	/**
	 * <p>
	 * 分页对象查询接口
	 * </p>
	 */
	protected String action;

	/**
	 * <p>
	 * 每页显示个数
	 * </p>
	 */
	protected int size;

	/**
	 * <p>
	 * 页面数据页
	 * </p>
	 */
	protected String file;

	private boolean valid;

	@Override
	public int doStartTag() throws JspException {
		name = name == null || (name = name.trim()).length() == 0
				? "page"
				: name;
		Object bean = TagUtil.getBean(pageContext, name);
		QEntity<?> q = null;
		if (bean != null && bean instanceof QEntity) {
			q = (QEntity<?>) bean;
		}
		int index = 0, size = 0, pageCount = 0;
		long count = 0;
		if (q != null) {
			index = q.getIndex();
			size = q.getSize();
			pageCount = q.getPageCount();
			count = q.getCount();
		} else {
			return SKIP_BODY;
		}
		valid = true;
		StringBuffer html = new StringBuffer();
		if (action == null) {
			action = "";
		}
		html.append("<form name=\"PageForm_").append(name)
				.append("\" method=\"post\" action=\"").append(action)
				.append("\" onsubmit=\"return false;\">\r\n");
		html.append("<input type=\"hidden\" name=\"index\" value=\"")
				.append(index).append("\" />\r\n");
		html.append("<input type=\"hidden\" name=\"size\" value=\"")
				.append(size > 0 ? size : 15).append("\" />\r\n");
		html.append("<input type=\"hidden\" name=\"count\" value=\"")
				.append(count).append("\" />\r\n");
		html.append("<input type=\"hidden\" name=\"pageCount\" value=\"")
				.append(pageCount).append("\" />\r\n");
		html.append("<input type=\"hidden\" name=\"query\" value=\"true\" />\r\n");
		write(html.toString());
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		if (!valid) {
			return EVAL_PAGE;
		}
		write("<div id=\"PageData_" + name + "\">");
		if (file != null && file.length() > 0) {
			try {
				pageContext.include(file, true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		write("</div></form>");
		return EVAL_PAGE;
	}

	private void write(String content) {
		try {
			pageContext.getOut().write(content);
		} catch (IOException e) {
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
