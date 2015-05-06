package com.coco.tag.f;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.env.Helper;
import com.coco.core.util.StringUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.coco.tag.TagUtil;

/**
 * <p>
 * 显示码表文本值标签
 * </p>
 * <p>
 * create: 2011-2-21 下午01:58:41
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CodeValueTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 模块
	 */
	protected String code;

	/**
	 * 键值
	 */
	protected String key;

	/**
	 * 显示字段：1-key，2-value，4-remark，8-number；若显示多个则采用与的方式，如：值为15表示都显示（15=1|2|4|8）
	 */
	protected int txt;

	/**
	 * 当该值与key相同时，要改变显示字段的样式
	 */
	protected String fixed;

	/**
	 * 改变显示字段样式
	 */
	protected String color;

	@Override
	public int doStartTag() throws JspException {
		Object $code = TagUtil.getBean(pageContext, code);
		if ($code == null) {
			return SKIP_BODY;
		}
		String $key = key;
		if ($key == null || ($key = key.trim()).isEmpty()) {
			return SKIP_BODY;
		}
		ICodeBO bo = Helper.getBean(ICodeBO.class);
		CodeItem item = bo.getCodeItem($code.toString(), $key);
		if (item == null) {
			return SKIP_BODY;
		}
		String v = null;
		BigDecimal number;
		// 显示值部分
		if (txt == 1) {
			v = StringUtils.toJsString(item.getKey());
		}
		else if (txt == 2 || txt < 1) {
			v = StringUtils.toJsString(item.getValue());
		}
		else if (txt == 4) {
			v = StringUtils.toJsString(item.getRemark());
		}
		else if (txt == 8) {
			v = (number = item.getNumber()) != null ? number.toString() : "";
		}
		StringBuilder html = new StringBuilder();
		html.append("<span");
		if ($key.equals(fixed) && color != null) {
			html.append(" style=\"color:").append(color).append("\"");
		}
		html.append(">");
		if (v != null) {
			html.append(v);
		}
		html.append("</span>");
		write(html.toString());
		return SKIP_BODY;
	}

	private void write(String content) {
		try {
			pageContext.getOut().write(content);
		}
		catch (IOException e) {
		}
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
	 * @return the txt
	 */
	public int getTxt() {
		return txt;
	}

	/**
	 * @param txt
	 *            the txt to set
	 */
	public void setTxt(int txt) {
		this.txt = txt;
	}

	public String getFixed() {
		return fixed;
	}

	public void setFixed(String fixed) {
		this.fixed = fixed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
