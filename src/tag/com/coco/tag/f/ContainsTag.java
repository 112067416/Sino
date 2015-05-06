package com.coco.tag.f;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

/**
 * <p>
 * 包含标签
 * </p>
 * <p>
 * create: 2010-12-16 下午06:07:34
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ContainsTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * 集合名称或者指定以逗号分隔的字符串,上下文获取
	 * </p>
	 */
	protected String name;

	/**
	 * <p>
	 * 检查对象名称,上下文获取
	 * </p>
	 */
	protected String object;

	/**
	 * 是否表示为不包含
	 */
	private boolean not;

	@Override
	public int doStartTag() throws JspException {
		Object bean = TagUtil.getBean(pageContext, name);
		Object obj = TagUtil.getBean(pageContext, object);
		if (bean == null || obj == null) {
			return SKIP_BODY;
		}
		if (bean instanceof String) {
			bean = Arrays.asList(((String) bean).split(","));
		}
		else if (!(bean instanceof Collection<?>)) {
			return SKIP_BODY;
		}
		Collection<?> beans = (Collection<?>) bean;
		boolean flag = false;
		if (not) {
			flag = !beans.contains(obj);
		}
		else {
			flag = beans.contains(obj);
		}
		return flag ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the object
	 */
	public String getObject() {
		return object;
	}

	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(String object) {
		this.object = object;
	}

	/**
	 * @return the not
	 */
	public boolean isNot() {
		return not;
	}

	/**
	 * @param not
	 *            the not to set
	 */
	public void setNot(boolean not) {
		this.not = not;
	}

}
