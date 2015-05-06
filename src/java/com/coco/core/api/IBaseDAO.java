package com.coco.core.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 基本数据访问层接口
 * </p>
 * <p>
 * create: 2010-12-31 上午09:31:33
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IBaseDAO {

	/**
	 * <p>
	 * 保存数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Object entity);

	/**
	 * <p>
	 * 更新数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Object entity);

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
	public <T> T get(Class<T> clazz, Serializable id);

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
	public <T> T getRef(Class<T> clazz, Serializable id);

	/**
	 * <p>
	 * 删除数据
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 */
	public <T> void delete(Class<T> clazz, Serializable id);

	/**
	 * <p>
	 * 分页查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<?> qentity);

	/**
	 * <p>
	 * 直接查询语句
	 * </p>
	 * 
	 * @param ql
	 * @param values
	 * @return List<?>
	 */
	public List<?> query(String ql, Object... values);

	/**
	 * <p>
	 * 执行SQL
	 * </p>
	 * 
	 * @param ql
	 * @param values
	 * @return int
	 */
	public int executeUpdate(final String ql, final Object... values);

	/**
	 * <p>
	 * 一个条件多值查询，如：delete from TABLE where COLUMN in (?)
	 * </p>
	 * 
	 * @param ql
	 * @param values
	 * @param params
	 * @return int
	 */
	public int executeForValues(final String ql, final Collection<?> values,
			Object... params);

	/**
	 * <p>
	 * 根据条件获取唯一数据，当数据有多个时返回查询第一个
	 * </p>
	 * 
	 * @param ql
	 * @param values
	 * @return Object
	 */
	public Object getUnique(String ql, Object... values);
}
