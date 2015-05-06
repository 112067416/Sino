package com.coco.tag.f;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

/**
 * <p>
 * </p>
 */
public class BoolTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 值
	 */
	protected String bool;

	protected String trueText;

	protected String falseText;

	protected String tableField;

	@Override
	public int doStartTag() throws JspException {
		Object bean = TagUtil.getBean(pageContext, bool);
		if (bean == null) {
			return SKIP_BODY;
		}
		boolean flag = false;
		if (bean instanceof String) {
			flag = "Y".equalsIgnoreCase((String) bean) || "true".equals(bean);
		}
		else if (bean instanceof Number) {
			flag = ((Number) bean).intValue() != 0;
		}
		else if (bean instanceof Boolean) {
			flag = (Boolean) bean;
		}
		else {
			return SKIP_BODY;
		}
		boolean isField = tableField != null
				&& !(tableField = tableField.trim()).isEmpty();
		StringBuilder html = new StringBuilder();
		Object text = flag ? TagUtil.getBean(pageContext, trueText)
				: TagUtil.getBean(pageContext, falseText);

		if (isField) {
			html.append("<span xu.f=\"\" name=\"");
			html.append(tableField);
			html.append("\" xu.value=\"");
			html.append(bean);
			html.append("\">");
		}
		if (text != null) {
			html.append(text);
		}
		if (isField) {
			html.append("</span>");
		}
		try {
			pageContext.getOut().write(html.toString());
		}
		catch (Exception ex) {
		}
		return SKIP_BODY;
	}

	public String getBool() {
		return bool;
	}

	public void setBool(String bool) {
		this.bool = bool;
	}

	public String getTrueText() {
		return trueText;
	}

	public void setTrueText(String trueText) {
		this.trueText = trueText;
	}

	public String getFalseText() {
		return falseText;
	}

	public void setFalseText(String falseText) {
		this.falseText = falseText;
	}

	public String getTableField() {
		return tableField;
	}

	public void setTableField(String tableField) {
		this.tableField = tableField;
	}

}
