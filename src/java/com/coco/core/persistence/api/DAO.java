package com.coco.core.persistence.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 数据访问接口
 * </p>
 * <p>
 * create: 2010-12-10 上午11:34:17
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface DAO {

	/**
	 * 根据主键获取对应的实体信息引用，该实体信息由容器托管，其属性更改将同步到数据库
	 * 
	 * @param <T>
	 * @param clazz
	 *            Class<T> 实体类
	 * @param id
	 *            Serializable 实体主键
	 * @return T
	 */
	public <T> T getRef(Class<T> clazz, Serializable id);

	/**
	 * 根据主键获取对应的实体信息
	 * 
	 * @param <T>
	 * @param clazz
	 *            Class<T> 实体类
	 * @param id
	 *            Serializable 实体主键
	 * @return T
	 */
	public <T> T get(Class<T> clazz, Serializable id);

	/**
	 * <p>
	 * 根据条件获取唯一数据，当数据有多个时返回查询第一个
	 * </p>
	 * 
	 * @param ql
	 *            String
	 * @param values
	 *            Object...
	 * @return Object
	 */
	public Object getUnique(String ql, Object... values);

	/**
	 * <p>
	 * 根据条件获取唯一数据，当数据有多个时返回查询第一个
	 * </p>
	 * 
	 * @param ql
	 *            String
	 * @return Object
	 */
	public Object getUnique(String ql);

	/**
	 * <p>
	 * 根据条件获取唯一数据，当数据有多个时返回查询第一个
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 *            Class<T>
	 * @param clause
	 *            String
	 * @param values
	 *            Object...
	 * @return T
	 */
	public <T> T getUnique(Class<T> clazz, String clause, Object... values);

	/**
	 * 查询所有指定实体的列表
	 * 
	 * @param <T>
	 * @param clazz
	 *            Class<T>
	 * @return List<T>
	 */
	public <T> List<T> findAll(Class<T> clazz);

	/**
	 * 根据分页条件获取对应的分页信息和列表
	 * 
	 * @param <T>
	 * @param qentity
	 *            QEntity<T>
	 */
	public <T> void query(QEntity<T> qentity);

	/**
	 * <p>
	 * 新增实体对象
	 * </p>
	 * 
	 * @param <T>
	 * @param entity
	 *            T
	 */
	public <T> void save(T entity);

	/**
	 * <p>
	 * 更新实体对象
	 * </p>
	 * 
	 * @param <T>
	 * @param entity
	 *            T
	 */
	public <T> void update(T entity);

	/**
	 * <p>
	 * 删除实体对象
	 * </p>
	 * 
	 * @param <T>
	 * @param entity
	 *            T
	 */
	public <T> void delete(T entity);

	/**
	 * <p>
	 * 删除指定ID实体对象
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 *            Class<T>
	 * @param id
	 *            Serializable
	 * @return T
	 */
	public <T> T delete(Class<T> clazz, Serializable id);

	/**
	 * <p>
	 * 保存多个数据
	 * </p>
	 * 
	 * @param <T>
	 * @param entities
	 *            List<T>
	 */
	public <T> void saveAll(List<T> entities);

	/**
	 * <p>
	 * 直接查询语句
	 * </p>
	 * 
	 * @param ql
	 *            String
	 * @return List<?>
	 */
	public List<?> query(String ql);

	/**
	 * <p>
	 * 直接查询语句
	 * </p>
	 * 
	 * @param ql
	 *            String
	 * @param values
	 *            Object...
	 * @return List<?>
	 */
	public List<?> query(String ql, Object... values);

	/**
	 * <p>
	 * 直接查询单一对象
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 *            Class<T>
	 * @param clause
	 *            String
	 * @param values
	 *            Object...
	 * @return List<T>
	 */
	public <T> List<T> find(Class<T> clazz, String clause, Object... values);

	/**
	 * <p>
	 * 获取指定ID的所有子系
	 * </p>
	 * 
	 * @param ql
	 *            String
	 * @param id
	 *            Serializable
	 * @return List<Serializable>
	 */
	public List<Serializable> getOffspring(String ql, Serializable id);

	/**
	 * <p>
	 * 执行SQL
	 * </p>
	 * 
	 * @param ql
	 *            String
	 * @param params
	 *            Object...
	 * @return int
	 */
	public int executeUpdate(String ql, Object... params);

	/**
	 * <p>
	 * 一个条件多值查询，如： from TABLE where COLUMN in (?)
	 * </p>
	 * 
	 * @param ql
	 *            String
	 * @param values
	 *            Collection<?>
	 * @param params
	 *            Object...
	 * @return List<?>
	 */
	public List<?> findForValues(String ql, Collection<?> values,
			Object... params);

	/**
	 * <p>
	 * 一个条件多值查询，如：delete from TABLE where COLUMN in (?)
	 * </p>
	 * 
	 * @param ql
	 *            String
	 * @param values
	 *            Collection<?>
	 * @param params
	 *            Object...
	 * @return int
	 */
	public int executeForValues(String ql, Collection<?> values,
			Object... params);

	/**
	 * 检查实体是否存在
	 * 
	 * @param clazz
	 *            Class<?> 实体类名
	 * @param id
	 *            Serializable 主键值
	 * @return boolean
	 */
	public boolean isExisted(Class<?> clazz, Serializable id);

	/**
	 * <p>
	 * 检查数据是否存在
	 * </p>
	 * 
	 * @param ql
	 *            String ql查询语句，from以后，例如 from User where id=?
	 * @param values
	 *            Object... 参数列表，根据ql提供的?传入对应个数的参数
	 * @return boolean
	 */
	public boolean isExisted(String ql, Object... values);

}
