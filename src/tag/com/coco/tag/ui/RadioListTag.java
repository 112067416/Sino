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

import com.coco.core.bean.Property;
import com.coco.core.env.Helper;
import com.coco.core.util.ReflectUtils;
import com.coco.tag.TagUtil;

public class RadioListTag extends BodyTagSupport {

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

	protected String attributes;

	protected String onclick;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	private void out(JspWriter out, String val, String text, String v)
			throws IOException {
		out.print("<span style=\"height:20\"><input type=\"radio\" xu.type=\"radiolist\" name=\"");
		out.print(name);
		out.print("\" value=\"");
		out.print(val.replaceAll("\"", "\\\\\""));
		out.print("\" ");
		if (v.equals(val)) {
			out.print("checked ");
		}
		if (attributes != null) {
			out.print(attributes);
		}
		if (onclick != null) {
			out.print(" onclick=\"" + onclick.replaceAll("\"", "\\\\\"") + "\"");
		}
		out.print("/>");
		out.print(text);
		out.print("&nbsp;&nbsp;</span>");
	}

	@Override
	public int doStartTag() throws JspException {
		if (list != null && !list.isEmpty()) {
			try {
				return doList(list);
			}
			catch (IOException e) {
				return SKIP_BODY;
			}
		}
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
			while (rs.next()) {
				val = columns > 0 ? rs.getString(1) : "";
				text = columns > 1 ? rs.getString(2) : "";
				if (val == null) {
					continue;
				}
				out(out, val, text, v);
			}
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
			out(out, val, text, v);
		}
		return SKIP_BODY;
	}

	private int doStringList(String str) throws JspException, IOException {
		List<Property> props = TagUtil.parseKvs(str);
		if (props.isEmpty()) {
			return SKIP_BODY;
		}
		Object $value = TagUtil.getBean(pageContext, value);
		String v = $value != null ? $value.toString() : "";
		JspWriter out = pageContext.getOut();
		for (Property prop : props) {
			out(out, prop.getKey(), prop.getValue(), v);
		}
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

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
}
