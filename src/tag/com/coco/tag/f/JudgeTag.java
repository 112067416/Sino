package com.coco.tag.f;

import java.math.BigDecimal;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.util.NumberUtils;

/**
 * <p>
 * 根据值的区间，设置其显示的效果
 * </p>
 */
public class JudgeTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 值
	 */
	protected Double value;

	/**
	 * 最小值
	 */
	protected Double min;

	/**
	 * 最大值
	 */
	protected Double max;

	/**
	 * 当值不在区间时，改变其颜色
	 */
	protected String color;

	/**
	 * 小数点精度
	 */
	protected int precision = -1;

	@Override
	public int doStartTag() throws JspException {
		if (value == null) {
			return SKIP_BODY;
		}
		StringBuilder html = new StringBuilder();
		html.append("<span");
		if (value > 0
				&& ((min != null && min > value) || (max != null && max < value))) {
			html.append(" style=\"color:").append(color != null
					&& !color.isEmpty() ? color : "#F94545;").append("\"");
		}
		else if (value == 0) {
			html.append(" style=\"color:").append(color != null
					&& !color.isEmpty() ? color : "#F94545;").append("\"");
		}
		else {
			html.append(" style=\"color:").append("#000000;").append("\"");
		}
		html.append(">");
		html.append(precision > 0 ? NumberUtils.format(value, precision, BigDecimal.ROUND_HALF_UP)
				: value);
		html.append("</span)>");
		try {
			pageContext.getOut().write(html.toString());
		}
		catch (Exception ex) {
		}
		return SKIP_BODY;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}
}
