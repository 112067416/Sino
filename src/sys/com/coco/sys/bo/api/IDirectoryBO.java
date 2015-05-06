package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;

import com.coco.sys.orm.Directory;

/**
 * <p>
 * 目录业务接口
 * </p>
 * <p>
 * create: 2011-1-8 下午01:26:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IDirectoryBO {

	public void saveOrUpdate(Directory entity);

	public List<Directory> findAll();

	public List<Directory> findValid();

	public Document tree(boolean onlyValid);

	public Directory get(Serializable id);

	public String getForJs(Serializable id);

	public List<Directory> findExclude(Serializable id);

	public void delete(Serializable id);

	/**
	 * <p>
	 * 获取除指定子系的父系目录
	 * </p>
	 * 
	 * @param id
	 * @return List<Inventory>
	 */
	public List<Directory> findAncestors(Serializable id);

}
