package com.quanta.sino.fxs.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.FxzyRz;

/**
 * <p>
 * 分析作业日志表
 * </p>
 * <p>
 * create: 2010-12-29 下午03:20:41
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IFxzyrzDAO {

	/**
	 * 新增分析作业日志表
	 * 
	 * @param entity
	 */
	public void save(FxzyRz entity);

	/**
	 * 更新分析作业日志表
	 * 
	 * @param entity
	 */
	public void update(FxzyRz entity);

	/**
	 * 查询分析作业日志表
	 * 
	 * @param qentity
	 */
	public void query(QEntity<FxzyRz> qentity);

	/**
	 * <p>
	 * 查询分析作业日志表
	 * </p>
	 * 
	 * @param ids
	 * @return List<FxzyRz>
	 */
	public List<FxzyRz> find(List<String> ids);

	/**
	 * 获取分析作业日志表
	 * 
	 * @param id
	 * @return FxzyRz
	 */
	public FxzyRz get(Serializable id);

	/**
	 * 设定删除标记为1
	 * <p>
	 * </p>
	 * 
	 * @param ids
	 */
	public void delete(List<String> ids);

}
