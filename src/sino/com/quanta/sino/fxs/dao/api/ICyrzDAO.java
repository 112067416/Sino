package com.quanta.sino.fxs.dao.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Cyrz;
import com.quanta.sino.orm.CyrzSlxb;

/**
 * <p>
 * 采样日志数据访问接口
 * </p>
 * <p>
 * create: 2011-4-17 下午05:33:42
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ICyrzDAO {

	/**
	 * 新增采样日志
	 * 
	 * @param entity
	 */
	public void save(Cyrz entity);

	/**
	 * 更新采样日志
	 * 
	 * @param entity
	 */
	public void update(Cyrz entity);

	/**
	 * 根据ID删除采样日志
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询采样日志
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Cyrz> qentity);

	/**
	 * 获取采样日志
	 * 
	 * @param id
	 * @return Cyrz
	 */
	public Cyrz get(Serializable id);

	/**
	 * <p>
	 * 获取采样日志引用
	 * </p>
	 * 
	 * @param id
	 * @return Cyrz
	 */
	public Cyrz getRef(Serializable id);

	/**
	 * <p>
	 * 判断指示书对应的卷材是否已发送采样单
	 * </p>
	 * 
	 * @param zsno
	 * @param jbno
	 * @return boolean
	 */
	public boolean isExisted(String zsno, String jbno);

	/**
	 * <p>
	 * 获取一个未收单的数据给剪切线SL
	 * </p>
	 * 
	 * @param xb
	 *            线别
	 * @return Cyrz
	 */
	public Cyrz getForSl(String xb);

	/**
	 * <p>
	 * 获取一个未收单的数据给剪切线SL
	 * </p>
	 * 
	 * @return Cyrz
	 */
	public Cyrz getForSl();

	/**
	 * <p>
	 * 获取一个未收单或者未收样的数据给分析室
	 * </p>
	 * 
	 * @return Cyrz
	 */
	public Cyrz getForFxs();

	/**
	 * <p>
	 * </p>
	 * 
	 * @param id
	 * @return CyrzSlxb
	 */
	public CyrzSlxb getCyrzSlxb(Serializable id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveCyrzSlxb(CyrzSlxb entity);

	/**
	 * <p>
	 * 判断一个卷检验次数
	 * </p>
	 * 
	 * @param jbno
	 * @return Integer
	 */
	public Integer getJycs(String jbno);

}
