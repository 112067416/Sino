package com.quanta.sino.ch.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.TsTp;

/**
 * <p>
 * 投诉记录数据访问接口
 * </p>
 * <p>
 * create: 2011-1-18 下午06:55:15
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ITsDAO {

	/**
	 * <p>
	 * 新增投诉记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(TsTp entity);

	/**
	 * <p>
	 * 修改制品状态
	 * </p>
	 * 
	 * @param ids
	 * @param stat
	 */
	public void updateStat(List<String> ids, String stat);

	/**
	 * <p>
	 * 更新投诉记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(TsTp entity);

	/**
	 * <p>
	 * 根据主键删除对应的投诉记录
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 查询投诉记录
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<TsTp> qentity);

	/**
	 * <p>
	 * 获取投诉记录
	 * </p>
	 * 
	 * @param id
	 * @return TsTp
	 */
	public TsTp get(Serializable id);

	/**
	 * <p>
	 * 由制品号和出货单号获取投诉记录
	 * </p>
	 * 
	 * @param jbno
	 * @param shno
	 * @param stat
	 * @return TsTp
	 */
	public TsTp getTsTp(String jbno, String shno, String stat);

}
