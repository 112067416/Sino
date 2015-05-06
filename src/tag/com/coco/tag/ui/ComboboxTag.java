package com.coco.tag.ui;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.util.ReflectUtils;
import com.coco.core.util.StringUtils;
import com.coco.tag.TagUtil;

/**
 * <p>
 * 组合框
 * </p>
 * <p>
 * create: 2010-12-27 上午11:07:14
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ComboboxTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String name;

	protected String value;

	protected String text;

	protected String list;

	protected String listValue;

	protected String listText;

	protected String listGroup;

	/**
	 * 初始数据js数组，格式如：[['value0', 'text0'],['value1','text1'],['value2','text2',
	 * 1]]，元素1为值，元素2为显示文本，元素3为组（仅为1时表示是组），元素4以后为其他数据值
	 */
	protected String items;

	/**
	 * 控件宽度
	 */
	protected String width;

	/**
	 * 强制匹配值
	 */
	protected boolean match;

	protected boolean clickdown;

	protected boolean required;

	protected boolean like;

	protected String onchange;

	/**
	 * 其他数据字段，以逗号分隔
	 */
	protected String fields;

	/**
	 * 选中时同时改变的域。以js数组方式显示，元素1表示fields的位置，元素2表示同表单（或同表格行）域的名称，元素3表示是否为同表格行，
	 * 仅等于1表示同表格行， 不填表示同表单。值如：[[0, 'name'],[1,'sex',1]]
	 */
	protected String tofield;

	/**
	 * 级联
	 */
	protected String cascade;

	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		if (name == null || name.length() == 0) {
			return SKIP_BODY;
		}
		Object obj = null;
		if (value != null) {
			obj = TagUtil.getBean(pageContext, value);
		}
		else {
			obj = TagUtil.getBean(pageContext, name);
		}
		String jsItems = items;
		if (jsItems == null && list != null) {
			String lv = listValue, lt = listText;
			if (lv != null && !(lv = lv.trim()).isEmpty()) {
				if (lt == null || (lt = lt.trim()).isEmpty()) {
					lt = lv;
				}
				Object listObj = TagUtil.getBean(pageContext, list);
				if (listObj != null && listObj instanceof Collection<?>) {
					Object v, t, d;
					String[] fieldArr = fields != null
							&& !(fields = fields.trim()).isEmpty() ? fields.split(",")
							: null;
					int group = 0;
					StringBuilder dataSb = new StringBuilder();
					dataSb.append("[");
					for (Object item : (Collection<Object>) listObj) {
						v = ReflectUtils.eval(item, lv);
						t = ReflectUtils.eval(item, lt);
						if (v == null) continue;
						v = v.toString().replaceAll("\"", "\\\\\"");
						dataSb.append("[\"").append(v).append("\",\"");
						dataSb.append(t == null ? v
								: t.toString().replaceAll("\"", "\\\\\""));
						group = 0;
						if (listGroup != null) {
							Object g = ReflectUtils.eval(item, listGroup);
							if ("1".equals(g)) {
								group = 1;
							}
						}
						dataSb.append(",").append(group);
						if (fieldArr != null) {
							for (String field : fieldArr) {
								d = ReflectUtils.eval(item, field);
								if (d == null) {
									dataSb.append(",null");
								}
								else {
									dataSb.append(",\"").append(d.toString().replaceAll("\"", "\\\\\"")).append("\"");
								}
							}
						}
						dataSb.append("\"]");
					}
					dataSb.append("]");
					jsItems = dataSb.toString();
				}
			}
		}
		if (jsItems == null) {
			jsItems = "[]";
		}
		StringBuilder content = new StringBuilder();
		content.append("<script type=\"text/javascript\" >cococombobox.write({name : \"");
		content.append(name).append("\",value:");
		if (obj == null) {
			content.append("null");
		}
		else {
			content.append("\"").append(StringUtils.toJsString(obj.toString())).append("\"");
		}
		content.append(",items:");
		content.append(jsItems);
		if (width != null) {
			content.append(",width:\"").append(width).append("\"");
		}
		if (match) {
			content.append(",match:true");
		}
		if (clickdown) {
			content.append(",clickdown:true");
		}
		if (required) {
			content.append(",required:true");
		}
		if (like) {
			content.append(",like:true");
		}
		if (onchange != null) {
			content.append(",onchange:\"").append(onchange).append("\"");
		}
		if (tofield != null && !(tofield = tofield.trim()).isEmpty()) {
			content.append(",tofield:").append(tofield);
		}
		if (cascade != null && !cascade.isEmpty()) {
			content.append(",cascade:\"").append(StringUtils.toJsString(cascade)).append("\"");
		}
		content.append("});</script>");
		try {
			pageContext.getOut().write(content.toString());
		}
		catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getListValue() {
		return listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	public String getListText() {
		return listText;
	}

	public void setListText(String listText) {
		this.listText = listText;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getListGroup() {
		return listGroup;
	}

	public void setListGroup(String listGroup) {
		this.listGroup = listGroup;
	}

	public boolean isMatch() {
		return match;
	}

	public void setMatch(boolean match) {
		this.match = match;
	}

	public boolean isClickdown() {
		return clickdown;
	}

	public void setClickdown(boolean clickdown) {
		this.clickdown = clickdown;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the like
	 */
	public boolean isLike() {
		return like;
	}

	/**
	 * @param like
	 *            the like to set
	 */
	public void setLike(boolean like) {
		this.like = like;
	}

	/**
	 * @return the onchange
	 */
	public String getOnchange() {
		return onchange;
	}

	/**
	 * @param onchange
	 *            the onchange to set
	 */
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	/**
	 * @return the tofield
	 */
	public String getTofield() {
		return tofield;
	}

	/**
	 * @param tofield
	 *            the tofield to set
	 */
	public void setTofield(String tofield) {
		this.tofield = tofield;
	}

	/**
	 * @return the fields
	 */
	public String getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getCascade() {
		return cascade;
	}

	public void setCascade(String cascade) {
		this.cascade = cascade;
	}

}
