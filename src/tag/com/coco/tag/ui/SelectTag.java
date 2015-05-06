package com.coco.tag.ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.api.IBaseDAO;
import com.coco.core.bean.Property;
import com.coco.core.env.Helper;
import com.coco.core.util.ReflectUtils;
import com.coco.tag.TagUtil;

public class SelectTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String name;

	protected String sql;

	protected String list;

	protected String listValue;

	protected String listText;

	protected String scope;

	protected String value;

	protected String cssClass;

	protected String cssStyle;

	protected String prop;

	/**
	 * 光标移开事件
	 */
	protected String onchange;

	/**
	 * 鼠标点击事件
	 */
	protected String onclick;

	/**
	 * 只读
	 */
	protected boolean readonly;

	/**
	 * 级联
	 */
	protected String cascade;

	/**
	 * QL查询
	 */
	protected String ql;

	/**
	 * 头选项值
	 */
	protected String headerValue;

	/**
	 * 头选项文本
	 */
	protected String headerText;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	private void outBegin(JspWriter out) throws IOException {
		StringBuilder content = new StringBuilder();
		content.append("<select name=\"");
		if (name != null) {
			content.append(name);
		}
		content.append("\" class=\"");
		if (cssClass != null) {
			content.append(cssClass);
		}
		else {
			content.append("normal");
		}
		content.append("\"");

		if (onclick != null) {
			content.append(" onclick=\"").append(onclick.replaceAll("\"", "\\\\\"")).append("\"");
		}
		content.append(" onchange=\"cocoform.changeValue(this);");
		if (onchange != null) {
			content.append(onchange.replaceAll("\"", "\\\\\""));
		}
		content.append("\"");
		if (readonly) {
			content.append(" disabled=\"true\"");
		}
		content.append(" xu.prop=\"");
		if (prop != null) {
			content.append(prop);
		}
		content.append("\"");
		if (cssStyle != null) {
			content.append(" style=\"");
			content.append(cssStyle);
			content.append("\"");
		}
		if (cascade != null && !(cascade = cascade.trim()).isEmpty()) {
			content.append(" xu.cascade=\"").append(cascade).append("\"");
		}
		content.append(" >");
		if (headerValue != null) {
			content.append("<option value=\"").append(headerValue.replaceAll("\"", "\\\\\"")).append("\">");
			if (headerText == null) {
				content.append(headerValue);
			}
			else {
				content.append(headerText);
			}
			content.append("</option>");
		}
		out.print(content.toString());
	}

	private void outOptions(JspWriter out, String val, String text, String v)
			throws IOException {
		out.print("<option value=\"");
		out.print(val.replaceAll("\"", "\\\\\""));
		out.print("\" ");
		if (v.equals(val)) {
			out.print("selected ");
		}
		out.print(">");
		out.print(text);
		out.print("</option>");
	}

	private void outEnd(JspWriter out) throws IOException {
		out.print("</select>");

	}

	@Override
	public int doStartTag() throws JspException {
		if (sql != null && !sql.isEmpty()) {
			return doSql(sql);
		}
		if (ql != null && !ql.isEmpty()) {
			return doQl(ql);
		}
		if (list != null) {
			try {
				return doList(list);
			}
			catch (IOException e) {
				return SKIP_BODY;
			}
		}
		return SKIP_BODY;
	}

	private int doSql(String sql) {
		String $sql = null;
		Object obj = TagUtil.getBean(pageContext, sql);
		if (obj == null) return SKIP_BODY;
		if (obj instanceof String) {
			$sql = (String) obj;
		}
		else if (obj instanceof StringBuffer) {
			$sql = obj.toString();
		}
		else {
			return SKIP_BODY;
		}
		if ($sql == null || $sql.length() == 0) {
			return SKIP_BODY;
		}
		Connection conn = null;
		try {
			conn = Helper.getBean(JdbcTemplate.class).getDataSource().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery($sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			String val, text;
			Object $value = TagUtil.getBean(pageContext, value);
			String v = $value != null ? $value.toString() : "";
			JspWriter out = pageContext.getOut();
			outBegin(out);
			while (rs.next()) {
				val = columns > 0 ? rs.getString(1) : "";
				text = columns > 1 ? rs.getString(2) : "";
				if (val == null) {
					continue;
				}
				outOptions(out, val, text, v);
			}
			outEnd(out);
			rs.close();
			stmt.close();
		}
		catch (Exception ex) {
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return SKIP_BODY;
	}

	private int doQl(String ql) throws JspException {
		IBaseDAO dao = Helper.getBean(IBaseDAO.class);
		List<?> items = dao.query(ql);
		if (items.isEmpty()) {
			return SKIP_BODY;
		}
		Object val, text;
		Object $value = TagUtil.getBean(pageContext, value);
		String v = $value != null ? $value.toString() : "";
		JspWriter out = pageContext.getOut();
		try {
			outBegin(out);
			for (Object item : items) {
				if (item == null) {
					continue;
				}
				val = null;
				text = null;
				if (item.getClass().isArray()) {
					Object[] vt = (Object[]) item;
					if (vt.length > 0) {
						val = vt[0];
					}
					if (vt.length > 1) {
						text = vt[1];
						if (text == null) {
							text = "";
						}
					}
				}
				else {
					text = val = item;
				}
				if (val == null) {
					continue;
				}
				if (text == null) {
					text = val;
				}
				outOptions(out, val.toString(), text.toString(), v);
			}
			outEnd(out);
		}
		catch (IOException e) {
		}
		return SKIP_BODY;
	}

	private int doList(String list) throws JspException, IOException {
		String lv = listValue, lt = listText;
		if (lv == null || (lv = lv.trim()).isEmpty()) {
			return doStringList(list);
		}
		if (lt == null || (lt = lt.trim()).isEmpty()) {
			lt = lv;
		}
		String[] textNames = lt.split(",");
		Object obj = TagUtil.getBean(pageContext, list, scope);
		if (obj == null || !(obj instanceof Collection)) {
			return SKIP_BODY;
		}
		Collection<?> coll = (Collection<?>) obj;
		Iterator<?> it = coll.iterator();
		Object o;
		String val, text;
		Object $value = TagUtil.getBean(pageContext, value);
		String v = $value != null ? $value.toString() : "";
		JspWriter out = pageContext.getOut();
		outBegin(out);
		while (it.hasNext()) {
			o = it.next();
			val = String.valueOf(ReflectUtils.eval(o, lv));
			if (val == null) {
				continue;
			}
			text = "";
			for (int i = 0; i < textNames.length; i++) {
				text += ReflectUtils.eval(o, textNames[i]) + " ";
			}
			outOptions(out, val, text, v);
		}
		outEnd(out);
		return SKIP_BODY;
	}

	private int doStringList(String str) throws JspException, IOException {
		List<Property> props = TagUtil.parseKvs(str);
		Object $value = TagUtil.getBean(pageContext, value);
		String v = $value != null ? $value.toString() : "";
		JspWriter out = pageContext.getOut();
		outBegin(out);
		for (Property prop : props) {
			outOptions(out, prop.getKey(), prop.getValue(), v);
		}
		outEnd(out);
		return SKIP_BODY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
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
	 * @return the ql
	 */
	public String getQl() {
		return ql;
	}

	/**
	 * @param ql
	 *            the ql to set
	 */
	public void setQl(String ql) {
		this.ql = ql;
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

}
