package com.quanta.sino.fxs.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.FxzyRzYcsx;

/**
 * <p>
 * 分析作业日志异常事项表数据访问接口
 * </p>
 * <p>
 * create: 2011-4-9 下午04:53:23
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IFxzyRzYcsxDAO {

	/**
	 * 新增分析作业日志异常事项表
	 * 
	 * @param entity
	 */
	public void save(FxzyRzYcsx entity);

	/**
	 * <p>
	 * 批量新增分析作业日志异常事项表
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<FxzyRzYcsx> entities);

	/**
	 * 更新分析作业日志异常事项表
	 * 
	 * @param entity
	 */
	public void update(FxzyRzYcsx entity);

	/**
	 * <p>
	 * 批量修改分析作业日志异常事项表
	 * </p>
	 * 
	 * @param entities
	 */
	public void updateAll(List<FxzyRzYcsx> entities);

	/**
	 * 查询分析作业日志异常事项表
	 * 
	 * @param qentity
	 */
	public void query(QEntity<FxzyRzYcsx> qentity);

	/**
	 * <p>
	 * 查询分析作业日志的异常事项
	 * </p>
	 * 
	 * @param pid
	 *            分析作业日志的主键
	 * @return List<FxzyRzYcsx>
	 */
	public List<FxzyRzYcsx> find(String pid);

	/**
	 * 获取分析作业日志异常事项表
	 * 
	 * @param id
	 * @return FxzyRzYcsx
	 */
	public FxzyRzYcsx get(Serializable id);

	/**
	 * <p>
	 * 删除分析作业日志异常事项表
	 * </p>
	 * 
	 * @param ids
	 */
	public void delete(List<String> pids);

	/**
	 * <p>
	 * 删除分析作业日志异常事项表
	 * </p>
	 * 
	 * @param pid
	 */
	public void deleteByPid(String pid);

	/**
	 * <p>
	 * 判断分析作业日志是否有异常事项
	 * </p>
	 * 
	 * @param pid
	 * @return boolean
	 */
	public boolean isFxzyRzYcsx(String pid);
}
