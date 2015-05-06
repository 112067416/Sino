package com.quanta.sino.yccl.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Jlbg;

/**
 * <p>
 * 现品信息变更日志表
 * </p>
 * <p>
 * create: 2011-2-9 上午11:42:20
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public interface IJlbgDAO {
	/**
	 * <p>
	 * 新增变更日志表
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Jlbg entity);

	/**
	 * <p>
	 * 批量新增变更日志表
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<Jlbg> entities);

	/**
	 * <p>
	 * 查询变更志表
	 * </p>
	 * 
	 * @param qentity
	 */

	public void query(QEntity<Jlbg> qentity);

	/**
	 * <p>
	 * 删除变更志表
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 获取变更志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public Jlbg get(Serializable id);

	/**
	 * <p>
	 * 更新变更志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public void update(Jlbg entity);

}
