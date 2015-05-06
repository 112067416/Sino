package com.quanta.sino.cmn.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.InformTp;

/**
 * <p>
 * 资料室数据访问接口
 * </p>
 * <p>
 * create: 2011-1-8 下午04:33:05
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IInformDAO {

	/**
	 * 新增资料
	 * 
	 * @param entity
	 */
	public void save(InformTp entity);

	/**
	 * 更新资料
	 * 
	 * @param entity
	 */
	public void update(InformTp entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询资料
	 * 
	 * @param qentity
	 */
	public void query(QEntity<InformTp> qentity);

	/**
	 * 获取资料
	 * 
	 * @param id
	 * @return InformTp
	 */
	public InformTp get(Serializable id);

	/**
	 * <p>
	 * 根据名称，获取资料
	 * </p>
	 * 
	 * @param name
	 * @return InformTp
	 */
	public InformTp getByName(String name);

	/**
	 * <p>
	 * 判断名称是否已存在
	 * </p>
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean isExisted(String name);

	/**
	 * <p>
	 * 批量删除资料
	 * </p>
	 * 
	 * @param ids
	 */
	public void deletes(List<String> ids);
}
