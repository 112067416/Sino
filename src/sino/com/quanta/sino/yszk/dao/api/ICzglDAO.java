package com.quanta.sino.yszk.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Czgl;

/**
 * <p>
 * 付款发票冲帐数据访问层接口
 * </p>
 * <p>
 * create: 2011-6-27 下午08:41:03
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ICzglDAO {

	/**
	 * <p>
	 * 分页查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Czgl> qentity);

	/**
	 * <p>
	 * 保存冲账关联表信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Czgl entity);

	/**
	 * <p>
	 * 修改从中关联信息
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Czgl entity);

	/**
	 * <p>
	 * 获取冲账关联表信息集合
	 * </p>
	 * 
	 * @param id
	 * @return List<Czgl>
	 */
	public List<Czgl> find(String id);

	/**
	 * <p>
	 * 获得调整金额
	 * </p>
	 * 
	 * @param khfkId
	 * @return Double
	 */
	public Double getTzje(String khfkId);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param khfkId
	 * @return Czgl
	 */
	public Czgl getByKhfk(String khfkId);

	/**
	 * <p>
	 * </p>
	 * 
	 * @param khfkId
	 * @return List<Czgl>
	 */
	public Czgl getByKhfp(String khfpId);

	/**
	 * <p>
	 * 删除冲账关联表对应信息
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 获得品质管理指定日期的生产卷数
	 * </p>
	 * 
	 * @param khfkId
	 * @return Long
	 */
	public Long countByKhfk(String khfkId);

	/**
	 * <p>
	 * 获得品质管理指定日期的生产卷数
	 * </p>
	 * 
	 * @param fkfpId
	 * @return Long
	 */
	public Long countByFkfp(String fkfpId);

}
