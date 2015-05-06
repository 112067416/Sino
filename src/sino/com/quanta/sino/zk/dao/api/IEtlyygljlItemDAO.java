package com.quanta.sino.zk.dao.api;

import java.io.Serializable;
import java.util.Date;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.EtlyygljlItem;

/**
 * <p>
 * ETL药液管理记录——从表
 * </p>
 * <p>
 * create: 2011-1-14 下午05:48:37
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IEtlyygljlItemDAO {

	/**
	 * <p>
	 * 新增ETL药液管理记录——从表
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(EtlyygljlItem entity);

	/**
	 * <p>
	 * 更新ETL药液管理记录——从表
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(EtlyygljlItem entity);

	/**
	 * <p>
	 * 查询ETL药液管理记录——从表
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<EtlyygljlItem> qentity);

	/**
	 * <p>
	 * 获取ETL药液管理记录——从表
	 * </p>
	 * 
	 * @param id
	 * @return EtlyygljlItem
	 */
	public EtlyygljlItem get(Serializable id);

	/**
	 * <p>
	 * 根据id判断记录是否存在
	 * </p>
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isExisted(String id);

	/**
	 * <p>
	 * 判断记录是否存在新的药液分析记录
	 * </p>
	 * 
	 * @param rqBegin
	 * @param rqEnd
	 * @return boolean
	 */
	public boolean isExistedNew(Date rqBegin, Date rqEnd);

	/**
	 * <p>
	 * 根据 分析室的药液分析ID,获取药液管理记录
	 * </p>
	 * 
	 * @param id
	 * @return EtlyygljlItem
	 */
	public EtlyygljlItem getByYyfxid(Serializable id);

	/**
	 * <p>
	 * 根据 分析室的药液分析ID,删除药液管理明细记录
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteByYyfxid(Serializable id);

	/**
	 * <p>
	 * 更新是否已读状态
	 * </p>
	 * 
	 * @param id
	 * @param readed
	 */
	public void read(Serializable id, boolean readed);

}
