package com.quanta.sino.fxs.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.MktfxshJl;

/**
 * <p>
 * 马口铁分析数据记录表
 * </p>
 * <p>
 * create: 2010-12-29 上午09:47:21
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IMktfxDAO {

	/**
	 * 新增马口铁分析数据记录表
	 * 
	 * @param entity
	 */
	public void save(MktfxshJl entity);

	/**
	 * 更新马口铁分析数据记录表
	 * 
	 * @param entity
	 */
	public void update(MktfxshJl entity);

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
	 * @return MktfxshJl
	 */
	public MktfxshJl get(Serializable id);

	/**
	 * <p>
	 * 根据卷板号判断原材卷板信息是否存在
	 * </p>
	 * 
	 * @param jbno
	 * @return
	 */
	public boolean isYcaiTpExisted(String jbno);

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
	 * @return List<MktfxshJl>
	 */
	public List<MktfxshJl> findMktfxshJls(String jbno);

}
