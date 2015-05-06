package com.quanta.sino.fxs.bo.api;

import java.io.Serializable;

import com.coco.core.bean.User;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.fxs.vo.MktfjVO;
import com.quanta.sino.fxs.vo.MktfxVO;
import com.quanta.sino.orm.Chpdb;
import com.quanta.sino.orm.MktfxshJl;

public interface IMktfxBO {

	/**
	 * 新增马口铁分析数据记录表
	 * 
	 * @param entity
	 * @param user
	 */
	public void save(MktfxshJl entity, User user);

	/**
	 * 更新马口铁分析数据记录表
	 * 
	 * @param entity
	 * @param user
	 */
	public void update(MktfxshJl entity, User user);

	/**
	 * 根据ID删除马口铁分析数据记录表
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询马口铁分析数据记录表
	 * 
	 * @param qentity
	 */
	public void query(QEntity<MktfxshJl> qentity);

	/**
	 * 获取马口铁分析数据记录表
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 获取马口铁分析数据记录表
	 * </p>
	 * 
	 * @param id
	 * @return MktfxshJl
	 */
	public MktfxshJl get(Serializable id);

	/**
	 * <p>
	 * 获得马口铁分析数据的目标附着量和目标涂油量
	 * </p>
	 * 
	 * @param cyrzId
	 * @param jbno
	 * @return MktfxVO
	 */
	public MktfxVO getMubiao(String cyrzId, String jbno);

	/**
	 * <p>
	 * 批量删除 马口铁分析数据记录表
	 * </p>
	 * 
	 * @param ids
	 */
	public void deletes(String[] ids);

	/**
	 * <p
	 * 获得采样日志对应的马口铁分析数据
	 * </p>
	 * 
	 * @param cyrzId
	 * @return
	 */
	public MktfxshJl getByCyrzId(String cyrzId);

	/**
	 * <p>
	 * 查询马口铁分析记录数
	 * </p>
	 * 
	 * @param jbno
	 * @return MktfjVO
	 */
	public MktfjVO getMktfjVO(String jbno);

	/**
	 * 查询马口铁分析数据记录表
	 * 
	 * @param qentity
	 */
	public void queryChpdb(QEntity<Chpdb> qentity);

	/**
	 * 新增出货判定表
	 * 
	 * @param entity
	 */
	public void saveChpdb(Chpdb entity);

	/**
	 * <p>
	 * 获得钢卷对应的出货判定信息
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	public String getChpdbForJS(String jbno);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param jbno
	 * @return Chpdb
	 */
	public Chpdb getChpdb(String jbno);

	/**
	 * <p>
	 * 批量删除 出货判定信息
	 * </p>
	 * 
	 * @param ids
	 */
	public void deleteChpdb(String[] ids);
}
