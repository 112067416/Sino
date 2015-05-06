package com.coco.sys.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Directory;

/**
 * <p>
 * 目录数据访问接口
 * </p>
 * <p>
 * create: 2011-1-8 下午01:26:55
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IDirectoryDAO {

	public void save(Directory entity);

	public void update(Directory entity);

	public List<Directory> findAll();

	public List<Directory> findValid();

	public Directory get(Serializable id);

	/**
	 * <p>
	 * 获取除指定子系外的目录
	 * </p>
	 * 
	 * @param id
	 * @return List<Inventory>
	 */
	public List<Directory> findExclude(Serializable id);

	/**
	 * <p>
	 * 获取除指定子系的父系目录
	 * </p>
	 * 
	 * @param id
	 * @return List<Inventory>
	 */
	public List<Directory> findAncestors(Serializable id);

	/**
	 * <p>
	 * 获取指定目录的所有后代代码
	 * </p>
	 * 
	 * @param id
	 * @return List<Serializable>
	 */
	public List<Serializable> getOffspring(Serializable id);

	/**
	 * <p>
	 * 删除目录，包括子系
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	public void query(QEntity<Directory> qentity);

}
