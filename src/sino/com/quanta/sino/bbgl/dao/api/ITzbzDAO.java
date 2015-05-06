package com.quanta.sino.bbgl.dao.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Tzbz;

/**
 * <p>
 * 台帐备注数据访问接口
 * </p>
 * <p>
 * create: 2011-8-31 上午11:02:07
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ITzbzDAO {

	/**
	 * <p>
	 * 新增台帐备注
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Tzbz entity);

	/**
	 * <p>
	 * 更新台帐备注
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Tzbz entity);

	/**
	 * <p>
	 * 删除单个台帐备注
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 查询台帐备注
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Tzbz> qentity);

	/**
	 * <p>
	 * 查询单个台帐备注信息
	 * </p>
	 * 
	 * @param id
	 * @return Tzbz
	 */
	public Tzbz get(Serializable id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param xpzk
	 * @param spbh
	 * @param ny
	 * @return Tzbz
	 */
	public Tzbz get(String xpzk, String spbh, String ny, String bbm);

}
