package com.quanta.sino.zkfp.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZkfpCwjl;

/**
 * <p>
 * 分配错误记录数据访问接口
 * </p>
 * <p>
 * create: 2011-1-21 下午03:41:47
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ICwjlDAO {

	/**
	 * 新增分配错误记录
	 * 
	 * @param entity
	 */
	public void save(ZkfpCwjl entity);

	/**
	 * 批量新增分配错误记录
	 * 
	 * @param entities
	 */
	public void saveAll(List<ZkfpCwjl> entities);

	/**
	 * 更新分配错误记录
	 * 
	 * @param entity
	 */
	public void update(ZkfpCwjl entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询分配错误记录
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZkfpCwjl> qentity);

	/**
	 * 获取分配错误记录
	 * 
	 * @param id
	 * @return ZkfpCwjl
	 */
	public ZkfpCwjl get(Serializable id);

}
