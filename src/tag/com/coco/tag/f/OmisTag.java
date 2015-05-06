package com.coco.tag.f;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

/**
 * <p>
 * 根据指定的输出长度省略文本内容
 * </p>
 * <p>
 * create: 2011-5-19 上午09:35:10
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class OmisTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 值
	 */
	protected String text;

	/**
	 * 
	 */
	protected Integer len;

	@Override
	public int doStartTag() throws JspException {
		Object bean = TagUtil.getBean(pageContext, text);
		String v = null;
		if (bean == null || (v = bean.toString().trim()).isEmpty()) {
			return SKIP_BODY;
		}
		StringBuilder builder = new StringBuilder();
		builder.append("<span title=\"");
		builder.append(v).append("\">");
		if (v.length() <= len) {
			builder.append(v);
		}
		else {
			builder.append(v.substring(0, len)).append("<span style=\"color: red; font-size: 14px; font-weight: bold;\">...</span>");
		}
		builder.append("</span>");
		try {
			pageContext.getOut().write(builder.toString());
		}
		catch (Exception ex) {
		}
		return SKIP_BODY;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getLen() {
		return len;
	}

	public void setLen(Integer len) {
		this.len = len;
	}
}
