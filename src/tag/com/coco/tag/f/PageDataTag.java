package com.coco.tag.f;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.persistence.query.QEntity;
import com.coco.tag.TagUtil;

public class PageDataTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * 分页对象名称
	 * </p>
	 */
	protected String name;

	/**
	 * <p>
	 * 页面显示模式，true-简短模式；false-全部显示（默认）
	 * </p>
	 */
	protected boolean shortMode;

	/**
	 * 分页信息表格显示的宽度
	 */
	protected String width = "100%";

	/**
	 * 查询回调函数（Js）
	 */
	protected String callback;

	@Override
	public int doStartTag() throws JspException {
		name = name == null || (name = name.trim()).length() == 0 ? "page"
				: name;
		Object bean = TagUtil.getBean(pageContext, name);
		QEntity<?> qpage = (QEntity<?>) bean;

		int index = 0, size = 0, pageCount = 0;
		long count = 0;
		if (qpage != null) {
			index = qpage.getIndex();
			size = qpage.getSize();
			pageCount = qpage.getPageCount();
			count = qpage.getCount();
		}
		else {
			return SKIP_BODY;
		}
		StringBuffer html = new StringBuffer();
		html.append("<table width="
				+ width
				+ " cellpadding=0 cellspacing=0 border=0 align=center class=\"pagination-result-area\" ><tr>");
		html.append("<td height=28 align=\"left\" style=\"padding-left:20px;\">");
		html.append("<span id=\"PageIndex_").append(name).append("\" class=\"pagination-result\" >");
		if (pageCount == 0) {
			html.append("0</span>");
		}
		else {
			html.append(index + 1).append("</span>");
		}
		boolean isFirst = index == 0;
		boolean isLast = index >= pageCount - 1;
		html.append("&nbsp;/&nbsp;<span id=\"PageCount_").append(name).append("\" class=\"pagination-result\" >");
		html.append(pageCount).append("</span>页&nbsp;&nbsp;共<span id=\"RecordCount_").append(name).append("\" class=\"pagination-result\" >");
		html.append(count).append("</span>条&nbsp;&nbsp;");
		if (!shortMode) {
			html.append("每页<span id=\"PageSize_").append(name).append("\" class=\"pagination-result\" ");
			html.append(">").append(size).append("</span>条&nbsp;&nbsp;");
		}
		html.append("</td><td align=right style=\"padding-right:20px;\" >");
		if (isFirst) {
			html.append("【<span id=\"PageFirst_").append(name).append("\" class=\"pagination-opt-disabled\" >首&nbsp;页</span>】");
			html.append("【<span id=\"PagePre_").append(name).append("\" class=\"pagination-opt-disabled\" >上&nbsp;页</span>】");
		}
		else {
			html.append("【<span id=\"PageFirst_").append(name).append("\" onclick=\"cocopage.jump('").append(name).append("', 'first'");
			if (callback != null && !callback.isEmpty()) {
				html.append(",").append(callback);
			}
			html.append(")\" class=\"pagination-opt-enabled\" >首&nbsp;页</span>】");
			html.append("【<span id=\"PagePre_").append(name).append("\" onclick=\"cocopage.jump('").append(name).append("', 'pre'");
			if (callback != null && !callback.isEmpty()) {
				html.append(",").append(callback);
			}
			html.append(")\" class=\"pagination-opt-enabled\" >上&nbsp;页</span>】");
		}
		if (isLast) {
			html.append("【<span id=\"PageNext_").append(name).append("\" class=\"pagination-opt-disabled\" >下&nbsp;页</span>】");
			html.append("【<span id=\"PageLast_").append(name).append("\" class=\"pagination-opt-disabled\" >尾&nbsp;页</span>】");
		}
		else {
			html.append("【<span id=\"PageNext_").append(name).append("\" onclick=\"cocopage.jump('").append(name).append("', 'next'");
			if (callback != null && !callback.isEmpty()) {
				html.append(",").append(callback);
			}
			html.append(")\" class=\"pagination-opt-enabled\" >下&nbsp;页</span>】");
			html.append("【<span id=\"PageLast_").append(name).append("\" onclick=\"cocopage.jump('").append(name).append("', 'last'");
			if (callback != null && !callback.isEmpty()) {
				html.append(",").append(callback);
			}
			html.append(")\" class=\"pagination-opt-enabled\" >尾&nbsp;页</span>】");
		}
		if (!shortMode) {
			html.append("至<input type=\"text\" name=\"").append(name).append("_jumpIndex\" maxlength=8 onkeyup=\"this.value=this.value.replace(/\\D/gi,'')\" class=\"pagination-box\" />页<input type=\"button\" class=\"pagination-opt-btn\" title=\"跳转至该页\" onclick=\"cocopage.jump('").append(name).append("'");
			if (callback != null && !callback.isEmpty()) {
				html.append(",null,").append(callback);
			}
			html.append(");\" />");
		}
		html.append("</td></tr></table>");
		write(html.toString());
		return SKIP_BODY;
	}

	private void write(String content) {
		try {
			pageContext.getOut().write(content);
		}
		catch (IOException e) {
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the shortMode
	 */
	public boolean isShortMode() {
		return shortMode;
	}

	/**
	 * @param shortMode
	 *            the shortMode to set
	 */
	public void setShortMode(boolean shortMode) {
		this.shortMode = shortMode;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the callback
	 */
	public String getCallback() {
		return callback;
	}

	/**
	 * @param callback
	 *            the callback to set
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}

}
