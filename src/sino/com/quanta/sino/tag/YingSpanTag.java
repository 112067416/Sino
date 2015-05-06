package com.quanta.sino.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <p>
 * 显示硬度是否合格
 * </p>
 * <p>
 * create: 2011-3-26 下午08:59:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YingSpanTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 实测硬度值
	 */
	protected Integer ying;

	/**
	 * 硬度下限值
	 */
	protected Double ymin;

	/**
	 * 硬度上限值
	 */
	protected Double ymax;

	/**
	 * 样式
	 */
	protected String cssClass;

	/**
	 * 不合格时：用该label表示
	 */
	protected String label1;

	/**
	 * 合格时：用该label表示
	 */
	protected String label2;

	/**
	 * 是否显示label
	 */
	protected boolean displayed;

	@Override
	public int doStartTag() throws JspException {
		ying = ying == null ? 0 : ying;
		ymin = ymin == null ? 0 : ymin;
		ymax = ymax == null ? 0 : ymax;

		label1 = label1 != null ? label1 : "不合格";
		label2 = label2 != null ? label2 : "合格";
		StringBuilder builder = new StringBuilder();
		builder.append("<span ");
		if (cssClass != null && !cssClass.isEmpty()) {
			builder.append("class=\"").append(cssClass).append("\" ");
		}
		if (ying == 0) {
			builder.append("style=\"color:red;\" ");
			builder.append(">");
			if (displayed) {
				builder.append(ying + "&nbsp;");
			}
			builder.append(label1);
		}
		else if (ymin <= (ying / 10d) && ymax >= (ying / 10d)) {
			builder.append(">");
			if (displayed) {
				builder.append(ying + "&nbsp;");
			}
			builder.append(label2);
		}
		else if (ying > 0) {
			builder.append("style=\"color:red;\" ");
			builder.append(">");
			if (displayed) {
				builder.append(ying + "&nbsp;");
			}
			builder.append(label1);
		}
		else {
			builder.append("style=\"color:red;\" ");
			builder.append(">");
			if (displayed) {
				builder.append(ying + "&nbsp;");
			}
			builder.append(label1);
		}
		builder.append("</span>");
		write(builder.toString());
		return SKIP_BODY;
	}

	private void write(String content) {
		try {
			pageContext.getOut().write(content);
		}
		catch (IOException e) {
		}
	}

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
	}

	public Double getYmin() {
		return ymin;
	}

	public void setYmin(Double ymin) {
		this.ymin = ymin;
	}

	public Double getYmax() {
		return ymax;
	}

	public void setYmax(Double ymax) {
		this.ymax = ymax;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getLabel1() {
		return label1;
	}

	public void setLabel1(String label1) {
		this.label1 = label1;
	}

	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public boolean isDisplayed() {
		return displayed;
	}

	public void setDisplayed(boolean displayed) {
		this.displayed = displayed;
	}

}
