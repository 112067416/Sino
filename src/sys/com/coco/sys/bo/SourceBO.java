package com.coco.sys.bo;

import java.io.Serializable;
import java.util.List;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.ISourceBO;
import com.coco.sys.dao.api.ISourceDAO;
import com.coco.sys.orm.SourceClass;
import com.coco.sys.orm.SourceMethod;

public class SourceBO implements ISourceBO {

	private ISourceDAO dao;

	@Override
	public void save(SourceClass entity) {
		if (dao.get(entity.getClassName()) != null) {
			throw new CocoException(-10003, "该类名已经定义");
		}
		dao.save(entity);
	}

	@Override
	public void update(SourceClass entity) {
		dao.update(entity);
	}

	@Override
	public void save(SourceMethod entity) {
		SourceMethod existedEntity = dao.getUniqueMethod(entity.getSourceClass().getClassName(), entity.getMethod());
		if (existedEntity != null) {
			throw new CocoException(-10003, "该方法名已经定义");
		}
		entity.setId(null);
		dao.save(entity);
	}

	@Override
	public void update(SourceMethod entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public SourceClass get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public List<SourceClass> findAll() {
		return dao.findAll();
	}

	@Override
	public void query(QEntity<SourceClass> qentity) {
		dao.query(qentity);
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(ISourceDAO dao) {
		this.dao = dao;
	}

	/**
	 * @return the dao
	 */
	public ISourceDAO getDao() {
		return dao;
	}

	@Override
	public void deleteMethod(Serializable id) {
		dao.deleteMethod(id);
	}

	@Override
	public SourceMethod getUniqueMethod(String className, String method) {
		return dao.getUniqueMethod(className, method);
	}

	@Override
	public int checkSource(String className, String method, List<String> roleIds) {
		SourceMethod source = dao.getUniqueMethod(className, method);
		if (source == null) {
			return 2;
		}
		boolean isExisted = dao.checkExisted(source.getId(), roleIds);
		return isExisted ? 1 : 0;
	}
}
