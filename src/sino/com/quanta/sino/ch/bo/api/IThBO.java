package com.quanta.sino.ch.bo.api;

import java.io.Serializable;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ThTp;

/**
 * <p>
 * 退货业务接口
 * </p>
 * <p>
 * create: 2011-1-20 下午09:46:25
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IThBO {

	/**
	 * <p>
	 * 新增退货记录
	 * </p>
	 * 
	 * @param ids
	 */
	public String doTh(String[] ids);

	/**
	 * <p>
	 * 更新退货记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(ThTp entity);

	/**
	 * <p>
	 * 根据主键删除对应的退货记录
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	public String doCxth(String[] ids);

	/**
	 * <p>
	 * 查询退货记录
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<ThTp> qentity);

	/**
	 * <p>
	 * 获取退货记录
	 * </p>
	 * 
	 * @param id
	 * @return ThTp
	 */
	public ThTp get(Serializable id);

	/**
	 * <p>
	 * 设定退货制品的发票号
	 * </p>
	 * 
	 * @param ids
	 * @param fpno
	 */
	public void setFpno(String[] ids, String fpno);

}
