package com.coco.tag.f;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

/**
 * <p>
 * 值为Null输出
 * </p>
 * <p>
 * create: 2011-1-27 下午03:43:57
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class NullTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 值，上下文获取
	 */
	protected String value;

	/**
	 * 非，表示不相等时输出
	 */
	protected boolean not;

	@Override
	public int doStartTag() throws JspException {
		Object bean = TagUtil.getBean(pageContext, value);
		if (not) {
			return bean == null ? SKIP_BODY : EVAL_BODY_INCLUDE;
		}
		return bean == null ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isNot() {
		return not;
	}

	public void setNot(boolean not) {
		this.not = not;
	}

}
