package com.quanta.sino.zk.bo.api;

import java.io.Serializable;
import java.util.Date;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Etlyygljl;
import com.quanta.sino.orm.EtlyygljlItem;

/**
 * <p>
 * ETL药液管理接口
 * </p>
 * <p>
 * create: 2011-5-1 下午03:14:57
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IEtlyyglBO {

	/**
	 * <p>
	 * 查询Etl药液品质管理记录 查询早上8点到第二天8点之间的数据
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<EtlyygljlItem> qentity);

	/**
	 * <p>
	 * 保存Etl药液品质管理记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void updateYyglItem(EtlyygljlItem entity);

	/**
	 * <p>
	 * 保存Etl药液管理记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Etlyygljl entity);

	/**
	 * <p>
	 * 保存Etl药液管理记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Etlyygljl entity);

	/**
	 * <p>
	 * 保存Etl药液管理明细记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveItem(EtlyygljlItem entity);

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
	 * 根据 分析室的药液分析ID,删除药液管理明细记录
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteByYyfxid(Serializable id);

	/**
	 * <p>
	 * 根据 分析室的药液分析ID,获取药液管理明细记录
	 * </p>
	 * 
	 * @param id
	 * @return EtlyygljlItem
	 */
	public EtlyygljlItem getByYyfxid(Serializable id);

	/**
	 * <p>
	 * 根据 分析室的药液分析ID,获得药液管理明细记录
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public EtlyygljlItem getItem(Serializable id);

	/**
	 * <p>
	 * 更新是否已读状态
	 * </p>
	 * 
	 * @param id
	 * @param readed
	 */
	public void read(Serializable id, boolean readed);

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
}
