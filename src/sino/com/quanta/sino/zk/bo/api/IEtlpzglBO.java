package com.quanta.sino.zk.bo.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.EtlpzGl;
import com.quanta.sino.zk.vo.EtlpzGlVO;

/**
 * <p>
 * ETL品质管理业务接口
 * </p>
 * <p>
 * create: 2011-4-19 下午10:41:30
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IEtlpzglBO {

	/**
	 * 新增ETL品质管理表
	 * 
	 * @param entity
	 */
	public void save(EtlpzGl entity);

	/**
	 * 更新ETL品质管理表
	 * 
	 * @param entity
	 */
	public void update(EtlpzGl entity);

	/**
	 * 更新ETL品质管理表
	 * 
	 * @param entity
	 * @return String
	 */
	public String updateEtlpzGl(EtlpzGl entity);

	/**
	 * <p>
	 * 查询ETL品质管理表,先查询原材卷板表信息,根据原材卷板表中的JBNO 获取马口铁记录
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<EtlpzGl> qentity);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param qentity
	 */
	public void queryDq(QEntity<EtlpzGlVO> qentity);

	/**
	 * <p>
	 * 批量删除 ETL品质管理表
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(String[] ids);

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
	 * 根据中控当前正在生产的钢卷，生成ETL实时品质管理记录
	 * </p>
	 * 
	 * @param jbno
	 */
	public void outEtlpzGl(String jbno);

	/**
	 * <p>
	 * 新增ETL实时品质管理记录
	 * </p>
	 * 
	 * @param jbno
	 * @param scsj
	 * @return String
	 */
	public String addEtlpzGl(String jbno, Date scsj);

	/**
	 * <p>
	 * 根据中控生产完成的卷，修改对应ETL实时品质管理记录
	 * </p>
	 * 
	 * @param jbno
	 */
	public void finish(String jbno);

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
	 * 根据主键，获得ETL品质管理记录
	 * </p>
	 * 
	 * @param id
	 * @return EtlpzGl
	 */
	public EtlpzGl get(Serializable id);

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
	 * 获取ETL品质管理，指天日期的页数
	 * </p>
	 * 
	 * @param scsj
	 * @param size
	 * @return int
	 */
	public int getPageSize(Date scsj, Integer size);

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
