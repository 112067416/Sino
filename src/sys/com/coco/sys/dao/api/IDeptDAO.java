package com.coco.sys.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Dept;

/**
 * <p>
 * 部门数据访问接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:54:42
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IDeptDAO {

	public void save(Dept entity);

	public void update(Dept entity);

	public List<Dept> findAll();

	public List<Dept> findValid();

	public Dept get(Serializable id);

	/**
	 * <p>
	 * 获取除指定子系外的机构
	 * </p>
	 * 
	 * @param id
	 * @return List<Dept>
	 */
	public List<Dept> findExclude(Serializable id);

	/**
	 * <p>
	 * 获取指定机构的所有后代代码
	 * </p>
	 * 
	 * @param id
	 * @return List<Serializable>
	 */
	public List<Serializable> getOffspring(Serializable id);

	/**
	 * <p>
	 * 删除机构，包括子系
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	public void query(QEntity<Dept> qentity);

}
