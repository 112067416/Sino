package com.quanta.sino.fxs.bo.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Cyrz;

/**
 * <p>
 * 采样日志业务接口
 * </p>
 * <p>
 * create: 2011-1-7 上午09:26:05
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ICyrzBO {

	/**
	 * <p>
	 * 更新类型
	 * </p>
	 * <p>
	 * create: 2011-1-7 上午11:55:43
	 * </p>
	 * 
	 * @author 许德建[xudejian@126.com]
	 * @version 1.0
	 */
	public static enum UpdateType {

		/**
		 * SL线收单
		 */
		slsd,

		/**
		 * SL线送样
		 */
		slsy,

		/**
		 * 分析室收单
		 */
		fxsd,

		/**
		 * 分析室收样
		 */
		fxsy,

		/**
		 * 分析室分析
		 */
		fx;
	}

	/**
	 * <p>
	 * 保存数据
	 * </p>
	 * 
	 * @param entity
	 */
	public void cydEtl(Cyrz entity);

	/**
	 * <p>
	 * 保存SL采样单
	 * </p>
	 * 
	 * @param entity
	 */
	public void cydSl(Cyrz entity);

	/**
	 * <p>
	 * 获取数据
	 * </p>
	 * 
	 * @param id
	 * @return Cyrz
	 */
	public Cyrz get(Serializable id);

	/**
	 * <p>
	 * 获取数据Js对象
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 分页查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Cyrz> qentity);

	/**
	 * <p>
	 * 更新采样单
	 * </p>
	 * 
	 * @param entity
	 * @param type
	 */
	public void update(Cyrz entity, UpdateType type);

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
	 * 更新通信状态
	 * </p>
	 * 
	 * @param id
	 * @param xb
	 * @param type
	 */
	public void updateState(String id, String xb, UpdateType type);

	/**
	 * <p>
	 * 更新通信状态
	 * </p>
	 * 
	 * @param id
	 * @param type
	 */
	public void updateState(String id, UpdateType type);

	/**
	 * <p>
	 * 删除，置删除标志为真
	 * </p>
	 * 
	 * @param id
	 * @param fsxb
	 */
	public void delete(Serializable id, String fsxb);

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
}
