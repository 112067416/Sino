/**
 * 
 */
package com.quanta.sino.yygl.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.SczsTp;

/**
 * <p>
 * 生产指示单数据访问接口
 * </p>
 * <p>
 * create: 2011-2-2 上午10:49:49
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ISczsDAO {
	/**
	 * <p>
	 * 新增生产指示
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(SczsTp entity);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<SczsTp> entities);

	/**
	 * <p>
	 * 更新生产指示
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(SczsTp entity);

	/**
	 * <p>
	 * 根据中文件编号删除对应的信息
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void delete(List<String> ids);

	/**
	 * <p>
	 * 查询生产指示
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<SczsTp> qentity);

	/**
	 * <p>
	 * 获取生产指示
	 * </p>
	 * 
	 * @param id
	 * @return SczsTp
	 */
	public SczsTp get(Serializable id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param ids
	 * @return
	 */
	public List<SczsTp> find(List<String> ids);

	/**
	 * <p>
	 * 修改状态
	 * </p>
	 * 
	 * @param stat
	 * @param ids
	 */
	public void updateStat(String stat, String[] ids);

}
