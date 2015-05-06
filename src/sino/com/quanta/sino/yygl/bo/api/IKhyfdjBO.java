package com.quanta.sino.yygl.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Khyfdj;

/**
 * <p>
 * 客户运费单价业务层接口
 * </p>
 * <p>
 * create: 2011-2-14 下午02:22:03
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IKhyfdjBO {

	/**
	 * 新增客户运费单价
	 * 
	 * @param entity
	 */
	public void save(Khyfdj entity);

	/**
	 * 批量新增客户运费单价
	 * 
	 * @param entities
	 */
	public void saveAll(List<Khyfdj> entities);

	/**
	 * 更新客户运费单价
	 * 
	 * @param entity
	 */
	public void update(Khyfdj entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询客户运费单价
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Khyfdj> qentity);

	/**
	 * 获取客户运费单价
	 * 
	 * @param id
	 * @return Khyfdj
	 */
	public Khyfdj get(Serializable id);

	/**
	 * <p>
	 * 根据运输行、用户名称、送货点、到达地点和运输方式，找出客户运费单价
	 * </p>
	 * 
	 * @param ysnm
	 *            运输行
	 * @param user
	 *            用户名称
	 * @param shno
	 *            送货点
	 * @param addr
	 *            到达地点
	 * @param ysfs
	 *            运输方式
	 * @param stat
	 *            运输单价状态
	 * @return Khyfdj
	 */
	public Khyfdj getUnique(String ysnm, String user, String shno, String addr,
			String ysfs, String stat, String ld);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param ysnm
	 * @param user
	 * @param shno
	 * @param addr
	 * @param ysfs
	 * @param stat
	 * @param djdw
	 * @param ld
	 * @return Khyfdj
	 */
	public Khyfdj getUnique(String ysnm, String user, String shno, String addr,
			String ysfs, String stat, String djdw, String ld);

	/**
	 * <p>
	 * 加载运费单价
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 修改运费单价
	 * </p>
	 * 
	 * @param id
	 * @param yfdj
	 */
	public void updateYfdj(Serializable id, Double yfdj);

	/**
	 * <p>
	 * 修改客户运费单价状态
	 * </p>
	 * 
	 * @param ids
	 * @param stat
	 */
	public void updateStat(List<String> ids, String stat);
}
