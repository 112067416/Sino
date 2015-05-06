package com.coco.core.mvc.cascade.executer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.coco.core.mvc.cascade.api.ICascadeExecuter;
import com.coco.core.mvc.cascade.bean.SqlField;
import com.coco.core.mvc.cascade.bean.SqlModule;
import com.coco.core.util.StringUtils;

/**
 * <p>
 * SQL级联执行器。sql的写法如select value_ from table where key=?，只允许一个参数条件
 * </p>
 * <p>
 * create: 2010-12-23 上午10:40:59
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SqlCascadeExecuter implements ICascadeExecuter {

	private static final Logger LOG = Logger.getLogger(SqlCascadeExecuter.class.getName());

	/**
	 * 数据库连接池
	 */
	private DataSource dataSource;

	/**
	 * 模块，[模块名,sql]
	 */
	private Map<String, SqlModule> modules;

	/**
	 * <p>
	 * 执行级联查询
	 * </p>
	 * 
	 * @param module
	 *            模块
	 * @param value
	 *            传入查询条件值
	 * @return String
	 */
	@Override
	public String execute(String module, String value) {
		if ((modules == null || modules.isEmpty())) {
			return "{}";
		}
		SqlModule sqlModule = modules.get(module);
		if (sqlModule == null) {
			return "{}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		Connection conn = null;
		String v = null;
		boolean hasField = false;
		try {
			conn = dataSource.getConnection();
			if (sqlModule.getAll() != null) {
				v = sqlModule.getAll().isSelect() ? getOptions(conn, sqlModule.getAll().getSql(), value)
						: getValue(conn, sqlModule.getAll().getSql(), value);
				hasField = true;
				if (v == null) {
					if (sqlModule.getAll().isSelect()) {
						sb.append("$:[\"\",1]");
					}
					else {
						sb.append("$:[\"\"]");
					}
				}
				else {
					if (sqlModule.getAll().isSelect()) {
						sb.append("$:[\"").append(StringUtils.toJsString(v)).append("\",1]");
					}
					else {
						sb.append("$:[\"").append(StringUtils.toJsString(v)).append("\"]");
					}
				}
			}
			if (sqlModule.getFields() != null
					&& !sqlModule.getFields().isEmpty()) {
				List<SqlField> fieldSqls = sqlModule.getFields();
				if (fieldSqls != null && !fieldSqls.isEmpty()) {
					for (SqlField fieldSql : fieldSqls) {
						v = fieldSql.isSelect() ? getOptions(conn, fieldSql.getSql(), value)
								: getValue(conn, fieldSql.getSql(), value);
						if (hasField) {
							sb.append(",");
						}
						else {
							hasField = true;
						}
						if (v == null) {
							if (fieldSql.isSelect()) {
								sb.append(fieldSql.getField()).append(":[\"\",1]");
							}
							else {
								sb.append(fieldSql.getField()).append(":[\"\"]");
							}
						}
						else {
							if (fieldSql.isSelect()) {
								sb.append(fieldSql.getField()).append(":[\"").append(StringUtils.toJsString(v)).append("\",1]");
							}
							else {
								sb.append(fieldSql.getField()).append(":[\"").append(StringUtils.toJsString(v)).append("\"]");
							}
						}
					}
				}
			}
		}
		catch (SQLException e) {
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
				}
			}
		}
		sb.append("}");
		return sb.toString();
	}

	private String getValue(Connection conn, String sql, String value) {
		String v = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			if (value == null) {
				pstmt.setNull(1, Types.VARCHAR);
			}
			else {
				pstmt.setString(1, value);
			}
			LOG.info(sql + " : " + value);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				v = rs.getString(1);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e) {
		}
		return v;
	}

	private String getOptions(Connection conn, String sql, String value) {
		StringBuilder options = new StringBuilder();
		String v, t;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			if (value == null) {
				pstmt.setNull(1, Types.VARCHAR);
			}
			else {
				pstmt.setString(1, value);
			}
			LOG.info(sql + " : " + value);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				v = rs.getString(1);
				t = rs.getString(2);
				if (v == null) {
					continue;
				}
				v = StringUtils.toJsString(v);
				if (t == null) {
					t = "";
				}
				options.append("<option value=\"").append(v).append("\">").append(t).append("</option>");
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e) {
		}
		return options.toString();
	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return the modules
	 */
	public Map<String, SqlModule> getModules() {
		return modules;
	}

	/**
	 * @param modules
	 *            the modules to set
	 */
	public void setModules(Map<String, SqlModule> modules) {
		this.modules = modules;
	}

}
