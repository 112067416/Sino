package com.quanta.sino.yygl.dao.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Jbdd;
import com.quanta.sino.orm.JbddItem;
import com.quanta.sino.orm.JbddItemLog;
import com.quanta.sino.yygl.vo.JbddtjVO;

/**
 * <p>
 * 基板订单数据访问接口
 * </p>
 * <p>
 * create: 2010-12-21 下午06:24:32
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IJbddDAO {
	/**
	 * 新增基板订单明细
	 * 
	 * @param entity
	 */
	public void saveItem(JbddItem entity);

	/**
	 * 更新基板订单明细
	 * 
	 * @param entity
	 */
	public void updateItem(JbddItem entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteJbdd(Serializable id);

	/**
	 * 查询基板订单明细
	 * 
	 * @param qentity
	 */
	public void queryItem(QEntity<JbddItem> qentity);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbdd
	 * @return List<JbddItem>
	 */
	public List<JbddItem> findItems(String jbdd);

	/**
	 * 获取基板订单明细
	 * 
	 * @param id
	 * @return JbddItem
	 */
	public JbddItem getItem(Serializable id);

	/**
	 * <p>
	 * 获取基板订单
	 * </p>
	 * 
	 * @param ql
	 * @param values
	 * @return JbddItem
	 */
	public JbddItem getUnique(String ql, Object... values);

	/**
	 * <p>
	 * 批量修改状态位为作废即进行批量假删除操作
	 * </p>
	 * 
	 * @param ids
	 *            选择的子表id
	 */
	public void executeForValues(String ql, Collection<?> values,
			Object... params);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param ql
	 * @param params
	 */
	public void executeUpdate(String ql, Object... params);

	/**
	 * <p>
	 * 批量保存操作
	 * </p>
	 * 
	 * @param entitys
	 *            多个实体
	 */
	public void saveAll(List<JbddItem> entitys);

	/**
	 * 新增基板订单主表
	 * 
	 * @param entity
	 */
	public void saveJdbb(Jbdd entity);

	/**
	 * 更新基板订单主表
	 * 
	 * @param entity
	 */
	public void updateJdbb(Jbdd entity);

	/**
	 * 查询基板订单主表
	 * 
	 * @param qentity
	 */
	public void queryJdbb(QEntity<Jbdd> qentity);

	/**
	 * 获取基板订单主表
	 * 
	 * @param id
	 * @return Jbdd
	 */
	public Jbdd getJdbb(Serializable id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbno
	 * @return Jbdd
	 */
	public Jbdd getJdbbByJbno(String jbno);

	/**
	 * <p>
	 * 根据主键获取对应的基板订单主信息引用，该订单信息由容器托管，其属性更改将同步到数据库
	 * </p>
	 * 
	 * @param id
	 * @return Jbdd
	 */
	public JbddItem getRef(Serializable id);

	/**
	 * <p>
	 * 统计订单量
	 * </p>
	 * 
	 * @param pid
	 * @param stat
	 * @param ddno
	 * @param abbr
	 * @return JbddtjVO
	 */
	public JbddtjVO getTj(String pid, String stat, String ddno, String abbr);

	/**
	 * <p>
	 * 判断基板订单号是否存在
	 * </p>
	 * 
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExistedJbno(String jbno);

	/**
	 * <p>
	 * 获得日志
	 * </p>
	 * 
	 * @param jbddItem
	 * @return JbddItemLog
	 */
	public JbddItemLog getLog(String jbddItem);

	/**
	 * <p>
	 * 删除日志
	 * </p>
	 * 
	 * @param jbddItem
	 */
	public void deleteLog(String jbddItem);

	/**
	 * <p>
	 * 保存修改日志
	 * </p>
	 * 
	 * @param log
	 */
	public void saveLog(JbddItemLog log);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbno
	 * @param nwai
	 * @param zjzt
	 * @return List<JbddItem>
	 */
	public List<JbddItem> findJbddItems(String jbno, String nwai, String zjzt);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbddId
	 * @return Integer
	 */
	public Integer getMax(Serializable jbddId);
}
