package com.quanta.sino.fxs.dao.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YyfxjgJl;

/**
 * <p>
 * 药液分析结果记录表
 * </p>
 * <p>
 * create: 2010-12-29 下午03:17:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IYyfxjgjlDAO {
	/**
	 * 新增药液分析结果记录表
	 * 
	 * @param entity
	 */
	public void save(YyfxjgJl entity);

	/**
	 * 更新药液分析结果记录表
	 * 
	 * @param entity
	 */
	public void update(YyfxjgJl entity);

	/**
	 * 根据ID删除药液分析结果记录表
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询药液分析结果记录表
	 * 
	 * @param qentity
	 */
	public void query(QEntity<YyfxjgJl> qentity);

	/**
	 * 获取药液分析结果记录表
	 * 
	 * @param id
	 * @return YyfxjgJl
	 */
	public YyfxjgJl get(Serializable id);

}
