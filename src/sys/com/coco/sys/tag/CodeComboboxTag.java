package com.coco.sys.tag;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.jsp.JspException;

import com.coco.core.env.Helper;
import com.coco.core.util.StringUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.coco.tag.TagUtil;
import com.coco.tag.ui.ComboboxTag;

/**
 * 代码复选框
 * 
 * @author 许德建[xudejian@126.com]
 */
public class CodeComboboxTag extends ComboboxTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 模块
	 */
	private String code;

	/**
	 * 值字段：1-key，2-value，4-remark，8-number
	 */
	private int val;

	/**
	 * 显示字段：1-key，2-value，4-remark，8-number；若显示多个则采用与的方式，如：值为15表示都显示（15=1|2|4|8）
	 */
	private int txt;

	/**
	 * 附加数据字段：1-key，2-value，4-remark，8-number；若显示多个则采用与的方式，如：值为15表示都显示（15=1|2|4|
	 * 8）
	 */
	private int dat;

	@Override
	public int doStartTag() throws JspException {
		super.match = true;
		Object $code = TagUtil.getBean(pageContext, code);
		if ($code == null) {
			return SKIP_BODY;
		}
		ICodeBO bo = Helper.getBean(ICodeBO.class);
		List<CodeItem> items = bo.findValidItems($code.toString());
		StringBuilder sb = new StringBuilder();
		String v = null, t = null;
		String key, value, remark;
		BigDecimal number;
		for (CodeItem item : items) {
			key = null;
			value = null;
			remark = null;
			number = null;
			// 值部分
			if (val <= 1) {
				v = key = StringUtils.toJsString(item.getKey());
			}
			else if (val == 2) {
				v = value = StringUtils.toJsString(item.getValue());
			}
			else if (val == 4) {
				v = remark = StringUtils.toJsString(item.getRemark());
			}
			else if (val == 8) {
				v = (number = item.getNumber()) != null ? number.toString()
						: "";
			}
			// 文本部分
			if (txt < 1) {
				t = v;
			}
			else {
				// 假设有key
				if ((txt & 1) == 1) {
					if (key != null) {
						t = key;
					}
					else {
						t = key = StringUtils.toJsString(item.getKey());
					}
				}
				else {
					t = "";
				}
				if ((txt & 2) == 2) {
					if (t.length() > 0) {
						t += " ";
					}
					if (value != null) {
						t += value;
					}
					else {
						t += value = StringUtils.toJsString(item.getValue());
					}
				}
				if ((txt & 4) == 4) {
					if (t.length() > 0) {
						t += " ";
					}
					if (remark != null) {
						t += remark;
					}
					else {
						t += remark = StringUtils.toJsString(item.getRemark());
					}
				}
				if ((txt & 8) == 8) {
					if (t.length() > 0) {
						t += " ";
					}
					if (number != null) {
						t += number;
					}
					else {
						t += (number = item.getNumber()) != null ? number.toString()
								: "";
					}
				}
			}
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append("[\"").append(v).append("\",\"").append(t).append("\"");
			// ===其他值==
			if (dat > 0) {
				sb.append(",0");
				if ((dat & 1) == 1) {
					if (key != null) {
						sb.append(",\"").append(key).append("\"");
					}
					else {
						sb.append(",\"").append(key = StringUtils.toJsString(item.getKey())).append("\"");
					}
				}
				if ((dat & 2) == 2) {
					if (value != null) {
						sb.append(",\"").append(value).append("\"");
					}
					else {
						sb.append(",\"").append(value = StringUtils.toJsString(item.getValue())).append("\"");
					}
				}
				if ((dat & 4) == 4) {
					if (remark != null) {
						sb.append(",\"").append(remark).append("\"");
					}
					else {
						sb.append(",\"").append(remark = StringUtils.toJsString(item.getRemark())).append("\"");
					}
				}
				if ((dat & 8) == 8) {
					if (number != null) {
						sb.append(",\"").append(number).append("\"");
					}
					else if ((number = item.getNumber()) != null) {
						sb.append(",").append(number);
					}
					else {
						sb.append(",null");
					}
				}
			}
			sb.append("]");
		}
		super.items = "[" + sb.toString() + "]";
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

	/**
	 * @return the val
	 */
	public int getVal() {
		return val;
	}

	/**
	 * @param val
	 *            the val to set
	 */
	public void setVal(int val) {
		this.val = val;
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

	/**
	 * @return the dat
	 */
	public int getDat() {
		return dat;
	}

	/**
	 * @param dat
	 *            the dat to set
	 */
	public void setDat(int dat) {
		this.dat = dat;
	}

}
