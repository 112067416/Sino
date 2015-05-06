package com.quanta.sino.zk.dao.api;

import java.io.Serializable;
import java.util.Date;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Etlyygljl;

/**
 * <p>
 * ETL药液管理记录
 * </p>
 * <p>
 * create: 2011-1-14 下午05:42:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IEtlyygljlDAO {

	/**
	 * <p>
	 * 新增ETL药液管理记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Etlyygljl entity);

	/**
	 * <p>
	 * 更新ETL药液管理记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Etlyygljl entity);

	/**
	 * <p>
	 * 查询ETL药液管理记录
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Etlyygljl> qentity);

	/**
	 * <p>
	 * 获取ETL药液管理记录
	 * </p>
	 * 
	 * @param id
	 * @return Etlyygljl
	 */
	public Etlyygljl get(Serializable id);

	/**
	 * <p>
	 * 根据日期获取ETL药液管理记录
	 * </p>
	 * 
	 * @param jlsj
	 * @return Etlyygljl
	 */
	public Etlyygljl getByJlsj(Date jlsj);

	/**
	 * <p>
	 * 根据ID判断ETL药液管理记录是否存在
	 * </p>
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isExisted(String id);

}
