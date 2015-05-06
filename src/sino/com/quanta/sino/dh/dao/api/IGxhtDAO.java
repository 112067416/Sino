/**
 * 
 */
package com.quanta.sino.dh.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.GxhtTp;

/**
 * <p>
 * 购销合同数据访问接口
 * </p>
 * <p>
 * create: 2011-2-2 上午10:49:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IGxhtDAO {
	/**
	 * <p>
	 * 新增购销合同
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(GxhtTp entity);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<GxhtTp> entities);

	/**
	 * <p>
	 * 更新购销合同
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(GxhtTp entity);

	/**
	 * <p>
	 * 根据中文件编号删除对应的信息
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 查询购销合同
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<GxhtTp> qentity);

	/**
	 * <p>
	 * 获取购销合同
	 * </p>
	 * 
	 * @param id
	 * @return GxhtTp
	 */
	public GxhtTp get(Serializable id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param user
	 * @return GxhtTp
	 */
	public GxhtTp getByUser(String user);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isExisted(Serializable id);

}
