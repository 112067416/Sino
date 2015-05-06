package com.quanta.sino.yccl.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Zyrz;

public interface IZyrzDAO {
	/**
	 * <p>
	 * 新增作业日志表
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Zyrz entity);

	/**
	 * <p>
	 * 查询作业日志表
	 * </p>
	 * 
	 * @param qentity
	 */

	public void query(QEntity<Zyrz> qentity);

	/**
	 * <p>
	 * 删除作业日志表
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 删除作业日志表
	 * </p>
	 * 
	 * @param jbnos
	 */
	public void deleteByJbno(List<String> jbnos);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbnos
	 * @param zylx
	 */
	public void deleteByJbno(List<String> jbnos, String zylx);

	/**
	 * <p>
	 * 获取作业日志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public Zyrz get(Serializable id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbno
	 * @param zylx
	 * @return Zyrz
	 */
	public Zyrz get(String jbno, String zylx);

	/**
	 * <p>
	 * 更新作业日志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public void update(Zyrz entity);

}
