package com.coco.sys.tag;

import javax.servlet.jsp.JspException;

import com.coco.tag.TagUtil;
import com.coco.tag.ui.SelectTag;

public class CodeSelectTag extends SelectTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	@Override
	public int doStartTag() throws JspException {
		if (code == null || code.isEmpty()) {
			return SKIP_BODY;
		}
		Object $code = TagUtil.getBean(pageContext, code);
		if ($code == null) {
			return SKIP_BODY;
		}
		ql = "select key, value from CodeItem where valid=1 and code.id='"
				+ $code.toString().replaceAll("'", "''")
				+ "' order by order,key";
		return super.doStartTag();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
