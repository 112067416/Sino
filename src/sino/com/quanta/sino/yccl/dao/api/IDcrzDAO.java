package com.quanta.sino.yccl.dao.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Dcrz;

/**
 * <p>
 * 堆场日志表
 * </p>
 * <p>
 * create: 2011-1-25 上午11:55:14
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public interface IDcrzDAO {
	/**
	 * <p>
	 * 新增堆场日志表
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Dcrz entity);

	/**
	 * <p>
	 * 查询堆场日志表
	 * </p>
	 * 
	 * @param qentity
	 */

	public void query(QEntity<Dcrz> qentity);

	/**
	 * <p>
	 * 删除堆场日志表
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 获取堆场日志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public Dcrz get(Serializable id);

	/**
	 * <p>
	 * 更新堆场日志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public void update(Dcrz entity);

}
