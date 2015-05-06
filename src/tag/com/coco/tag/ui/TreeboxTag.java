package com.coco.tag.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;
import com.coco.tag.assist.OptionParam;
import com.coco.tag.assist.OptionUtil;

public class TreeboxTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	protected String id;

	protected String name;

	protected String items;

	protected String fieldId;

	protected String fieldParent;

	protected String fieldLabel;

	protected String fieldOrder;

	protected String value;

	protected String root;

	protected String headerValue;

	protected String headerText;

	protected String cssStyle;

	protected String cssClass;

	protected String prop;

	public String toHtml(List<?> objs, Object $value) {
		OptionParam param = new OptionParam();
		param.objs = objs;
		param.id = fieldId != null && !fieldId.isEmpty() ? fieldId : "id";
		param.parent = fieldParent != null && !fieldParent.isEmpty()
				? fieldParent
				: "parent";
		param.label = fieldLabel;
		param.order = fieldOrder;
		if ($value != null) {
			param.value = $value.toString();
		}
		param.root = root != null ? root : "0";
		StringBuilder html = new StringBuilder();
		html.append("<select ");
		if (id != null) {
			html.append(" id=\"").append(id).append("\"");
		}
		if (name != null) {
			html.append(" name=\"").append(name).append("\"");
		}
		html.append(" class=\"normal");
		if (cssClass != null) {
			html.append(" ").append(cssClass);
		}
		html.append("\"");
		if (prop != null) {
			html.append(" xu.prop=\"").append(prop).append("\"");
		}
		if (cssStyle != null) {
			html.append(" style=\"").append(cssStyle).append("\"");
		}
		html.append(" >");
		if (headerValue != null && headerText != null) {
			html.append("<option value=\"").append(headerValue).append("\">")
					.append(headerText).append("</option>");
		}
		html.append(OptionUtil.toTreeOptions(param));
		html.append("</select>");
		return html.toString();
	}

	@Override
	public int doStartTag() throws JspException {
		Object bean = TagUtil.getBean(pageContext, items);
		if (bean == null || !(bean instanceof List)) {
			bean = new ArrayList<Object>();
		}
		Object $value = TagUtil.getBean(pageContext, value);
		if ($value == null) {
			$value = TagUtil.getBean(pageContext, name);
		}
		try {
			pageContext.getOut().write(toHtml((List<?>) bean, $value));
		} catch (IOException e) {
		}
		return SKIP_BODY;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldParent() {
		return fieldParent;
	}

	public void setFieldParent(String fieldParent) {
		this.fieldParent = fieldParent;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getFieldOrder() {
		return fieldOrder;
	}

	public void setFieldOrder(String fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

}
