package com.coco.tag.f;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <p>
 * 替换字符串的指定字符
 * </p>
 * <p>
 * create: 2011-5-19 上午09:35:10
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ReplaceTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 值
	 */
	protected String value;

	/**
	 * 源符号
	 */
	protected String oChar;

	/**
	 * 目标符号
	 */
	protected String dChar;

	/**
	 * 
	 */
	protected int num;

	@Override
	public int doStartTag() throws JspException {
		if (value == null || value.isEmpty()) {
			return SKIP_BODY;
		}
		if (oChar == null || oChar.isEmpty()) {
			oChar = ",";
		}
		if (dChar == null || dChar.isEmpty()) {
			dChar = "<br />";
		}
		try {
			if (num > 0) {
				String[] vs = value.split(oChar);
				StringBuilder builder = new StringBuilder();
				int i = 0;
				for (String v : vs) {
					i++;
					if (builder.length() == 0) {
						builder.append(v);
						continue;
					}
					builder.append(oChar).append(v);
					if (i % num == 0) {
						builder.append(dChar);
					}
				}
				pageContext.getOut().write(builder.toString());
			}
			else {
				pageContext.getOut().write(value.replaceAll(oChar, dChar));
			}
		}
		catch (Exception ex) {
		}
		return SKIP_BODY;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getoChar() {
		return oChar;
	}

	public void setoChar(String oChar) {
		this.oChar = oChar;
	}

	public String getdChar() {
		return dChar;
	}

	public void setdChar(String dChar) {
		this.dChar = dChar;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
