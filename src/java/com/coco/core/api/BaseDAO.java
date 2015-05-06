package com.coco.core.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 基本数据访问层
 * </p>
 * <p>
 * create: 2010-12-31 上午09:31:33
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class BaseDAO implements IBaseDAO {

	private DAO dao;

	/**
	 * <p>
	 * 保存数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Object entity) {
		dao.save(entity);
	}

	/**
	 * <p>
	 * 更新数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Object entity) {
		dao.update(entity);
	}

	/**
	 * <p>
	 * 获取数据
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return T
	 */
	public <T> T get(Class<T> clazz, Serializable id) {
		return dao.get(clazz, id);
	}

	/**
	 * <p>
	 * 获取数据引用
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return T
	 */
	public <T> T getRef(Class<T> clazz, Serializable id) {
		return dao.getRef(clazz, id);
	}

	/**
	 * <p>
	 * 删除数据
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 */
	public <T> void delete(Class<T> clazz, Serializable id) {
		dao.delete(clazz, id);
	}

	/**
	 * <p>
	 * 分页查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<?> qentity) {
		dao.query(qentity);
	}

	@Override
	public List<?> query(String ql, Object... values) {
		return dao.query(ql, values);
	}

	@Override
	public int executeUpdate(String ql, Object... values) {
		return dao.executeUpdate(ql, values);
	}

	@Override
	public int executeForValues(String ql, Collection<?> values,
			Object... params) {
		return dao.executeForValues(ql, values, params);
	}

	@Override
	public Object getUnique(String ql, Object... values) {
		return dao.getUnique(ql, values);
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
}
