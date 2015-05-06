package com.coco.sys.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.ISourceDAO;
import com.coco.sys.orm.SourceClass;
import com.coco.sys.orm.SourceMethod;

public class SourceDAO implements ISourceDAO {

	private DAO dao;

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.ISourceDAO#save(com.coco.sys.orm.SourceClass)
	 */
	@Override
	public void save(SourceClass entity) {
		dao.save(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.coco.sys.dao.api.ISourceDAO#upadate(com.coco.sys.orm.SourceClass)
	 */
	@Override
	public void update(SourceClass entity) {
		dao.update(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.ISourceDAO#save(com.coco.sys.orm.SourceMethod)
	 */
	@Override
	public void save(SourceMethod entity) {
		dao.save(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.coco.sys.dao.api.ISourceDAO#update(com.coco.sys.orm.SourceMethod)
	 */
	@Override
	public void update(SourceMethod entity) {
		dao.update(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.ISourceDAO#delete(java.io.Serializable)
	 */
	@Override
	public void delete(Serializable id) {
		SourceClass sourceClass = dao.get(SourceClass.class, id);
		if (sourceClass == null) {
			return;
		}
		List<String> ids = new ArrayList<String>();
		if (sourceClass.getMethods() != null) {
			for (SourceMethod method : sourceClass.getMethods()) {
				ids.add(method.getId());
			}
		}
		if (!ids.isEmpty()) {
			dao.executeForValues("delete RoleSource where source.id in (?)", ids);
		}
		dao.delete(sourceClass);
	}

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.ISourceDAO#get(java.io.Serializable)
	 */
	@Override
	public SourceClass get(Serializable id) {
		return dao.get(SourceClass.class, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.ISourceDAO#findAll(boolean)
	 */
	@Override
	public List<SourceClass> findAll() {
		return dao.find(SourceClass.class, "order by className");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.coco.sys.dao.api.ISourceDAO#query(com.coco.core.persistence.query
	 * .QEntity)
	 */
	@Override
	public void query(QEntity<SourceClass> qentity) {
		dao.query(qentity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.ISourceDAO#getMethod(java.io.Serializable)
	 */
	@Override
	public SourceMethod getMethod(Serializable id) {
		return dao.get(SourceMethod.class, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.ISourceDAO#deleteMethod(java.io.Serializable)
	 */
	@Override
	public void deleteMethod(Serializable id) {
		dao.executeUpdate("delete RoleSource where source.id=?", id);
		dao.delete(SourceMethod.class, id);
	}

	@Override
	public SourceMethod getUniqueMethod(String className, String method) {
		return (SourceMethod) dao.getUnique("from SourceMethod where sourceClass.className=? and method=?", className, method);
	}

	/**
	 * @return the dao
	 */
	public DAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.ISourceDAO#checkExisted(java.lang.String,
	 * java.util.List)
	 */
	@Override
	public boolean checkExisted(String methodId, List<String> roleIds) {
		StringBuilder ql = new StringBuilder();
		ql.append("from RoleSource where source.id=? and role.id in (?");
		int size = roleIds.size();
		for (int i = 1; i < size; i++) {
			ql.append(",?");
		}
		ql.append(")");
		return dao.isExisted(ql.toString(), methodId, roleIds.toArray());
	}
}
