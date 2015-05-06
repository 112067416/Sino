package com.quanta.sino.etl.dao.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.DmLp;
import com.quanta.sino.orm.Dmgs;

/**
 * <p>
 * 木工数据访问接口
 * </p>
 * <p>
 * create: 2011-1-20 下午05:35:37
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IMgDAO {
	/**
	 * <p>
	 * 取垫木库存
	 * </p>
	 * 
	 * @param kuan
	 * @param cang
	 * @return
	 */
	public Dmgs getUnique(Double kuan, Double cang, String dmfx, String kbao,
			String kw);

	/**
	 * <p>
	 * 保存日志
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Dmgs entity);

	/**
	 * 查询
	 * <p>
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Dmgs> qentity);

	/**
	 * <p>
	 * 取端板
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public Dmgs get(Serializable id);

	/**
	 * <p>
	 * 更新
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Dmgs entity);

	/**
	 * <p>
	 * 保存日志
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveDmlp(DmLp entity);

	/**
	 * <p>
	 * 删除
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);
}
