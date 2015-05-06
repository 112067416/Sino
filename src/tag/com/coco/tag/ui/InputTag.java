package com.coco.tag.ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.core.util.ReflectUtils.ClassType;
import com.coco.core.xml.XMLHelper;
import com.coco.tag.TagUtil;

public class InputTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 控件ID
	 */
	protected String id;

	/**
	 * 控件名称
	 */
	protected String name;

	/**
	 * 控件初始值，从上下文获取
	 */
	protected String value;

	/**
	 * 控件属性
	 */
	protected String prop;

	/**
	 * 必填
	 */
	protected boolean required;

	/**
	 * 校验键盘
	 */
	protected boolean validate;

	/**
	 * 是否关闭输入法,true表示不关闭
	 */
	protected boolean imeMode = true;

	/**
	 * 样式类
	 */
	protected String cssClass;

	/**
	 * 样式
	 */
	protected String cssStyle;

	/**
	 * 指定最大长度
	 */
	protected int maxlength;

	/**
	 * 控件主题
	 */
	protected String title;

	/**
	 * 根据定宽算出长度
	 */
	protected boolean widthable = true;

	/**
	 * 光标移开事件
	 */
	protected String onblur;

	/**
	 * 鼠标点击事件
	 */
	protected String onclick;

	/**
	 * 键盘按下事件
	 */
	protected String onkeydown;

	/**
	 * 聚焦事件
	 */
	protected String onfocus;

	/**
	 * 值改变事件
	 */
	protected String onchange;

	/**
	 * 只读
	 */
	protected boolean readonly;

	/**
	 * 小写转化成大写
	 */
	protected boolean lowToUp = true;

	/**
	 * 级联
	 */
	protected String cascade;

	/**
	 * 去掉前后空格
	 */
	protected boolean trim = true;

	/**
	 * 格式化值
	 */
	protected String format;

	/**
	 * 输入值下限
	 */
	protected Double min;

	/**
	 * 输入值上限
	 */
	protected Double max;

	protected Type type() {
		return null;
	}

	/**
	 * 初始化标签
	 */
	protected void init() {

	}

	/**
	 * <p>
	 * 附加页面控件属性
	 * </p>
	 * 
	 * @param content
	 */
	protected void appendAttribute(StringBuilder content) {

	}

	/**
	 * <p>
	 * 附加自定义属性
	 * </p>
	 * 
	 * @param content
	 */
	protected void appendProp(StringBuilder content) {

	}

	protected String formatValue() {
		Object obj = null;
		if (value != null) {
			obj = TagUtil.getBean(pageContext, value);
		}
		else {
			obj = TagUtil.getBean(pageContext, name);
		}
		if (obj == null) {
			return null;
		}
		ClassType classType = ReflectUtils.classType(obj.getClass());
		if (ClassType.date.equals(classType)) {
			String format = this.format;
			if (format == null || format.length() == 0) {
				format = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return (sdf.format((Date) obj));
		}
		else if (ClassType.number.equals(classType)) {
			return (NumberUtils.formatNumber((Number) obj, format));
		}
		else {
			return (obj.toString());
		}

	}

	@Override
	public int doStartTag() throws JspException {
		init();
		StringBuilder content = new StringBuilder();
		content.append("<input type=\"text\"");
		if (id != null) {
			content.append(" id=\"");
			content.append(id.replaceAll("\\.", "_"));
			content.append("\"");
		}
		content.append(" name=\"");
		if (name != null) {
			content.append(name);
		}
		content.append("\" class=\"");
		Type type = type();
		if (type != null) {
			content.append(type.cssClass);
		}
		if (cssClass != null) {
			content.append(" ").append(cssClass);
		}
		else if (readonly) {
			content.append(" form-readonly");
		}
		else {
			content.append(" normal");
		}
		content.append("\"");
		content.append(" xu.prop=\"");
		if (type != null) {
			content.append(type.prop);
		}
		if (required) {
			content.append("nn:1;");
		}
		if (!trim) {
			content.append("trim:0;");
		}
		if (prop != null) {
			content.append(prop);
		}
		this.appendProp(content);

		content.append("\"");

		if (title != null) {
			content.append(" title=\"").append(XMLHelper.formatXmlValue(title.replaceAll("\"", "\\\\\""))).append("\"");
		}
		content.append(" onblur=\"cocoform.onblur(event);");
		if (onblur != null) {
			content.append(onblur.replaceAll("\"", "\\\\\""));
		}
		content.append("\"");
		if (onclick != null) {
			content.append(" onclick=\"").append(onclick.replaceAll("\"", "\\\\\"")).append("\"");
		}
		if (onkeydown != null) {
			content.append(" onkeydown=\"").append(onkeydown.replaceAll("\"", "\\\\\"")).append("\"");
		}
		if (onfocus != null) {
			content.append(" onfocus=\"").append(onfocus.replaceAll("\"", "\\\\\"")).append("\"");
		}
		if (onchange != null) {
			content.append(" onchange=\"").append(onchange.replaceAll("\"", "\\\\\"")).append("\"");
		}
		if (readonly) {
			content.append(" READONLY=\"true\"");
		}
		if (validate) {
			content.append(" xu.validate=\"1\"");
		}
		if (lowToUp) {
			content.append(" xu.ltu=\"1\"");
		}
		if (cascade != null && !(cascade = cascade.trim()).isEmpty()) {
			content.append(" xu.cascade=\"").append(cascade).append("\"");
		}

		String style = "";
		if (maxlength > 0) {
			content.append(" maxlength=").append(maxlength);
			if (widthable) {
				style += "width:" + (maxlength * 10 + 2) + "px;";
			}
		}
		if (!imeMode) {
			style += ";ime-mode:disabled !important;";
		}
		if (cssStyle != null) {
			style += cssStyle;
		}
		content.append(" style=\"");
		content.append(style);
		content.append("\"");
		String val = formatValue();
		if (val != null) {
			content.append(" value=\"");
			content.append(XMLHelper.formatXmlValue(val.replaceAll("\r|\n", " ")));
			content.append("\"");
		}
		if (min != null) {
			content.append(" min=\"").append(min).append("\"");
		}
		if (max != null) {
			content.append(" max=\"").append(max).append("\"");
		}
		appendAttribute(content);
		content.append(" />");
		try {
			pageContext.getOut().write(content.toString());
		}
		catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	protected enum Type {
		none("", ""), date("date", "type:date;"), datetime("datetime",
				"type:datetime;"), time("time", "type:time;"), email("email",
				"type:email;"), rmb("rmb", "type:number;"), usd("usd",
				"type:number;"), number("number", "type:number;"), integer(
				"number", "type:int;"), digit("digit", "type:digit;"), abcn(
				"abcn", "type:abcn;");

		public final String cssClass;

		public final String prop;

		Type(String cssClass, String prop) {
			this.cssClass = cssClass;
			this.prop = prop;
		}
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

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the maxlength
	 */
	public int getMaxlength() {
		return maxlength;
	}

	/**
	 * @param maxlength
	 *            the maxlength to set
	 */
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the widthable
	 */
	public boolean isWidthable() {
		return widthable;
	}

	/**
	 * @param widthable
	 *            the widthable to set
	 */
	public void setWidthable(boolean widthable) {
		this.widthable = widthable;
	}

	/**
	 * @return the onblur
	 */
	public String getOnblur() {
		return onblur;
	}

	/**
	 * @param onblur
	 *            the onblur to set
	 */
	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	/**
	 * @return the onclick
	 */
	public String getOnclick() {
		return onclick;
	}

	/**
	 * @param onclick
	 *            the onclick to set
	 */
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * @return the onkeydown
	 */
	public String getOnkeydown() {
		return onkeydown;
	}

	/**
	 * @param onkeydown
	 *            the onkeydown to set
	 */
	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	/**
	 * @return the onfocus
	 */
	public String getOnfocus() {
		return onfocus;
	}

	/**
	 * @param onfocus
	 *            the onfocus to set
	 */
	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	/**
	 * @return the readonly
	 */
	public boolean isReadonly() {
		return readonly;
	}

	/**
	 * @param readonly
	 *            the readonly to set
	 */
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	/**
	 * @return the lowToUp
	 */
	public boolean isLowToUp() {
		return lowToUp;
	}

	/**
	 * @param lowToUp
	 *            the lowToUp to set
	 */
	public void setLowToUp(boolean lowToUp) {
		this.lowToUp = lowToUp;
	}

	/**
	 * @return the cascade
	 */
	public String getCascade() {
		return cascade;
	}

	/**
	 * @param cascade
	 *            the cascade to set
	 */
	public void setCascade(String cascade) {
		this.cascade = cascade;
	}

	/**
	 * @return the trim
	 */
	public boolean isTrim() {
		return trim;
	}

	/**
	 * @param trim
	 *            the trim to set
	 */
	public void setTrim(boolean trim) {
		this.trim = trim;
	}

	/**
	 * @return the validate
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * @param validate
	 *            the validate to set
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * @return the imeMode
	 */
	public boolean isImeMode() {
		return imeMode;
	}

	/**
	 * @param imeMode
	 *            the imeMode to set
	 */
	public void setImeMode(boolean imeMode) {
		this.imeMode = imeMode;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
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

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

}
