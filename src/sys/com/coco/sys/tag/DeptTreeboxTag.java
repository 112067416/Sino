package com.coco.sys.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;

import com.coco.core.env.Helper;
import com.coco.sys.bo.api.IDeptBO;
import com.coco.sys.orm.Dept;
import com.coco.tag.TagUtil;
import com.coco.tag.ui.TreeboxTag;

public class DeptTreeboxTag extends TreeboxTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see com.coco.tag.ui.TreeboxTag#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {

		Object $value = TagUtil.getBean(pageContext, value);
		if ($value == null) {
			$value = TagUtil.getBean(pageContext, name);
		}
		super.fieldLabel = "name";
		super.fieldId = "id";
		super.fieldParent = "parent";
		super.fieldOrder = "order";
		List<Dept> depts = Helper.getBean(IDeptBO.class).findValid();
		try {
			pageContext.getOut().write(toHtml(depts, $value));
		}
		catch (IOException e) {
		}
		return SKIP_BODY;
	}

}
