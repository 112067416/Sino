package com.quanta.sino.tag;

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
public class IfYingSpanTag extends BodyTagSupport {

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

	@Override
	public int doStartTag() throws JspException {
		ying = ying == null ? 0 : ying;
		ymin = ymin == null ? 0 : ymin;
		ymax = ymax == null ? 0 : ymax;
		if (ymin <= (ying / 10d) && ymax >= (ying / 10d)) {
			return SKIP_BODY;
		}
		else if (ying > 0) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return SKIP_BODY;
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

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
	}

}
