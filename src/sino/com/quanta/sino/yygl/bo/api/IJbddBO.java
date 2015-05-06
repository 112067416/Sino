package com.quanta.sino.yygl.bo.api;

import java.io.OutputStream;
import java.io.Serializable;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Jbdd;
import com.quanta.sino.orm.JbddItem;
import com.quanta.sino.yygl.vo.JbddVO;
import com.quanta.sino.yygl.vo.JbddtjVO;

/**
 * <p>
 * 基板订单明细业务处理接口
 * </p>
 * <p>
 * create: 2010-12-21 下午05:58:19
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IJbddBO {

	/**
	 * <p>
	 * 新增基板订单明细
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveItem(JbddItem entity);

	/**
	 * <p>
	 * 更新基板订单明细
	 * </p>
	 * 
	 * @param entity
	 * @param flag
	 * @return flag
	 */
	public String updateItem(JbddItem entity, String flag);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public String deleteItem(String[] ids);

	/**
	 * <p>
	 * 修改上锁字段
	 * </p>
	 * 
	 * @param ids
	 * @param lock
	 * @return String
	 */
	public String updateLock(String[] ids, String lock);

	/**
	 * <p>
	 * 复制生成方法接口
	 * </p>
	 * 
	 * @param ids
	 * @param user
	 */
	public void copyItem(String[] ids);

	/**
	 * 查询基板订单明细
	 * 
	 * @param qentity
	 */
	public void queryItem(QEntity<JbddItem> qentity);

	/**
	 * 获取基板订单明细
	 * 
	 * @param id
	 * @return JbddItem
	 */
	public JbddItem getItem(Serializable id);

	/**
	 * <p>
	 * 查询基板订单明细
	 * </p>
	 * 
	 * @param abbr
	 * @param face
	 * @param yuny
	 * @param houa
	 * @param houb
	 * @param width
	 * @param ddno
	 * @return String
	 */
	public String getForJs(String abbr, String face, String yuny, Double houa,
			Double houb, Double width, String ddno);

	/**
	 * <p>
	 * 将获取的对象转化成JS
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 获取基板订单主表
	 * </p>
	 * 
	 * @param id
	 * @return Jbdd
	 */
	public Jbdd getJbdd(Serializable id);

	/**
	 * 查询基板订单主表
	 * 
	 * @param qentity
	 */
	public void queryJbdd(QEntity<Jbdd> qentity);

	/**
	 * <p>
	 * 生成基板订购单
	 * </p>
	 * 
	 * @param ids
	 *            订购单明细表主键id（多个）
	 * @param user
	 *            当前登录用户
	 * @param jbno
	 *            基板订单号
	 */
	public String build(String[] ids, User user, String jbno);

	/**
	 * <p>
	 * 追加基板订单
	 * </p>
	 * 
	 * @param ids
	 * @param jbdd
	 */
	public void zjJbdd(String[] ids, String jbdd);

	/**
	 * <p>
	 * 补加基板订单
	 * </p>
	 * 
	 * @param ids
	 * @param jbdd
	 */
	public void bjJbdd(String[] ids, String jbdd);

	/**
	 * <p>
	 * 移出该基板订单
	 * </p>
	 * 
	 * @param ids
	 */
	public void ycJbdd(String[] ids);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param pid
	 */
	public void deleteJbdd(String pid);

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
	 * </p>
	 * 
	 * @param id
	 * @param jbno
	 */
	public String updateJbno(String id, String jbno);

	/**
	 * <p>
	 * 日出货统计数据Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param os
	 */
	public void fetchJbdd(JbddVO vo, OutputStream os);

	/**
	 * 更新基板订单主表
	 * 
	 * @param ids
	 * @param stat
	 */
	public void updateJdbbStat(String[] ids, String stat);

}
