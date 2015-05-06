package com.coco.core.persistence;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.JpaTemplate;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.converter.Converter;
import com.coco.core.persistence.converter.DateConverter;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.ReflectUtils;
import com.coco.core.util.ReflectUtils.ClassType;

public class JpaDAO implements DAO {

	private static final Logger LOGGER = Logger.getLogger(JpaDAO.class.getName());

	/**
	 * ORDEY BY 起始正则
	 */
	private static final Pattern START_ORDER = Pattern.compile("^order\\s+by", Pattern.CASE_INSENSITIVE
			| Pattern.MULTILINE);

	/**
	 * IN(?)的分割正则
	 */
	private static final Pattern QL_IN_PARAM = Pattern.compile("\\sin\\s*\\(\\s*\\?\\s*\\)", Pattern.CASE_INSENSITIVE
			| Pattern.MULTILINE);

	protected JpaTemplate template;

	@Override
	public <T> void delete(T entity) {
		if (entity == null) {
			throw new CocoException(-10001);
		}
		template.remove(entity);
	}

	@Override
	public <T> T delete(Class<T> clazz, Serializable id) {
		if (clazz == null || id == null) {
			return null;
		}
		T entity = template.find(clazz, id);
		if (entity != null) {
			template.remove(entity);
			return entity;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		if (clazz == null) {
			return new ArrayList<T>();
		}
		return template.find("from " + clazz.getSimpleName());
	}

	@Override
	public <T> T getRef(Class<T> clazz, Serializable id) {
		if (clazz == null || id == null) {
			return null;
		}
		try {
			return template.getReference(clazz, id);
		}
		catch (Exception e) {
			// 没有找到则返回null
			return null;
		}
	}

	@Override
	public <T> T get(Class<T> clazz, Serializable id) {
		if (clazz == null || id == null) {
			return null;
		}
		try {
			return template.find(clazz, id);
			// T t = template.find(clazz, id);
			// if (t == null) {
			// return null;
			// }
			// T target = clazz.newInstance();
			// BeanUtils.copyProperties(t, target);
			// return target;
		}
		catch (Exception e) {
			// 没有找到则返回null
			return null;
		}
	}

	@Override
	public List<?> query(String ql) {
		if (ql == null) {
			return new ArrayList<Object>();
		}
		return template.find(ql);
	}

	@Override
	public <T> void save(T entity) {
		if (entity == null) {
			return;
		}
		template.persist(entity);

	}

	@Override
	public <T> void saveAll(List<T> entities) {
		if (entities == null || entities.isEmpty()) {
			return;
		}
		for (T entity : entities) {
			if (entity != null) {
				template.persist(entity);
			}
		}
	}

	@Override
	public <T> void update(T entity) {
		if (entity == null) {
			return;
		}
		template.merge(entity);
	}

	@Override
	public int executeUpdate(final String ql, final Object... values) {
		return template.execute(new JpaCallback<Integer>() {

			@Override
			public Integer doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em.createQuery(ql);
				if (values != null) {
					if (values.length == 1 && values[0].getClass().isArray()) {
						Object[] params = (Object[]) values[0];
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i + 1, params[i]);
						}
					}
					else {
						for (int i = 0; i < values.length; i++) {
							query.setParameter(i + 1, values[i]);
						}
					}
				}
				return query.executeUpdate();
			}

		});
	}

	@Override
	public List<?> query(String ql, Object... values) {
		return template.find(ql, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> find(Class<T> clazz, String clause, Object... values) {
		String ql = "from " + clazz.getSimpleName();
		if (clause != null && !(clause = clause.trim()).isEmpty()) {
			ql += (START_ORDER.matcher(clause).find() ? " " : " where ")
					+ clause;
		}
		return (List<T>) query(ql, values);
	}

	public JpaTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JpaTemplate template) {
		this.template = template;
	}

	@Override
	public Object getUnique(final String ql, final Object... values) {
		return template.execute(new JpaCallback<Object>() {

			@Override
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(ql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i + 1, values[i]);
					}
				}
				query.setMaxResults(1);
				List<?> list = query.getResultList();
				return list.isEmpty() ? null : list.get(0);
			}

		});
	}

	@Override
	public Object getUnique(final String ql) {
		return template.execute(new JpaCallback<Object>() {

			@Override
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(ql);
				query.setMaxResults(1);
				List<?> list = query.getResultList();
				return list.isEmpty() ? null : list.get(0);
			}

		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getUnique(Class<T> clazz, String clause, Object... values) {
		String ql = "from " + clazz.getSimpleName();
		if (clause != null && !(clause = clause.trim()).isEmpty()) {
			ql += (START_ORDER.matcher(clause).find() ? " " : " where ")
					+ clause;
		}
		return (T) getUnique(ql, values);
	}

	@SuppressWarnings("unchecked")
	private void setParams(Object bean, List<Object> condValues,
			StringBuilder clauses) {
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String fieldName;
		Object value = null;
		QF xfield;
		for (Field field : fields) {
			value = null;
			xfield = field.getAnnotation(QF.class);
			if (xfield == null) {
				continue;
			}
			fieldName = field.getName();
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
			}
			if (xfield.alias() != null && xfield.alias().length() > 0) {
				fieldName = xfield.alias();
			}
			EO opt = xfield.operator();
			if (opt == null || opt.equals(EO.EQ)) {
				clauses.append(" AND ").append(fieldName).append("=?");
				condValues.add(value);
			}
			else if (opt.equals(EO.NULL)) {
				if (value instanceof Boolean && (Boolean) value) {
					clauses.append(" AND ").append(fieldName).append(" is null");
				}
			}
			else if (opt.equals(EO.IS_NULL)) {
				if (value instanceof Boolean && (Boolean) value) {
					clauses.append(" AND ").append(fieldName).append(" is null");
				}
				else {
					clauses.append(" AND ").append(fieldName).append(" is not null");
				}
			}
			else if (opt.equals(EO.NOT_NULL)) {
				if (value instanceof Boolean && (Boolean) value) {
					clauses.append(" AND ").append(fieldName).append(" is not null");
				}
			}
			else if (opt.equals(EO.GE)) {
				clauses.append(" AND ").append(fieldName).append(">=?");
				condValues.add(value);
			}
			else if (opt.equals(EO.GE_NULL)) {
				clauses.append(" AND (").append(fieldName).append(">=?").append(" or ").append(fieldName).append(" is null)");
				condValues.add(value);
			}
			else if (opt.equals(EO.LE)) {
				clauses.append(" AND ").append(fieldName).append("<=?");
				condValues.add(value);
			}
			else if (opt.equals(EO.GT)) {
				clauses.append(" AND ").append(fieldName).append(">?");
				condValues.add(value);
			}
			else if (opt.equals(EO.LT)) {
				clauses.append(" AND ").append(fieldName).append("<?");
				condValues.add(value);
			}
			else if (opt.equals(EO.NE)) {
				clauses.append(" AND ").append(fieldName).append("<>?");
				condValues.add(value);
			}
			else if (opt.equals(EO.LIKE)) {
				if (value instanceof String) {
					clauses.append(" AND ").append(fieldName).append(" like ?");
					value = "%" + value + "%";
					condValues.add(value);
				}
			}
			else if (opt.equals(EO.NLIKE)) {
				if (value instanceof String) {
					clauses.append(" AND ").append(fieldName).append(" not like ?");
					value = "%" + value + "%";
					condValues.add(value);
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
					clauses.append(" AND ").append(fieldName).append("=?");
					condValues.add(values[0]);
					continue;
				}
				else if (values.length > 1) {
					clauses.append(" AND ").append(fieldName).append(" IN (");
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
							clauses.append(",");
						}
						isStart = false;
						clauses.append("?");
						condValues.add(values[i]);
					}
					clauses.append(")");
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
					clauses.append(" AND ").append(fieldName).append("<>?");
					condValues.add(values[0]);
				}
				else if (values.length > 1) {
					clauses.append(" AND ").append(fieldName).append(" NOT IN (");
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
							clauses.append(",");
						}
						isStart = false;
						clauses.append("?");
						condValues.add(values[i]);
					}
					clauses.append(")");
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
					clauses.append(" AND ").append(xfield.alias());
				}
				else if (values.length > 1) {
					String ql = xfield.alias();
					StringBuilder sb = new StringBuilder();
					sb.append("?");
					for (int i = 1; i < values.length; i++) {
						sb.append(",?");
					}
					ql = ql.replace("?", sb);
					clauses.append(" AND ").append(ql);
				}
				condValues.addAll(Arrays.asList(values));
			}
			else if (opt.equals(EO.VQL)) {
				clauses.append(" AND (").append(value).append(")");
			}
		}
	}

	@Override
	public <T> void query(final QEntity<T> qentity) {
		if (qentity == null) {
			return;
		}
		Class<?> clazz = qentity.getClass();
		Q q = clazz.getAnnotation(Q.class);
		if (q == null || q.value() != null && q.value().sql() != null
				&& !q.value().sql().isEmpty()) {
			return;
		}
		final Class<?> metaClass = ReflectUtils.fetchActualType(clazz);
		if (metaClass == null
				|| metaClass.equals(Object.class)
				|| ReflectUtils.classType(metaClass) != ReflectUtils.ClassType.object) {
			return;
		}
		final boolean isEntity = metaClass.getAnnotation(Entity.class) != null;

		// 查询元素
		final List<Field> resultFields = new ArrayList<Field>();
		QE qe;
		QF qf;
		String metaName;
		String entityName;
		String entityAlias;
		// 字段列表
		final StringBuffer metas = new StringBuffer();
		// jpql表实体串，即查询关联表
		StringBuilder jpqlFroms = new StringBuilder();

		if (q.entities() == null || q.entities().length == 0) {
			jpqlFroms.append(metaClass.getSimpleName());
		}
		else {
			String queryEntityAlias = null;
			for (int i = 0; i < q.entities().length; i++) {
				qe = q.entities()[i];
				entityName = qe.name() != null && !qe.name().isEmpty() ? qe.name()
						: qe.clazz().getSimpleName();
				entityAlias = qe.alias() != null && !qe.alias().isEmpty() ? qe.alias()
						: (entityName + "_");
				if (qe.clazz().equals(metaClass)) {
					queryEntityAlias = entityAlias;
				}
				if (i > 0) {
					jpqlFroms.append(",");
				}
				jpqlFroms.append(entityName).append(" ").append(entityAlias);
				if (qe.joinOn() != null && !qe.joinOn().isEmpty()) {
					jpqlFroms.append(" ").append(qe.joinOn());
				}
			}
			if (jpqlFroms.length() == 0) {
				return;
			}
			if (!isEntity) {
				List<Field> metaFields = ReflectUtils.fields(metaClass);
				for (Field metaField : metaFields) {
					qf = metaField.getAnnotation(QF.class);
					if (qf != null) {
						if (metas.length() > 0) {
							metas.append(",");
						}
						resultFields.add(metaField);
						if (qf.alias() != null
								&& (metaName = qf.alias().trim()).length() > 0) {
							metas.append(metaName);
						}
						else {
							metas.append(metaField.getName());
						}
					}
				}
				if (metas.length() == 0) {
					metas.append("0 none_");
				}
			}
			else {
				metas.append(queryEntityAlias);
			}
		}

		StringBuilder clauses = new StringBuilder();
		final List<Object> condValues = new ArrayList<Object>();

		this.setParams(qentity, condValues, clauses);

		final StringBuilder ql = new StringBuilder();
		ql.append(" from ");
		ql.append(jpqlFroms.toString());

		if (clauses.length() > 0) {
			ql.append(" where ");
			if (q.where() != null && !q.where().isEmpty()) {
				ql.append(q.where()).append(" ").append(clauses);
			}
			else {
				// 去掉前面的AND，5个字符
				ql.append(clauses.substring(5));
			}
		}
		else if (q.where() != null && !q.where().isEmpty()) {
			ql.append(" where ").append(q.where());
		}

		final StringBuilder sumFields = new StringBuilder();
		if (q.sums() != null && q.sums().length > 0) {
			sumFields.append(" sum(").append(q.sums()[0]).append(") as sum0");
			for (int i = 1; i < q.sums().length; i++) {
				sumFields.append(", sum(").append(q.sums()[i]).append(") as sum"
						+ i);
			}
			sumFields.append(" ");
		}
		template.execute(new JpaCallback<Void>() {
			@SuppressWarnings("unchecked")
			@Override
			public Void doInJpa(EntityManager em) throws PersistenceException {
				Query query = null;
				int size = qentity.getSize();
				boolean isPage = size > 0;

				if (sumFields.length() > 0) {
					String sumQl = "select " + sumFields + ql.toString();
					LOGGER.info(sumQl);
					query = em.createQuery(sumQl);
					for (int i = 0; i < condValues.size(); i++) {
						query.setParameter(i + 1, condValues.get(i));
					}
					Object result = query.getSingleResult();
					Number[] results = null;
					if (result.getClass().isArray()) {
						Object[] arr = (Object[]) result;
						results = new Number[arr.length];
						for (int i = 0; i < arr.length; i++) {
							results[i] = (Number) arr[i];
						}
						// results = (Number[]) result;
					}
					else {
						results = new Number[1];
						results[0] = (Number) result;
					}
					qentity.setSums(results);
				}

				if (isPage) {
					String countQl = "select count(*) as count_ "
							+ ql.toString();
					LOGGER.info(countQl);
					query = em.createQuery(countQl);
					for (int i = 0; i < condValues.size(); i++) {
						query.setParameter(i + 1, condValues.get(i));
					}

					Object count = query.getSingleResult();
					if (count instanceof Long) {
						qentity.setCount((Long) count);
					}
					else {
						qentity.setCount(Long.valueOf(count.toString()));
					}
				}
				else {
					size = QEntity.MAX_SIZE;
				}
				if (qentity.getIndex() < 0) {
					qentity.setIndex(0);
				}
				String listQl = metas.length() == 0 ? ql.toString() : "select "
						+ metas + ql.toString();
				if (qentity.getOrderBys() != null
						&& qentity.getOrderBys().trim().length() > 0) {
					listQl += " ORDER BY " + qentity.getOrderBys();
				}
				LOGGER.info(listQl);
				query = em.createQuery(listQl);
				if (isPage) {
					query.setFirstResult(qentity.getIndex() * size);
				}
				query.setMaxResults(size);
				for (int i = 0; i < condValues.size(); i++) {
					query.setParameter(i + 1, condValues.get(i));
				}
				if (isEntity) {
					qentity.setDatas(query.getResultList());
				}
				else {
					List<T> vos = new ArrayList<T>();
					List<Object[]> results = (List<Object[]>) query.getResultList();
					T vo;
					for (Object[] objs : results) {
						try {
							vo = (T) metaClass.newInstance();
						}
						catch (Exception e) {
							break;
						}
						if (vo == null) {
							continue;
						}
						vos.add(vo);
						for (int i = 0; i < objs.length; i++) {
							if (resultFields.size() > i) {
								ReflectUtils.putFieldValue(vo, resultFields.get(i), objs[i]);
							}
						}
					}
					qentity.setDatas(vos);
				}
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Serializable> getOffspring(final String ql,
			final Serializable id) {
		if (ql == null) {
			return new ArrayList<Serializable>();
		}
		final List<Serializable> ids = new ArrayList<Serializable>();
		template.execute(new JpaCallback<Void>() {

			@Override
			public Void doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(ql);
				recurse(query, id, 0);
				return null;
			}

			private void recurse(Query query, Serializable id, int deep) {
				if (deep > 100) {
					return;
				}
				query.setParameter(1, id);
				List<Serializable> children = query.getResultList();
				if (children != null && !children.isEmpty()) {
					for (Serializable child : children) {
						ids.add(child);
						recurse(query, child, deep + 1);
					}
				}
			}

		});
		return ids;
	}

	@Override
	public List<?> findForValues(final String ql, final Collection<?> values,
			final Object... params) {
		return template.execute(new JpaCallback<List<?>>() {

			@Override
			public List<?> doInJpa(EntityManager em)
					throws PersistenceException {
				return createInQuery(em, ql, values, params).getResultList();
			}
		});
	}

	@Override
	public int executeForValues(final String ql, final Collection<?> values,
			final Object... params) {
		return template.execute(new JpaCallback<Integer>() {

			@Override
			public Integer doInJpa(EntityManager em)
					throws PersistenceException {
				return createInQuery(em, ql, values, params).executeUpdate();
			}
		});
	}

	@Override
	public boolean isExisted(Class<?> clazz, Serializable id) {
		return template.find(clazz, id) != null;
	}

	@Override
	public boolean isExisted(final String ql, final Object... values) {
		return template.execute(new JpaCallback<Boolean>() {
			@Override
			public Boolean doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em.createQuery("select count(*) as count_ " + ql);
				int index = 1;
				for (Object value : values) {
					if (value.getClass().isArray()) {
						for (Object val : (Object[]) value) {
							query.setParameter(index++, val);
						}
					}
					else {
						query.setParameter(index++, value);
					}
				}
				Object obj = query.getSingleResult();
				if (obj instanceof Integer) {
					return ((Integer) obj).intValue() > 0;
				}
				else if (obj instanceof Long) {
					return ((Long) obj).longValue() > 0;
				}
				return false;
			}

		});
	}

	/**
	 * <p>
	 * 生成In的查询器
	 * </p>
	 * 
	 * @param em
	 * @param ql
	 * @param values
	 * @param params
	 * @return Query
	 */
	private Query createInQuery(EntityManager em, String ql,
			Collection<?> values, Object... params) {
		int paramIndex = indexInParam(ql);
		if (paramIndex < 0) {
			throw new CocoException(-1, "ql hasn't in!");
		}
		int size = values.size();
		if (size == 0) {
			throw new CocoException(-1, "collection is emppty!");
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" in (?");
		for (int i = 1; i < size; i++) {
			sb.append(",?");
		}
		sb.append(")");
		ql = QL_IN_PARAM.matcher(ql).replaceFirst(sb.toString());
		Query query = em.createQuery(ql);
		int index = 1, pi = 0;
		Object param = null;
		if (paramIndex > 0) {
			for (int i = pi; i < paramIndex; i++) {
				param = params[pi++];
				if (param == null) {
					query.setParameter(index++, null);
				}
				else {
					if (param.getClass().isArray()) {
						for (Object val : (Object[]) param) {
							query.setParameter(index++, val);
							i++;
						}
						i--;
					}
					else if (param instanceof Collection<?>) {
						for (Object val : (Collection<?>) param) {
							query.setParameter(index++, val);
							i++;
						}
						i--;
					}
					else {
						query.setParameter(index++, param);
					}
				}
			}
		}
		for (Object value : values) {
			query.setParameter(index++, value);
		}
		if (params != null) {
			for (int i = pi; i < params.length; i++) {
				param = params[pi++];
				if (param == null) {
					query.setParameter(index++, null);
				}
				else {
					if (param.getClass().isArray()) {
						for (Object val : (Object[]) param) {
							query.setParameter(index++, val);
							i++;
						}
						i--;
					}
					else if (param instanceof Collection<?>) {
						for (Object val : (Collection<?>) param) {
							query.setParameter(index++, val);
							i++;
						}
						i--;
					}
					else {
						query.setParameter(index++, param);
					}
				}
			}
		}
		return query;
	}

	private int indexInParam(String ql) {
		String[] qls = QL_IN_PARAM.split(ql);
		if (qls.length == 0) {
			return -1;
		}
		int count = 0;
		for (char ch : qls[0].toCharArray()) {
			if (ch == '?') count++;
		}
		return count;
	}

}
