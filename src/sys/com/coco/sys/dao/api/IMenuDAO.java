package com.coco.sys.dao.api;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;

import com.coco.sys.orm.Menu;

public interface IMenuDAO {

	public void save(Menu entity);

	public void update(Menu entity);

	public List<Menu> findAll();

	public List<Menu> findValid();

	public Document tree(boolean onlyValid);

	public Menu get(Serializable id);

	/**
	 * 
	 * <p>
	 * 获取除指定子系外的菜单
	 * </p>
	 * 
	 * @param id
	 * @return List<Menu>
	 */
	public List<Menu> findExclude(Serializable id);

	/**
	 * 
	 * <p>
	 * 获取指定菜单的所有后代代码
	 * </p>
	 * 
	 * @param id
	 * @return List<Serializable>
	 */
	public List<Serializable> getOffspring(Serializable id);

	/**
	 * 
	 * <p>
	 * 删除菜单，包括子系
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	public Document treeByRoles(List<String> roles);
}
