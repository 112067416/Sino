package com.quanta.sino.yccl.dao.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Xpxx;

/**
 * 现品信息日志表
 * <p>
 * </p>
 * <p>
 * create: 2011-1-18 下午04:08:43
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public interface IXpxxDAO {

	/**
	 * <p>
	 * 新增现品信息日志表
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Xpxx entity);

	/**
	 * <p>
	 * 查询现品信息日志表
	 * </p>
	 * 
	 * @param qentity
	 */

	public void query(QEntity<Xpxx> qentity);

	/**
	 * <p>
	 * 删除现品信息日志表
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 获取现品信息日志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public Xpxx get(Serializable id);

	/**
	 * <p>
	 * 更新现品信息日志表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public void update(Xpxx entity);

}
