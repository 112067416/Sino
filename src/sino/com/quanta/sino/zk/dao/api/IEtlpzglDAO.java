package com.quanta.sino.zk.dao.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.EtlpzGl;

/**
 * <p>
 * ETL品质管理表
 * </p>
 * <p>
 * create: 2011-1-10 下午03:15:53
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IEtlpzglDAO {
	/**
	 * <p>
	 * 新增分析作业日志表
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(EtlpzGl entity);

	/**
	 * <p>
	 * 判断对应的记录是否存在
	 * </p>
	 * 
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExisted(String jbno);

	/**
	 * <p>
	 * 更新ETL品质管理表
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(EtlpzGl entity);

	/**
	 * <p>
	 * 查询ETL品质管理表
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<EtlpzGl> qentity);

	/**
	 * <p>
	 * 获取ETL品质管理表
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	public EtlpzGl get(Serializable id);

	/**
	 * <p>
	 * 根据卷号，获得ETL品质管理记录
	 * </p>
	 * 
	 * @param jbno
	 * @return
	 */
	public EtlpzGl getByJbno(String jbno);

	/**
	 * <p>
	 * 删除ETL品质管理表
	 * </p>
	 * 
	 * @param ids
	 */
	public void delete(List<String> ids);

	/**
	 * <p>
	 * 删除ETL品质管理表
	 * </p>
	 * 
	 * @param jbno
	 */
	public void delete(String jbno);

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
	 * 获得最新的品质管理记录
	 * </p>
	 * 
	 * @param scsj
	 * @return EtlpzGl
	 */
	public EtlpzGl getLatest(Date scsj);

	/**
	 * <p>
	 * 判断记录是否存在新的马口铁分析记录
	 * </p>
	 * 
	 * @param scsjBegin
	 * @param scsjEnd
	 * @return boolean
	 */
	public boolean isExistedNew(Date scsjBegin, Date scsjEnd);

	/**
	 * <p>
	 * 判断记录是否存在新的马口铁分析记录
	 * </p>
	 * 
	 * @return boolean
	 */
	public boolean isExistedNew();

	/**
	 * <p>
	 * 根据状态获得ETL品质管理记录
	 * </p>
	 * 
	 * @param stat
	 * @return EtlpzGl
	 */
	public EtlpzGl getByStat(String stat);

	/**
	 * <p>
	 * 获得品质管理指定日期的生产卷数
	 * </p>
	 * 
	 * @param scsj
	 * @return Long
	 */
	public Long count(Date scsj);

	/**
	 * <p>
	 * 将当前卷更新为已完成
	 * </p>
	 */
	public void updateStatDqjToYwc();

	/**
	 * <p>
	 * 根据生产时间查询品质管理记录
	 * </p>
	 * 
	 * @param scsjBegin
	 * @param scsjEnd
	 * @return List<EtlpzGl>
	 */
	public List<EtlpzGl> query(Date scsjBegin, Date scsjEnd);

}
