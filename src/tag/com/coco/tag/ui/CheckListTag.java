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
import com.coco.core.util.StringUtils;
import com.coco.tag.TagUtil;

/**
 * <p>
 * 复选框列表
 * </p>
 * <p>
 * create: 2010-12-27 上午10:58:32
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class CheckListTag extends BodyTagSupport {
	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String name;

	protected String sql;

	protected String ql;

	protected String list;

	protected String listValue;

	protected String listText;

	protected String scope;

	protected String value;

	protected String serp;

	protected String attributes;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	private void out(JspWriter out, String val, String text, String v)
			throws IOException {
		out.print("<span style=\"height:20\" style=\"white-space:nowrap;\"><input type=\"checkbox\" xu.type=\"checklist\" name=\"");
		out.print(name);
		out.print("\" value=\"");
		out.print(StringUtils.toJsString(val));
		out.print("\" ");
		if (v != null && v.equals(val)) {
			out.print("checked ");
		}
		if (attributes != null) {
			out.print(attributes);
		}
		out.print("/>");
		out.print(text);
		out.print("&nbsp;&nbsp;</span>");
	}

	@Override
	public int doStartTag() throws JspException {
		if (list != null && !list.isEmpty()) {
			return doList(list);
		}
		if (ql != null && !ql.isEmpty()) {
			return doQl();
		}
		return doSql();
	}

	private int doList(String list) throws JspException {
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
		if (serp == null || serp.length() == 0) {
			serp = ",";
		}
		Object $value = TagUtil.getBean(pageContext, value);
		String v = $value != null ? $value.toString() : null;
		if (v != null) {
			v = serp + v + serp;
		}
		else {
			v = "";
		}
		JspWriter out = pageContext.getOut();
		try {
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
		}
		catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	private int doSql() throws JspException {
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
			if (serp == null || serp.length() == 0) {
				serp = ",";
			}
			Object $value = TagUtil.getBean(pageContext, value);
			String v = $value != null ? $value.toString() : null;
			if (v != null) {
				v = serp + v + serp;
			}
			else {
				v = "";
			}
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
			throw new JspException(ex);
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

	private int doQl() throws JspException {
		IBaseDAO dao = Helper.getBean(IBaseDAO.class);
		List<?> items = dao.query(ql);
		if (items == null || items.isEmpty()) {
			return SKIP_BODY;
		}
		try {
			if (serp == null || serp.length() == 0) {
				serp = ",";
			}
			Object $value = TagUtil.getBean(pageContext, value);
			String v = $value != null ? $value.toString() : null;
			if (v != null) {
				v = serp + v + serp;
			}
			else {
				v = "";
			}
			JspWriter out = pageContext.getOut();
			Object val, text;
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
				out(out, val.toString(), text.toString(), v);
			}
		}
		catch (Exception ex) {
			throw new JspException(ex);
		}
		return SKIP_BODY;
	}

	private int doStringList(String str) throws JspException {
		List<Property> props = TagUtil.parseKvs(str);
		if (props.isEmpty()) {
			return SKIP_BODY;
		}
		Object $value = TagUtil.getBean(pageContext, value);
		String v = $value != null ? $value.toString() : null;
		JspWriter out = pageContext.getOut();
		try {
			for (Property prop : props) {
				out(out, prop.getKey(), prop.getValue(), v);
			}
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

	public String getSerp() {
		return serp;
	}

	public void setSerp(String serp) {
		this.serp = serp;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
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

}
