package com.quanta.sino.fxs.dao.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Chpdb;

/**
 * <p>
 * 高耐蚀性马口铁出荷判定表(#75以上)数据访问接口
 * </p>
 * <p>
 * create: 2011-4-17 下午05:33:42
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IChpdbDAO {

	/**
	 * 新增马口铁出荷判定
	 * 
	 * @param entity
	 */
	public void save(Chpdb entity);

	/**
	 * 更新马口铁出荷判定
	 * 
	 * @param entity
	 */
	public void update(Chpdb entity);

	/**
	 * 根据ID删除马口铁出荷判定
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询马口铁出荷判定
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Chpdb> qentity);

	/**
	 * 获取马口铁出荷判定
	 * 
	 * @param id
	 * @return Chpdb
	 */
	public Chpdb get(Serializable id);

	/**
	 * <p>
	 * 批量删除 出货判定信息
	 * </p>
	 * 
	 * @param ids
	 */
	public void deletes(String[] ids);

	/**
	 * <p>
	 * 判断是否存在出货判定信息
	 * </p>
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isExisted(Serializable id);

}
