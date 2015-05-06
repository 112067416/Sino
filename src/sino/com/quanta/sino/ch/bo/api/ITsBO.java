package com.quanta.sino.ch.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.TsTp;

/**
 * <p>
 * 投诉业务接口
 * </p>
 * <p>
 * create: 2011-1-18 下午06:56:44
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ITsBO {

	/**
	 * <p>
	 * 制品投诉
	 * <ul>
	 * <li>写投诉记录表
	 * <li>将装箱指示书明细Zng2Tp的状态改为投诉状态
	 * </ul>
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	public String doTs(TsTp entity);

	/**
	 * <p>
	 * 制品厂外转卖
	 * <ul>
	 * <li>修改投诉记录表中的状态为厂外转卖
	 * <li>将装箱指示书明细Zng2Tp的状态改为厂外转卖
	 * </ul>
	 * </p>
	 * 
	 * @param ids
	 */
	public void doCwzm(List<String> ids);

	/**
	 * <p>
	 * 撤销制品投诉
	 * <ul>
	 * <li>修改投诉记录表中的状态为撤销投诉
	 * <li>将装箱指示书明细Zng2Tp的状态改为撤销投诉
	 * </ul>
	 * </p>
	 * 
	 * @param ids
	 */
	public void doCxts(List<String> ids);

	/**
	 * <p>
	 * 更新投诉记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(TsTp entity);

	/**
	 * <p>
	 * 查询投诉记录
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<TsTp> qentity);

	/**
	 * <p>
	 * 获取投诉记录
	 * </p>
	 * 
	 * @param id
	 * @return TsTp
	 */
	public TsTp get(Serializable id);

	/**
	 * <p>
	 * 获取制品信息 做投诉
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	public String get(String jbno);

	/**
	 * <p>
	 * 修改投诉记录
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 由制品号，出货单号获取投诉记录
	 * </p>
	 * 
	 * @param jbno
	 * @param shno
	 * @return TsTp
	 */
	public TsTp getTsTp(String jbno, String shno);
}
