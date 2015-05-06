package com.coco.report.api;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;

import com.coco.core.env.Helper;
import com.coco.report.bean.Entry;
import com.coco.report.bean.Module;
import com.coco.report.bean.Param;
import com.coco.report.bean.Value;

/**
 * <p>
 * 执行配置查询
 * </p>
 * <p>
 * create: 2010-12-21 上午10:33:49
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SqlExecuter {

	private static final Logger logger = Logger.getLogger(SqlExecuter.class.getName());

	public static final String IGNORE_PREFIX = "_";

	public static final int MAX_ROW = 5000;

	/**
	 * <p>
	 * 执行SQL模块
	 * </p>
	 * 
	 * @param entry
	 * @param conditions
	 * @return Map<String, Value>
	 */
	public static Map<String, Value> execute(final Entry entry,
			final Map<String, String> conditions) {

		final Map<String, Value> values = new HashMap<String, Value>();
		if (entry == null || entry.getModules() == null) {
			return values;
		}
		Helper.getBean(JdbcTemplate.class).execute(new StatementCallback<Object>() {
			@Override
			public Object doInStatement(Statement stmt) throws SQLException,
					DataAccessException {
				Iterator<Module> modules = entry.getModules().values().iterator();
				Module module = null;
				Value value = null;
				Map<String, Object> vm = null;
				List<Object> vl = null;
				while (modules.hasNext()) {
					module = modules.next();
					String sql = parseSql(module, entry.getParams(), conditions);
					stmt.setMaxRows(MAX_ROW);
					logger.info("JDBC SQL : " + sql);
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					int count = rsmd.getColumnCount();
					String[] names = new String[count];
					for (int i = 0; i < count; i++) {
						names[i] = rsmd.getColumnName(i + 1);
						if (names[i].startsWith(IGNORE_PREFIX)) {
							names[i] = null;
						}
					}
					value = new Value(module);
					value.setGroupNames(module.getGroupNames());
					while (rs.next()) {
						vm = new HashMap<String, Object>();
						vl = new ArrayList<Object>();
						for (int i = 0; i < count; i++) {
							if (names[i] == null) {
								continue;
							}
							Object o = rs.getObject(i + 1);
							vm.put(names[i], o);
							vl.add(o);
						}
						value.addValue(vm, vl);
					}
					values.put(module.getName(), value);
				}
				return null;
			}
		});

		return values;
	}

	private static String parseSql(Module module, List<Param> params,
			Map<String, String> conditions) {
		Map<String, StringBuffer> clauses = new HashMap<String, StringBuffer>();
		String name, column, area, v, v1;
		StringBuffer clause;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (params != null && params.size() > 0 && conditions != null
				&& conditions.size() > 0) {
			for (Param param : params) {
				name = param.getColumn();
				if (name.indexOf("#") == 0) {
					int dot = name.indexOf(".");
					if (dot == -1) {
						area = "1";
						column = name.substring(1);
					}
					else {
						area = name.substring(1, dot);
						column = name.substring(dot + 1);
					}
				}
				else {
					area = "1";
					column = name;
				}
				clause = clauses.get(area);
				if (clause == null) {
					clause = new StringBuffer();
					clauses.put(area, clause);
				}
				v = conditions.get(name);
				v1 = conditions.get(name + "_end");
				String valAppend = param.getValAppend();
				if (valAppend == null) {
					valAppend = "";
				}
				// 因为中日达项目要求统计时间从8时起，时间查询从0:00增加到8:00
				if ("date".equals(param.getType())) {
					if (!valAppend.isEmpty()) {
						valAppend = " " + valAppend;
					}
					if ("between".equals(param.getOpt())) {
						// 如果区间查询只录一个值的情况
						String $v11 = null, $v12 = null, $v21 = null, $v22 = null;
						if (v != null && !(v = v.trim()).isEmpty()) {
							try {
								Date date = sdf.parse(v);
								Calendar cal = Calendar.getInstance();
								cal.setTime(date);
								cal.add(Calendar.DAY_OF_MONTH, 1);
								// 补上8时
								$v12 = sdf.format(cal.getTime());
								$v11 = v;
							}
							catch (ParseException e) {
							}
						}
						if (v1 != null && !(v1 = v1.trim()).isEmpty()) {
							try {
								Date date = sdf.parse(v1);
								Calendar cal = Calendar.getInstance();
								cal.setTime(date);
								cal.add(Calendar.DAY_OF_MONTH, 1);
								$v22 = sdf.format(cal.getTime());
								$v21 = v1;
							}
							catch (ParseException e) {
							}
						}
						// 当只填第一个值的情况
						if ($v11 != null && $v21 == null) {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							clause.append(column).append(">='").append($v11).append(valAppend).append("' and ").append(column).append("<'").append($v12).append(valAppend).append("'");
						}
						// 当只填第二个的情况
						else if ($v11 == null && $v21 != null) {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							clause.append(column).append(">='").append($v21).append(valAppend).append("' and ").append(column).append("<'").append($v22).append(valAppend).append("'");
						}
						// 当第一个和第二个都填的情况
						else if ($v11 != null && $v21 != null) {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							clause.append(column).append(">='").append($v11).append(valAppend).append("' and ").append(column).append("<'").append($v22).append(valAppend).append("'");
						}
						continue;
					}
					if (v == null || v.isEmpty()) {
						continue;
					}
					try {
						Date date = sdf.parse(v);
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1);
						v1 = sdf.format(cal.getTime());

						if (clause.length() > 0) {
							clause.append(" and ");
						}
						clause.append(column).append(">='").append(v).append(valAppend).append("' and ");
						clause.append(column).append("<'").append(v1).append(valAppend).append("'");
					}
					catch (ParseException e) {
					}
					continue;
				}
				else if ("int".equals(param.getType())) {
					if ("between".equals(param.getOpt())) {
						String $v1 = null, $v2 = null;
						if (v != null && !(v = v.trim()).isEmpty()) {
							$v1 = v;
						}
						if (v1 != null && !(v1 = v1.trim()).isEmpty()) {
							$v2 = v1;
						}
						// 当只填第一个值的情况
						if ($v1 != null && $v2 == null) {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							clause.append(column).append("=").append($v1);
						}
						// 当只填第二个的情况
						else if ($v1 == null && $v2 != null) {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							clause.append(column).append("=").append($v2);
						}
						// 当第一个和第二个都填的情况
						else if ($v1 != null && $v2 != null) {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							clause.append(column).append(">=").append($v1).append(" and ").append(column).append("<=").append($v2);
						}
						continue;
					}
					if (v == null || (v = v.trim()).isEmpty()) {
						continue;
					}
					if (clause.length() > 0) {
						clause.append(" and ");
					}
					clause.append(column);
					if ("in".equals(param.getOpt())) {
						clause.append(" in (").append(v).append(")");
						continue;
					}
					else if ("not in".equals(param.getOpt())) {
						clause.append(" not in (").append(v).append(")");
						continue;
					}
					if ("gt".equals(param.getOpt())) {
						clause.append(">");
					}
					else if ("lt".equals(param.getOpt())) {
						clause.append("<");
					}
					else if ("ge".equals(param.getOpt())) {
						clause.append(">=");
					}
					else if ("le".equals(param.getOpt())) {
						clause.append("<=");
					}
					else {
						clause.append("=");
					}
					clause.append(v);
					continue;
				}
				else {
					String jointColumn = param.getJointColumn();
					boolean isJoint = param.isJointed();
					String joint1, joint2, $joint1 = null, $joint2 = null;
					if (jointColumn != null && !jointColumn.isEmpty()) {
						joint1 = conditions.get(jointColumn);
						$joint1 = joint1 != null ? joint1.trim() : null;
						joint2 = conditions.get(jointColumn + "_end");
						$joint2 = joint2 != null ? joint2.trim() : null;
					}
					if ("between".equals(param.getOpt())) {
						String $v1 = null, $v2 = null;
						if (v != null && !(v = v.trim()).isEmpty()) {
							$v1 = v;
							if (isJoint && $joint1 != null) {
								$v1 = $v1 + $joint1;
							}
						}
						if (v1 != null && !(v1 = v1.trim()).isEmpty()) {
							$v2 = v1;
							if (isJoint && $joint2 != null) {
								$v2 = $v2 + $joint2;
							}
						}
						// 当只填第一个值的情况
						if ($v1 != null && $v2 == null) {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							if (!isJoint && $joint1 != null) {
								clause.append(column).append("='").append($v1).append("'").append(" and ").append(jointColumn).append("='").append($joint1).append("'");
							}
							else if (isJoint && $joint1 != null) {
								clause.append(column).append("='").append($v1).append("'");
							}
							else {
								clause.append(column).append("='").append($v1).append("'");
							}
						}
						// 当只填第二个的情况
						else if ($v1 == null && $v2 != null) {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							if (!isJoint && $joint2 != null) {
								clause.append(column).append("<='").append($v2).append("'").append(" and ").append(jointColumn).append("<='").append($joint2).append("'");
							}
							else if (isJoint && $joint2 != null) {
								clause.append(column).append("<='").append($v2).append("'");
							}
							else {
								clause.append(column).append("<='").append($v2).append("'");
							}
						}
						// 当第一个和第二个都填的情况
						else if ($v1 != null && $v2 != null) {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							if (!isJoint && $joint1 != null) {
								clause.append(column).append(">='").append($v1).append("'").append(" and ").append(jointColumn).append(">='").append($joint1).append("'");
							}
							else {
								clause.append(column).append(">='").append($v1).append("'");
							}
							clause.append(" and ");
							if (!isJoint && $joint2 != null) {
								clause.append(column).append("<='").append($v2).append("'").append(" and ").append(jointColumn).append("<='").append($joint2).append("'");
							}
							else {
								clause.append(column).append("<='").append($v2).append("'");
							}
						}
						continue;
					}
					if (v == null || (v = v.trim()).isEmpty()) {
						continue;
					}
					if ($joint1 != null) {
						if (isJoint) {
							v = v + $joint1;
						}
						else {
							if (clause.length() > 0) {
								clause.append(" and ");
							}
							clause.append(jointColumn).append("='").append($joint1).append("'");
						}
					}
					if (clause.length() > 0) {
						clause.append(" and ");
					}
					clause.append(column);
					if ("in".equals(param.getOpt())) {
						clause.append(" in ('").append(v.replaceAll(",", "','")).append("')");
						continue;
					}
					else if ("not in".equals(param.getOpt())) {
						clause.append(" not in ('").append(v.replaceAll(",", "','")).append("')");
						continue;
					}
					if (param.getOpt() != null && !param.getOpt().isEmpty()
							&& param.getOpt().indexOf("like") >= 0) {
						// 控制是左模糊查询，还是右模糊查询
						if (param.getOpt().indexOf("%") == 0) {
							clause.append(" like '%").append(v).append("'");
						}
						else if (param.getOpt().indexOf("%") > 0) {
							clause.append(" like '").append(v).append("%'");
						}
						else {
							clause.append(" like '%").append(v).append("%'");
						}
					}
					else {
						clause.append("='").append(v).append("'");
					}
					continue;
				}
			}
		}
		String sql = module.getSql();
		if (clauses.size() > 0) {
			Iterator<String> areas = clauses.keySet().iterator();
			while (areas.hasNext()) {
				area = areas.next();
				clause = clauses.get(area);
				if (clause.length() > 0) {
					sql = sql.replaceAll("#" + area + "#", " " + clause);
				}
			}
		}
		sql = sql.replaceAll("\\s\\w+#\\d+#", " ");
		return sql;
	}

	/**
	 * <p>
	 * 执行变量值
	 * </p>
	 * 
	 * @param entry
	 * @param conditions
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> executeForVarible(final Entry entry,
			final Map<String, String> conditions) {
		final Map<String, Object> values = new HashMap<String, Object>();
		if (entry == null || entry.getVariable() == null
				|| entry.getVariable().length() == 0) {
			return values;
		}
		Helper.getBean(JdbcTemplate.class).execute(new StatementCallback<Object>() {
			@Override
			public Object doInStatement(Statement stmt) throws SQLException,
					DataAccessException {
				String sql = entry.getVariable();
				if (conditions != null) {
					Iterator<String> it = conditions.keySet().iterator();
					String name;
					while (it.hasNext()) {
						name = it.next();
						sql = sql.replaceAll("#" + name + "#", conditions.get(name));
					}
				}
				sql = sql.replaceAll("#.+?#(\\s+,|\\s+)", " ");
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				if (rs.next()) {
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						values.put(rsmd.getColumnName(i), rs.getObject(i));
					}
				}
				rs.close();
				return null;
			}
		});
		return values;
	}
}
