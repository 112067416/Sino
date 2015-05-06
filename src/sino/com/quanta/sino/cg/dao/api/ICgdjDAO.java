package com.quanta.sino.cg.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.YbCgdj;

/**
 * <p>
 * 原板采购单价数据访问接口
 * </p>
 * <p>
 * create: 2011-7-8 下午03:44:59
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ICgdjDAO {
	/**
	 * <p>
	 * 新增原板采购单价
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(YbCgdj entity);

	/**
	 * <p>
	 * 批量保存原板采购单价
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<YbCgdj> entities);

	/**
	 * <p>
	 * 更新原板采购单价
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(YbCgdj entity);

	/**
	 * <p>
	 * 删除单个原板采购单价
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 查询原板采购单价
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<YbCgdj> qentity);

	/**
	 * <p>
	 * 查询全部
	 * </p>
	 * 
	 * @return List<YbCgdj>
	 */
	public List<YbCgdj> find();

	/**
	 * <p>
	 * 查询单个原板采购单价信息
	 * </p>
	 * 
	 * @param id
	 * @return YbCgdj
	 */
	public YbCgdj get(Serializable id);

	/**
	 * <p>
	 * 查询单个原板采购单价信息
	 * </p>
	 * 
	 * @return YbCgdj
	 */
	public YbCgdj get();

	/**
	 * <p>
	 * 获得原板采购单价
	 * </p>
	 * 
	 * @param houu
	 * @param knfw
	 * @param yuny
	 * @return Double
	 */
	public Double getCgdj(Double houu, String knfw, String yuny);

	/**
	 * <p>
	 * 判断是否存在
	 * </p>
	 * 
	 * @param houu
	 * @param knfw
	 * @param yuny
	 * @return boolean
	 */
	public boolean isExisted(Double houu, String knfw, String yuny);

}
