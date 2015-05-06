package com.coco.core.persistence;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.api.ISqlDAO;
import com.coco.core.persistence.converter.Converter;
import com.coco.core.persistence.converter.DateConverter;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.core.util.ReflectUtils.ClassType;

/**
 * <p>
 * 原生SQL访问实现
 * </p>
 * <p>
 * create: 2011-1-24 下午05:18:12
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SqlDAO implements ISqlDAO {

	private static final Logger LOGGER = Logger.getLogger(SqlDAO.class.getName());

	private static final Pattern SQL_CLAUSE_AREA = Pattern.compile("(where|and)#\\d+#", Pattern.CASE_INSENSITIVE);

	private JdbcTemplate template;

	@Override
	public <T> void query(final QEntity<T> qentity) {
		if (qentity == null) {
			return;
		}
		Class<?> clazz = qentity.getClass();
		Q q = clazz.getAnnotation(Q.class);
		if (q == null) {
			return;
		}
		if (q.value() == null || q.value().sql() == null
				|| q.value().sql().isEmpty()) {
			return;
		}
		final Class<?> metaClass = ReflectUtils.fetchActualType(clazz);
		if (ClassType.object != ReflectUtils.classType(metaClass)) {
			return;
		}
		String sql = q.value().sql();
		String meta = q.value().meta();
		if (meta == null || meta.isEmpty()) {
			meta = "*";
		}
		Map<String, StringBuilder> clauses = new HashMap<String, StringBuilder>();
		Map<String, List<Object>> condValues = new HashMap<String, List<Object>>();
		this.setParams(qentity, condValues, clauses);
		Matcher m = null;
		String group, whereAnd, area;
		int index;
		StringBuilder clause = null;
		List<Object> condValue = null;
		final List<Object> values = new ArrayList<Object>();
		while ((m = SQL_CLAUSE_AREA.matcher(sql)).find()) {
			group = m.group();
			index = group.indexOf('#');
			whereAnd = group.substring(0, index);
			area = group.substring(index + 1, group.length() - 1);
			clause = clauses.get(area);
			if (clause == null) {
				sql = m.replaceFirst("");
			}
			else {
				// 去掉前面的AND，5个字符
				sql = m.replaceFirst(whereAnd + " " + clause.substring(5));
				condValue = condValues.get(area);
				if (condValue != null) {
					values.addAll(condValue);
				}
			}
		}
		final String $sql = sql;
		final String $meta = meta;
		template.execute(new ConnectionCallback<Void>() {

			@SuppressWarnings("unchecked")
			@Override
			public Void doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				String sql = null;
				int size = qentity.getSize();
				boolean isPage = size > 0;
				int index = 1;
				PreparedStatement pstmt = null;
				ResultSet rs;
				if (isPage) {
					sql = "select count(*) " + $sql;
					LOGGER.info(sql);
					pstmt = conn.prepareStatement(sql);
					index = 1;
					for (Object value : values) {
						pstmt.setObject(index++, value);
					}
					rs = pstmt.executeQuery();
					if (rs.next()) {
						qentity.setCount(rs.getLong(1));
					}
					rs.close();
					pstmt.close();
				}
				else {
					size = QEntity.MAX_SIZE;
				}
				if (qentity.getIndex() < 0) {
					qentity.setIndex(0);
				}
				sql = "select " + $meta + " " + $sql;
				if (qentity.getOrderBys() != null
						&& qentity.getOrderBys().trim().length() > 0) {
					sql += " ORDER BY " + qentity.getOrderBys();
				}
				LOGGER.info(sql);
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				index = 1;
				for (Object value : values) {
					pstmt.setObject(index++, value);
				}
				pstmt.setMaxRows((qentity.getIndex() + 1) * size);
				rs = pstmt.executeQuery();
				List<String> fieldNames = new ArrayList<String>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int rsmdCount = rsmd.getColumnCount();
				String metaName;
				String fieldName;
				for (int i = 0; i < rsmdCount; i++) {
					metaName = rsmd.getColumnName(i + 1);
					fieldName = ReflectUtils.formatFieldName(metaName);
					fieldNames.add(fieldName);
				}

				if (isPage) {
					rs.absolute(qentity.getIndex() * size);
				}
				rs.setFetchSize(size);
				List<T> vos = new ArrayList<T>();
				T vo;
				while (rs.next()) {
					try {
						vo = (T) metaClass.newInstance();
						vos.add(vo);
					}
					catch (Exception e) {
						break;
					}
					for (int i = 0; i < rsmdCount; i++) {
						fieldName = fieldNames.get(i);
						if (fieldName != null) {
							ReflectUtils.putPropertyValue(vo, fieldName, rs.getString(i + 1));
						}
					}
				}
				qentity.setDatas(vos);

				return null;
			}

		});
	}

	@SuppressWarnings("unchecked")
	private void setParams(Object bean, Map<String, List<Object>> condValues,
			Map<String, StringBuilder> clauses) {
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String fieldName;
		Object value = null;
		QF xfield;
		String area;
		List<Object> condValue;
		StringBuilder clause;
		for (Field field : fields) {
			value = null;
			xfield = field.getAnnotation(QF.class);
			if (xfield == null) {
				continue;
			}
			value = ReflectUtils.fieldValue(bean, field);
			if (value == null) {
				continue;
			}
			if (value instanceof String) {
				value = ((String) value).trim();
				if (((String) value).isEmpty()) {
					continue;
				}
			}
			else if (value instanceof Date) {
				Converter<Date> converter = new DateConverter();
				value = converter.convert((Date) value, xfield);
				value = java.sql.Date.valueOf(DateUtils.format((Date) value, "yyyy-MM-dd"));
			}
			if (xfield.alias() != null && xfield.alias().length() > 0) {
				fieldName = xfield.alias();
			}
			else {
				fieldName = field.getName().toUpperCase() + "_";
			}
			area = xfield.area() <= 0 ? "1" : String.valueOf(xfield.area());
			condValue = condValues.get(area);
			clause = clauses.get(area);
			if (condValue == null) {
				condValue = new ArrayList<Object>();
				clause = new StringBuilder();
				condValues.put(area, condValue);
				clauses.put(area, clause);
			}

			EO opt = xfield.operator();
			if (opt == null || opt.equals(EO.EQ)) {
				clause.append(" AND ").append(fieldName).append("=?");
				condValue.add(value);
			}
			else if (opt.equals(EO.NULL)) {
				if (value instanceof Boolean && (Boolean) value) {
					clause.append(" AND ").append(fieldName).append(" is null");
				}
			}
			else if (opt.equals(EO.IS_NULL)) {
				if (value instanceof Boolean && (Boolean) value) {
					clause.append(" AND ").append(fieldName).append(" is null");
				}
				else {
					clause.append(" AND ").append(fieldName).append(" is not null");
				}
			}
			else if (opt.equals(EO.NOT_NULL)) {
				if (value instanceof Boolean && (Boolean) value) {
					clause.append(" AND ").append(fieldName).append(" is not null");
				}
			}
			else if (opt.equals(EO.GE)) {
				clause.append(" AND ").append(fieldName).append(">=?");
				condValue.add(value);
			}
			else if (opt.equals(EO.GE_NULL)) {
				clause.append(" AND (").append(fieldName).append(">=?").append(" or ").append(fieldName).append(" is null)");
				condValue.add(value);
			}
			else if (opt.equals(EO.LE)) {
				clause.append(" AND ").append(fieldName).append("<=?");
				condValue.add(value);
			}
			else if (opt.equals(EO.GT)) {
				clause.append(" AND ").append(fieldName).append(">?");
				condValue.add(value);
			}
			else if (opt.equals(EO.LT)) {
				clause.append(" AND ").append(fieldName).append("<?");
				condValue.add(value);
			}
			else if (opt.equals(EO.NE)) {
				clause.append(" AND ").append(fieldName).append("<>?");
				condValue.add(value);
			}
			else if (opt.equals(EO.LIKE)) {
				if (value instanceof String) {
					clause.append(" AND ").append(fieldName).append(" like ?");
					value = "%" + value + "%";
					condValue.add(value);
				}
			}
			else if (opt.equals(EO.IN)) {
				ClassType type = ReflectUtils.classType(value.getClass());
				Object[] values = null;
				if (type == ClassType.array) {
					values = (Object[]) value;
				}
				else if (type == ClassType.list) {
					values = ((Collection<Object>) value).toArray();
				}
				if (values.length == 1) {
					if (values[0] instanceof String
							&& ((String) values[0]).length() == 0) {
						continue;
					}
					clause.append(" AND ").append(fieldName).append("=?");
					condValue.add(values[0]);
					continue;
				}
				else if (values.length > 1) {
					clause.append(" AND ").append(fieldName).append(" IN (");
					boolean isStart = true;
					for (int i = 0; i < values.length; i++) {
						if (values[i] == null) {
							continue;
						}
						if (values[i] instanceof String
								&& ((String) values[i]).length() == 0) {
							continue;
						}
						if (!isStart) {
							clause.append(",");
						}
						isStart = false;
						clause.append("?");
						condValue.add(values[i]);
					}
					clause.append(")");
				}
			}
			else if (opt.equals(EO.NOT_IN)) {
				ClassType type = ReflectUtils.classType(value.getClass());
				Object[] values = null;
				if (type == ClassType.array) {
					values = (Object[]) value;
				}
				else if (type == ClassType.list) {
					values = ((Collection<Object>) value).toArray();
				}
				if (values.length == 1) {
					if (values[0] instanceof String
							&& ((String) values[0]).length() == 0) {
						continue;
					}
					clause.append(" AND ").append(fieldName).append("<>?");
					condValue.add(values[0]);
				}
				else if (values.length > 1) {
					clause.append(" AND ").append(fieldName).append(" NOT IN (");
					boolean isStart = true;
					for (int i = 0; i < values.length; i++) {
						if (values[i] == null) {
							continue;
						}
						if (values[i] instanceof String
								&& ((String) values[i]).length() == 0) {
							continue;
						}
						if (!isStart) {
							clause.append(",");
						}
						isStart = false;
						clause.append("?");
						condValue.add(values[i]);
					}
					clause.append(")");
				}
			}
			else if (opt.equals(EO.QL)) {
				ClassType type = ReflectUtils.classType(value.getClass());
				Object[] values = null;
				if (type == ClassType.array) {
					values = (Object[]) value;
				}
				else if (type == ClassType.list) {
					values = ((Collection<Object>) value).toArray();
				}
				else {
					values = new Object[1];
					values[0] = value;
				}
				if (values.length == 1) {
					if (values[0] instanceof String
							&& ((String) values[0]).length() == 0) {
						continue;
					}
					clause.append(" AND ").append(xfield.alias());
				}
				else if (values.length > 1) {
					String ql = xfield.alias();
					StringBuilder sb = new StringBuilder();
					sb.append("?");
					for (int i = 1; i < values.length; i++) {
						sb.append(",?");
					}
					ql = ql.replace("?", sb);
					clause.append(" AND ").append(ql);
				}
				condValue.addAll(Arrays.asList(values));
			}
		}
	}

	/**
	 * @return the template
	 */
	public JdbcTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

}
