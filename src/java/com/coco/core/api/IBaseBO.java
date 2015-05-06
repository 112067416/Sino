package com.coco.core.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 基本业务接口
 * </p>
 * <p>
 * create: 2010-12-31 上午09:39:40
 * </p>
 * 
 * @param <T>
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IBaseBO<T> {

	/**
	 * <p>
	 * 保存数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * <p>
	 * 更新数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * <p>
	 * 获取数据
	 * </p>
	 * 
	 * @param id
	 * @return T
	 */
	public T get(Serializable id);

	/**
	 * <p>
	 * 删除数据
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 分页查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<T> qentity);

}
