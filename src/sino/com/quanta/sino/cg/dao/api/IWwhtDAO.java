package com.quanta.sino.cg.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.WwhtTp;

/**
 * <p>
 * 采购合同数据访问接口
 * </p>
 * <p>
 * create: 2010-12-22 上午10:39:34
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public interface IWwhtDAO {
	/**
	 * <p>
	 * 新增未完合同
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(WwhtTp entity);

	/**
	 * <p>
	 * 批量保存采购合同
	 * </p>
	 * 
	 * @param entities
	 *            采购合同实体数组
	 */
	public void saveAll(List<WwhtTp> entities);

	/**
	 * <p>
	 * 更新未完合同
	 * </p>
	 * 
	 * @param entity
	 *            采购合同实体
	 */
	public void update(WwhtTp entity);

	/**
	 * <p>
	 * 删除单个合同
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合主键
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 查询合同
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<WwhtTp> qentity);

	/**
	 * <p>
	 * 查询单个合同信息
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合主键
	 * @return WwhtTp
	 */
	public WwhtTp get(Serializable id);

	/**
	 * <p>
	 * 查询单个合同信息
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合主键
	 * @return WwhtTp
	 */
	public WwhtTp getRef(Serializable id);

}
