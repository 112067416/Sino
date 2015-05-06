package com.coco.tag.f;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

/**
 * <p>
 * 值相等输出
 * </p>
 * <p>
 * create: 2011-1-27 下午03:43:57
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class EqualsTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 值1，上下文获取
	 */
	protected String value1;

	/**
	 * 值2，上下文获取
	 */
	protected String value2;

	/**
	 * 非，表示不相等时输出
	 */
	protected boolean not;

	@Override
	public int doStartTag() throws JspException {
		Object bean1 = TagUtil.getBean(pageContext, value1);
		Object bean2 = TagUtil.getBean(pageContext, value2);
		if (bean1 == null) {
			return SKIP_BODY;
		}
		boolean flag = bean1.equals(bean2);
		if (not) {
			return flag ? SKIP_BODY : EVAL_BODY_INCLUDE;
		}
		return flag ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public boolean isNot() {
		return not;
	}

	public void setNot(boolean not) {
		this.not = not;
	}
}
