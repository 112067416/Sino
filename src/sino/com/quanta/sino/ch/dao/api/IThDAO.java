package com.quanta.sino.ch.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ThTp;

/**
 * <p>
 * 退货记录数据访问接口
 * </p>
 * <p>
 * create: 2011-1-20 下午10:17:54
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IThDAO {

	/**
	 * <p>
	 * 新增退货记录
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<ThTp> entities);

	/**
	 * <p>
	 * 新增退货记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(ThTp entity);

	/**
	 * <p>
	 * 更新退货记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(ThTp entity);

	/**
	 * <p>
	 * 根据主键删除对应的退货记录
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 查询退货记录
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ThTp> qentity);

	/**
	 * <p>
	 * 获取退货记录
	 * </p>
	 * 
	 * @param id
	 * @return ThTp
	 */
	public ThTp get(Serializable id);

	/**
	 * <p>
	 * 获取退货信息列表
	 * </p>
	 * 
	 * @param ids
	 * @return List[ThTp]
	 */
	public List<ThTp> find(List<String> ids);

	/**
	 * <p>
	 * 修改退货状态
	 * </p>
	 * 
	 * @param id
	 * @param stat
	 */
	public void updateById(Serializable id, String stat);
}
