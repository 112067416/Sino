package com.quanta.sino.tag;

import java.util.List;

import javax.servlet.jsp.JspException;

import com.coco.core.env.Helper;
import com.coco.core.util.StringUtils;
import com.coco.tag.ui.ComboboxTag;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.orm.YongMp;

/**
 * <p>
 * 用户组合框
 * </p>
 * <p>
 * create: 2011-2-11 上午10:44:08
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class YongComboboxTag extends ComboboxTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 值字段：1-用户代码user，2-用户名称name，4-用户略称abbr，8-中文名rsv1。默认为1
	 */
	private int val = 1;

	/**
	 * 显示字段：1-用户代码user，2-用户名称name，4-用户略称abbr，8-中文名rsv1；默认为4，若显示多个则采用与的方式，如：
	 * 值为15表示都显示（ 15=1|2|4|8）
	 */
	private int txt = 4;

	/**
	 * 附加数据字段：1-用户代码user，2-用户名称name，4-用户略称abbr，8-中文名rsv1；若显示多个则采用与的方式，如：
	 * 值为15表示都显示（ 15=1|2|4|8）
	 */
	private int dat;

	/*
	 * (non-Javadoc)
	 * @see com.coco.tag.ui.ComboboxTag#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		super.match = true;
		IYongBO bo = Helper.getBean(IYongBO.class);
		List<YongMp> items = bo.findAll();
		StringBuilder sb = new StringBuilder();
		String v = null, t = null;
		String user, name, abbr, rsv1;
		for (YongMp item : items) {
			user = null;
			name = null;
			abbr = null;
			rsv1 = null;
			// 值部分
			if (val <= 1) {
				v = user = StringUtils.toJsString(item.getUser());
			}
			else if (val == 2) {
				v = name = StringUtils.toJsString(item.getName());
			}
			else if (val == 4) {
				v = abbr = StringUtils.toJsString(item.getAbbr());
			}
			else if (val == 8) {
				v = rsv1 = StringUtils.toJsString(item.getRsv1());
			}
			// 文本部分
			if (txt < 1) {
				t = v;
			}
			else {
				// 假设有key
				if ((txt & 1) == 1) {
					if (user != null) {
						t = user;
					}
					else {
						t = user = StringUtils.toJsString(item.getUser());
					}
				}
				else {
					t = "";
				}
				if ((txt & 2) == 2) {
					if (t.length() > 0) {
						t += " ";
					}
					if (name != null) {
						t += name;
					}
					else {
						t += name = StringUtils.toJsString(item.getName());
					}
				}
				if ((txt & 4) == 4) {
					if (t.length() > 0) {
						t += " ";
					}
					if (abbr != null) {
						t += abbr;
					}
					else {
						t += abbr = StringUtils.toJsString(item.getAbbr());
					}
				}
				if ((txt & 8) == 8) {
					if (t.length() > 0) {
						t += " ";
					}
					if (rsv1 != null) {
						t += rsv1;
					}
					else {
						t += rsv1 = StringUtils.toJsString(item.getRsv1());
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
					if (user != null) {
						sb.append(",\"").append(user).append("\"");
					}
					else {
						sb.append(",\"").append(user = StringUtils.toJsString(item.getUser())).append("\"");
					}
				}
				if ((dat & 2) == 2) {
					if (name != null) {
						sb.append(",\"").append(name).append("\"");
					}
					else {
						sb.append(",\"").append(name = StringUtils.toJsString(item.getName())).append("\"");
					}
				}
				if ((dat & 4) == 4) {
					if (abbr != null) {
						sb.append(",\"").append(abbr).append("\"");
					}
					else {
						sb.append(",\"").append(abbr = StringUtils.toJsString(item.getAbbr())).append("\"");
					}
				}
				if ((dat & 8) == 8) {
					if (rsv1 != null) {
						sb.append(",\"").append(rsv1).append("\"");
					}
					else {
						sb.append(",\"").append(rsv1 = StringUtils.toJsString(item.getRsv1())).append("\"");
					}
				}
			}
			sb.append("]");
		}
		super.items = "[" + sb.toString() + "]";
		return super.doStartTag();
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
