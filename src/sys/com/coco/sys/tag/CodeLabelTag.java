package com.coco.sys.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.env.Helper;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.coco.tag.TagUtil;

public class CodeLabelTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 码表模块
	 */
	private String code;

	/**
	 * 码表键
	 */
	private String key;

	/**
	 * 显示码表的字段，1-值（value），2-备注（remark），4-数值（number），可允许显示多个，比如值为3时显示1和2，
	 * 使用join指定的串来分割。默认为1
	 */
	private int show = 1;

	/**
	 * 连接串
	 */
	private String join;

	@Override
	public int doStartTag() throws JspException {
		Object $code = TagUtil.getBean(pageContext, code);
		Object $key = TagUtil.getBean(pageContext, key);
		if ($code == null || $key == null) {
			return SKIP_BODY;
		}
		ICodeBO bo = Helper.getBean(ICodeBO.class);
		CodeItem item = bo.getCodeItem($code.toString(), $key.toString());
		if (item == null) {
			return SKIP_BODY;
		}
		String content = null;
		if (show <= 1) {
			content = item.getValue();
		}
		else {
			content = "";
			String $join = (join == null ? " " : join);
			if ((show & 1) == 1) {
				if (content.length() > 0) {
					content += $join;
				}
				content += item.getValue();
			}
			if ((show & 2) == 2) {
				if (content.length() > 0) {
					content += $join;
				}
				content += item.getRemark();
			}
			if ((show & 4) == 4) {
				if (content.length() > 0) {
					content += $join;
				}
				content += item.getNumber();
			}
		}
		try {
			pageContext.getOut().write(content);
		}
		catch (IOException e) {
		}
		return SKIP_BODY;
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

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the show
	 */
	public int getShow() {
		return show;
	}

	/**
	 * @param show
	 *            the show to set
	 */
	public void setShow(int show) {
		this.show = show;
	}

	/**
	 * @return the join
	 */
	public String getJoin() {
		return join;
	}

	/**
	 * @param join
	 *            the join to set
	 */
	public void setJoin(String join) {
		this.join = join;
	}

}
