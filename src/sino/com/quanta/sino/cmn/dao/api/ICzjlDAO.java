package com.quanta.sino.cmn.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZkfpCzjl;

/**
 * <p>
 * 分配和余材操作记录数据访问接口
 * </p>
 * <p>
 * create: 2011-1-14 下午02:01:01
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ICzjlDAO {

	/**
	 * 新增制品余材操作记录
	 * 
	 * @param entity
	 */
	public void save(ZkfpCzjl entity);

	/**
	 * 批量新增制品余材操作记录
	 * 
	 * @param entities
	 */
	public void saveAll(List<ZkfpCzjl> entities);

	/**
	 * 更新制品余材操作记录
	 * 
	 * @param entity
	 */
	public void update(ZkfpCzjl entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询制品余材操作记录
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ZkfpCzjl> qentity);

	/**
	 * 获取制品余材操作记录
	 * 
	 * @param id
	 * @return ZkfpCzjl
	 */
	public ZkfpCzjl get(Serializable id);

}
