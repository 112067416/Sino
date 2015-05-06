package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;

import com.coco.sys.orm.Menu;

public interface IMenuBO {

	public void saveOrUpdate(Menu entity);

	public List<Menu> findAll();

	public List<Menu> findValid();

	public Document tree(boolean onlyValid);

	public String getForJs(Serializable id);

	public List<Menu> findExclude(Serializable id);

	public void delete(Serializable id);

	public Document treeByRoles(List<String> roles);
}
