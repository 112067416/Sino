package com.coco.tag.f;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.env.Helper;
import com.coco.core.persistence.api.DAO;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.core.util.ReflectUtils.ClassType;
import com.coco.tag.TagUtil;

/**
 * <p>
 * 显示值
 * </p>
 */
public class ValueTag extends BodyTagSupport {
	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 输出值
	 */
	protected String value;

	/**
	 * 格式化
	 */
	protected String format;

	/**
	 * ql查询
	 */
	protected String ql;

	@Override
	public int doStartTag() throws JspException {
		Object bean = TagUtil.getBean(pageContext, value);
		if (bean == null) {
			return SKIP_BODY;
		}
		String $ql = ql;
		if ($ql != null && !($ql = $ql.trim()).isEmpty()) {
			bean = Helper.getBean(DAO.class).getUnique($ql, bean);
			if (bean == null) {
				return SKIP_BODY;
			}
		}

		ClassType classType = ReflectUtils.classType(bean.getClass());
		if (ClassType.date.equals(classType)) {
			String format = this.format;
			if (format == null || format.length() == 0) {
				format = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			write(sdf.format((Date) bean));
		}
		else if (ClassType.number.equals(classType)) {
			write(NumberUtils.formatNumber((Number) bean, format));
		}
		else {
			write(bean.toString());
		}
		return SKIP_BODY;
	}

	private void write(String content) {
		try {
			pageContext.getOut().write(content);
		}
		catch (IOException e) {
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the ql
	 */
	public String getQl() {
		return ql;
	}

	/**
	 * @param ql
	 *            the ql to set
	 */
	public void setQl(String ql) {
		this.ql = ql;
	}

}
