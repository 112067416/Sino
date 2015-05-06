package com.quanta.sino.yccl.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Ylrz;

public interface IYlrzDAO {

	/**
	 * <p>
	 * 新增业连日志表
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Ylrz entity);

	/**
	 * <p>
	 * 批量业连日志表
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<Ylrz> entities);

	/**
	 * <p>
	 * 查询业连日志表
	 * </p>
	 * 
	 * @param qentity
	 */

	public void query(QEntity<Ylrz> qentity);

	/**
	 * <p>
	 * 删除业连日志表
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 获取业连日志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public Ylrz get(Serializable id);

	/**
	 * <p>
	 * 更新业连日志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public void update(Ylrz entity);

}
